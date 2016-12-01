package com.sdi.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.LoginService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

/**
 * Bean de session que se encarga del login de un usuario en el sistema
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin extends User implements Serializable {
	private static final long serialVersionUID = 6L;

	private Long id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;

	@ManagedProperty(value = "#{user}")
	private BeanUser user;

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	private String result = "login_form_result_blank";

	public BeanLogin() {
		System.out.println("BeanLogin - No existia");
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanLogin - PostConstruct");
		user = (BeanUser) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("user"));
		if (user == null) {
			System.out.println("BeanUser - No existia");
			user = new BeanUser();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("user", user);
		}
	}

	// Es sólo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanUser - PreDestroy");
	}

	/**
	 * Método que comprueba si el email y la contraseña con correctos
	 * 
	 * @return el outcome dependiendo del exito o fracaso de la validación
	 */
	public String verify() {
		LoginService login = Factories.services.getLoginService();
		User user = login.verify(name, password);
		if (user != null) {

			this.email = user.getEmail();
			this.name = user.getName();
			this.surname = user.getSurname();
			this.password = user.getPassword();
			this.id = user.getId();
			this.login = user.getLogin();
			cargarViajesRelaccionados();
			this.user.setUser(user);
			putUserInSession(user);
			return "exito";
		}
		setResult("login_form_result_error");
		return "fallo";
	}

	/**
	 * Método que carga en la bean de usuario los viajes pendientes del mismo
	 * 
	 * @return outcome
	 */
	public String cargarViajesRelaccionados() {
		TripsService trips = Factories.services.getTripsService();
		this.user.setViajes(trips.viajesPendientes(id));
		return "exito";
	}

	/**
	 * Método auxiliar que registra la sesión del usuario
	 * @param user
	 */
	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

}
