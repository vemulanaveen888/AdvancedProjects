package com.pratap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new Account(rs.getInt("accid"), rs.getString("name"), rs.getDouble("balance"));
	}

}
