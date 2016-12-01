package com.sdi.business.impl.ejbImpl.classes.user;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

/**
 * Clase que proporciona todos los métodos para obtener diferentes listados de
 * usuarios
 * 
 * @author sdi2-10
 * 
 */
public class UsersFind {

	/**
	 * Método que busca todos los usuarios que estén como Seat con un estado
	 * concreto, en un viaje concreto
	 * 
	 * @param id
	 *            del viaje
	 * @param status
	 *            estado del seat
	 * @return la lista de usuarios
	 */
	public List<User> findByTripAndStatus(Long id, SeatStatus status) {
		return Factories.persistence.newUserDao().findByTripAndSeat(id, status);
	}

	/**
	 * Método que busca un usuario por su ID
	 * 
	 * @param id
	 *            del usuario
	 * @return DTO del usuario
	 */
	public User findById(Long id) {
		return Factories.persistence.newUserDao().findById(id);
	}

	/**
	 * Método que busca todos los usuarios con application pendiente para un
	 * viaje y aquellos que además están aceptados
	 * 
	 * @param id
	 *            del viaje
	 * @return List con todos los usuarios obtenidos
	 */
	public List<User> findByTripAcceptedAndPending(long id) {
		
		UserDao uDao = Factories.persistence.newUserDao();
		
		List<User> users = uDao.findByTripAndApplicationPending(id);
		List<User> dto = userToDTO(users, false);
		users = uDao.findByTripAndSeat(id, SeatStatus.ACCEPTED);
		dto.addAll(userToDTO(users, true));
		return dto;
	}

	/**
	 * Método auxiliar que clasifica un Usuario según esté aceptado o pendiente
	 * para un viaje.
	 * 
	 * @param users
	 *            lista de usuarios a clasificar
	 * @param opcion
	 *            a clasificar
	 * @return Lista clasificada
	 */
	private List<User> userToDTO(List<User> users, boolean opcion) {
		List<User> listDTO = new ArrayList<User>();
		for (User user : users) {
			User dto = new User();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setLogin(user.getLogin());
			dto.setName(user.getName());
			dto.setSurname(user.getSurname());
			dto.setPassword(user.getPassword());
			dto.setStatus(user.getStatus());
			if (opcion)
				dto.setState("ACCEPTED");
			else {
				dto.setState("PENDING");
			}
			listDTO.add(dto);
		}
		return listDTO;
	}

	/**
	 * Método que encuentra un usuario por su usuario de login
	 * 
	 * @param login
	 *            a buscar
	 * @return Usuario en caso de encontrarse, null de cualquier otra forma
	 */
	public User findByLogin(String login) {
		return Factories.persistence.newUserDao().findByLogin(login);
	}

	/**
	 * Método que busca un usuario por su email
	 * 
	 * @param email
	 *            a buscar
	 * @return usuario con el email
	 */
	public User findByEmail(String email) {
		return Factories.persistence.newUserDao().findByEmail(email);
	}

	public List<User> findAll() {
		return Factories.persistence.newUserDao().findAll();
	}

	public List<User> findAllActive() {
		return Factories.persistence.newUserDao().findAllActive();
	}
}
