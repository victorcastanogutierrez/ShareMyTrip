package com.sdi.business.impl.ejbImpl.classes.application;

import java.util.List;

import com.sdi.business.util.DateUtil;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;

/**
 * Clase que obtiene listados de applications
 * 
 * @author Victor
 * 
 */
public class ApplicationFind {

	/**
	 * Retorna una lista con todas las applications para cuyo viaje ya ha pasado
	 * la fecha de cierre
	 * 
	 * @return
	 */
	public List<Application> findNoSeats() {
		return Factories.persistence.newApplicationDao().findNoSeat(
				DateUtil.fechaHoy());
	}

	public List<Application> findAll() {
		return Factories.persistence.newApplicationDao().findAll();
	}

	public Application findByIds(Long tripId, Long userId) {
		return Factories.persistence.newApplicationDao().findById(
				new Long[] { tripId, userId });
	}

	public List<Application> findByTripId(Long id) {
		return Factories.persistence.newApplicationDao().findByTripId(id);
	}

}
