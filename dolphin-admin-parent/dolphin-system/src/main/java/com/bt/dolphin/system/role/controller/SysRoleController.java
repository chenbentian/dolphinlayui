/**
 *
 * @(#) SysRoleController.java
 * @Package com.bt.dolphin.system.role.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.controller.QueryController;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.common.vo.WrappedResult;
import com.bt.dolphin.system.role.api.SysRoleService;
import com.bt.dolphin.system.role.vo.SysRoleCondition;
import com.bt.dolphin.system.role.vo.SysRoleVo;
import com.bt.dolphin.system.user.vo.SysUserVo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

/**
 *  类描述：角色管理
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月14日 下午3:44:15   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysRole")
public class SysRoleController extends QueryController<SysRoleCondition> {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("/roleQuery")
	public @ResponseBody QueryResultObject roleQuery(HttpServletResponse response,HttpServletRequest request) {
		SysRoleCondition condition = this.rCondition2QCondition(request);
		QueryResultObject resultObject = new QueryResultObject();
		resultObject = sysRoleService.queryRoleList(condition);
		return resultObject;
	}
	
	@RequestMapping(value = "/getRoleByRoleId")
	public  @ResponseBody WrappedResult getRoleByRoleId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String roleId = StrUtil.nullToEmpty(param.get("roleId"));
		SysRoleVo sysRoleVo = sysRoleService.getRoleByRoleId(roleId);
		return WrappedResult.successWrapedResult(sysRoleVo);
	}
	
	@RequestMapping(value = "/saveRole")
	public  @ResponseBody WrappedResult saveRole(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		SysRoleVo sysRoleVo =  new SysRoleVo();
		BeanUtil.copyProperties(param, sysRoleVo);
		sysRoleService.saveSysRole(sysRoleVo);
		return WrappedResult.successWrapedResult("保存成功！");
	}
	
	@RequestMapping(value = "/deleteByRoleId")
	public  @ResponseBody WrappedResult deleteByRoleId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String roleId = StrUtil.nullToEmpty(param.get("roleId"));
		sysRoleService.deleteByRoleId(roleId);
		return WrappedResult.successWrapedResult("删除成功！");
	}
	
	@Override
	protected SysRoleCondition initCondition() {
		// TODO Auto-generated method stub
		return new SysRoleCondition();
	}

}
