package com.sdi.business;

/**
 * Factoría que instancia las fachadas de los diferentes servicios de la capa de
 * negocio del sistema
 * 
 * @author sdi2-10
 * 
 */

public interface ServicesFactory {

	/**
	 * Instancia la fachada de Login
	 * 
	 * @return
	 */
	LoginService getLoginService();

	/**
	 * Instancia la fachada de Usuarios
	 * 
	 * @return
	 */
	UsersService getUsersService();

	/**
	 * Instancia la fachada de Trips
	 * 
	 * @return
	 */
	TripsService getTripsService();

	/**
	 * Instancia la fachada de Seats
	 * 
	 * @return
	 */
	SeatsService getSeatsService();

	/**
	 * Instancia la fachada de Applications
	 * 
	 * @return
	 */
	ApplicationsService getApplicationService();
	
	RatingsService getRatingsService();

}
