package com.sdi.business.impl.ejbImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.application.ApplicationDelete;
import com.sdi.business.impl.ejbImpl.classes.application.ApplicationFind;
import com.sdi.business.impl.ejbImpl.classes.application.ApplicationManage;
import com.sdi.business.impl.ejbImpl.classes.application.ApplicationSave;
import com.sdi.business.impl.ejbLocal.LocalApplicationsService;
import com.sdi.business.impl.ejbRemote.RemoteApplicationsService;
import com.sdi.model.Application;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="ApplicationsService")
public class EjbApplicationsService implements LocalApplicationsService,RemoteApplicationsService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Application save(Long trip, Long user) {
		return new ApplicationSave().save(trip, user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long trip, Long user) {
		new ApplicationDelete().delete(trip, user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accept(Long[] id) {
		new ApplicationManage().accept(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exclude(Long[] id) {
		new ApplicationManage().exclude(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Application> findNoSeats() {
		return new ApplicationFind().findNoSeats();
	}

	@Override
	public List<Application> findAll() {
		return new ApplicationFind().findAll();
	}

	@Override
	public Application findByIds(Long tripId, Long userId) {
		return new ApplicationFind().findByIds(tripId,userId);
	}

	@Override
	public List<Application> findByTripId(Long id) {
		return new ApplicationFind().findByTripId(id);
	}
}
