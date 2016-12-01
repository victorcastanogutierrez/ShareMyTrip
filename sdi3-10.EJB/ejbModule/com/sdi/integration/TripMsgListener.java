package com.sdi.integration;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.business.UsersService;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.business.impl.ejbLocal.LocalUsersService;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/TripSenderQueue") })
public class TripMsgListener implements MessageListener {

	@EJB(beanInterface = LocalUsersService.class)
	private UsersService service;
	
	@EJB
	private SenderTripTopicMsg sender;
	
	@EJB
	private SenderAdminLogMsg senderLog;

	@Override
	public void onMessage(Message msg) {
		try {
			process(msg);
		} catch (JMSException | BusinessException
				| EntityAlreadyExistsException | EntityNotFoundException jex) {
			jex.printStackTrace();
		}
	}

	/**
	 * Método que procesa un mensaje nuevo. Identifica el viaje para el que va 
	 * dicho mensaje y obtiene la lista de usuarios con plaza en dicho viaje, 
	 * para posteriormente enviar los mensajes a dichos usuarios.
	 * @param msg
	 * @throws BusinessException
	 * @throws JMSException
	 * @throws EntityAlreadyExistsException
	 * @throws EntityNotFoundException
	 */
	private void process(Message msg) throws BusinessException, JMSException,
			EntityAlreadyExistsException, EntityNotFoundException {
		if (!messageOfExpectedType(msg)) {
			System.out.println("El mensaje recibido no es correcto " + msg);
			return;
		}
		MapMessage men = (MapMessage) msg;
		Long tripId = men.getLong("trip");
		Long userId = men.getLong("userId");
		System.out.println("SERVIDOR: mensaje recibido, trip " + tripId);
		
		List<User> users = service.listByTripAndSeatStatus(tripId,
		SeatStatus.ACCEPTED);
		 
		List<Long> usersId = getIdList(users);
		
		//Comprobamos que no sea un mensaje ilegal
		if (assertSenderInTrip(usersId, userId)) {
			sender.sendFilteredMessage(usersId, men);
		} else {
			senderLog.sendLogMessage(men);
		}
	}

	/**
	 * Método que comprueba si el que envia el mensaje está realmente en el viaje,
	 * y de lo contrario, crear el mensaje de LOG para los administradores
	 * en la queue correspondiente
	 * @param usersId
	 * @param userId
	 * @return
	 */
	private boolean assertSenderInTrip(List<Long> usersId, Long userId) {
		return usersId.contains(userId);
	}

	private boolean messageOfExpectedType(Message msg) {
		return msg instanceof MapMessage;
	}

	private List<Long> getIdList(List<User> users) {
		List<Long> list = new ArrayList<Long>();
		for (User u : users) {
			list.add(u.getId());
		}
		return list;
	}

}
