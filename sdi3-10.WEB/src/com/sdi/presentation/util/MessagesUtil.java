package com.sdi.presentation.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Clase de utilidad para obtener cadenas de los archivos de propiedades de los
 * idiomas
 * 
 * @author sdi2-10
 * 
 */
public class MessagesUtil {

	/**
	 * Método que obtiene el ResourceBundle para obtener cualquier string del
	 * idioma seleccionado
	 * 
	 * @return
	 */
	public static ResourceBundle getResourceBoundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle b = facesContext.getApplication().getResourceBundle(
				facesContext, "msgs");
		return b;
	}

}
