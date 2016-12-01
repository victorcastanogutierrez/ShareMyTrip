package com.sdi.business.impl.ejbImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.user.UserManage;
import com.sdi.business.impl.ejbImpl.classes.user.UsersFind;
import com.sdi.business.impl.ejbImpl.classes.user.UsersRegister;
import com.sdi.business.impl.ejbLocal.LocalUsersService;
import com.sdi.business.impl.ejbRemote.RemoteUsersService;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="UsersService")
public class EjbUsersService implements LocalUsersService,RemoteUsersService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> listByTripAndSeatStatus(Long id, SeatStatus status) {
		return new UsersFind().findByTripAndStatus(id, status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(Long id) {
		return new UsersFind().findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User register(User rdto) {
		return new UsersRegister().verifyRegister(rdto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> listByAcceptedAndPending(Long id) {
		return new UsersFind().findByTripAcceptedAndPending(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByLogin(String login) {
		return new UsersFind().findByLogin(login);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByEmail(String email) {
		return new UsersFind().findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return new UsersFind().findAll();
	}

	@Override
	public void cancelUser(Long id) {
		new UserManage().cancelUser(id);
	}

	@Override
	public List<User> findAllActive() {
		return new UsersFind().findAllActive();
	}

}
