package com.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sdi.model.SeatStatus;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.util.JdbcTemplate;
import com.sdi.persistence.util.RowMapper;

/**
 * {@inheritDoc}
 */
public class UserDaoJdbcImpl implements UserDao {

	public class UserMapper implements RowMapper<User> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public User toObject(ResultSet rs) throws SQLException {
			User res = new User();

			res.setId(rs.getLong("id"));
			res.setLogin(rs.getString("login"));
			res.setPassword(rs.getString("password"));
			res.setName(rs.getString("name"));
			res.setSurname(rs.getString("surname"));
			res.setEmail(rs.getString("email"));
			res.setStatus(UserStatus.values()[rs.getInt("status")]);

			return res;
		}

	}

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long save(User dto) {
		jdbcTemplate.execute("USER_INSERT", dto.getLogin(), dto.getPassword(),
				dto.getName(), dto.getSurname(), dto.getEmail(), dto
						.getStatus().ordinal() // enum saved as integer
				);
		return jdbcTemplate.getGeneratedKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(User dto) {
		return jdbcTemplate.execute("USER_UPDATE", dto.getLogin(),
				dto.getPassword(), dto.getName(), dto.getSurname(),
				dto.getEmail(), dto.getStatus().ordinal(), dto.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("USER_DELETE", id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findById(Long id) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_ID", new UserMapper(),
				id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAll() {
		return jdbcTemplate.queryForList("USER_FIND_ALL", new UserMapper());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByLogin(String login) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_LOGIN",
				new UserMapper(), login);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findByTrip(Long trip_id) {
		return jdbcTemplate.queryForList("USER_FIND_BY_TRIP", new UserMapper(),
				trip_id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findByTripAndApplicationPending(Long id) {
		return jdbcTemplate.queryForList(
				"USER_FIND_BY_TRIP_APPLICATION_PENDING", new UserMapper(), id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findByTripAndSeat(Long id, SeatStatus status) {
		return jdbcTemplate.queryForList("USER_FIND_BY_TRIP_SEAT",
				new UserMapper(), id, status.ordinal());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("USER_FIND_BY_EMAIL",
				new UserMapper(), email);
	}

	@Override
	public List<User> findAllActive() {
		return jdbcTemplate.queryForList("USER_FIND_ALL_ACTIVE",
				new UserMapper());
	}

}
