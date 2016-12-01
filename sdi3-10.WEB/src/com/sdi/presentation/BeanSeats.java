package com.sdi.presentation;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;

/**
 * Bean de sesion encargada de los seats
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "seats")
@SessionScoped
public class BeanSeats implements Serializable {

	private static final long serialVersionUID = 55555L;

	@ManagedProperty(value = "#{trip}")
	private BeanTrip trip;

	private List<User> pasajeros = null;
	private User promotor = null;

	@PostConstruct
	public void init() {
		System.out.println("BeanSeats - PostConstruct");
		trip = (BeanTrip) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("trip"));
		// si no existe lo creamos e inicializamos
		if (trip == null) {
			System.out.println("BeanTrip - No existia");
			trip = new BeanTrip();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("trip", trip);
		}
	}

	/**
	 * Método que carga todos los pasajeros incluido el promotor, de un viaje en
	 * concreto
	 * 
	 * @return outcome de la acción
	 */
	public String listadoPasajeros() {
		UsersService service;
		try {
			service = Factories.services.getUsersService();
			pasajeros = service.listByTripAndSeatStatus(trip.getId(),
					SeatStatus.ACCEPTED);
			promotor = service.findById(trip.getPromoterId());
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanSeats - PreDestroy");
	}

	public BeanTrip getTrip() {
		return trip;
	}

	public void setTrip(BeanTrip trip) {
		this.trip = trip;
	}

	public List<User> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<User> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public User getPromotor() {
		return promotor;
	}

	public void setPromotor(User promotor) {
		this.promotor = promotor;
	}

}
