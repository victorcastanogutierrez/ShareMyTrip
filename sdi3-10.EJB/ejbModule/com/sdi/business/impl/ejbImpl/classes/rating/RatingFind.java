package com.sdi.business.impl.ejbImpl.classes.rating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.TripDao;

public class RatingFind {

	/**
	 * Obtiene un listado de Ratings en el último mes.
	 * @return
	 */
	public List<Rating> findByLastMonth() {

		TripDao tD = Factories.persistence.newTripDao();
		List<Rating> ratings = Factories.persistence.newRatingDao().findAll();
		List<Rating> ratingLastMonth = new ArrayList<Rating>();
		Calendar c = Calendar.getInstance();

		for (Rating rating : ratings) {
			Trip trip = tD.findById(rating.getSeatFromTripId());
			Date fechaViaje = trip.getArrivalDate();
			Calendar b = Calendar.getInstance();
			b.setTime(fechaViaje);
			if (assertMesPasado(c, b)
					&& trip.getStatus().equals(TripStatus.DONE)) {
				ratingLastMonth.add(rating);
			}
		}
		return ratingLastMonth;
	}

	private boolean assertMesPasado(Calendar c, Calendar b) {
		return c.get(Calendar.MONTH)-b.get(Calendar.MONTH) == 1;
	}

	public List<Rating> findAll() {
		return Factories.persistence.newRatingDao().findAll();
	}

}
