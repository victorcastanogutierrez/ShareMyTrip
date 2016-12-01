package com.sdi.business.impl.ejbImpl.classes.seat;

import com.sdi.business.exception.BusinessException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;

/**
 * Clase que actualiza un Seat
 * 
 * @author sdi2-10
 * 
 */
public class SeatUpdate {

	/**
	 * Método que actualiza un seat a SIN_PLAZA y elimina su correspondiente
	 * application
	 * 
	 * @param trip
	 * @param user
	 */
	public void updateNoSeat(Long trip, Long user) {
		try {
			Seat seat = obtainSeat(trip, user);
			
			Factories.services.getSeatsService().save(seat);
			Factories.services.getApplicationService().delete(trip, user);
		
		} catch (Exception e) {
			throw new BusinessException(
					"Error con el mantenimiento programado.");
		}
	}

	/**
	 * Método auxiliar que constuye el DTO a partir de la id del trip y del
	 * usuario
	 * 
	 * @param trip
	 * @param user
	 * @return
	 */
	private Seat obtainSeat(Long trip, Long user) {
		Seat seat = new Seat();
		seat.setStatus(SeatStatus.NO_SEAT);
		seat.setTripId(trip);
		seat.setUserId(user);
		return seat;
	}

}
