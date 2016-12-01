package com.sdi.business.impl.ejbImpl.classes.user;

import com.sdi.business.util.MD5Util;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.persistence.UserDao;

/**
 * Clase que proporciona los métodos necesarios para registrar a un usuario en
 * la aplicación
 * 
 * @author sdi2-10
 * 
 */
public class UsersRegister {

	/**
	 * Método que comprueba si un usuario no existe ya y en caso de no existir
	 * lo registra
	 * 
	 * @param rdto
	 *            DTO con los datos necesarios para el registro
	 * @return
	 */
	public User verifyRegister(User rdto) {
		if (validUser(rdto.getLogin())) {
			return null;
		}
		User user = crearUsuario(rdto.getName(), rdto.getSurname(),
				MD5Util.getMD5(rdto.getPassword()), rdto.getLogin(),
				rdto.getEmail());
		Factories.persistence.newUserDao().save(user);
		return user;
	}

	/**
	 * Método auxiliar que comprueba que un login no esté registrado ya
	 * 
	 * @param login
	 *            a buscar
	 * @return true si ya está registrado, false en caso contrario
	 */
	private boolean validUser(String login) {
		UserDao userDao = Factories.persistence.newUserDao();
		User user = userDao.findByLogin(login);
		return user != null;
	}

	/**
	 * Método auxiliar que crea un DTO de usuario
	 * 
	 * @param name
	 *            nombre
	 * @param surname
	 *            apellidos
	 * @param password
	 *            contraseña
	 * @param login
	 * @param email
	 * @return el DTO creado
	 */
	private User crearUsuario(String name, String surname, String password,
			String login, String email) {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);
		user.setLogin(login);
		user.setEmail(email);
		user.setStatus(UserStatus.ACTIVE);
		return user;
	}

}
