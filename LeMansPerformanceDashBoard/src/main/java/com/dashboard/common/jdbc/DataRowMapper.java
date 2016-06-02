package com.dashboard.common.jdbc;



import java.sql.ResultSet;
import java.sql.SQLException;
import  org.springframework.jdbc.core.RowMapper;


public class DataRowMapper implements RowMapper<DATA>{

	@Override
	public DATA mapRow(ResultSet resultSet, int line) throws SQLException {
		
		Extractor userExtractor = new Extractor();
		return userExtractor.extractData(resultSet);
	}
	

}
