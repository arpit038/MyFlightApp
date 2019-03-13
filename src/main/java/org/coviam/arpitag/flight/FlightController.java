package org.coviam.arpitag.flight;

import org.coviam.arpitag.flight.model.FlightDetail;
import org.coviam.arpitag.flight.model.FlightSearch;
import org.coviam.arpitag.flight.model.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

	@Autowired
	FlightService service;
	
	@RequestMapping("/flights")
	public Flights getFlights(@RequestBody FlightSearch flightSearch) {
		
		flightSearch.setMaxItems(2);
		return service.getflightOffers(flightSearch);
	}
	
	@RequestMapping("/flights/{flightsSearchId}/{flightId}")
	public FlightDetail getFlightDetail(@PathVariable int flightsSearchId, @PathVariable String flightId) {
		
		return service.getflightdeatils(flightsSearchId,flightId);
	}
	
}
