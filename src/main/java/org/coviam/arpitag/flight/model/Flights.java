package org.coviam.arpitag.flight.model;

import java.util.Map;

import org.json.JSONObject;

public class Flights {
	
	private Flight[] flights;
	private Map<String,Object> dictionary;
	
	public Flights() {
		// TODO Auto-generated constructor stub
	}
	
	public Flights(Flight[] flights, Map<String,Object> dictionary) {
		super();
		this.flights = flights;
		this.dictionary = dictionary;
	}
	public Flight[] getFlights() {
		return flights;
	}
	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}
	public Map<String,Object> getDictionary() {
		return dictionary;
	}
	public void setDictionary(Map<String,Object> dictionary) {
		this.dictionary = dictionary;
	}
	
	

}
