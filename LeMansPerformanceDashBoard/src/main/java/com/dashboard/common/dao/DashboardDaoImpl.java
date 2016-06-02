package com.dashboard.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dashboard.common.model.Data;



public class DashboardDaoImpl implements DashboardDao {
	@Autowired
	DataSource dataSource;

	@Override
	public Data getStaticCount(String from, String to, int daysBefore) {
		Data data = new Data();


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int daydiff = -1;
		int daydiff1 = -1;

		Calendar calT = Calendar.getInstance();
		String today = dateFormat.format(calT.getTime());
		String[] weekdays = new DateFormatSymbols().getWeekdays();
		String weekday = weekdays[calT.get(Calendar.DAY_OF_WEEK)];

		if (weekday.toUpperCase().equals("MONDAY")) {
			if (from.equals("05")) {
				String q = "select COUNT(*) from VTW_MSG_COUNT " + "where itemcode is not null";
				data=getList(q);

				// jdbcTemplate.query(q, new DataRowMapper());

			}
		} else {
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DATE, daydiff);
			String yesterday = dateFormat.format(cal2.getTime());
			String date = yesterday.split(" ")[0];
			String f = date + " " + from + ":30:00";

			String t = date + " " + to + ":30:00";
			if (Integer.parseInt(to) == 5 && Integer.parseInt(from) == 23) {
				date = cal2.get(Calendar.YEAR) + "-0" + (cal2.get(Calendar.MONTH) + 1) + "-"
						+ (cal2.get(Calendar.DATE) + 1);
				t = date + " " + to + ":30:00";
			}
			String q = "select COUNT(*) from VTW_MSG_COUNT v " + "where BUCKET_TIME BETWEEN '" + f + "' AND '" + t
					+ "' and itemcode is not null";
			data=getList(q);
			// jdbcTemplate.query(q,new DataRowMapper());

		}
		return data;

	}

	private Data getList(String sql) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Data> listData = jdbcTemplate.query(sql, new RowMapper<Data>() {

			@Override
			public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
				Data data = new Data();
				data.setCount(rs.getInt(0));
				return data;
			}
		});
		
		return listData.get(0);
	}

}
