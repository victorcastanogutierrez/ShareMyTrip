package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Seat;
import com.sdi.persistence.util.GenericDao;

/**
 * Fachada con la interfaz de métodos necesaria para los Seat
 * 
 * @author sdi2-10
 * 
 */
public interface SeatDao extends GenericDao<Seat, Long[]> {

	/**
	 * Busca un seat por su usuario y el viaje
	 * 
	 * @param userId
	 *            id del usuario
	 * @param tripId
	 *            id del viaje
	 * @return
	 */
	Seat findByUserAndTrip(Long userId, Long tripId);

	/**
	 * Busca todos los seat en estado ACCPETED de un viaje
	 * 
	 * @param tripId
	 *            id del viaje
	 * @return
	 */
	List<Seat> findByTripStatusAccepted(Long tripId);

	List<Seat> findByUserId(Long id);

}
