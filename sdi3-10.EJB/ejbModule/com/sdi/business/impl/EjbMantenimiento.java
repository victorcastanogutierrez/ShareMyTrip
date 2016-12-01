package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import com.sdi.business.SeatsService;
import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;

@Stateless
public class EjbMantenimiento {

	//@Schedule(second = "*/5", minute = "*", hour = "*")
	public void timerNoSeat() {
		System.out.println("me");
		List<Application> applications = Factories.services
				.getApplicationService().findNoSeats();
		SeatsService ss = Factories.services.getSeatsService();
		if (applications.size() > 0) {
			for (Application app : applications) {
				ss.updateNoSeat(app.getTripId(), app.getUserId());
			}
		}
		System.out.println("ejecute");
		TripsService tps = Factories.services.getTripsService();
		List<Trip> trips = tps.findToClose();
		if (trips.size() > 0) {
			for (Trip trip : trips) {
				trip.setStatus(TripStatus.CLOSED);
				tps.update(trip);
			}
		}

	}
}
