package com.sdi.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sdi.business.ApplicationsService;
import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;

/**
 * Bean session que se encarga de administrar un viaje
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "tripAdmin")
@SessionScoped
public class BeanTripAdmin {

	private Trip dto;
	private List<User> pasajeros;
	private User promotor;

	/**
	 * Método que obtiene la lista de usuarios de un viaje, ya sean aceptados o
	 * pendientes como application
	 * 
	 * @return outcome de la acción
	 */
	public String listadoUsuarios() {
		try {
			UsersService service = Factories.services.getUsersService();
			setPasajeros(service.listByAcceptedAndPending(dto.getId()));
			setPromotor(service.findById(dto.getPromoterId()));
			return "exito";
		} catch (Exception e) {
			return "error";
		}
	}

	/**
	 * Método que acepta a un pasajero, ésto es, le pasa de application a seat
	 * ACCEPTED
	 * 
	 * @param idUser
	 *            id del usuario
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String aceptarPasajero(Long idUser) {
		ApplicationsService application = Factories.services
				.getApplicationService();
		application.accept(new Long[] { idUser, dto.getId() });
		User userL = removeFromList(idUser);
		pasajeros.add(userL);
		dto.setAvailablePax(dto.getAvailablePax() - 1);
		return null;
	}

	/**
	 * Método auxiliar que elimina a una persona de la lista de pasajeros del
	 * viaje
	 * 
	 * @param idUser
	 *            id del usuario a eliminar
	 * @return outcome
	 */
	private User removeFromList(Long idUser) {
		User userL = removeUserFromList(idUser);
		userL.setState("ACCEPTED");
		return userL;
	}

	/**
	 * Método auxiliar para eliminar al pasajero del List de los pasajeros.
	 * 
	 * @param idUser
	 *            id del usuario a eliminar
	 * @return pasajero eliminado, en caso de haberlo encontrado, null para otro
	 *         caso
	 */
	private User removeUserFromList(Long idUser) {
		User userL = null;
		for (User user : pasajeros) {
			if (user.getId().equals(idUser)) {
				userL = user;
			}
		}
		pasajeros.remove(userL);
		return userL;
	}

	/**
	 * Método que pasa a un usuario con seat ACCEPTED en un trip a EXCLUDED
	 * 
	 * @param idUser
	 *            id del usuario a pasar
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String excluirPasajero(Long idUser) {
		Factories.services.getSeatsService().exclude(
				new Long[] { idUser, dto.getId() });
		User userL = removeUserFromList(idUser);
		userL.setState("EXCLUDED");
		dto.setAvailablePax(dto.getAvailablePax() + 1);
		return null;
	}

	/**
	 * Método que pasa de un application a Seat EXCLUDED a un usuario
	 * 
	 * @param idUser
	 *            id del usuario
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String excluirPasajeroPendiente(Long idUser) {
		ApplicationsService application;
		application = Factories.services.getApplicationService();
		application.exclude(new Long[] { idUser, dto.getId() });
		removeUserFromList(idUser);
		return null;
	}

	public Trip getDto() {
		return dto;
	}

	public void setDto(Trip dto) {
		this.dto = dto;
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
