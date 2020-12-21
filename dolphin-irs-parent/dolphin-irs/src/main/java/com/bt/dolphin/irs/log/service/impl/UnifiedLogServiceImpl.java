/**
 *
 * @(#) UnifiedLogServiceImpl.java
 * @Package com.bt.dolphin.irs.log.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.log.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bt.dolphin.irs.common.util.CoreStringTool;
import com.bt.dolphin.irs.common.util.DateTool;
import com.bt.dolphin.irs.common.util.IrsSeqUtil;
import com.bt.dolphin.irs.common.util.JsonUtil;
import com.bt.dolphin.irs.common.util.thread.IrsThreadManger;
import com.bt.dolphin.irs.log.dao.IUnifiedLogDao;
import com.bt.dolphin.irs.log.service.UnifiedLogService;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月11日 下午8:59:08   cbt-34201   Created.
 *           
 */

@Service
@Transactional
public class UnifiedLogServiceImpl implements UnifiedLogService{
	
	@Autowired
	private IUnifiedLogDao unifiedLogDao;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UnifiedLogServiceImpl.class);
	
	public Map getLog(String serviceCode,String serviceName, Map<String, Object> inParam, String logId){
		Map  log = new HashMap();
		log.put("interServiceName", serviceCode);
		log.put("requestTime", DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		log.put("interName", serviceName);
		String inParamStr = JsonUtil.toJSONString(inParam);
		if (inParamStr.length()>4000) {
			inParamStr = "";
		}
		log.put("inParam", inParamStr);
		log.put("appNo", logId);
		return log;
	}
	
	@Override
	public void saveSucLog(Map log, Map outParam,String rtnCode) {
		 saveLog(log, outParam,rtnCode);
	}
	
	@Override
	public void saveFailLog(Map log, Map outParam,String rtnCode) {
		 saveLog(log, outParam,rtnCode);
	}
	
	@Override
	public void saveLog(Map log, Map outParam, String sucFlag) {
		String appNo = "";
		if (!outParam.isEmpty()) {
			appNo = CoreStringTool.getString(outParam.get("appNo"));
		}
		log.put("endTime", DateTool.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		log.put("sucFlag", sucFlag);
		log.put("appNo",appNo );
		log.put("outParam",JsonUtil.toJSONString(outParam));
		saveInterfaceLog(log);
	}
	
	public void saveInterfaceLog(Map log){
		final Map log0 = log;
		try {
			IrsThreadManger.threadPoolExecutor.execute(new Runnable(){
				public void run() {
					log0.put("logId", IrsSeqUtil.uuidS());
					String outParam = CoreStringTool.getString(log0.get("outParam"));
					outParam = CoreStringTool.subStr(outParam, 4000);
					log0.put("outParam", outParam);
					String inParam = CoreStringTool.getString(log0.get("inParam"));
					try {
						if(inParam.getBytes("utf-8").length >4000){
							log0.put("tempParam", inParam);
						}
					} catch (UnsupportedEncodingException e) {
						LOGGER.error("", e);
					}
					
					unifiedLogDao.saveLog(log0);
				}
			});
		} catch (Exception e) {
			LOGGER.error("接口日志保存失败!", e);
		}
	}
}
