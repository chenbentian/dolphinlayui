/**
 *
 * @(#) NewEnergyServiceImpl.java
 * @Package com.bt.dolphin.irs.newenergy.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.newenergy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.dolphin.irs.common.constant.InterfaceConstant;
import com.bt.dolphin.irs.common.util.CoreStringTool;
import com.bt.dolphin.irs.common.util.DateTool;
import com.bt.dolphin.irs.common.util.FmtTool;
import com.bt.dolphin.irs.common.util.ZhongzlyRsaUtil;
import com.bt.dolphin.irs.newenergy.dao.NewEnergyDao;
import com.bt.dolphin.irs.newenergy.service.NewEnergyService;

import cn.hutool.json.JSONUtil;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月9日 下午6:25:01   cbt-34201   Created.
 *           
 */

@Service
@Transactional
public class NewEnergyServiceImpl implements NewEnergyService{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(NewEnergyServiceImpl.class);

	@Autowired
	private NewEnergyDao newEnergyDao;
	
	@Autowired
	ZhongzlyRsaUtil zhongzlyRsaUtil;
	
	
	public String getTest() throws Exception{
		LOGGER.info(">>>>>>任务调度getTest测试 开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		Map data = new HashMap();
		data.put("generators_val", "22");
		data.put("subsidies_val", "11");
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_REAL_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_REAL_DATA,dataJsonStr1, param);
		LOGGER.info(">>>>>>任务调度getTest测试 结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
		return JSONUtil.toJsonStr(result);
	}
	/**
	 * 1、实时推送发电量，补贴资金总额
	 */
	public void energyRealData() throws Exception{
		LOGGER.info(">>>>>> 实时推送发电量开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		Map data = new HashMap();
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		Double dayElec = newEnergyDao.getDayElec(currentDate);
		String curYM = currentDate.substring(0,6);
		Double monthElec = newEnergyDao.getMonthElec(curYM);
		String curY = currentDate.substring(0,4);
		Double yearElec = newEnergyDao.getYearElec(curY);
		Double  elecTotal = (dayElec + monthElec + yearElec)/10000;//万kw.h
		data.put("generators_val", FmtTool.keepPoint(elecTotal,2));
		Double subsidies_val = newEnergyDao.getYearGovernment(curY);
		data.put("subsidies_val", subsidies_val);
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_REAL_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_REAL_DATA,dataJsonStr1, param);
		LOGGER.info(">>>>>> 实时推送发电量结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
	}
	/**
	 * 2历年补贴资金（政府投资）带动社会投资（社会投资）
		补贴资金放大比例（社会投资/政府投资*100）
	 */
	public void energyInvestorHisData() throws Exception{
		LOGGER.info(">>>>>> 历年补贴资金（政府投资）带动社会投资（社会投资）推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		List<Map> dataList = newEnergyDao.getAllHisYearGovernment();
		if(dataList != null) {
			Map param = new HashMap();
			String dataJsonStr1= JSONUtil.toJsonStr(dataList);
			param.put("data", dataJsonStr1);
			param.put("appId", "79fa31f693d34d40980a1a5b075377af");
			param.put("event", InterfaceConstant.NEW_ENERGY_INVESTOR_HIS_DATA);
			Long timestamp = System.currentTimeMillis();
			param.put("timestamp", timestamp);
			Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_INVESTOR_HIS_DATA,dataJsonStr1, param);
			LOGGER.info(">>>>>> 历年补贴资金（政府投资）带动社会投资（社会投资）结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
		}
	}
	/**
	 * 3、今天每小时各分类累计发电量（光伏，风电，生物质）
	 */
	public void energyHourCategoryData() throws Exception{
		LOGGER.info(">>>>>> 今天每小时各分类累计发电量（光伏，风电，生物质）推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		Map cpvMap = newEnergyDao.getHourOutputByEnergyType(currentDate,"0102");//太阳能
		Map windPowerMap = newEnergyDao.getHourOutputByEnergyType(currentDate,"03");// 风能
		Map biomassMap = newEnergyDao.getHourOutputByEnergyType(currentDate,"0506");//生物质能
		String hourStr = DateTool.getCurrentDate("HH");
		if(hourStr.startsWith("0")) {
			hourStr = hourStr.substring(1, 2);
		}
		int curHour = Integer.parseInt(hourStr);
		List dataList = new ArrayList();
		for(int j = 0;j <= curHour;j++) {
			Map data = new HashMap();
			String hour = "";
			if(j < 10) {
				hour = "0" + String.valueOf(j);
			}else {
				hour =  String.valueOf(j);
			}
			data.put("hour",hour);
			String key = "P" + String.valueOf(j+1);
			data.put("cpv",CoreStringTool.getString(cpvMap.get(key)));
			data.put("windPower",CoreStringTool.getString(windPowerMap.get(key)));
			data.put("biomass",CoreStringTool.getString(biomassMap.get(key)));
			dataList.add(data);
		}
		
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(dataList);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_HOUR_CATEGORY_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_HOUR_CATEGORY_DATA,dataJsonStr1, param);
		LOGGER.info(">>>>>> 今天每小时各分类累计发电量（光伏，风电，生物质）结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
	}
	/**
	 * 4、本月每日分类累计发电量（光伏，风电，生物质）
	 */
	public void energyDayCategoryData() throws Exception{
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		String curYM = currentDate.substring(0,6);
		Map cpvMap = newEnergyDao.getDayOutputByEnergyType(curYM,"0102");//太阳能
		Map windPowerMap = newEnergyDao.getDayOutputByEnergyType(curYM,"03");// 风能
		Map biomassMap = newEnergyDao.getDayOutputByEnergyType(curYM,"0506");//生物质能
		String curTime = DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		String dayStr = curTime.substring(8,10);
		if(dayStr.startsWith("0")) {
			dayStr = dayStr.substring(1, 2);
		}
		int curDay = Integer.parseInt(dayStr);
		List dataList = new ArrayList();
		for(int j = 1;j <= curDay;j++) {
			Map data = new HashMap();
			String day = "";
			if(j < 10) {
				day = "0" + String.valueOf(j);
			}else {
				day =  String.valueOf(j);
			}
			data.put("day",day);
			String key = "P" + String.valueOf(j);
			Double cpv =  Double.parseDouble(CoreStringTool.getString(cpvMap.get(key)));
			Double cpvK = cpv/10000;
			Double windPower = Double.parseDouble(CoreStringTool.getString(windPowerMap.get(key)));
			Double windPowerK = windPower/10000;
			Double biomass = Double.parseDouble(CoreStringTool.getString(biomassMap.get(key)));
			Double biomassK = biomass/10000;
			data.put("cpv",CoreStringTool.getString(cpvK));
			data.put("windPower",CoreStringTool.getString(windPowerK));
			data.put("biomass",CoreStringTool.getString(biomassK));
			dataList.add(data);
		}
		
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(dataList);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_DAY_CATEGORY_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_DAY_CATEGORY_DATA,dataJsonStr1, param);
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
	}
	/**
	 * 5、本年度每月分类累计发电量(光伏，风电，生物质)
	 */
	public void energyMonthCategoryData() throws Exception{
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		String curY = currentDate.substring(0,4);
		Map cpvMap = newEnergyDao.getMonthOutputByEnergyType(curY,"0102");//太阳能
		Map windPowerMap = newEnergyDao.getMonthOutputByEnergyType(curY,"03");// 风能
		Map biomassMap = newEnergyDao.getMonthOutputByEnergyType(curY,"0506");//生物质能
		String curTime = DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss");
		String monthStr = curTime.substring(5,7);
		if(monthStr.startsWith("0")) {
			monthStr = monthStr.substring(1, 2);
		}
		int curMonth = Integer.parseInt(monthStr);
		List dataList = new ArrayList();
		for(int j = 1;j <= curMonth;j++) {
			Map data = new HashMap();
			String month = "";
			if(j < 10) {
				month = "0" + String.valueOf(j);
			}else {
				month =  String.valueOf(j);
			}
			data.put("month",month);
			String key = "P" + String.valueOf(j);
			Double cpv =  Double.parseDouble(CoreStringTool.getString(cpvMap.get(key)));
			Double cpvK = cpv/10000000;
			Double windPower = Double.parseDouble(CoreStringTool.getString(windPowerMap.get(key)));
			Double windPowerK = windPower/10000000;
			Double biomass = Double.parseDouble(CoreStringTool.getString(biomassMap.get(key)));
			Double biomassK = biomass/10000000;
			data.put("cpv",CoreStringTool.getString(cpvK));
			data.put("windPower",CoreStringTool.getString(windPowerK));
			data.put("biomass",CoreStringTool.getString(biomassK));
			dataList.add(data);
		}
		
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(dataList);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_MONTH_CATEGORY_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_MONTH_CATEGORY_DATA,dataJsonStr1, param);
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
	
	}
	/**
	 * 6、历年各分类发电量(光伏，风电，生物质)
	 */
	public void energyHisYearCategoryData() throws Exception{
		LOGGER.info(">>>>>> 历年各分类发电量(光伏，风电，生物质)推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		List<Map> dataList = newEnergyDao.getHisYearOutputByEnergyType();
		if(dataList != null) {
			Map param = new HashMap();
			String dataJsonStr1= JSONUtil.toJsonStr(dataList);
			param.put("data", dataJsonStr1);
			param.put("appId", "79fa31f693d34d40980a1a5b075377af");
			param.put("event", InterfaceConstant.NEW_ENERGY_YEAR_CATEGORY_DATA);
			Long timestamp = System.currentTimeMillis();
			param.put("timestamp", timestamp);
			Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_YEAR_CATEGORY_DATA,dataJsonStr1, param);
			LOGGER.info(">>>>>> 历年各分类发电量(光伏，风电，生物质)结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
		}
	}
	
	/**
	 * 10、当年发电量（光伏，风电，生物质）
	 */
	public void energyCurYearCategoryData() throws Exception{
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）推送开始 >>>>>>:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		String curY = currentDate.substring(0,4);
		Double cpv = newEnergyDao.getCurYearOutputByEnergyType(curY,"0102");//太阳能
		Double windPower = newEnergyDao.getCurYearOutputByEnergyType(curY,"03");// 风能
		Double biomass = newEnergyDao.getCurYearOutputByEnergyType(curY,"0506");//生物质能
		
		Map data = new HashMap();
		data.put("cpv",CoreStringTool.getString(cpv));
		data.put("windPower",CoreStringTool.getString(windPower));
		data.put("biomass",CoreStringTool.getString(biomass));
		
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_CATEGORY_PPT);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_CATEGORY_PPT,dataJsonStr1, param);
		LOGGER.info(">>>>>> 本月每日分类累计发电量（光伏，风电，生物质）结束 >>>>>>:{},推送结果:{}",DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"),result);
	}
	/**
	 * 7、本年各区发电量
		占比公式(城区发电量/本年总累计 *100)
	 */
	public void energyYearAreaData() throws Exception{
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		String curY = currentDate.substring(0,4);
		List<Map> areaList = newEnergyDao.getAreaIdList();
		double tCap = 0.0D;
		
		Map data = new HashMap();
		List areaDataList = new ArrayList();
		if(areaList !=null && areaList.size() > 0) {
			for(Map areaMap :areaList) {
				Map areaData = new HashMap();
				String areaId = CoreStringTool.getString(areaMap.get("value"));
				double yearAreaElec = newEnergyDao.getYearAreaElec(curY, areaId);
				tCap += yearAreaElec;
				String areaName = CoreStringTool.getString(areaMap.get("name"));
				areaData.put("area", areaName);
				areaData.put("elec_val", yearAreaElec);
				areaDataList.add(areaData);
			}
		}
		data.put("total_val", CoreStringTool.getString(tCap));
		data.put("area_data", areaDataList);
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_YEAR_AREA_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_YEAR_AREA_DATA,dataJsonStr1, param);
	}
	/**
	 * 8、本月各区发电量
	占比公式(城区发电量/本日总累计 *100)
	 */
	public void energyMonthAreaData() throws Exception{
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		String curYM = currentDate.substring(0,6);
		List<Map> areaList = newEnergyDao.getAreaIdList();
		double tCap = 0.0D;
		
		Map data = new HashMap();
		List areaDataList = new ArrayList();
		if(areaList !=null && areaList.size() > 0) {
			for(Map areaMap :areaList) {
				Map areaData = new HashMap();
				String areaId = CoreStringTool.getString(areaMap.get("value"));
				double yearAreaElec = newEnergyDao.getMonthAreaElec(curYM, areaId);
				tCap += yearAreaElec;
				String areaName = CoreStringTool.getString(areaMap.get("name"));
				areaData.put("area", areaName);
				areaData.put("elec_val", yearAreaElec);
				areaDataList.add(areaData);
			}
		}
		data.put("total_val", CoreStringTool.getString(tCap));
		data.put("area_data", areaDataList);
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_MONTH_AREA_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_MONTH_AREA_DATA,dataJsonStr1, param);
	}
	/**
	 * 9、今日各区发电量
	占比公式(城区发电量/本日总累计 *100)
	 */
	public void energyDayAreaData() throws Exception{
		String currentDate = DateTool.getCurrentDate("yyyyMMdd");
		List<Map> areaList = newEnergyDao.getAreaIdList();
		double tCap = 0.0D;
		
		Map data = new HashMap();
		List areaDataList = new ArrayList();
		if(areaList !=null && areaList.size() > 0) {
			for(Map areaMap :areaList) {
				Map areaData = new HashMap();
				String areaId = CoreStringTool.getString(areaMap.get("value"));
				double yearAreaElec = newEnergyDao.getDayAreaElec(currentDate, areaId);
				tCap += yearAreaElec;
				String areaName = CoreStringTool.getString(areaMap.get("name"));
				areaData.put("area", areaName);
				areaData.put("elec_val", yearAreaElec);
				areaDataList.add(areaData);
			}
		}
		data.put("total_val", CoreStringTool.getString(tCap));
		data.put("area_data", areaDataList);
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", InterfaceConstant.NEW_ENERGY_DAY_AREA_DATA);
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		Map result = zhongzlyRsaUtil.encryptBase64AndInvokePost(InterfaceConstant.NEW_ENERGY_DAY_AREA_DATA,dataJsonStr1, param);
	}
	
	
	

	/*public static void main(String[] args) {
		Map data = new HashMap();
		data.put("generators_val", "4213.21");
		data.put("subsidies_val", "4123213");
		Map param = new HashMap();
        String dataJsonStr1= JSONUtil.toJsonStr(data);
		param.put("data", dataJsonStr1);
		param.put("appId", "79fa31f693d34d40980a1a5b075377af");
		param.put("event", "NEW_ENERGY_REAL_DATA");
		Long timestamp = System.currentTimeMillis();
		param.put("timestamp", timestamp);
		//ZhongzlyRsaUtil(dataJsonStr1, param);
		Map result = PtccsRestUtils.invokePost("http://cockpit.ngrok.zhongzly.com/v1/event/data", param);
        System.out.println("接口调用结果： "+result);
	}*/
}
