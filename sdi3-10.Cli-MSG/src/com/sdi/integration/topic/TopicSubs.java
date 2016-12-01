package com.sdi.integration.topic;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import com.sdi.client.UserLogged;
import com.sdi.util.Jndi;

public class TopicSubs {

	private TopicSubscriber subscriber;
	private TopicSession session;
	private TopicConnectionFactory factory;

	public void init() {
		factory = (TopicConnectionFactory) Jndi
				.find("jms/RemoteConnectionFactory");
		Topic tripTopic = (Topic) Jndi.find("jms/topic/FilteredTripQueue");
		TopicConnection con = UserLogged.getUserLogged().getCon();
		try {
			
			con = factory.createTopicConnection("sdi", "password");
			con.setClientID(Long.toString(UserLogged.getUserLogged()
					.getUserId()));

			session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			subscriber = session.createDurableSubscriber(tripTopic,
					Long.toString(UserLogged.getUserLogged().getUserId()));
			subscriber.setMessageListener(new MsgTopicListener());
			con.start();
		} catch (JMSException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
		}
	}
}
