package com.sdi.business;

import com.sdi.model.User;

/**
 * Fachada con la interfaz de métodos necesarios para el login en la aplicación
 * 
 * @author sdi2-10
 * 
 */
public interface LoginService {

	/**
	 * Verifica si un usuario y su contraseña son correctas
	 * 
	 * @param login
	 * @param password
	 * @return el usuario en caso de serlo, null en caso contrario
	 */
	public User verify(String login, String password);

}