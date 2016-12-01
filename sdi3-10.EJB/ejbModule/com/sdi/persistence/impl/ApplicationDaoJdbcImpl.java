package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.Application;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

/**
 * {@inheritDoc}
 */
public class ApplicationDaoJdbcImpl implements ApplicationDao {

	/**
	 * {@inheritDoc}
	 */
	public class ApplicationMapper implements RowMapper<Application> {

		@Override
		public Application toObject(ResultSet rs) throws SQLException {
			Application res = new Application();
			res.setUserId( rs.getLong("applicants_id") );
			res.setTripId( rs.getLong("appliedtrips_id") );
			return res;
		}

	}
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long[] save(Application dto) {
		jdbcTemplate.execute("APPLICATION_INSERT", 
				dto.getUserId(), 
				dto.getTripId() 
			);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Application dto) {
		throw new RuntimeException("This method is not applicable for this dto");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Long[] ids) {
		return jdbcTemplate.execute("APPLICATION_DELETE", 
				ids[0], ids[1] 
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Application findById(Long[] ids) {
		return jdbcTemplate.queryForObject(
				"APPLICATION_FIND_BY_ID", 
				new ApplicationMapper(), 
				ids[0], ids[1]
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Application> findAll() {
		return jdbcTemplate.queryForList(
				"APPLICATION_FIND_ALL", 
				new ApplicationMapper()
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Application> findByUserId(Long userId) {
		return jdbcTemplate.queryForList(
				"APPLICATION_FIND_BY_USER_ID", 
				new ApplicationMapper(), 
				userId
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Application> findByTripId(Long tripId) {
		return jdbcTemplate.queryForList(
				"APPLICATION_FIND_BY_TRIP_ID", 
				new ApplicationMapper(), 
				tripId
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Application> findNoSeat(String fechaHoy) {
		return jdbcTemplate.queryForList(
				"APPLICATION_FIND_BY_NO_SEAT", 
				new ApplicationMapper(),fechaHoy
			);
	}

}
