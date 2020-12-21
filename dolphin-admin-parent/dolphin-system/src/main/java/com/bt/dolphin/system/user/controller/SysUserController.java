/**
 *
 * @(#) SysUserController.java
 * @Package com.bt.dolphin.system.user.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import com.bt.dolphin.common.controller.QueryController;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.user.api.SysUserService;
import com.bt.dolphin.system.user.vo.SysUserCondition;
import com.bt.dolphin.system.user.vo.SysUserVo;

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
@RequestMapping(value = "/dolpin/system")
public class SysUserController extends QueryController<SysUserCondition> {

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

	@Override
	protected SysUserCondition initCondition() {
		// TODO Auto-generated method stub
		return new SysUserCondition();
	}

	
}
