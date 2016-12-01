package com.sdi.business.impl.ejbImpl.classes.trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

/**
 * Clase que proporciona los métodos necesarios para actualizar un Trip
 * 
 * @author sdi2-10
 * 
 */
public class TripUpdate {

	/**
	 * Método que actualiza todos los campos de un Trip
	 * 
	 * @param trip
	 *            DTO del trip a actualizar
	 */
	public void update(Trip trip) {
		Factories.persistence.newTripDao().update(trip);
	}

}
