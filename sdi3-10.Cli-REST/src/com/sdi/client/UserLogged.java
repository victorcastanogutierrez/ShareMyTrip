package com.sdi.client;

public class UserLogged {
	
	private String nombre;
	private String password;
	
	private static UserLogged userLogged;

	private UserLogged(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}
	
	public static void createInstance(String user, String password) {
		if (userLogged == null) {
			userLogged = new UserLogged (user,password);
		}
	}

	public static UserLogged getUserLogged() {
		return userLogged;
	}
}
