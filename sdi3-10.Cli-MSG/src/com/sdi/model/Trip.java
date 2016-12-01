package com.sdi.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as 
 * a row in the table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@XmlRootElement
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
	@XmlElement
	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}
	@XmlElement
	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
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
	@XmlElement
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	@XmlElement
	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	@XmlElement
	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}
	@XmlElement
	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}
	@XmlElement
	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	@XmlElement
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@XmlElement
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
	@XmlElement
	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

}
