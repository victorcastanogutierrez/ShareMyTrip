package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;

/**
 * Bean Session que se encarga de los viajes
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "tripController")
@SessionScoped
public class BeanTrips implements Serializable {

	private static final long serialVersionUID = 55555L;

	@PostConstruct
	public void init() {
		System.out.println("BeanViajes - PostConstruct");
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanViajes - PreDestroy");
	}

	private List<Trip> viajes = null;

	private List<Trip> viajesSeleccionados;

	/**
	 * Método que elimina para el usuario los viajes que seleccionó mediante los
	 * checkbox
	 * 
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String borrarViajesSel() {
		Date fecha_actual = new Date();
		TripsService s = Factories.services.getTripsService();
		for (Trip t : viajesSeleccionados) {
			if (!t.getStatus().equals(TripStatus.CANCELLED)
					&& fecha_actual.before(t.getClosingDate())) {
				t.setTripStatus("CANCELADO");
				t.setStatus(TripStatus.CANCELLED);
				s.update(t);
			}
		}
		return null;
	}

	public List<Trip> getViajes() {
		return (viajes);
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	/**
	 * Método que obtiene los viajes disponibles para cualquier usuario
	 * 
	 * @return outcome de la acción
	 */
	public String listado() {
		TripsService service;
		try {
			service = Factories.services.getTripsService();
			viajes = service.getAvailableTrips();
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public List<Trip> getViajesSeleccionados() {
		return viajesSeleccionados;
	}

	public void setViajesSeleccionados(List<Trip> viajesSeleccionados) {
		this.viajesSeleccionados = viajesSeleccionados;
	}
}
