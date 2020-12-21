/**
 *
 * @(#) RsaUtil.java
 * @Package com.bt.dolphin.irs.common.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bt.dolphin.irs.common.constant.InterfaceConstant;
import com.bt.dolphin.irs.log.service.UnifiedLogService;
import com.bt.dolphin.irs.newenergy.job.ZhongzlyProperties;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * 类描述：驾驶舱接口
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年8月10日 下午3:59:26 cbt-34201 Created.
 * 
 */
@Component
public class ZhongzlyRsaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZhongzlyRsaUtil.class);

	@Autowired
	ZhongzlyProperties zhongzlyProperties;
	
	@Autowired
	private UnifiedLogService unifiedLogService;
	
	public Map<String, Object> encryptBase64AndInvokePost(String serviceCode,String dataJsonStr1, Map<String, Object> params) throws Exception{
		Map log = null;
		Map result =  new HashMap();
		try{
		    String md5Str = SecureUtil.md5(dataJsonStr1);
			// 拼接签名原文
			String signStr = "appId=" + CoreStringTool.getString(params.get("appId")) + "&data=" + md5Str + "&event="
					+  CoreStringTool.getString(params.get("event")) + "&timestamp=" +  CoreStringTool.getString(params.get("timestamp"));
			System.out.println("sign公钥加密前:" + signStr);
			// 公钥加密
			RSA rsaEn = new RSA(null, zhongzlyProperties.getPublicKey());
			String sign = rsaEn.encryptBase64(StrUtil.bytes(signStr, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
			System.out.println("sign公钥加密后:" + sign);
			params.put("sign", sign);
			log = unifiedLogService.getLog(serviceCode, serviceCode, params, CoreStringTool.getString(params.get("event")));
			result = PtccsRestUtils.invokePost(zhongzlyProperties.getRestUrl(), params);
			unifiedLogService.saveSucLog(log, result,InterfaceConstant.RTN_CODE_VALUE_SUCC1);
		}catch(Exception e){
			LOGGER.error("接口" + serviceCode  +"连接WS异常:", e);
			result = this.failResultList(serviceCode + "接口调用失败，失败原因为：" + e.getMessage());
			unifiedLogService.saveFailLog(log, result,InterfaceConstant.RTN_CODE_VALUE_FAIL0);
			throw new Exception("接口调用失败",e);
		}
		return result;
	}
	
	
	public Map failResultList(String errorMessage){
    	Map map =  new HashMap();
    	map.put("rtnCode", InterfaceConstant.RTN_CODE_VALUE_FAIL0);
    	map.put("rtnMsg", errorMessage);
    	return map;
	}


}
