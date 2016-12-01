package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.RatingsService;
import com.sdi.business.TripsService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Rating;
import com.sdi.model.Trip;

public class ListarComentariosYPuntuacionesAction implements Action {

	@Override
	public void execute() throws Exception {

		RatingsService rS = new RemoteEjbServicesLocator().getRatingsService();
		TripsService tS = new RemoteEjbServicesLocator().getTripsService();

		List<Rating> ratings = rS.findByLastMonth();
		Trip t = null;

		Console.println("Rating del ultimo mes");
		for (Rating r : ratings) {
			t = tS.findById(r.getSeatAboutTripId());
			Console.println("Del usuario: " + r.getSeatFromUserId()
					+ " al usuario: " + r.getSeatAboutUserId()
					+ " en el viaje: " + r.getSeatAboutTripId()
					+ " comentario: " + r.getComment() + " y puntuacion: "
					+ r.getValue() + " con destino: "
					+ t.getDestination().getCity());
		}

	}

}
