package com.sdi.client.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.business.RatingsService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Rating;

public class EliminarComentariosYPuntuacionesAction implements Action {

	@Override
	public void execute() throws Exception {
		RatingsService rS = new RemoteEjbServicesLocator().getRatingsService();

		List<Rating> ratings = rS.findAll();
		Console.println("Lista de los comentarios del sistema");
		for (Rating rating : ratings) {
			Console.println(rating.toString());
		}
		boolean condicion = false;
		while (!condicion) {
			Long id = Console
					.readLong("Seleccione el id de uno de los comentarios "
							+ "a borrar");
			rS.remove(id);
			String opcion = Console
					.readString("Si desea eliminar algun comentario más diga "
							+ "'Si' en caso contrario 'No'");
			
			if (opcion.equals("No"))
				condicion = true;
			else {
				ratings = rS.findAll();
				Console.println("Lista de los comentarios del sistema");
				for (Rating rating : ratings) {
					Console.println(rating.toString());
				}
			}
		}

	}
}
