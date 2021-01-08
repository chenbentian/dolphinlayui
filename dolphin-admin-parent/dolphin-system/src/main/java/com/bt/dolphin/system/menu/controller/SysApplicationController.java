/**
 *
 * @(#) SysApplicationController.java
 * @Package com.bt.dolphin.system.menu.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.vo.WrappedResult;
import com.bt.dolphin.system.menu.api.SysApplicationService;
import com.bt.dolphin.system.menu.vo.SysApplicationVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月29日 下午3:56:59   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysApplication")
public class SysApplicationController {
	private static final Logger logger = LoggerFactory.getLogger(SysApplicationController.class);
	
	@Autowired
	private SysApplicationService sysApplicationService;
	
	
	@RequestMapping(value = "/getAppNameList")
	public  @ResponseBody WrappedResult getAppNameList(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		List<SysApplicationVo> allApp = sysApplicationService.getAllApplication();
		return WrappedResult.successWrapedResult(allApp);
	}
}
