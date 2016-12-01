package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.EjbRatingsServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.RatingsService;

public class EliminarComentariosYPuntuacionesAction implements Action {

	@Override
	public void execute() throws Exception {
		RatingsService rS = new EjbRatingsServiceService()
				.getRatingsServicePort();

		List<Rating> ratings = rS.findAll();
		Console.println("Lista de los comentarios del sistema");
		for (Rating r : ratings) {
			imprimirRating(r);
		}
		boolean condicion = false;
		while (!condicion) {
			Long id = Console
					.readLong("Seleccione el id de uno de los comentarios a "
							+ "borrar");
			rS.remove(id);
			String opcion = Console
					.readString("Si desea eliminar algun comentario mas diga "
							+ "Si en caso contrario No");
			if (opcion.equals("No"))
				condicion = true;
			else {
				ratings = rS.findAll();
				Console.println("Lista de los comentarios del sistema");
				for (Rating r : ratings) {
					imprimirRating(r);
				}
			}
		}

	}

	private void imprimirRating(Rating r) {
		Console.println("id del rating: " + r.getId() + " Del usuario: "
				+ r.getSeatFromUserId() + " al usuario: "
				+ r.getSeatAboutUserId() + " en el viaje: "
				+ r.getSeatAboutTripId() + " comentario: " + r.getComment()
				+ " y puntuacion: " + r.getValue());
	}
}
