package com.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CrudDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CrudDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> getAllRecords() {
		String query = "SELECT * FROM emplo";

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setDepartment(resultSet.getString("department"));
				user.setSalary(resultSet.getDouble("salary"));

				return user;
			}
		};

		List<User> users = jdbcTemplate.query(query, rowMapper);

		return users;
	}
}
