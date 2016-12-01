package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.util.GenericDao;

/**
 * Fachada que proporciona la interfaz de metodos necesaria para Trip con la
 * persistencia de la aplicación
 * 
 * @author sdi2-10
 * 
 */
public interface TripDao extends GenericDao<Trip, Long> {

	/**
	 * Encuentra todos los viajes de un promotor en concreto con una fecha de
	 * llegada menor a la pasada por parametro
	 * 
	 * @param id
	 *            del promotor
	 * @param arrivalDate
	 *            Date con la fecha de llegada
	 * @return
	 */
	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

	/**
	 * Encuentra todos los viajes pendientes de un usuario como promotor que
	 * cumplen con un estado pasado por parametro y cuya fecha de cierre es
	 * inferior a la pasada por parámetro
	 * 
	 * @param open
	 *            estado del viaje
	 * @param id
	 *            del promotor
	 * @param fechaHoy
	 *            String con la fecha formateada
	 * @return
	 */
	List<Trip> findTripsPendingPromoter(TripStatus open, Long id,
			String fechaHoy);

	/**
	 * Busca todos los viajes en el que un usuario en concreto tiene seats.
	 * Estos viajes tienen que tener un estado pasado por parámetro así como los
	 * seats. La fecha de cierre del viaje será comparada con una pasada por
	 * parametro
	 * 
	 * @param open
	 *            estado del viaje
	 * @param id
	 *            del usuario
	 * @param state
	 *            estado del seat
	 * @param fechaHoy
	 *            String con la fecha formateada
	 * @return
	 */
	List<Trip> findTripsPendingApplicantExcludedOrAccepted(TripStatus open,
			Long id, SeatStatus state, String fechaHoy);

	/**
	 * Encuentra todos los viajes para los que un usuario en concreto tiene
	 * Applicants, que además tenga plazas disponibles y la fecha de cierre del
	 * viaje sea inferior a la pasada por parámetro. El estado del viaje será
	 * pasado por parámetro
	 * 
	 * @param open
	 *            estado del viaje
	 * @param id
	 *            del usuario
	 * @param fechaHoy
	 *            String con la fecha de hoy formateada
	 * @return
	 */
	List<Trip> findTripsPendingApplicantPending(TripStatus open, Long id,
			String fechaHoy);

	/**
	 * Encuentra todos los viajes para los que se puede solicitar plaza. Éstos
	 * son los que están bajo un estado en concreto, tienen plazas disponibles y
	 * la fecha de cierre es superior a la actual
	 * 
	 * @param open
	 *            estado del viaje
	 * @param fechaHoy
	 *            String con la fecha a comparar con la fecha de cierre
	 * @return
	 */
	List<Trip> findTripsAvailableToApply(TripStatus open, String fechaHoy);

	/**
	 * Busca todos los viajes para los que un usuario en concreto tenga un seat
	 * con un estado en concreto
	 * 
	 * @param no_seat
	 *            estado del seat
	 * @param id
	 *            del usuario
	 * @return
	 */
	List<Trip> findTripsPendingApplicantPendingNoSeat(SeatStatus no_seat,
			Long id);

	/**
	 * Obtiene un listado con todos los viajes bajo un promotor y un estado
	 * 
	 * @param cancelled
	 *            estado del viaje
	 * @param id
	 *            del promotor
	 * @return
	 */
	List<Trip> findTripsCanceled(TripStatus cancelled, long id);

	/**
	 * Obtiene un listado de aquellos viajes cuya fecha de cierre ha pasado ya y
	 * están aún en estado abierto
	 * 
	 * @param fechaHoy string con la fecha actual del sistema formateada
	 * @return
	 */
	List<Trip> findTripsToClose(String fechaHoy);

	List<Trip> findByPromoterId(Long id);

	List<Trip> findByParticipated(Long id);

	List<Trip> findByPromoterIdAndOpen(Long id);

}
