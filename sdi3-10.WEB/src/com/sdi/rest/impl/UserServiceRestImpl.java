package com.sdi.rest.impl;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.rest.UsersServiceRest;

public class UserServiceRestImpl implements UsersServiceRest {

	LoginService service = Factories.services.getLoginService();

	@Override
	public User getUserLogin(String user, String password) {
		return service.verify(user, password);
	}

	

	

}
