package com.sdi.business;

import java.util.List;

import com.sdi.model.Application;

/**
 * Fachada con la interfaz de métodos necesaria para las Applications
 * 
 * @author sdi2-10
 * 
 */
public interface ApplicationsService {

	/**
	 * Guarda una application nueva
	 * 
	 * @param trip
	 *            id del trip
	 * @param user
	 *            id del usuario
	 * @return la nueva Application
	 */
	Application save(Long trip, Long user);

	/**
	 * Elimina una application
	 * 
	 * @param trip
	 *            id
	 * @param user
	 *            id
	 */
	void delete(Long trip, Long user);

	/**
	 * Acepta una application y la pasa como Seat
	 * 
	 * @param id
	 *            array de ids con la id del usuario y del viaje
	 */
	void accept(Long[] id);

	/**
	 * Pasa a Excluded un seat y elimina su aplication
	 * 
	 * @param id
	 *            array de ids con la id del usuario y del viaje
	 */
	void exclude(Long[] id);

	/**
	 * Busca todas las applications
	 * 
	 * @return Lista con las applications
	 */
	List<Application> findNoSeats();
	
	List<Application> findAll();

	Application findByIds(Long tripId,Long userId);

	List<Application> findByTripId(Long id);
}
