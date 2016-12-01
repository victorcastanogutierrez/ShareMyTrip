package com.sdi.presentation.registerValidators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sdi.infrastructure.Factories;

/**
 * Validador del nombre de usuario en un registro
 * 
 * @author sdi2-10
 * 
 */
@FacesValidator("loginValidator")
public class UserNameValidator implements Validator {

	/**
	 * Comprueba que el nombre de usuario introducido no exista ya en el sistema
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (Factories.services.getUsersService().findByLogin(
				value.toString()) != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ResourceBundle b = facesContext.getApplication().getResourceBundle(
					facesContext, "msgs");
			FacesMessage msg = new FacesMessage("",
					b.getString("usuarioYaExiste"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}
