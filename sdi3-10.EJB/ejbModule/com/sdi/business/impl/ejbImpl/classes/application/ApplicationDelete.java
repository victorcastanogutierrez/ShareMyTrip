package com.sdi.business.impl.ejbImpl.classes.application;

import com.sdi.infrastructure.Factories;

/**
 * Clase de negocio que se encarga de eliminar una application
 * 
 * @author sdi2-10
 * 
 */
public class ApplicationDelete {

	/**
	 * Método que elimina una application dada de la base de datos
	 * 
	 * @param trip
	 *            viaje de la application
	 * @param user
	 *            usuario de la application
	 */
	public void delete(Long trip, Long user) {
		Factories.persistence.newApplicationDao().delete(
				new Long[] { user, trip });
	}

}
