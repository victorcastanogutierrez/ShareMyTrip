package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.EjbTripsServiceService;
import com.sdi.ws.EjbUsersServiceService;
import com.sdi.ws.ListadoDTO;
import com.sdi.ws.TripsService;
import com.sdi.ws.User;
import com.sdi.ws.UsersService;

public class ListaUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {

		UsersService uS = new EjbUsersServiceService().getUsersServicePort();
		TripsService tS = new EjbTripsServiceService().getTripsServicePort();

		List<User> usuarios = uS.findAllActive();
		for (User user : usuarios) {
			ListadoDTO dto = tS.findPromoterOrParticipated(user.getId());
			Console.println("Datos personales");
			Console.println("Login: " + user.getLogin() + "\nNombre: "
					+ user.getName() + "\nApellidos: " + user.getSurname());
			Console.println("Viajes promotor: "
					+ dto.getViajesPromoter().size());
			Console.println("Viajes participa: " + dto.getViajesSeat().size());
			Console.println("Viajes totales : " + dto.getViajes().size());
		}
	}
}
