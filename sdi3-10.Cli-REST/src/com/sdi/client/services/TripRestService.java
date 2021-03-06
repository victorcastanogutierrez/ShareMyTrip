package com.sdi.client.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.Trip;

@Path("/TripsServiceRs")
public interface TripRestService {

	@GET
	@Path("{user}/{password}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Trip> getTrips(@PathParam("user") String user,
			@PathParam("password") String password);
}
