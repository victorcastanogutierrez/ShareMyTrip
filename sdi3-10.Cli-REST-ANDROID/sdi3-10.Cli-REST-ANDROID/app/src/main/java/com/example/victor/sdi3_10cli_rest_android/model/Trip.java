package com.example.victor.sdi3_10cli_rest_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is not an entity, it is a DTO with the same fields as 
 * a row in the table
 *
 */

public class Trip implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private AddressPoint departure;
	private AddressPoint destination;
	private Date arrivalDate;
	private Date departureDate;
	private Date closingDate;
	private Integer availablePax = 0; 
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;
	private TripStatus status;
	private String tripStatus;
	
	private Long promoterId;

	public Trip() { }
	
	public Trip(AddressPoint departure, AddressPoint destination,
			Date arrivalDate, Date departureDate, Date closingDate,
			Integer availablePax, Integer maxPax, Double estimatedCost,
			String comments, TripStatus status, Long promoterId) {
		super();
		this.departure = departure;
		this.destination = destination;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.closingDate = closingDate;
		this.availablePax = availablePax;
		this.maxPax = maxPax;
		this.estimatedCost = estimatedCost;
		this.comments = comments;
		this.status = status;
		this.promoterId = promoterId;
	}

	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	@Override
	public String toString() {
		return "Viaje: id " + id
				+ ", Datos salida: " + departure
				+ ", Datos llegada: " + destination
				+ ", Fecha de llegada " + arrivalDate 
				+ ", Fecha de salida " + departureDate 
				+ ", Fecha de cierre " + closingDate 
				+ ", Plazas disp. " + availablePax 
				+ ", Plazas máximas " + maxPax 
				+ ", Coste estimado " + estimatedCost +"€"
				+ ", Comentarios -" + comments +"-"
				+ ", Estado " + status
				+ ", Id promotor " + promoterId 
			+ "]";
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

}
