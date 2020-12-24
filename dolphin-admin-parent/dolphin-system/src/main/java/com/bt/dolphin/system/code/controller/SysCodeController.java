/**
 *
 * @(#) SysCodeController.java
 * @Package com.bt.dolphin.system.code.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.code.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.vo.WrappedResult;
import com.bt.dolphin.system.code.api.SysCodeService;
import com.bt.dolphin.system.code.vo.CodeVo;

import cn.hutool.core.util.StrUtil;

/**
 *  类描述：标准代码
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月21日 下午4:13:50   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysCode")
public class SysCodeController {
	private static final Logger logger = LoggerFactory.getLogger(SysCodeController.class);
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@RequestMapping(value = "/getCodeList")
	public  @ResponseBody WrappedResult getCodeList(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		try{
			String codeType = param.get("codeType");
			//String preAttr1 = StrUtil.toString(param.get("preAttr1"));
			List<CodeVo> codeList = sysCodeService.getStandardCodes(codeType);
			/*if(!StringUtils.isEmpty(preAttr1)&& CollectionUtils.isNotEmpty(codeList)){
				List<CodeVo> ret=new ArrayList<>();
				for(CodeVo bo:codeList){
					if(preAttr1.equals(bo.getPreAttr1())){
						ret.add(bo);
					}
				}
				codeList=ret;
			}*/
			logger.info("获取标准代码成功："+codeType);
			return WrappedResult.successWrapedResult(codeList);
		}catch (Exception e) {
			logger.error("获取标准代码失败",e);
			return  WrappedResult.failedWrappedResult("获取标准代码失败!");
		}
	}
	
}
