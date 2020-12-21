/**
 *
 * @(#) QueryController.java
 * @Package com.bt.dolphin.common.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import com.bt.dolphin.common.vo.QueryCondition;

import cn.hutool.core.util.StrUtil;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月11日 下午4:55:02 cbt-34201 Created.
 * 
 */
public abstract class QueryController<T extends QueryCondition>{

	protected abstract T initCondition();

	protected T rCondition2QCondition(HttpServletRequest request) {
		QueryCondition condition = initCondition();
		try {
			
			Map map = filterToMap(request);
		    BeanUtils.copyProperties(condition, map);
			int page = Integer.parseInt(StrUtil.toString(request.getParameter("page"))); //第几页
			if (StringUtils.hasText(request.getParameter("limit"))) {
				int limit = Integer.parseInt(StrUtil.toString(request.getParameter("limit")));//一页最大记录数
				condition.setLimit(limit);
				condition.setPageBegin((page - 1) * limit + 1);
				condition.setPageEnd(page * limit);
				condition.setRows(limit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T)condition;
	}
	
	 protected Map<String, Object> filterToMap(HttpServletRequest request){
	    Map map = new HashMap();
	    try
	    {
	    	
	      String filters = StrUtil.toString(request.getQueryString());
	      String[] firstArray = filters.split("&");
	      for (String keyValue : firstArray)
	      {
	       /* try{
	          keyValue = URLDecoder.decode(keyValue, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	          e.printStackTrace();
	        }*/

	        map.put(keyValue.substring(0, keyValue.indexOf("=")), keyValue.substring(keyValue.indexOf("=") + 1));
	      }
	    }
	    catch (Exception localException) {
	    }
	    return map;
	  }
	
}
