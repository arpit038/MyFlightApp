package org.coviam.arpitag.flight.model;

public class Flight {

	private String id;
	private String origin;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private String duration;
	private int stops;
	private int flightSearchId;
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}
	
	public Flight(String id, String origin, String destination, String departureTime, String arrivalTime,
			String duration, int stops, int flightSearchId) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.stops = stops;
		this.flightSearchId = flightSearchId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}

	public int getFlightSearchId() {
		return flightSearchId;
	}

	public void setFlightSearchId(int flightSearchId) {
		this.flightSearchId = flightSearchId;
	}
}
