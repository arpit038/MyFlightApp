package org.coviam.arpitag.flight;

import org.coviam.arpitag.flight.model.FlightSearch;

public class FlightAppConstants {

	public static final String access_token = "ARSkTgNcQ2kGhJ4JX65QSkrc3Cag";
	
	public static String getSearchUrl(FlightSearch flightSearch) {
		String url = "https://test.api.amadeus.com/v1/shopping/flight-offers?"
				 +"origin="+flightSearch.getOrigin()
				 +"&destination="+flightSearch.getDestination()
				 +"&departureDate="+flightSearch.getDepartDate()
				 +"&max=2";
		if(flightSearch.getReturnDate()!=null && flightSearch.getReturnDate().length() > 0) {
			url += "&returnDate="+flightSearch.getReturnDate();
		}
		
		return url;
	}
}
