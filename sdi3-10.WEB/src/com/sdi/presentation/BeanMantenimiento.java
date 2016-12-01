package com.sdi.presentation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Bean scope Application encargada de mantener la base de datos. Ésto es buscar
 * continuamente los viajes cuya fecha de cierre haya pasado y aún estén en
 * estado abierto
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "mantenimiento", eager = true)
@ApplicationScoped
public class BeanMantenimiento {

	/**
	 * Tiempo cada cuanto se ejecuta el mantenimiento
	 */
	//private static final int TIEMPO_MS = 4000;

	@PostConstruct
	public void init() {
		System.out.println("BeanMantenimiento - PostConstruct");
		timerMantenimientoSeats();
	}

	/**
	 * Método auxiliar que realiza el trabajo del mantenimiento mediante un
	 * Timer cada cierto tiempo. Comprobará todos los viajes en estado abierto
	 * cuya fecha de cierre haya pasado para volverlos a CLOSED. Además todos
	 * aquellos usuarios que tengan una application pendiente para dicho viaje
	 * pasarán como seat en estado SIN_PLAZA
	 * 
	 * Ademas el segundo timer de este método actualizará el estado de los
	 * viajes a CLOSED siempre que la fecha actual sea superior a la de cerrado
	 * del propio viaje y aún estén en estado abierto
	 */
	private void timerMantenimientoSeats() {
		/*Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				List<Application> applications = Factories.services
						.getApplicationService().findNoSeats();
				SeatsService ss = Factories.services.getSeatsService();
				if (applications.size() > 0) {
					for (Application app : applications) {
						ss.updateNoSeat(app.getTripId(), app.getUserId());
					}
				}
			}
		};
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				TripsService tps = Factories.services.getTripsService();
				List<Trip> trips = tps.findToClose();
				if (trips.size() > 0) {
					for (Trip trip : trips) {
						trip.setStatus(TripStatus.CLOSED);
						tps.update(trip);
					}
				}
			}
		};
		timer.schedule(task, 0, TIEMPO_MS);
		timer.schedule(task2, 0, TIEMPO_MS);*/
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanMantenimiento - PreDestroy");
	}

}
