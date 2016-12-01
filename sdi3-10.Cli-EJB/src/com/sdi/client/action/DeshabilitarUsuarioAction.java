package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.SeatsService;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {

		UsersService uS = new RemoteEjbServicesLocator().getUsersService();
		TripsService tS = new RemoteEjbServicesLocator().getTripsService();
		SeatsService sS = new RemoteEjbServicesLocator().getSeatsService();
		
		List<User> usuarios = uS.findAllActive();
		for (User user : usuarios) {
			Console.println(user.toString());
		}
		Long id = Console.readLong("Seleccione el id del usuario a cancelar");
		uS.cancelUser(id);
		List<Trip> cancelados = tS.findByPromoter(id);
		List<Seat> excluidos = sS.findByUserId(id);
		Console.println("Viajes cancelados al cancelar al usuario con el id: "
				+ id);
		for (Trip trip : cancelados) {
			Console.println(trip.toString());
		}
		Console.println("Asientos del que ha sido excluido el usuario cancelado "
				+ "con el id: "
				+ id);
		for (Seat seat : excluidos) {
			Console.println(seat.toString());
		}

	}

}
