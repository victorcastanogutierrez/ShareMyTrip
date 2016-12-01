package com.sdi.presentation.registerValidators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sdi.infrastructure.Factories;
import com.sdi.presentation.util.MessagesUtil;

/**
 * Validador del email del usuario para el registro
 * 
 * @author sdi2-10
 * 
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	/**
	 * Comprueba que el email introducido no exista ya en la base de datos
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (Factories.services.getUsersService().findByEmail(
				value.toString()) != null) {
			FacesMessage msg = new FacesMessage("", MessagesUtil
					.getResourceBoundle().getString("emailYaExiste"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}
