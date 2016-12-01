package com.sdi.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListadoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Trip> viajesPromoter;
	private List<Trip> viajesSeat;
	private List<Trip> viajes;
	
	@XmlElement
	public List<Trip> getViajesPromoter() {
		return viajesPromoter;
	}
	public void setViajesPromoter(List<Trip> viajesPromoter) {
		this.viajesPromoter = viajesPromoter;
	}
	
	@XmlElement
	public List<Trip> getViajesSeat() {
		return viajesSeat;
	}
	public void setViajesSeat(List<Trip> viajesSeat) {
		this.viajesSeat = viajesSeat;
	}
	
	@XmlElement
	public List<Trip> getViajes() {
		return viajes;
	}
	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

}
