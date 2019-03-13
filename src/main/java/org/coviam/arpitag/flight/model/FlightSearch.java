package org.coviam.arpitag.flight.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FlightSearch {

	@Id
	private int searchId;
	private String origin;
	private String destination;
	private String departDate ;
	private String returnDate;
	private int maxItems;
	private byte[] result;
	
	public FlightSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightSearch(int searchId, String origin, String destination, String departDate, String returnDate,
			int maxItems, byte[] result) {
		super();
		this.searchId = searchId;
		this.origin = origin;
		this.destination = destination;
		this.departDate = departDate;
		this.returnDate = returnDate;
		this.maxItems = maxItems;
		this.result = result;
	}

	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
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
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getMaxItems() {
		return maxItems;
	}
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}
	public byte[] getResult() {
		return result;
	}
	public void setResult(byte[] result) {
		this.result = result;
	}
	
}
