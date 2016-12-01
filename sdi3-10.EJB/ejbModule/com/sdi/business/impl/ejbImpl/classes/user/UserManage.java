package com.sdi.business.impl.ejbImpl.classes.user;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.UserDao;

public class UserManage {

	public void cancelUser(Long id) {
		UserDao uD = Factories.persistence.newUserDao();
		User user = uD.findById(id);
		user.setStatus(UserStatus.CANCELLED);
		uD.update(user);
		TripDao tD = Factories.persistence.newTripDao();
		List<Trip> trips = tD.findByPromoterId(user.getId());
		for (Trip trip : trips) {
			trip.setStatus(TripStatus.CANCELLED);
			tD.update(trip);
		}
		trips = tD.findByParticipated(id);
		SeatDao sD = Factories.persistence.newSeatDao();
		for (Trip trip : trips) {
			if (!trip.getStatus().equals(TripStatus.CANCELLED)
					&& !trip.getPromoterId().equals(id)) {
				Seat seat = sD.findById(new Long[] {id,trip.getId()});
				seat.setStatus(SeatStatus.EXCLUDED);
				sD.update(seat);
				trip.setAvailablePax(trip.getAvailablePax() + 1);
				tD.update(trip);
			}
			else if(trip.getStatus().equals(TripStatus.CANCELLED)
					&& trip.getPromoterId().equals(id)){
				Seat seat = sD.findById(new Long[] {id,trip.getId()});
				seat.setStatus(SeatStatus.EXCLUDED);
				sD.update(seat);
			}
		}
	}

}
