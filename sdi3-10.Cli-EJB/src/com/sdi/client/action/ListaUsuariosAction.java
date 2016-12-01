package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.ListadoDTO;
import com.sdi.model.User;

public class ListaUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {

		UsersService uS = new RemoteEjbServicesLocator().getUsersService();
		TripsService tS = new RemoteEjbServicesLocator().getTripsService();

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
