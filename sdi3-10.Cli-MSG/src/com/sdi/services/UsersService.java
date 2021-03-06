package com.sdi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.model.User;

@Path("/UsersServiceRs")
public interface UsersService {

	@GET
	@Path("{user}/{password}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	User getUserLogin(@PathParam("user") String user,
			@PathParam("password") String password);

}
