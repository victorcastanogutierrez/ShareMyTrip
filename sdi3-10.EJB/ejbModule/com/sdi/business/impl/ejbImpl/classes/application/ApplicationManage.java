package com.sdi.business.impl.ejbImpl.classes.application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

/**
 * Clase que aporta la interfaz de métodos necesaria para administrar una
 * application.
 * 
 * @author sdi2-10
 * 
 */
public class ApplicationManage {

	/**
	 * Acepta una application, la elimina y crea el correspondiente seat.
	 * Decrementa en una unidad las plazas disponibles de un viaje
	 * 
	 * @param id
	 */
	public void accept(Long[] id) {
		SeatDao seatDao = Factories.persistence.newSeatDao();
		Seat seat = obtenerSeat(id);

		Factories.persistence.newApplicationDao().delete(id);
		seatDao.save(seat);
		decrementarPlazasViaje(id);

	}

	/**
	 * Método que crea un DTO de Seat
	 * 
	 * @param id
	 *            array de ids: usuario y viaje del seat
	 * @return
	 */
	private Seat obtenerSeat(Long[] id) {
		Seat seat = new Seat();
		seat.setUserId(id[0]);
		seat.setTripId(id[1]);
		seat.setStatus(SeatStatus.ACCEPTED);
		return seat;
	}

	/**
	 * Método que decrementa las plazas disponibles en un viaje determinado
	 * 
	 * @param id
	 */
	private void decrementarPlazasViaje(Long[] id) {
		TripDao tdao = Factories.persistence.newTripDao();
		Trip trip = tdao.findById(id[1]);
		trip.setAvailablePax(trip.getAvailablePax() - 1);
		tdao.update(trip);
	}

	/**
	 * Método que elimina una application y la guarda como exluida en Seats
	 * 
	 * @param id
	 */
	public void exclude(Long[] id) {
		Seat seat = obtainSeat(id);
		Factories.persistence.newApplicationDao().delete(id);
		Factories.persistence.newSeatDao().save(seat);
	}

	/**
	 * Método que crea un DTO de seat con el estado excluido y lo guarda
	 * 
	 * @param id
	 */
	private Seat obtainSeat(Long[] id) {
		Seat seat = new Seat();
		seat.setTripId(id[1]);
		seat.setUserId(id[0]);
		seat.setStatus(SeatStatus.EXCLUDED);
		return seat;
	}

}
