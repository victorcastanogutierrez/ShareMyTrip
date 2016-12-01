package com.sdi.persistence;

import java.util.List;

import com.sdi.model.SeatStatus;
import com.sdi.model.User;
import com.sdi.persistence.util.GenericDao;

public interface UserDao extends GenericDao<User, Long> {

	/**
	 * Busca a un usuario por su login
	 * 
	 * @param login
	 * @return
	 */
	User findByLogin(String login);

	/**
	 * Encuentra todos los usuarios que tienen una application en un trip
	 * 
	 * @param trip_id
	 *            id del trip
	 * @return
	 */
	List<User> findByTrip(Long trip_id);

	/**
	 * Busca todos los usuarios que tienen application para un viaje en concreto
	 * 
	 * @param id
	 *            del viaje
	 * @return
	 */
	List<User> findByTripAndApplicationPending(Long id);

	/**
	 * Encuentra todos los usuarios con Seat en un estado pasado por parámetro
	 * para un trip pasado por parametro
	 * 
	 * @param id
	 *            del trip
	 * @param excluded
	 *            estado del seat
	 * @return
	 */
	List<User> findByTripAndSeat(Long id, SeatStatus excluded);

	/**
	 * Encuentra un usuario por su email
	 * 
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	List<User> findAllActive();
}
