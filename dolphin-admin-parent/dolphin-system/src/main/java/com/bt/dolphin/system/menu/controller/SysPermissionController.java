/**
 *
 * @(#) SysPermissionController.java
 * @Package com.bt.dolphin.system.menu.controller
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.bt.dolphin.common.vo.TreeResult;
import com.bt.dolphin.common.vo.WrappedResult;
import com.bt.dolphin.system.menu.api.SysPermissionService;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

/**
 *  类描述：权限项
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月24日 上午9:54:56   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysPermission")
public class SysPermissionController {
	private static final Logger logger = LoggerFactory.getLogger(SysApplicationController.class);

	@Autowired
	private SysPermissionService sysPermissionService;
	
	/**
	 * 
	 * 方法说明：获取权限树
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月24日 下午4:12:15
	 * History:  2020年12月24日 下午4:12:15   cbt-34201   Created.
	 *
	 * @param response
	 * @param request
	 * @param param
	 * @return
	 *
	 */
	@RequestMapping(value = "/getSubTree")
	public  @ResponseBody TreeResult getSubTree(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String id = StrUtil.trimToEmpty(param.get("nodeId"));
		String parentId = StrUtil.trimToEmpty(param.get("parentId"));
		List<Map> resultList = new ArrayList<Map>();
		if(id.equals("")) {
			id = "9999";
			Map map = new HashMap();
			map.put("id",id);
			map.put("parentId","");
			map.put("title","权限项管理");
			map.put("last",false);
			map.put("leaf",false);
			resultList.add(map);
		}else {
			List<SysPermissionVo> list = sysPermissionService.getSubPermissByPId(id);
			if(list != null && list.size() > 0) {
				for(SysPermissionVo vo : list) {
					Map map = new HashMap();
					map.put("id",vo.getPermissionId());
					map.put("parentId",vo.getPermissionParentid());
					map.put("title",vo.getPermissionName());
					map.put("isOpen",false);
					String permissionType = vo.getPermissionType();
					if("1".equals(permissionType)) {
						map.put("last",false);//WebFolder
						map.put("leaf",false);
					}else {
						map.put("last",true);
						map.put("leaf",true);
					}
				//	map.put("checked",false);
					map.put("appId",vo.getAppId());
					resultList.add(map);
				}
			}
		}
		return TreeResult.successWrapedResult(resultList);
	}
	
	
	@RequestMapping(value = "/getPermissionById")
	public  @ResponseBody WrappedResult getPermissionById(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String permissionId = StrUtil.toString(param.get("permissionId"));
		SysPermissionVo sysPermissionVo = sysPermissionService.getPermissionById(permissionId);
		return WrappedResult.successWrapedResult(sysPermissionVo);
	}
	
	@RequestMapping(value = "/getPermissionAndArrById")
	public  @ResponseBody WrappedResult getPermissionAndArrById(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String permissionId = StrUtil.toString(param.get("permissionId"));
		SysPermissionVo sysPermissionVo = sysPermissionService.getPermissionAndArrByAppId(permissionId);
		return WrappedResult.successWrapedResult(sysPermissionVo);
	}
	
	/**
	 * 
	 * 方法说明：权限目录保存，更新
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月30日 下午4:43:02
	 * History:  2020年12月30日 下午4:43:02   cbt-34201   Created.
	 *
	 * @param response
	 * @param request
	 * @param param
	 * @return
	 *
	 */
	@RequestMapping(value = "/savePermissionType")
	public  @ResponseBody WrappedResult savePermissionType(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		SysPermissionVo sysPermissionVo =  new SysPermissionVo();
		BeanUtil.copyProperties(param, sysPermissionVo);			
		SysPermissionVo result = sysPermissionService.savePermissionType(sysPermissionVo);
		return WrappedResult.successWrapedResult(result);
	}
	
	@RequestMapping(value = "/savePermission")
	public  @ResponseBody WrappedResult savePermission(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
			SysPermissionVo sysPermissionVo =  new SysPermissionVo();
			BeanUtil.copyProperties(param, sysPermissionVo);			
			SysPermissionVo result = sysPermissionService.savePermission(sysPermissionVo);
			return WrappedResult.successWrapedResult(result);
	}
	
	@RequestMapping(value = "/deleteBypermissionId")
	public  @ResponseBody WrappedResult deleteBypermissionId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String permissionId = StrUtil.toString(param.get("id"));
		List<SysPermissionVo> list = sysPermissionService.getSubPermissByPId(permissionId);
		if(list != null && list.size() > 0){
			return WrappedResult.failedWrappedResult("存在下级目录不允许删除，请先删除下级！");
		}
		sysPermissionService.deleteBypermissionId(permissionId);
		return WrappedResult.successWrapedResult("删除成功！");
	}
}
