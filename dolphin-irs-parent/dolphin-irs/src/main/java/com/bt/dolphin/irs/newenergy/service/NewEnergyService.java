/**
 *
 * @(#) NewEnergyService.java
 * @Package com.bt.dolphin.irs.newenergy.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.service;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月9日 下午6:24:43   cbt-34201   Created.
 *           
 */
public interface NewEnergyService {
	
	public String getTest() throws Exception;
	/**
	 * 1、实时推送发电量，补贴资金总额
	 */
	public void energyRealData() throws Exception;
	/**
	 * 2历年补贴资金（政府投资）带动社会投资（社会投资）
		补贴资金放大比例（社会投资/政府投资*100）
	 */
	public void energyInvestorHisData() throws Exception;
	/**
	 * 3、今天每小时各分类累计发电量（光伏，风电，生物质）
	 */
	public void energyHourCategoryData() throws Exception;
	/**
	 * 4、本月每日分类累计发电量（光伏，风电，生物质）
	 */
	public void energyDayCategoryData() throws Exception;
	/**
	 * 5、本年度每月分类累计发电量(光伏，风电，生物质)
	 */
	public void energyMonthCategoryData() throws Exception;
	/**
	 * 6、历年各分类发电量(光伏，风电，生物质)
	 */
	public void energyHisYearCategoryData() throws Exception;
	/**
	 * 10、发电量分类占比（光伏，风电，生物质）
	 */
	public void energyCurYearCategoryData() throws Exception;
	/**
	 * 7、本年各区发电量
	占比公式(城区发电量/本年总累计 *100)
	 */
	public void energyYearAreaData() throws Exception;
	/**
	 * 8、本月各区发电量
	占比公式(城区发电量/本日总累计 *100)
	 */
	public void energyMonthAreaData() throws Exception;
	/**
	 * 9、今日各区发电量
	占比公式(城区发电量/本日总累计 *100)
	 */
	public void energyDayAreaData() throws Exception;
	
}
