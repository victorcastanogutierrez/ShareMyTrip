package com.sdi.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Jndi {

	public static Object find(String jmsConnectionFactory) {
		Context ctx;
		try {
			ctx = new InitialContext();
			return ctx.lookup(jmsConnectionFactory);
		} catch (NamingException e) {
			throw new RuntimeException ("JNDI problem: "+e.getMessage());
		}
	}

	
}
