package com.dashboard.common.dao;

import com.dashboard.common.model.Data;


public interface DashboardDao {
	public  Data getStaticCount(String from,String to,int daysBefore);
}
