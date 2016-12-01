package com.example.victor.sdi3_10cli_rest_android.model;

import java.io.Serializable;
import java.util.List;

public class ListadoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Trip> viajesPromoter;
	private List<Trip> viajesSeat;
	private List<Trip> viajes;
	

	public List<Trip> getViajesPromoter() {
		return viajesPromoter;
	}
	public void setViajesPromoter(List<Trip> viajesPromoter) {
		this.viajesPromoter = viajesPromoter;
	}
	

	public List<Trip> getViajesSeat() {
		return viajesSeat;
	}
	public void setViajesSeat(List<Trip> viajesSeat) {
		this.viajesSeat = viajesSeat;
	}
	

	public List<Trip> getViajes() {
		return viajes;
	}
	public void setViajes(List<Trip> viajes) {
		this.viajes = viajes;
	}

}
