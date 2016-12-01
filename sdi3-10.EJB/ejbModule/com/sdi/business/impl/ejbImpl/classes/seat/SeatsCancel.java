package com.sdi.business.impl.ejbImpl.classes.seat;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

/**
 * Clase que da la interfaz de métodos necesaria para pasar un Seat a excluded
 * 
 * @author sdi2-10
 * 
 */
public class SeatsCancel {

	/**
	 * Método que con un Seat existente y aceptado lo pasa a exluído
	 * 
	 * @param trip
	 *            id del viaje
	 * @param user
	 *            id del usuario
	 */
	public void cancel(Long trip, Long user) {
		Long[] ids = { user, trip };
		Factories.persistence.newSeatDao().delete(ids);
		aumentarPlazasDisponiblesViaje(trip);
	}

	/**
	 * Método que elimina un Seat y aumenta las plazas del viaje en una
	 * 
	 * @param id
	 *            array de ids: usuario y viaje
	 */
	public void exclude(Long[] id) {

		SeatDao seatDao = Factories.persistence.newSeatDao();
		
		Seat seat = seatDao.findById(id);
		seat.setStatus(SeatStatus.EXCLUDED);
		seatDao.update(seat);
		aumentarPlazasDisponiblesViaje(id[1]);
		
	}

	/**
	 * Método auxiliar que aumenta en una unidad las plazas disponibles de un
	 * viaje
	 * 
	 * @param trip
	 */
	private void aumentarPlazasDisponiblesViaje(Long trip) {
		TripDao td = Factories.persistence.newTripDao();
		Trip t = td.findById(trip);
		t.setAvailablePax(t.getAvailablePax() + 1);
		td.update(t);
	}

}
