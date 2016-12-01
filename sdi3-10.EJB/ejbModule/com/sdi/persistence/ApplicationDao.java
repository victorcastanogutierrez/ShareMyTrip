package com.sdi.persistence;

import java.util.List;

import com.sdi.model.Application;
import com.sdi.persistence.util.GenericDao;

/**
 * Fachada con la interfaz de metodos necesaria para las Application con la Base
 * de Datos
 * 
 * @author sdi2-10
 * 
 */
public interface ApplicationDao extends GenericDao<Application, Long[]> {

	/**
	 * Encuentra todas las application bajo una id de usuario
	 * 
	 * @param userId
	 * @return List con las application
	 */
	List<Application> findByUserId(Long userId);

	/**
	 * Encuentra todas las application bajo una id de trip
	 * 
	 * @param tripId
	 * @return
	 */
	List<Application> findByTripId(Long tripId);

	/**
	 * Encuentra todas las application que cuyo viaje la fecha de cierre es
	 * superior a la actual
	 * 
	 * @param string
	 *            fecha formateada
	 * @return List con las application
	 */
	List<Application> findNoSeat(String string);

}
