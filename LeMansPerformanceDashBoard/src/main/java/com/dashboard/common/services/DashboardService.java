package com.dashboard.common.services;

import com.dashboard.common.model.Data;

public interface DashboardService {

	public Data getCountStatic(String from, String to, int daysBefore);
}
