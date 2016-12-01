package com.sdi.business;

import java.util.List;

import com.sdi.model.SeatStatus;
import com.sdi.model.User;

/**
 * Fachada con la interfaz de métodos necesaria para los User
 * 
 * @author sdi2-10
 * 
 */
public interface UsersService {

	/**
	 * Lista todos los usuarios como seat de un viaje en un estado concreto
	 * 
	 * @param id
	 *            del usuario
	 * @param status
	 *            estado del seat
	 * @return
	 */
	List<User> listByTripAndSeatStatus(Long id, SeatStatus status);

	/**
	 * Encuentra un usuario por su ID
	 * 
	 * @param id
	 *            del usuario
	 * @return
	 */
	User findById(Long id);

	/**
	 * Método que valida y registra en caso de ser correcto, a un usuario nuevo
	 * 
	 * @param user
	 *            DTO que contiene los datos del nuevo usuario a registrar
	 * @return el usuario nuevo registrado, null en caso de no haber validado
	 *         correctamente sus datos
	 */
	User register(User rdto);

	/**
	 * Encuentra la lsita de usuarios aceptados y pendientes de un viaje
	 * 
	 * @param id
	 *            del viaje
	 * @return
	 */
	List<User> listByAcceptedAndPending(Long id);

	/**
	 * Encuentra un usuario por su login
	 * 
	 * @param login
	 * @return
	 */
	User findByLogin(String login);

	/**
	 * Encuentra un usuario por su email
	 * 
	 * @param string
	 *            email
	 * @return
	 */
	User findByEmail(String string);

	List<User> findAll();
	
	List<User> findAllActive();

	void cancelUser(Long id);
}