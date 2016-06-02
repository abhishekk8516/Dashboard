package com.dashboard.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.dashboard.common.dao.DashboardDao;
import com.dashboard.common.model.Data;

public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardDao dashboardDao;

	@Override
	public Data getCountStatic(String from, String to, int daysBefore) {
		Data data = new Data();
		data =	dashboardDao.getStaticCount(from, to, daysBefore);
		return data;
	}

}
