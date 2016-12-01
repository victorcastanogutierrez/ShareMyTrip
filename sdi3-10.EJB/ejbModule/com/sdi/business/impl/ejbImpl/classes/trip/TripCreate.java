package com.sdi.business.impl.ejbImpl.classes.trip;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;

/**
 * Clase para crear un Trip nuevo
 * 
 * @author sdi2-10
 * 
 */
public class TripCreate {

	/**
	 * Crea un trip nuevo y mete como seat a su promotor
	 * 
	 * @param trip
	 *            DTO del nuevo trip
	 * @return
	 */
	public Trip create(Trip trip) {
		
		Factories.persistence.newTripDao().save(trip);
		Long tripid = obtenerTripInsertado(trip);
		Seat sdto = obtenerNuevoSeat(trip);
		sdto.setTripId(tripid);
		Factories.persistence.newSeatDao().save(sdto);
		
		return trip;

	}

	/**
	 * Método auxiliar que crea un seat ACCEPTED con usuario el promotor del
	 * viaje
	 * 
	 * @param trip
	 *            viaje a sacar el promotor y su ID para el Seat
	 * @return DTO del Seat
	 */
	private Seat obtenerNuevoSeat(Trip trip) {
		Seat sdto = new Seat();
		sdto.setStatus(SeatStatus.ACCEPTED);
		sdto.setUserId(trip.getPromoterId());
		sdto.setComment("");
		return sdto;
	}

	/**
	 * Método auxiliar que obtiene el trip que se creo a raíz de un DTO
	 * 
	 * @param trip
	 *            DTO del trip creado
	 * @return ID del trip
	 */
	private Long obtenerTripInsertado(Trip trip) {
		return Factories.persistence
				.newTripDao()
				.findByPromoterIdAndArrivalDate(trip.getPromoterId(),
						trip.getArrivalDate()).getId();
	}

}
