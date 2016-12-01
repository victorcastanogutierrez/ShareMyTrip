package com.sdi;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import alb.util.console.Console;

import com.sdi.client.Authenticator;
import com.sdi.client.UserLogged;
import com.sdi.client.menu.MainMenu;
import com.sdi.model.User;
import com.sdi.services.UsersService;
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
		User u = client.getUserLogin(user, passwd);
		while (u == null) {
			Console.println("Datos incorrectos");
			user = Console.readString("Introduce tu nombre de usuario");
			passwd = Console.readString("Introduce tu password");
			u = client.getUserLogin(user, passwd);
		}
		
		UserLogged.createInstance(user, passwd, u.getId());
		new MainMenu().execute();
	}

}
