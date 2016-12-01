package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;

/**
 * Fachada con la interfaz de métodos necesaria para las Ratings
 * 
 * @author sdi2-10
 * 
 */
public interface RatingsService {
	
	
	List<Rating> findByLastMonth();

	List<Rating> findAll();
	
	void remove(Long id);
	
}
