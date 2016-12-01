package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.EjbSeatsServiceService;
import com.sdi.ws.EjbTripsServiceService;
import com.sdi.ws.EjbUsersServiceService;
import com.sdi.ws.Seat;
import com.sdi.ws.SeatsService;
import com.sdi.ws.Trip;
import com.sdi.ws.TripsService;
import com.sdi.ws.User;
import com.sdi.ws.UsersService;

public class DeshabilitarUsuarioAction implements Action {

	@Override
	public void execute() throws Exception {

		UsersService uS = new EjbUsersServiceService().getUsersServicePort();
		TripsService tS = new EjbTripsServiceService().getTripsServicePort();
		SeatsService sS = new EjbSeatsServiceService().getSeatsServicePort();

		List<User> usuarios = uS.findAllActive();
		for (User user : usuarios) {
			Console.println("id: " + user.getId() + " login: "
					+ user.getLogin());
		}
		Long id = Console.readLong("Seleccione el id del usuario a cancelar");
		uS.cancelUser(id);
		List<Trip> cancelados = tS.findByPromoter(id);
		List<Seat> excluidos = sS.findByUserId(id);
		Console.println("Viajes cancelados al cancelar al usuario con el id: "
				+ id);
		for (Trip trip : cancelados) {
			Console.println("id: " + trip.getId());
		}
		Console.println("Asientos del que ha sido excluido el usuario "
				+ "cancelado con el id: "
				+ id);
		for (Seat seat : excluidos) {
			Console.println("trip id: " + seat.getTripId() + " user id: "
					+ seat.getUserId());
		}

	}

}
