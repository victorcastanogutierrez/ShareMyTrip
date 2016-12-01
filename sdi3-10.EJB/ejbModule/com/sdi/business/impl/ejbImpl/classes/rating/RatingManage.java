package com.sdi.business.impl.ejbImpl.classes.rating;

import com.sdi.infrastructure.Factories;

public class RatingManage {

	public void remove(Long id) {
		Factories.persistence.newRatingDao().delete(id);
	}

}
