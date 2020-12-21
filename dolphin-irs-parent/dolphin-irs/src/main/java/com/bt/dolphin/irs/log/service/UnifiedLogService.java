/**
 *
 * @(#) UnifiedLogService.java
 * @Package com.bt.dolphin.irs.log.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.log.service;

import java.util.Map;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月11日 下午8:55:27   cbt-34201   Created.
 *           
 */
public interface UnifiedLogService {
	
	public Map getLog(String serviceCode,String serviceName, Map<String, Object> inParam, String logId);
	
	public void saveSucLog(Map log, Map outParam,String rtnCode);
	
	public void saveFailLog(Map log, Map outParam,String rtnCode);
	
	public void saveLog(Map log, Map outParam, String sucFlag);
}
