package com.sdi.business.impl.ejbImpl.classes.user;

import com.sdi.business.util.MD5Util;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

/**
 * Clase que da los métodos necesarios para loguear a un usuario en la
 * aplicación
 * 
 * @author sdi2-10
 * 
 */
public class UsersLogin {

	/**
	 * Método auxiliar que identifica si un usuario ha introducido de manera
	 * correcta su usuario y contraseña
	 * 
	 * @param login
	 *            del usuario
	 * @param password
	 *            del usuario
	 * @return true en caso de haberlo hecho correctamente false en cualquier
	 *         otro caso
	 */
	private boolean validLogin(String login, String password) {
		UserDao userDao = Factories.persistence.newUserDao();
		User user = userDao.findByLogin(login);
		if (user == null)
			return false;
		return MD5Util.getMD5(password).equals(user.getPassword());
	}

	/**
	 * Método que retorna un usuario en caso de que el login sea correcto, null
	 * en cualquier otro caso
	 * 
	 * @param login
	 *            string de login del usuario
	 * @param password
	 * @return el usuario si los datos son correcto, null si no lo son
	 */
	public User verifyLogin(String login, String password) {
		if (!validLogin(login, password))
			return null;
		return Factories.persistence.newUserDao().findByLogin(login);
	}

}
