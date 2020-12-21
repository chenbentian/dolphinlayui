/**
 *
 * @(#) NewEnergyController.java
 * @Package com.bt.dolphin.irs.newenergy.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.irs.newenergy.service.NewEnergyService;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年8月9日 下午6:31:58 cbt-34201 Created.
 * 
 */

@Controller
@RequestMapping("/newEnergyController")
public class NewEnergyController {

	@Autowired
	NewEnergyService newEnergyService;

	@RequestMapping("/test")
	public @ResponseBody String putCache(HttpServletRequest request) throws Exception{
		return newEnergyService.getTest();
	}
	
	@RequestMapping("/energyRealData")
	public @ResponseBody String energyRealData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyRealData();
		 return "实时发电量推送成功";
	}
	
	@RequestMapping("/energyInvestorHisData")
	public @ResponseBody String energyInvestorHisData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyInvestorHisData();
		 return "历年投资金额累计推送成功";
	}
	
	@RequestMapping("/energyHourCategoryData")
	public @ResponseBody String energyHourCategoryData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyHourCategoryData();
		 return "今天每小时各分类累计发电量（光伏，风电，生物质）推送成功";
	}
	
	@RequestMapping("/energyDayCategoryData")
	public @ResponseBody String energyDayCategoryData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyDayCategoryData();
		 return "本月每日分类累计发电量（光伏，风电，生物质）推送成功";
	}
	

	@RequestMapping("/energyMonthCategoryData")
	public @ResponseBody String energyMonthCategoryData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyMonthCategoryData();
		 return "本年度每月分类累计发电量（光伏，风电，生物质）推送成功";
	}
	
	@RequestMapping("/energyHisYearCategoryData")
	public @ResponseBody String energyHisYearCategoryData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyHisYearCategoryData();
		 return "历年各分类发电量(光伏，风电，生物质)推送成功";
	}
	
	@RequestMapping("/energyCurYearCategoryData")
	public @ResponseBody String energyCurYearCategoryData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyCurYearCategoryData();
		 return "当年发电量（光伏，风电，生物质）推送成功";
	}
	
	@RequestMapping("/energyYearAreaData")
	public @ResponseBody String energyYearAreaData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyYearAreaData();
		 return "本年各区发电量推送成功";
	}
	
	@RequestMapping("/energyMonthAreaData")
	public @ResponseBody String energyMonthAreaData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyMonthAreaData();
		 return "本月各区发电量推送成功";
	}
	
	@RequestMapping("/energyDayAreaData")
	public @ResponseBody String energyDayAreaData(HttpServletRequest request) throws Exception{
		 newEnergyService.energyDayAreaData();
		 return "本日各区发电量推送成功";
	}
}
