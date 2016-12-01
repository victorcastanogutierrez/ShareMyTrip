package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.EjbRatingsServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.RatingsService;

public class ListarComentariosYPuntuacionesAction implements Action {

	@Override
	public void execute() throws Exception {

		RatingsService rS = new EjbRatingsServiceService()
				.getRatingsServicePort();

		List<Rating> ratings = rS.findByLastMonth();

		Console.println("Rating del ultimo mes");
		for (Rating r : ratings) {
			Console.println("Del usuario: " + r.getSeatFromUserId()
					+ " al usuario: " + r.getSeatAboutUserId()
					+ " en el viaje: " + r.getSeatAboutTripId()
					+ " comentario: " + r.getComment() + " y puntuacion: "
					+ r.getValue());
		}

	}

}
