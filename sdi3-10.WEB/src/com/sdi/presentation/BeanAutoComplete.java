package com.sdi.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * Bean de autocompletar datos co scope request
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "autoCompleteCity")
public class BeanAutoComplete {

	private List<String> cities;

	/**
	 * Método para meter provincias a la lista
	 */
	@PostConstruct
	public void init() {
		cities = new ArrayList<String>();
		cities.add("A coruna");
		cities.add("Alava");
		cities.add("Albacete");
		cities.add("Alicante");
		cities.add("Almeria");
		cities.add("Asturias");
		cities.add("Avila");
		cities.add("Badajoz");
		cities.add("Baleares");
		cities.add("Barcelona");
		cities.add("Burgos");
		cities.add("Caceres");
		cities.add("Cadiz");
		cities.add("Cantabria");
		cities.add("Castellon");
		cities.add("Ceuta");
		cities.add("Ciudad Real");
		cities.add("Cordoba");
		cities.add("Cuenca");
		cities.add("Girona");
		cities.add("Granada");
		cities.add("Guadalajara");
		cities.add("Guipuzcoa");
		cities.add("Huelva");
		cities.add("Huesca");
		cities.add("Jaen");
		cities.add("La rivoja");
		cities.add("Las palmas");
		cities.add("Leon");
		cities.add("Lleida");
		cities.add("Lugo");
		cities.add("Madrid");
		cities.add("Malaga");
		cities.add("Melilla");
		cities.add("Murcia");
		cities.add("Navarra");
		cities.add("Ourense");
		cities.add("Palencia");
		cities.add("Pontevedra");
		cities.add("Salamanca");
		cities.add("Tenerife");
		cities.add("Segovia");
		cities.add("Sevilla");
		cities.add("Soria");
		cities.add("Tarragona");
		cities.add("Teruel");
		cities.add("Toledo");
		cities.add("Valencia");
		cities.add("Valladolid");
		cities.add("Vizcaya");
		cities.add("Zamora");
		cities.add("Zaragoza");
	}

	/**
	 * Método que busca las provincias cuyo nombre empieza por el string
	 * introducido por el usuario
	 * 
	 * @param query
	 *            nombre o subcadena de inicio a buscar
	 * @return Lista con las provincias en la lista que lo cumplen
	 */
	public List<String> completeCity(String query) {
		List<String> result = new ArrayList<String>();
		for (String c : cities) {
			if (c.toLowerCase().startsWith(query.toLowerCase())) {
				result.add(c);
			}
		}
		return result;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
}
