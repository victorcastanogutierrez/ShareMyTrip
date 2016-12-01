package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.ApplicationsService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.presentation.util.MessagesUtil;

/**
 * Bean session que se encarga del usuario que usa la aplicación
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "user")
@SessionScoped
public class BeanUser extends User implements Serializable {
	private static final long serialVersionUID = 8L;

	private String name = "";
	private String password = "";
	private String password2 = "";
	private String surname = "";
	private String email = "";
	private String login = "";
	private Long id;
	private String result = "register_form_result_blank";
	private List<Trip> viajes;

	/**
	 * Método que inicializa al usuario con sus datos
	 * 
	 * @param user
	 *            DTO de donde sacar los datos de inicialización
	 */
	public void setUser(User user) {
		setId(user.getId());
		setLogin(user.getLogin());
		setName(user.getName());
		setSurname(user.getSurname());
		setPassword(user.getPassword());
		setEmail(user.getEmail());
	}

	/**
	 * Método que registra un usuario en el sistema.
	 * 
	 * @return outcome de la acción
	 */
	public String registraUsuario() {
		User user = Factories.services.getUsersService().register(
				new User(name, surname, password, login, email));
		if (user != null) {
			setResult("register_form_result_valid");
			return "index";
		}
		setResult("register_form_result_error");
		return "registro";
	}

	/**
	 * Método que solicita una plaza en un viaje por un usuario
	 * 
	 * @param trip
	 *            id del viaje para solicitar la plaza
	 * @return outcome de la acción
	 */
	public String solicitarPlaza(Long trip) {
		ResourceBundle b = MessagesUtil.getResourceBoundle();
		TripsService tservice = Factories.services.getTripsService();
		Trip t = tservice.findById(trip);
		if (t.getAvailablePax() == 0) {
			crearMensaje(FacesMessage.SEVERITY_ERROR,
					b.getString("viajeSinPlaza"));
		} else {
			ApplicationsService sservice = Factories.services
					.getApplicationService();
			if (sservice.save(trip, getId()) == null) {
				crearMensaje(FacesMessage.SEVERITY_ERROR,
						b.getString("viajeApuntado"));
			} else {
				crearMensaje(FacesMessage.SEVERITY_INFO,
						b.getString("plazaSolicitada"));
			}
		}
		return "exito";
	}

	/**
	 * Método que cancela la solicitud de plaza de un usuario en un trip y que
	 * por tanto la borra del sistema. Solo podrá ser posible si la fecha de
	 * cierre es superior a la actual
	 * 
	 * @param trip
	 *            id del trip
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String cancelarApplicant(Long trip) {
		if (!AssertComprobarFechaCierre(trip)) {
			return null;
		}
		Factories.services.getApplicationService().delete(trip, id);
		eliminarViajeLista(trip);
		crearMensajeSuceso(MessagesUtil.getResourceBoundle().getString(
				"asistenciaCancelada"));
		return null;
	}

	/**
	 * Método que cancela el seat del usuario en un viaje. Solo podrá ser
	 * posible si la fecha de cierre es superior a la actual
	 * 
	 * @param trip
	 *            id del viaje
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String cancelarSeat(Long trip) {
		if (!AssertComprobarFechaCierre(trip)) {
			return null;
		}
		Factories.services.getSeatsService().cancel(trip, id);
		eliminarViajeLista(trip);
		crearMensajeSuceso(MessagesUtil.getResourceBoundle().getString(
				"seatCancelado"));
		return null;
	}

	/**
	 * Método auxiliar que comprueba si la fecha de cierre de un viaje en
	 * concreto es superior a la actual
	 * 
	 * @param trip
	 *            id del trip a comprobar su fecha
	 * @return true en caso de ser la fecha de cierre superior, false en caso
	 *         contrario
	 */
	private boolean AssertComprobarFechaCierre(Long trip) {
		return Factories.services.getTripsService().findById(trip)
				.getClosingDate().after(new Date());
	}

	/**
	 * Método auxiliar que crea un mensaje de advertencia para el usuario con
	 * contenido pasado por parámetro
	 * 
	 * @param mensaje
	 *            String con el contenido del mensaje
	 */
	private void crearMensajeSuceso(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("", mensaje));

	}

	/**
	 * Método auxiliar que elimina un viaje de la lista de viajes disponibles
	 * 
	 * @param trip
	 *            id del viaje a eliminar de la lista
	 */
	private void eliminarViajeLista(Long trip) {
		int pos = 0;
		for (int i = 0; i < viajes.size(); i++) {
			if (viajes.get(i).getId().equals(trip)) {
				pos = i;
				break;
			}
		}
		viajes.remove(pos);
	}

	/**
	 * Método que crea un mensaje, del tipo pasado por parámetro y con contenido
	 * pasado por parámetro
	 * 
	 * @param tipo
	 *            del mensaje. Puede ser de información, advertencia, error o
	 *            fallo grave
	 * @param msInfo
	 *            String con el contenido del sistema
	 */
	private void crearMensaje(Severity tipo, String msInfo) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(tipo, "", msInfo));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public List<Trip> getViajes() {
		return viajes;
	}

	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
