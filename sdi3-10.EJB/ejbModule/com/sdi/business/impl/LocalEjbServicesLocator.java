 package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.ApplicationsService;
import com.sdi.business.LoginService;
import com.sdi.business.RatingsService;
import com.sdi.business.SeatsService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class LocalEjbServicesLocator implements ServicesFactory {

	private static final String LOGIN_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbLoginService!"
			+ "com.sdi.business.impl.ejbLocal.LocalLoginService";
	private static final String USERS_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbUsersService!"
			+ "com.sdi.business.impl.ejbLocal.LocalUsersService";
	private static final String TRIPS_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbTripsService!"
			+ "com.sdi.business.impl.ejbLocal.LocalTripsService";
	private static final String SEATS_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbSeatsService!"
			+ "com.sdi.business.impl.ejbLocal.LocalSeatsService";
	private static final String APPLICATIONS_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbApplicationsService!"
			+ "com.sdi.business.impl.ejbLocal.LocalApplicationsService";
	private static final String RATINGS_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-10/" + "sdi3-10.EJB/" + "EjbRatingsService!"
			+ "com.sdi.business.impl.ejbLocal.LocalRatingsService";

	@Override
	public LoginService getLoginService() {
		try {
			Context ctx = new InitialContext();
			return (LoginService) ctx.lookup(LOGIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UsersService getUsersService() {
		try {
			Context ctx = new InitialContext();
			return (UsersService) ctx.lookup(USERS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TripsService getTripsService() {
		try {
			Context ctx = new InitialContext();
			return (TripsService) ctx.lookup(TRIPS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public SeatsService getSeatsService() {
		try {
			Context ctx = new InitialContext();
			return (SeatsService) ctx.lookup(SEATS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public ApplicationsService getApplicationService() {
		try {
			Context ctx = new InitialContext();
			return (ApplicationsService) ctx
					.lookup(APPLICATIONS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public RatingsService getRatingsService() {
		try {
			Context ctx = new InitialContext();
			return (RatingsService) ctx.lookup(RATINGS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
