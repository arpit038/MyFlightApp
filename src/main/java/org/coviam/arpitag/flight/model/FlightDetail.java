package org.coviam.arpitag.flight.model;

import java.util.Map;

public class FlightDetail {

	Map<String, Object> flightdetail;
	Map<String, Object> dictionary;
	public FlightDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlightDetail(Map<String, Object> flightdetail, Map<String, Object> dictionary) {
		super();
		this.flightdetail = flightdetail;
		this.dictionary = dictionary;
	}
	public Map<String, Object> getFlightdetail() {
		return flightdetail;
	}
	public void setFlightdetail(Map<String, Object> flightdetail) {
		this.flightdetail = flightdetail;
	}
	public Map<String, Object> getDictionary() {
		return dictionary;
	}
	public void setDictionary(Map<String, Object> dictionary) {
		this.dictionary = dictionary;
	}
	
	
}
