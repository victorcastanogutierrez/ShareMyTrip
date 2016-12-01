package com.sdi.client;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import alb.util.console.Console;

import com.sdi.client.menu.MainMenu;
import com.sdi.client.services.UsersService;
import com.sdi.util.LogConfig;

public class Main {

	public static void main(String[] args) {
		LogConfig.config();
		new Main().run();
	}

	private void run() {
		UsersService client = new ResteasyClientBuilder().build()
				.register(new Authenticator("sdi", "password"))
				.target("http://localhost:8280/sdi3-10.WEB/rest/")
				.proxy(UsersService.class);
		
		String user = Console.readString("Introduce tu nombre de usuario");
		String passwd = Console.readString("Introduce tu password");
		
		while (client.getUserLogin(user, passwd) == null) {
			Console.println("Datos incorrectos");
			user = Console.readString("Introduce tu nombre de usuario");
			passwd = Console.readString("Introduce tu password");
		}
		
		UserLogged.createInstance(user, passwd);
		new MainMenu().execute();
	}

}
