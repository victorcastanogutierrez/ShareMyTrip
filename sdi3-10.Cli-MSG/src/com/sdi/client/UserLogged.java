package com.sdi.client;

import javax.jms.JMSException;
import javax.jms.TopicConnection;

import com.sdi.integration.topic.TopicSubs;

/**
 * Clase para almacenar los datos del usuario logueado en el cliente Nombre,
 * password, userId y conexión
 * 
 */
public class UserLogged {

	private String nombre;
	private String password;
	private Long userId;
	private TopicConnection con;

	private static UserLogged userLogged;

	private UserLogged(String nombre, String password, Long userId) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.userId = userId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public static void createInstance(String user, String password, Long userId) {
		if (userLogged == null) {
			userLogged = new UserLogged(user, password, userId);
			new TopicSubs().init();
		}
	}

	public static UserLogged getUserLogged() {
		return userLogged;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TopicConnection getCon() {
		return con;
	}

	public void setCon(TopicConnection con) {
		this.con = con;
	}

	public void cerrarConexion() throws JMSException {
		con.close();
	}
}
