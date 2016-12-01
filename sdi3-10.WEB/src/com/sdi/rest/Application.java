package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.impl.ApplicationServiceRestImpl;
import com.sdi.rest.impl.TripServiceRestImpl;
import com.sdi.rest.impl.UserServiceRestImpl;


public class Application extends javax.ws.rs.core.Application {
	
	@Override
	public Set<Class<?>> getClasses() {
	Set< Class<?> > res = new HashSet<>();
		res.add(ApplicationServiceRestImpl.class );
		res.add(TripServiceRestImpl.class );
		res.add(UserServiceRestImpl.class);
	return res;
	}

}
