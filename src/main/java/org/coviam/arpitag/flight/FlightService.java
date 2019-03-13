package org.coviam.arpitag.flight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.coviam.arpitag.flight.model.Flight;
import org.coviam.arpitag.flight.model.FlightDetail;
import org.coviam.arpitag.flight.model.FlightSearch;
import org.coviam.arpitag.flight.model.Flights;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

	public Flights getflightOffers(FlightSearch flightSearch) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			
			//incrementing and setting search index
			Query<FlightSearch> query = session.createQuery("Select COALESCE(max(searchId),0) from FlightSearch");
			List list = query.list();
			int index = Integer.parseInt(list.get(0).toString());
			flightSearch.setSearchId(index+1);
			
			//setting url
			String url = FlightAppConstants.getSearchUrl(flightSearch);
			
			//calling api to get results
			String results = callAPIforResult(url,FlightAppConstants.access_token);
			flightSearch.setResult(results.getBytes());
			
			//saving into database and close session
			session.save(flightSearch);
			
			//parsing only necessary information to be displayed on the search flights screen
			Flights flights = getBasicFlightsDetails(results,flightSearch.getSearchId());
			
			session.getTransaction().commit();
			session.close();
			return flights;
			
		}catch(Exception e) {
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}
	
	
	public FlightDetail getflightdeatils(int flightsSearchId, String flightId) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			
			//incrementing and setting search index
			Query<FlightSearch> query = session.createQuery("Select result from FlightSearch where searchid="+flightsSearchId);
			List list = query.list();
			String result = new String((byte[])list.get(0));

			JSONObject jsonObject = new JSONObject(result);
			JSONArray array = jsonObject.getJSONArray("data");
			FlightDetail flightDetail = new FlightDetail();
			for(int i=0; i<array.length();i++) {
				if(array.getJSONObject(i).get("id").toString().equals(flightId))
					flightDetail.setFlightdetail(array.getJSONObject(i).toMap());
			}
			flightDetail.setDictionary(jsonObject.getJSONObject("dictionaries").toMap());
			session.close();
			return flightDetail;
			
		}catch(Exception e) {
			session.getTransaction().rollback();
			return null;
		}
	}
	
	
	private static String callAPIforResult(String urlString,String bearerToken) {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	        connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line = null;
	        StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        String response = out.toString();
	        
	        return response;
	    } catch (Exception e) {
	    	return "Error in calling API";
	    }
	}
	
	
	public Flights getBasicFlightsDetails(String result, int searchId) {
		
		try {
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray array = jsonObject.getJSONArray("data");
			Flight[] flights = new Flight[array.length()];
			
			for(int i=0; i<array.length();i++) {
				flights[i] = new Flight();
				JSONObject element = array.getJSONObject(i);
				if(!element.get("type").toString().equals("flight-offer"))
					continue;
				
				flights[i].setId(element.get("id").toString());
				JSONArray segments = element.getJSONArray("offerItems").getJSONObject(0).getJSONArray("services").getJSONObject(0).getJSONArray("segments");
				
				//setting origin and departure time from first segment
				flights[i].setOrigin(segments.getJSONObject(0).getJSONObject("flightSegment").getJSONObject("departure").get("iataCode").toString());
				flights[i].setDepartureTime(segments.getJSONObject(0).getJSONObject("flightSegment").getJSONObject("departure").get("at").toString());
				
				//setting destination and arrival time from first segment
				flights[i].setDestination(segments.getJSONObject(segments.length()-1).getJSONObject("flightSegment").getJSONObject("arrival").get("iataCode").toString());
				flights[i].setArrivalTime(segments.getJSONObject(segments.length()-1).getJSONObject("flightSegment").getJSONObject("arrival").get("at").toString());
				
				flights[i].setStops(segments.length()-1);
			
				if(segments.length()==1) {
					flights[i].setDuration(segments.getJSONObject(0).getJSONObject("flightSegment").get("duration").toString());
				}else {
					flights[i].setDuration(getDurationSum(segments));
				}
				
				flights[i].setFlightSearchId(searchId);
			}
			
			Map<String,Object> dictionary = jsonObject.getJSONObject("dictionaries").toMap();
					//.toString().replace("\\", "");
			System.out.println(dictionary);
			Flights flightsObject = new Flights(flights,dictionary);
	
			return flightsObject;
		}
		catch(Exception e) { 		
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public String getDurationSum(JSONArray segments) {
		
		String ind_durations[] = new String[segments.length()];
		for(int i=0;i<segments.length();i++) {
			ind_durations[i] = segments.getJSONObject(0).getJSONObject("flightSegment").get("duration").toString();
		}
		
		String total_duration = "";
		int days = 0;
		int hours = 0;
		int mins = 0;
		
		int temp = 0;
		int temp2 = 0;
		int temp3 = 0;
		for(int i=0;i<segments.length();i++) {
			temp = ind_durations[i].indexOf("DT");
			temp2 = ind_durations[i].indexOf("H");
			temp3 = ind_durations[i].indexOf("M");
			days += Integer.parseInt(ind_durations[i].substring(0,temp));
			hours += Integer.parseInt(ind_durations[i].substring(temp+2,temp2));
			mins += Integer.parseInt(ind_durations[i].substring(temp2+1,temp3));
		}
		
		days += hours/24;
		hours %= 24;
		hours += mins/60;
		mins %= 60;
		
		
		total_duration = days+"DT"+hours+"H"+mins+"M";
		return total_duration;
	}
}
