package com.sdi.integration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.client.UserLogged;
import com.sdi.util.Jndi;

public class SendMessage {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String NOTANEITOR_QUEUE = "jms/queue/TripSenderQueue";

	private Connection con;
	private Session session;
	private MessageProducer sender;
	private Long tripId;
	private Long userId;

	public SendMessage(Long tripId, Long userId) {
		this.tripId = tripId;
		this.userId = userId;
	}

	public void send(String men) throws JMSException {
		initialize();
		MapMessage msg = createMessage(men);
		sender.send(msg);
		close();
	}

	private void close() throws JMSException {
		session.close();
		con.close();
	}

	private void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi
				.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(NOTANEITOR_QUEUE);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}

	private MapMessage createMessage(String mensaje) throws JMSException {
		MapMessage msg = session.createMapMessage();
		msg.setLong("trip", (Long) tripId);
		msg.setLong("userId", (Long) userId);
		msg.setString("mensaje", "[User: "
				+ UserLogged.getUserLogged().getNombre() + " trip " + tripId
				+ "]: " + mensaje);
		return msg;
	}
}
