package com.sdi.business.impl.ejbImpl.classes.seat;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.persistence.SeatDao;

/**
 * Clase que proporciona los métodos para obtener listados de seats
 * 
 * @author sdi2-10
 * 
 */
public class SeatsFind {

	/**
	 * Obtiene un listado de todos los Seats
	 * 
	 * @return List con los seats
	 */
	public List<Seat> findAll() {
		SeatDao dao = Factories.persistence.newSeatDao();
		return dao.findAll();
	}

	/**
	 * Obtiene un listado de todos los seats para un viaje en concreto
	 * 
	 * @param id
	 *            del viaje a buscar
	 * @return List con los seats
	 */
	public List<Seat> findByTrip(Long id) {
		SeatDao dao = Factories.persistence.newSeatDao();
		return dao.findByTripStatusAccepted(id);
	}

	public List<Seat> findByUserId(Long id) {
		return Factories.persistence.newSeatDao().findByUserId(id);
	}
}
