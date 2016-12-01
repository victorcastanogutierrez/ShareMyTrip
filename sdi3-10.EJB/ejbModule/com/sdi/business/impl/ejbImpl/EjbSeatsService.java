package com.sdi.business.impl.ejbImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.seat.SeatUpdate;
import com.sdi.business.impl.ejbImpl.classes.seat.SeatsCancel;
import com.sdi.business.impl.ejbImpl.classes.seat.SeatsFind;
import com.sdi.business.impl.ejbImpl.classes.seat.SeatsSave;
import com.sdi.business.impl.ejbLocal.LocalSeatsService;
import com.sdi.business.impl.ejbRemote.RemoteSeatsService;
import com.sdi.model.Seat;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="SeatsService")
public class EjbSeatsService implements LocalSeatsService,RemoteSeatsService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Seat> findAdll() {
		return new SeatsFind().findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Seat> findByTrip(Long id) {
		return new SeatsFind().findByTrip(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancel(Long trip, Long user) {
		new SeatsCancel().cancel(trip, user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exclude(Long[] ids) {
		new SeatsCancel().exclude(ids);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Seat seat) {
		new SeatsSave().save(seat);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateNoSeat(Long tripId, Long userId) {
		new SeatUpdate().updateNoSeat(tripId, userId);
	}

	@Override
	public List<Seat> findByUserId(Long id) {
		return new SeatsFind().findByUserId(id);
	}
}
