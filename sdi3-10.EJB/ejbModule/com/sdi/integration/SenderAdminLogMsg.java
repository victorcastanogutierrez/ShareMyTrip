package com.sdi.integration;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Stateless
public class SenderAdminLogMsg {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	@Resource(mappedName = "java:/queue/LogAdminQueue")
	private Destination queue;
	@Resource
	private SessionContext ctx;

	private Connection con;
	private Session session;

	public void sendLogMessage(MapMessage men)
			throws JMSException {
		init();
		send(ctx.getCallerPrincipal(), men);
	}

	private void send(Principal principal, MapMessage men)
			throws JMSException {

		MessageProducer sender;
		try {
			sender = session.createProducer(queue);
			sender.send(men);
			close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		System.out.println("SERVIDOR: enviado mensaje de log ");
	}

	private void init() {
		try {
			con = factory.createConnection("sdi", "password");
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			session.close();
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
