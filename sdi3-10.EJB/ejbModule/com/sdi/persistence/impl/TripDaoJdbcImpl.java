package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sdi.model.AddressPoint;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.Waypoint;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

public class TripDaoJdbcImpl implements TripDao {

	public class TripMapper implements RowMapper<Trip> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Trip toObject(ResultSet rs) throws SQLException {
			Trip res = new Trip();

			Waypoint wpt = new Waypoint(rs.getDouble("departure_wpt_lat"),
					rs.getDouble("departure_wpt_lon"));
			AddressPoint ap = new AddressPoint(
					rs.getString("departure_address"),
					rs.getString("departure_city"),
					rs.getString("departure_state"),
					rs.getString("departure_country"),
					rs.getString("departure_zipcode"), wpt);
			res.setDeparture(ap);

			wpt = new Waypoint(rs.getDouble("destination_wpt_lat"),
					rs.getDouble("destination_wpt_lon"));
			ap = new AddressPoint(rs.getString("destination_address"),
					rs.getString("destination_city"),
					rs.getString("destination_state"),
					rs.getString("destination_country"),
					rs.getString("destination_zipcode"), wpt);
			res.setDestination(ap);

			res.setArrivalDate(toDate(rs.getTimestamp("arrivalDate")));
			res.setDepartureDate(toDate(rs.getTimestamp("departureDate")));
			res.setClosingDate(toDate(rs.getTimestamp("closingDate")));

			res.setAvailablePax(rs.getInt("availablePax"));
			res.setMaxPax(rs.getInt("maxPax"));
			res.setComments(rs.getString("comments"));
			res.setEstimatedCost(rs.getDouble("estimatedCost"));
			res.setPromoterId(rs.getLong("promoter_Id"));
			res.setStatus(TripStatus.values()[rs.getInt("status")]);
			res.setId(rs.getLong("id"));

			return res;
		}

		private Date toDate(Timestamp timestamp) throws SQLException {
			return new Date(timestamp.getTime());
		}

	}

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long save(Trip dto) {
		jdbcTemplate.execute("TRIP_INSERT", dto.getDeparture().getAddress(),
				dto.getDeparture().getCity(), dto.getDeparture().getState(),
				dto.getDeparture().getCountry(), dto.getDeparture()
						.getZipCode(), dto.getDeparture().getWaypoint()
						.getLat(), dto.getDeparture().getWaypoint().getLon(),

				dto.getDestination().getAddress(), dto.getDestination()
						.getCity(), dto.getDestination().getState(), dto
						.getDestination().getCountry(), dto.getDestination()
						.getZipCode(), dto.getDestination().getWaypoint()
						.getLat(), dto.getDestination().getWaypoint().getLon(),

				dto.getArrivalDate(), dto.getDepartureDate(), dto
						.getClosingDate(), dto.getAvailablePax(), dto
						.getMaxPax(), dto.getEstimatedCost(),
				dto.getComments(), dto.getStatus().ordinal(), dto
						.getPromoterId());
		return jdbcTemplate.getGeneratedKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Trip dto) {
		return jdbcTemplate.execute("TRIP_UPDATE", dto.getDeparture()
				.getAddress(), dto.getDeparture().getCity(), dto.getDeparture()
				.getState(), dto.getDeparture().getCountry(), dto
				.getDeparture().getZipCode(), dto.getDeparture().getWaypoint()
				.getLat(), dto.getDeparture().getWaypoint().getLon(),

		dto.getDestination().getAddress(), dto.getDestination().getCity(), dto
				.getDestination().getState(),
				dto.getDestination().getCountry(), dto.getDestination()
						.getZipCode(), dto.getDestination().getWaypoint()
						.getLat(), dto.getDestination().getWaypoint().getLon(),

				dto.getArrivalDate(), dto.getDepartureDate(), dto
						.getClosingDate(), dto.getAvailablePax(), dto
						.getMaxPax(), dto.getEstimatedCost(),
				dto.getComments(), dto.getStatus().ordinal(), dto
						.getPromoterId(),

				dto.getId());
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("TRIP_DELETE", id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trip findById(Long id) {
		return jdbcTemplate.queryForObject("TRIP_FIND_BY_ID", new TripMapper(),
				id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findAll() {
		return jdbcTemplate.queryForList("TRIP_FIND_ALL", new TripMapper());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate) {
		return jdbcTemplate.queryForObject("TRIP_FIND_BY_PROMOTER_AND_ARRIVAL",
				new TripMapper(), id, arrivalDate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsPendingPromoter(TripStatus open, Long id,
			String fechaHoy) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_ALL_BY_STATUS_AND_ID_PROMOTER_PENDING_DATE",
				new TripMapper(), open.ordinal(), id, fechaHoy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsPendingApplicantExcludedOrAccepted(
			TripStatus open, Long id, SeatStatus state, String fechaHoy) {
		return jdbcTemplate
				.queryForList(
						"TRIP_FIND_ALL_BY_STATUS_AND_ID_STATE_APPLICANT_DATE",
						new TripMapper(), id, open.ordinal(), state.ordinal(),
						fechaHoy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsPendingApplicantPending(TripStatus open,
			Long id, String fechaHoy) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_ALL_BY_STATUS_AND_ID_PENDING_APPLICANT_DATE",
				new TripMapper(), id, open.ordinal(), fechaHoy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsAvailableToApply(TripStatus open, String fechaHoy) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_ALL_STATE_DATE_AVAILABLEPAX", new TripMapper(),
				open.ordinal(), fechaHoy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsPendingApplicantPendingNoSeat(SeatStatus open,
			Long id) {
		return jdbcTemplate.queryForList(
				"TRIP_FIND_ALL_BY_STATUS_AND_ID_PENDING_APPLICANT_DATE_SEAT",
				new TripMapper(), id, open.ordinal());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsCanceled(TripStatus cancelled, long id) {
		return jdbcTemplate.queryForList("TRIP_FIND_ALL_BY_STATUS",
				new TripMapper(), cancelled.ordinal(), id, id, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Trip> findTripsToClose(String fechaHoy) {
		return jdbcTemplate.queryForList("TRIP_FIND_TO_CLOSE",
				new TripMapper(), fechaHoy);
	}

	@Override
	public List<Trip> findByPromoterId(Long id) {
		return jdbcTemplate.queryForList("TRIP_FIND_PROMOTER",
				new TripMapper(), id);
	}

	@Override
	public List<Trip> findByParticipated(Long id) {
		return jdbcTemplate.queryForList("TRIP_FIND_PARTICIPATED",
				new TripMapper(),id);
	}

	@Override
	public List<Trip> findByPromoterIdAndOpen(Long id) {
		return jdbcTemplate.queryForList("TRIP_FIND_PROMOTER_OPEN",
				new TripMapper(),id);
	}

}
