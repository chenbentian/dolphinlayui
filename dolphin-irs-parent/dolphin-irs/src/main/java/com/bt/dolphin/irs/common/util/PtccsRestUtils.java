/**
 *
 * @(#) PtccsRestUtils.java
 * @Package com.ls.ptccs.commoncore.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.json.JSONUtil;


/**
 * 类描述：rest客户端工具
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年7月23日 上午10:16:57 cbt-34201 Created.
 * 
 */
public class PtccsRestUtils {

	private static RestTemplate restTemplate = new RestTemplate();
	/**
	 * 
	 * 方法说明：post方式调用，接口map
	 *
	 * Author：        cbt               
	 * Create Date：   2020年7月23日 上午10:28:48
	 * History:  2020年7月23日 上午10:28:48   cbt-34201   Created.
	 *
	 * @param url
	 * @param params
	 * @return
	 *
	 */
	public static Map<String, Object> invokePost(String url, Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		String paramsStr = JSONUtil.toJsonStr(params);
		JSONObject jsonObj = JSONObject.parseObject(paramsStr);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(), headers);
		ResponseEntity<Map> postExchange = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		result = (Map) postExchange.getBody();
		return result;
	}
	/**
	 * 
	 * 方法说明：get调用，url拼接参数，返回map
	 *
	 * Author：        cbt               
	 * Create Date：   2020年7月23日 上午10:29:16
	 * History:  2020年7月23日 上午10:29:16   cbt-34201   Created.
	 *
	 * @param httpMethod
	 * @param params
	 * @return
	 *
	 */
	public static Map<String, Object> invokeGet(String url) {
		Map<String, Object> result = new HashMap<String, Object>();
		ResponseEntity<Map> getExchange = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
		result = (Map) getExchange.getBody();
		return result;
	}
}
