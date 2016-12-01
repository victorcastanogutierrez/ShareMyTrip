package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.ApplicationsService;
import com.sdi.business.LoginService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.rest.ApplicationsServiceRest;

public class ApplicationServiceRestImpl implements ApplicationsServiceRest {

	ApplicationsService service = Factories.services.getApplicationService();
	LoginService lService = Factories.services.getLoginService();

	@Override
	public List<Application> findByTripId(String user, String password, Long id)
			throws EntityNotFoundException {
		if(lService.verify(user, password)!=null)
			return service.findByTripId(id);
		return null;
	}

	@Override
	public void accept(String user, String password, Long tripId, Long userId)
			throws EntityAlreadyExistsException {
		if(lService.verify(user, password)!=null)
			service.accept(new Long[]{userId,tripId});
	}

	

}
