package com.sdi.business;

import java.util.List;

import com.sdi.model.Seat;

/**
 * Fachada con la interfaz de metodos necesaria para los Seat
 * 
 * @author sdi2-10
 * 
 */
public interface SeatsService {

	/**
	 * Método que devuelve todos los Seats
	 * 
	 * @return List con todos los seats
	 */
	List<Seat> findAdll();

	/**
	 * Método que devuelve todos los seats de un viaje en concreto
	 * 
	 * @param id
	 *            del viaje
	 * @return
	 */
	List<Seat> findByTrip(Long id);

	/**
	 * Método que cancela un Seat
	 * 
	 * @param trip
	 *            id
	 * @param user
	 *            id
	 */
	void cancel(Long trip, Long user);

	/**
	 * Método que pasa un Seat a estado EXCLUIDO
	 * 
	 * @param ids
	 */
	void exclude(Long[] ids);

	/**
	 * Método que guarda un Seat
	 * 
	 * @param seat
	 *            a guardar
	 */
	void save(Seat seat);

	/**
	 * Método que borra una aplication e introduce el seat como SIN_PLAZA
	 * 
	 * @param tripId
	 * @param userId
	 */
	void updateNoSeat(Long tripId, Long userId);

	List<Seat> findByUserId(Long id);

}