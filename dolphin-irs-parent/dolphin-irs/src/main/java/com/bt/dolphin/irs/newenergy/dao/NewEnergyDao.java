/**
 *
 * @(#) NewEnergyDao.java
 * @Package com.bt.dolphin.irs.newenergy.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年8月9日 下午6:25:35 cbt-34201 Created.
 * 
 */
public interface NewEnergyDao {

	public String getTest(@Param("nodeId") String nodeId);

	// 获取当天发电量
	public Double getDayElec(@Param("currentDate") String currentDate);

	// 获取当月发电量
	public Double getMonthElec(@Param("curYM") String curYM);

	// 获取当年发电量
	public Double getYearElec(@Param("curY") String curY);

	// 获取一天内，太阳能、风能、生物质能每小时发电量
	public Map getHourOutputByEnergyType(@Param("date") String date, @Param("elecType") String elecType);

	// 本月每日分类累计发电量（光伏，风电，生物质
	public Map getDayOutputByEnergyType(@Param("curYM") String curYM, @Param("elecType") String elecType);
	//本年度每月分类累计发电量(光伏，风电，生物质)
	public Map getMonthOutputByEnergyType(@Param("curY") String curY, @Param("elecType") String elecType);
	//历年各分类发电量(光伏，风电，生物质)
	public List<Map> getHisYearOutputByEnergyType();
	//当年发电量（光伏，风电，生物质）
	public Double getCurYearOutputByEnergyType(@Param("curY") String curY, @Param("elecType") String elecType);
	//获取区域
	public List<Map> getAreaIdList();
	//获取当年各区域发电量 
	public Double getYearAreaElec(@Param("curY") String curY, @Param("areaId") String areaId);
	//本月各区发电量
	public Double getMonthAreaElec(@Param("curYM") String curYM, @Param("areaId") String areaId);
	//本日各区发电量
	public Double getDayAreaElec(@Param("currentDate") String currentDate, @Param("areaId") String areaId);
	//本年政府补贴金额
	public Double getYearGovernment(@Param("curY") String curY);
	//历年投资金额累计
	public List<Map> getAllHisYearGovernment();
}
