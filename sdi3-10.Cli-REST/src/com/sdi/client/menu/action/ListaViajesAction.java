package com.sdi.client.menu.action;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.Authenticator;
import com.sdi.client.UserLogged;
import com.sdi.client.services.ApplicationRestService;
import com.sdi.client.services.TripRestService;
import com.sdi.model.Application;
import com.sdi.model.Trip;

public class ListaViajesAction implements Action {

	private TripRestService tripClient;
	private ApplicationRestService appClient;

	@Override
	public void execute() throws Exception {
		tripClient = new ResteasyClientBuilder().build()
				.register(new Authenticator("sdi", "password"))
				.target("http://localhost:8280/sdi3-10.WEB/rest/")
				.proxy(TripRestService.class);

		UserLogged userLogged = UserLogged.getUserLogged();

		List<Trip> listaTrip = tripClient.getTrips(userLogged.getNombre(),
				userLogged.getPassword());
		listaTrips(listaTrip);

		if (listaTrip.size() > 0) {
			Long viajesel = Console
					.readLong("Introduce la ID del viaje para "
							+ "confimar pasajeros");

			while (!assertViajeSeleccionado(listaTrip, viajesel)) {
				viajesel = Console
						.readLong("El viaje seleccionado no está en la lista");
			}
			acceptApplication(userLogged, viajesel);
		} else {
			Console.println("No hay pasajeros pendientes para ese viaje");
		}
	}

	private void acceptApplication(UserLogged userLogged, Long viajesel) {
		appClient = new ResteasyClientBuilder().build()
				.register(new Authenticator("sdi", "password"))
				.target("http://localhost:8280/sdi3-10.WEB/rest/")
				.proxy(ApplicationRestService.class);

		List<Application> pendings = appClient.findByTripId(
				userLogged.getNombre(), userLogged.getPassword(), viajesel);
		listaPasajerosPendientes(pendings);

		if (pendings.size() > 0) {
			Long iduser = Console.readLong("Introduce la ID del usuario para "
					+ "aceptar en el viaje");

			while (!assertUserSeleccionado(pendings, iduser)) {
				iduser = Console.readLong("Introduce la ID del usuario para "
						+ "aceptar en el viaje");
			}
			appClient.accept(userLogged.getNombre(), userLogged.getPassword(),
					viajesel, iduser);
			Console.println("Pasajero aceptado");
		}
	}

	private boolean assertUserSeleccionado(List<Application> lpendings, 
			Long iduser) {
		for (Application a : lpendings) {
			if (a.getUserId().equals(iduser)) {
				return true;
			}
		}
		return false;
	}

	private void listaPasajerosPendientes(List<Application> AppList) {
		for (Application a : AppList) {
			Console.println(a);
		}
	}

	private boolean assertViajeSeleccionado(List<Trip> listaTrip, Long viajesel) {
		for (Trip t : listaTrip) {
			if (t.getId().equals(viajesel)) {
				return true;
			}
		}
		return false;
	}

	private void listaTrips(List<Trip> trips) {
		for (Trip t : trips) {
			Console.println(t);
		}
	}

}
