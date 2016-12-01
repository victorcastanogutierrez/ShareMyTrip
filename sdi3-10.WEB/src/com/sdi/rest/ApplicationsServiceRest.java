package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Application;

@Path("/ApplicationsServiceRs")
public interface ApplicationsServiceRest {

	@GET
	@Path("{user}/{password}/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Application> findByTripId(@PathParam("user") String user,
			@PathParam("password") String password, @PathParam("id") Long id)
			throws EntityNotFoundException;

	@GET
	@Path("{user}/{password}/{tripId}/{userId}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void accept(@PathParam("user") String user,
			@PathParam("password") String password,
			@PathParam("tripId") Long tripId, @PathParam("userId") Long userId)
			throws EntityAlreadyExistsException;

}
