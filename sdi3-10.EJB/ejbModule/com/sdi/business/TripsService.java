package com.sdi.business;

import java.util.List;

import com.sdi.model.ListadoDTO;
import com.sdi.model.Trip;

/**
 * Fachada con la interfaz de métodos necesaria para los Trip
 * 
 * @author sdi2-10
 * 
 */
public interface TripsService {

	/**
	 * Obtiene todos los viajes disponibles
	 * 
	 * @return List con los viajes
	 * @throws Exception
	 */
	List<Trip> getAvailableTrips() throws Exception;

	/**
	 * Obtiene un trip por su ID
	 * 
	 * @param id
	 * @return
	 */
	Trip findById(Long id);

	/**
	 * Registra un Trip nuevo en el sistema
	 * 
	 * @param trip
	 * @return
	 */
	Trip registerTrip(Trip trip);

	/**
	 * Obtiene una lista de viajes pendientes de un usuario
	 * 
	 * @param id
	 *            del usuario
	 * @return
	 */
	List<Trip> viajesPendientes(long id);

	/**
	 * Actualiza un Trip
	 * 
	 * @param trip
	 */
	void update(Trip trip);

	/**
	 * Método que busca aquellos viajes cuya fecha de cierre ha pasado y el
	 * estado está abierto
	 * 
	 * @return
	 */
	List<Trip> findToClose();
	
	List<Trip> findAllUserTrip(Long id);

	ListadoDTO findPromoterOrParticipated(Long id);

	List<Trip> findByPromoter(Long id);

	List<Trip> findByPromoterAndOpen(String user);
}