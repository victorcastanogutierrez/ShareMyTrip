package com.sdi.business.impl.ejbImpl.classes.seat;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;

/**
 * Clase para guardar seats
 * 
 * @author sdi2-10
 * 
 */
public class SeatsSave {

	/**
	 * Método que guarda un seat
	 * 
	 * @param seat
	 *            DTO del seat a guardar
	 */
	public void save(Seat seat) {
		Factories.persistence.newSeatDao().save(seat);
	}

}
