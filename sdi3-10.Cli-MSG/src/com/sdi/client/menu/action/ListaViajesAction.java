package com.sdi.client.menu.action;

import java.util.List;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.util.DateUtil;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.Authenticator;
import com.sdi.client.UserLogged;
import com.sdi.integration.SendMessage;
import com.sdi.model.Trip;
import com.sdi.services.TripRestService;

public class ListaViajesAction implements Action {

	private TripRestService tripClient;

	@Override
	public void execute() throws Exception {
		tripClient = new ResteasyClientBuilder().build()
				.register(new Authenticator("sdi", "password"))
				.target("http://localhost:8280/sdi3-10.WEB/rest/")
				.proxy(TripRestService.class);

		UserLogged ul = UserLogged.getUserLogged();
		List<Trip> listaViajes = tripClient.getTripsForUser(ul.getUserId());

		printHeader();
		for (Trip t : listaViajes) {
			printTrip(t);
		}

		Long idTrip = Console
				.readLong("Introduce la ID de un viaje para enviar mensajes");
		while (!assertIdValida(idTrip, listaViajes)) {
			idTrip = Console
					.readLong("Introduce la ID de un viaje en la lista "
							+ "para enviar mensajes");
		}

		SendMessage sm = new SendMessage(idTrip, ul.getUserId());
		String msg = Console.readString("Escribe mensajes uno detrás de otro "
				+ "o pulsa enter para parar");
		while (!msg.isEmpty()) {
			sm.send(msg);
			msg = Console.readString();
		}
		UserLogged.getUserLogged().cerrarConexion();
	}

	private boolean assertIdValida(Long id, List<Trip> listaViajes) {
		for (Trip t : listaViajes) {
			if (t.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	private void printHeader() {
		Console.println("ID\tCiudad salida\tCiudad destino\tFecha salida\t"
				+ "Fecha cierre\tPlazas disponibles");
	}

	private void printTrip(Trip t) {
		Console.println(t.getId() + "\t" + t.getDeparture().getCity() + "\t"
				+ t.getDestination().getCity() + "\t"
				+ DateUtil.formatDate(t.getDepartureDate(), "dd/MM/yyyy")
				+ "\t" + DateUtil.formatDate(t.getArrivalDate(), "dd/MM/yyyy")
				+ "\t" + DateUtil.formatDate(t.getClosingDate(), "dd/MM/yyyy")
				+ "\t" + t.getAvailablePax());
	}
}
