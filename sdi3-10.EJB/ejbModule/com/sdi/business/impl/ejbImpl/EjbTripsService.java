package com.sdi.business.impl.ejbImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.trip.TripCreate;
import com.sdi.business.impl.ejbImpl.classes.trip.TripUpdate;
import com.sdi.business.impl.ejbImpl.classes.trip.TripsFind;
import com.sdi.business.impl.ejbLocal.LocalTripsService;
import com.sdi.business.impl.ejbRemote.RemoteTripsService;
import com.sdi.model.ListadoDTO;
import com.sdi.model.Trip;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="TripsService")
public class EjbTripsService implements LocalTripsService,RemoteTripsService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> getAvailableTrips() throws Exception {
		return new TripsFind().findAvailable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trip findById(Long id) {
		return new TripsFind().findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trip registerTrip(Trip trip) {
		return new TripCreate().create(trip);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> viajesPendientes(long id) {
		return new TripsFind().findViajesPendientes(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Trip trip) {
		new TripUpdate().update(trip);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findToClose() {
		return new TripsFind().findToClose();
	}

	@Override
	public ListadoDTO findPromoterOrParticipated(Long id) {
		return new TripsFind().findPromoterOrParticipated(id);
	}

	@Override
	public List<Trip> findByPromoter(Long id) {
		return new TripsFind().findByPromoter(id);
	}

	@Override
	public List<Trip> findByPromoterAndOpen(String user) {
		return new TripsFind().findByPromoterAndOpen(user);
	}

	@Override
	public List<Trip> findAllUserTrip(Long id) {
		return new TripsFind().findAllUserTrip(id);
	}

}
