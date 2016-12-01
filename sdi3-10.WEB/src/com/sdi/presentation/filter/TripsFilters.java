package com.sdi.presentation.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sdi.model.Trip;

/**
 * Clase que implementa los filtros para un trip
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "tripFilter")
@ViewScoped
public class TripsFilters {

	private List<Trip> filteredViajes;

	/**
	 * Filtro para las plazas disponibles de un Trip. Se quedará con aquellos
	 * que tengan más de las que introduce el usuario
	 * 
	 * @param value
	 * @param filter
	 * @param locale
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean filterByPax(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) >= 0;
	}

	/**
	 * Filtro para el coste. Se mantendrán aquellos trip con coste menor o igual
	 * al introducido opr el usuario
	 * 
	 * @param value
	 * @param filter
	 * @param locale
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean filterByCost(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Double.valueOf(filterText)) <= 0;
	}

	/**
	 * Filtro para fechas. Se queda con aquellos trips con fecha que comience
	 * (formateada a string) de la misma manera que la pasada por parámetro
	 * 
	 * @param value
	 * @param filter
	 * @param locale
	 * @return
	 */
	public boolean filterByDate(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(value).startsWith(filterText);
	}

	public List<Trip> getFilteredViajes() {
		return filteredViajes;
	}

	public void setFilteredViajes(List<Trip> filteredViajes) {
		this.filteredViajes = filteredViajes;
	}
}
