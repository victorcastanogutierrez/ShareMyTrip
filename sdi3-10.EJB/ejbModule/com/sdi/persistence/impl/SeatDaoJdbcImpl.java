package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class SeatDaoJdbcImpl implements SeatDao {

	/**
	 * {@inheritDoc}
	 */
	public class SeatMapper implements RowMapper<Seat> {

		@Override
		public Seat toObject(ResultSet rs) throws SQLException {
			Seat res = new Seat();
			
			res.setUserId( rs.getLong("user_id") );
			res.setTripId( rs.getLong("trip_id") );
			res.setComment( rs.getString("comment") );
			res.setStatus( SeatStatus.values()[ rs.getInt("status") ] );
			
			return res;
		}

	}
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long[] save(Seat dto) {
		jdbcTemplate.execute("SEAT_INSERT", 
				dto.getUserId(), 
				dto.getTripId(), 
				dto.getComment(),
				dto.getStatus().ordinal()
			);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Seat dto) {
		return jdbcTemplate.execute("SEAT_UPDATE", 
				dto.getComment(),
				dto.getStatus().ordinal(),
				dto.getUserId(), 
				dto.getTripId()
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Long[] ids) {
		return jdbcTemplate.execute("SEAT_DELETE", ids[0], ids[1]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Seat findById(Long[] ids) {
		return jdbcTemplate.queryForObject(
				"SEAT_FIND_BY_ID", 
				new SeatMapper(), 
				ids[0], ids[1]
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Seat> findAll() {
		return jdbcTemplate.queryForList("SEAT_FIND_ALL", new SeatMapper());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Seat findByUserAndTrip(Long userId, Long tripId) {
		return jdbcTemplate.queryForObject(
				"SEAT_FIND_BY_ID", 
				new SeatMapper(), 
				userId, tripId
			);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Seat> findByTripStatusAccepted(Long tripId) {
		return jdbcTemplate.queryForList(
				"SEAT_FIND_BY_TRIP_ID", 
				new SeatMapper(), 
				tripId
			);
	}

	@Override
	public List<Seat> findByUserId(Long id) {
		return jdbcTemplate.queryForList(
				"SEAT_FIND_ALL_BY_USER_ID", 
				new SeatMapper(), 
				id
			);
	}
}
