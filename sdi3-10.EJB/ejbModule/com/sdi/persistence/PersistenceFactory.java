package com.sdi.persistence;


/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada 
 * de persistencia para cada Entidad del Modelo (en este caso solo hay 
 * una: Alumno; pero en futuras versiones habr�� m��s)
 *  
 * @author alb
 *
 */
public interface PersistenceFactory {
	

	UserDao newUserDao();

	TripDao newTripDao();

	SeatDao newSeatDao();

	ApplicationDao newApplicationDao();

	RatingDao newRatingDao();
}

