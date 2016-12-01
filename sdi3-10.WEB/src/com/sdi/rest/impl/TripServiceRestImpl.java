package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.LoginService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.rest.TripsServiceRest;

public class TripServiceRestImpl implements TripsServiceRest {

	TripsService service = Factories.services.getTripsService();
	LoginService lService = Factories.services.getLoginService();

	@Override
	public List<Trip> getTripsUserOpenAndPromoter(String user, String password) {
		if(lService.verify(user, password)!=null)
			return service.findByPromoterAndOpen(user);
		return null;
	}

	@Override
	public List<Trip> getTripsForUser(Long user) {
		return service.findAllUserTrip(user);
	}

	

	

}
