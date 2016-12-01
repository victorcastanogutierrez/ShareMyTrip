package com.sdi.business.impl.ejbImpl.classes.application;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;

/**
 * Clase que aporta la interfaz de métodos para guardar una application
 * 
 * @author sdi2-10
 * 
 */
public class ApplicationSave {

	/**
	 * Método que valida datos para un DTO nuevo, lo crea y lo guarda
	 * 
	 * @param trip
	 *            id del viaje
	 * @param user
	 *            id del usuario
	 * @return el DTO del application
	 */
	public Application save(Long trip, Long user) {
		if (!AssertNoEsApplicant(trip, user) || !AssertNoEsSeat(trip, user)) {
			return null;
		}

		Application adto = new Application(user, trip);
		Factories.persistence.newApplicationDao().save(adto);
		return adto;
	}

	private boolean AssertNoEsSeat(Long trip, Long user) {
		Long[] ids = { user, trip };
		return Factories.persistence.newSeatDao().findById(ids) == null;
	}

	private boolean AssertNoEsApplicant(Long trip, Long user) {
		Long[] ids = { user, trip };
		return Factories.persistence.newApplicationDao().findById(ids) == null;
	}

}
