package com.sdi.integration.topic;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.client.UserLogged;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/FilteredTripQueue") })
public class MsgTopicListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		try {
			process(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void process(Message msg) throws JMSException {
		if (!messageOfExpectedType(msg)) {
			System.out.println("El mensaje recibido no es correcto " + msg);
			return;
		}
		MapMessage men = (MapMessage) msg;
		if (assertDestinatarioUsuario(men)) {
			String mensaje = men.getString("mensaje");
			System.out.println("Recibido mensaje : " + mensaje);
		}
	}

	/**
	 * Método que comprueba si el destinatario del mensaje es el usuario en
	 * sesion ahora mismo
	 * 
	 * @param userId
	 * @return
	 */
	private boolean assertDestinatarioUsuario(MapMessage mapm) {
		try {
			Long uid = mapm.getLong("user");
			return uid != null
					&& uid.equals(UserLogged.getUserLogged().getUserId());
		} catch (JMSException e) {
			return false;
		}
	}

	private boolean messageOfExpectedType(Message msg) {
		return msg instanceof MapMessage;
	}

}
