package com.sdi.integration;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

@Stateless
public class SenderTripTopicMsg {

	@Resource(mappedName = "java:/ConnectionFactory")
	private TopicConnectionFactory factory;
	@Resource(mappedName = "java:/topic/FilteredTripQueue")
	private Destination queue;
	@Resource
	private SessionContext ctx;

	private TopicConnection con;
	private TopicSession session;

	public void sendFilteredMessage(List<Long> usersId, MapMessage men)
			throws JMSException {
		init();
		send(ctx.getCallerPrincipal(), usersId, men);
	}

	private void send(Principal principal, List<Long> usersId, MapMessage men)
			throws JMSException {
		Long trip = men.getLong("trip");
		String mensaje = men.getString("mensaje");
		Long userId = men.getLong("userId");

		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("mensaje", mensaje);
		msg.put("trip", trip);
		for (Long uid : usersId) {
			// Para que no le envie el mensaje al que lo envió
			if (!uid.equals(userId)) {
				msg.put("user", uid);
				sendMapMessage(msg);
			}
		}
		System.out.println("SERVIDOR: mensaje filtrado y enviado: " + mensaje);
	}

	private void init() {
		try {
			con = factory.createTopicConnection("sdi", "password");
			session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void sendMapMessage(Map<String, Object> msgMap) {

		MessageProducer sender;
		try {
			sender = session.createProducer(queue);
			MapMessage msg = createJmsMapMessage(msgMap, session);
			sender.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private MapMessage createJmsMapMessage(Map<String, Object> msgMap,
			Session session) throws JMSException {
		MapMessage msg = session.createMapMessage();
		msg.setString("mensaje", (String) msgMap.get("mensaje"));
		msg.setLong("user", (Long) msgMap.get("user"));
		msg.setLong("trip", (Long) msgMap.get("trip"));
		return msg;
	}
}
