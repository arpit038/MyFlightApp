package org.coviam.arpitag;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		
		String str = "{\"aircraft\":{\"789\":\"BOEING 787-9\",\"339\":\"AIRBUS A330-900NEO\",\"319\":\"AIRBUS INDUSTRIE A319\"},\"carriers\":{\"DY\":\"NORWEGIAN AIR SHUTTLE\",\"TP\":\"TAP PORTUGAL\"},\"locations\":{\"MAD\":{\"subType\":\"AIRPORT\",\"detailedName\":\"ADOLFO SUAREZ BARAJAS\"},\"LIS\":{\"subType\":\"AIRPORT\",\"detailedName\":\"AIRPORT\"},\"JFK\":{\"subType\":\"AIRPORT\",\"detailedName\":\"JOHN F KENNEDY INTL\"}},\"currencies\":{\"EUR\":\"EURO\"}}";
		
		System.out.println(str.replace("\\", ""));
/*		Amadeus amadeus = Amadeus
	            .builder("9r2eecjwZsUgAflprRS0W4BxMAJmvpfH", "P5RuewiAbF1UtFfk")
	            .build();

	    Location[] locations;
		try {
			locations = amadeus.referenceData.locations.get(Params
			  .with("keyword", "LON")
			  .and("subType", Locations.ANY));
			
			for(Location loc: locations){
				System.out.println(loc.getDetailedName());
			}
			
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static String executePost(String targetURL, String urlParameters) {

		HttpURLConnection connection = null;

		try {
			//Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Language", "en-US");  
			
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			//Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());

			wr.close();

			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
    
			rd.close();
			return response.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
