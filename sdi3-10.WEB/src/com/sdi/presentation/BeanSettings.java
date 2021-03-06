package com.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Bean session encargada de los parámetros del usuario. Entre ellos, el idioma.
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {

	private static final long serialVersionUID = 2L;

	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");

	private Locale locale = new Locale("es");

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesión)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavía si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
	}

	// Es sólo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de código para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida “es”
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			/*
			 * if (alumno != null) alumno.iniciaAlumno(null);
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			/*
			 * if (alumno != null) alumno.iniciaAlumno(null);
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Método que cierra la sesión
	 * 
	 * @return outcome de destino
	 */
	public String logout() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.remove("LOGGEDIN_USER");
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession(); // Invalida la sesion
		return "cierreSesion";
	}

}
