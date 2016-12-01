package com.sdi.business.impl.ejbImpl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.user.UsersLogin;
import com.sdi.business.impl.ejbLocal.LocalLoginService;
import com.sdi.business.impl.ejbRemote.RemoteLoginService;
import com.sdi.model.User;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="LoginService")
public class EjbLoginService implements LocalLoginService,RemoteLoginService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User verify(String login, String password) {
		return new UsersLogin().verifyLogin(login, password);
	}

}
