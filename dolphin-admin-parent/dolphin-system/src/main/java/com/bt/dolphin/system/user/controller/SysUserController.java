/**
 *
 * @(#) SysUserController.java
 * @Package com.bt.dolphin.system.user.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.controller.QueryController;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.common.vo.WrappedResult;
import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.code.vo.CodeVo;
import com.bt.dolphin.system.user.api.SysUserService;
import com.bt.dolphin.system.user.vo.SysUserCondition;
import com.bt.dolphin.system.user.vo.SysUserVo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 类描述：账号管理
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月10日 下午4:19:09 cbt-34201 Created.
 * 
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysUser")
public class SysUserController extends QueryController<SysUserCondition> {
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;
	
	private static String USER_MAIN = "/dolphin/system/user/userMain";

	@GetMapping("/userMain")
	public String main(Model model) {
		// 获取当前登录的用户
		SysUserVo user = ShiroUtil.getSubject();
		return USER_MAIN;
	}
	
	@RequestMapping("/userQuery")
	public @ResponseBody QueryResultObject userQuery(HttpServletResponse response,HttpServletRequest request) {
		SysUserCondition condition = this.rCondition2QCondition(request);
		QueryResultObject resultObject = new QueryResultObject();
		resultObject = sysUserService.queryUserList(condition);
		return resultObject;
	}
	/**
	 * 
	 * 方法说明：根据用户编号获取用户
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月23日 上午10:25:00
	 * History:  2020年12月23日 上午10:25:00   cbt-34201   Created.
	 *
	 * @param response
	 * @param request
	 * @param param
	 * @return
	 *
	 */
	@RequestMapping(value = "/getUserByUserId")
	public  @ResponseBody WrappedResult getUserByUserId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		try{
			String userId = StrUtil.toString(param.get("userId"));
			SysUserVo sysUserVo = sysUserService.getUserByUserId(userId);
			return WrappedResult.successWrapedResult(sysUserVo);
		}catch (Exception e) {
			logger.error("获取标准代码失败",e);
			return  WrappedResult.failedWrappedResult("获取标准代码失败!");
		}
	}
	
	@RequestMapping(value = "/saveUser")
	public  @ResponseBody WrappedResult saveUser(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		try{
			SysUserVo sysUserVo =  new SysUserVo();
			BeanUtil.copyProperties(param, sysUserVo);
			sysUserService.saveSysUser(sysUserVo);
			return WrappedResult.successWrapedResult("保存成功！");
		}catch (Exception e) {
			logger.error("获取标准代码失败",e);
			return  WrappedResult.failedWrappedResult("获取标准代码失败!");
		}
	}
	
	@RequestMapping(value = "/deleteByUserId")
	public  @ResponseBody WrappedResult deleteByUserId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		try{
			String userId = StrUtil.nullToEmpty(param.get("userId"));
			sysUserService.deleteByUserId(userId);
			return WrappedResult.successWrapedResult("保存成功！");
		}catch (Exception e) {
			logger.error("获取标准代码失败",e);
			return  WrappedResult.failedWrappedResult("获取标准代码失败!");
		}
	}

	@Override
	protected SysUserCondition initCondition() {
		// TODO Auto-generated method stub
		return new SysUserCondition();
	}

	
}
