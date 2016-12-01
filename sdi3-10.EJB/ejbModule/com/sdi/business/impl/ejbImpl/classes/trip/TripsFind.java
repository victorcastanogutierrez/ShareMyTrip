package com.sdi.business.impl.ejbImpl.classes.trip;

import java.util.ArrayList;
import java.util.List;

import com.sdi.business.util.DateUtil;
import com.sdi.infrastructure.Factories;
import com.sdi.model.ListadoDTO;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.persistence.TripDao;

/**
 * Clase que recupera listados de Trips
 * 
 * @author sdi2-10
 * 
 */
public class TripsFind {

	/**
	 * Método que recupera un trip por su id
	 * 
	 * @param id
	 *            del trip a buscar
	 * @return DTO del trip
	 */
	public Trip findById(Long id) {
		return Factories.persistence.newTripDao().findById(id);
	}

	/**
	 * Método que buscar todos los trips que están disponibles.Ésto es que su
	 * fecha de cierre aún no ha pasado, que tienen plazas disponibles y que su
	 * estado es OPEN
	 * 
	 * @return la lista de Trip encontrados
	 * @throws Exception
	 */
	public List<Trip> findAvailable() throws Exception {
		TripDao dao = Factories.persistence.newTripDao();
		return dao.findTripsAvailableToApply(TripStatus.OPEN,
				DateUtil.fechaHoy());
	}

	/**
	 * Método que devuelve una lista con todos los viajes pendientes de un
	 * usuario
	 * 
	 * @param id
	 *            del usuario a buscar sus viajes
	 * @return
	 */
	public List<Trip> findViajesPendientes(long id) {
		TripDao dao = Factories.persistence.newTripDao();

		List<Trip> trips = dao.findTripsPendingPromoter(TripStatus.OPEN, id,
				DateUtil.fechaHoy());
		List<Trip> dto = tripStatus(trips, 1);

		trips = dao.findTripsPendingApplicantExcludedOrAccepted(
				TripStatus.OPEN, id, SeatStatus.EXCLUDED, DateUtil.fechaHoy());
		dto.addAll(tripStatus(trips, 2));
		trips = dao.findTripsPendingApplicantExcludedOrAccepted(
				TripStatus.OPEN, id, SeatStatus.ACCEPTED, DateUtil.fechaHoy());
		dto.addAll(tripStatus(trips, 3));
		trips = dao.findTripsPendingApplicantPending(TripStatus.OPEN, id,
				DateUtil.fechaHoy());
		dto.addAll(tripStatus(trips, 4));
		trips = dao.findTripsPendingApplicantPendingNoSeat(SeatStatus.NO_SEAT,
				id);
		dto.addAll(tripStatus(trips, 5));
		trips = dao.findTripsCanceled(TripStatus.CANCELLED, id);
		dto.addAll(tripStatus(trips, 0));

		return dto;
	}

	/**
	 * Método auxiliar, que dependiendo del tipo de viaje lo clasifica bajo un
	 * rol que puede ser CANCELADO, PROMOTER, EXCLUDED, PENDING o NO_SEAT Cada
	 * uno de ellos son estados que se calculan a partir de fechas o estado del
	 * propio viaje
	 * 
	 * @param trips
	 *            lista con los viajes a clasificar
	 * @param opcion
	 *            tipo de clasificación
	 * @return Lista con los viajes clasificados
	 */
	private List<Trip> tripStatus(List<Trip> trips, int opcion) {
		List<Trip> tripsDTO = new ArrayList<Trip>();
		for (Trip trip : trips) {
			if (trip.getStatus().equals(TripStatus.CANCELLED)) {
				trip.setTripStatus("CANCELADO");
			} else {
				switch (opcion) {
				case 1:
					trip.setTripStatus("PROMOTER");
					break;
				case 2:
					trip.setTripStatus("EXCLUDED");
					break;
				case 3:
					trip.setTripStatus("ACCEPTED");
					break;
				case 4:
					trip.setTripStatus("PENDING");
					break;
				case 5:
					trip.setTripStatus("NO_SEAT");
				}
			}
			tripsDTO.add(trip);
		}
		return tripsDTO;
	}

	public List<Trip> findToClose() {
		return Factories.persistence.newTripDao().findTripsToClose(
				DateUtil.fechaHoy());
	}

	public ListadoDTO findPromoterOrParticipated(Long id) {
		List<Trip> viajesPromoter = Factories.persistence.newTripDao()
				.findByPromoterId(id);
		List<Trip> viajesSeat = Factories.persistence.newTripDao()
				.findByParticipated(id);
		List<Trip> viajes = new ArrayList<Trip>();
		for (Trip tripP : viajesPromoter) {
			for (Trip tripS : viajesSeat) {
				if (!tripP.getPromoterId().equals(tripS.getPromoterId())) {
					if (!viajes.contains(tripS))
						viajes.add(tripS);
					if (!viajes.contains(tripP))
						viajes.add(tripP);
				}
			}
		}
		ListadoDTO dto = new ListadoDTO();
		dto.setViajesPromoter(viajesPromoter);
		dto.setViajesSeat(viajesSeat);
		dto.setViajes(viajes);
		return dto;
	}

	public List<Trip> findByPromoter(Long id) {
		return Factories.persistence.newTripDao().findByPromoterId(id);
	}

	public List<Trip> findByPromoterAndOpen(String user) {
		User u = Factories.persistence.newUserDao().findByLogin(user);
		return Factories.persistence.newTripDao().findByPromoterIdAndOpen(
				u.getId());
	}

	public List<Trip> findAllUserTrip(Long id) {
		return Factories.persistence.newTripDao()
				.findByParticipated(id);
	}

}
