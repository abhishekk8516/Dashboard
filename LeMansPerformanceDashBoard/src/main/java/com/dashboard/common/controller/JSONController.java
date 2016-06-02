package com.dashboard.common.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dashboard.common.model.Data;
import com.dashboard.common.services.DashboardService;


@Controller
public class JSONController {
	
	@Autowired
	private DashboardService dashboard;

	/*@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	Shop getShopInJSON(@PathVariable String name) {

		Shop shop = new Shop();
		shop.setName(name);
		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });

		return shop;

	}*/
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public   @ResponseBody
	Data listContact(@PathVariable String from,String to, int daysBefore) throws IOException{
		Data data = new Data();
		data =	dashboard.getCountStatic(from, to, daysBefore);
		return data;
	}

}