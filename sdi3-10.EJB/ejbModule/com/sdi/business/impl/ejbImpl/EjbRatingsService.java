package com.sdi.business.impl.ejbImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.ejbImpl.classes.rating.RatingFind;
import com.sdi.business.impl.ejbImpl.classes.rating.RatingManage;
import com.sdi.business.impl.ejbLocal.LocalRatingsService;
import com.sdi.business.impl.ejbRemote.RemoteRatingsService;
import com.sdi.model.Rating;

/**
 * {@inheritDoc}
 */
@Stateless
@WebService(name="RatingsService")
public class EjbRatingsService implements LocalRatingsService,RemoteRatingsService {

	@Override
	public List<Rating> findByLastMonth() {
		return new RatingFind().findByLastMonth();
	}

	@Override
	public List<Rating> findAll() {
		return new RatingFind().findAll();
	}

	@Override
	public void remove(Long id) {
		new RatingManage().remove(id);
		
	}

}
