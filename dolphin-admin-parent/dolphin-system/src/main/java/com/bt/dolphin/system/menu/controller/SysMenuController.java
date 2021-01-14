/**
 *
 * @(#) SysMenuController.java
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
import com.bt.dolphin.system.menu.api.SysMenuService;
import com.bt.dolphin.system.menu.vo.SysMenuVo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

/**
 *  类描述：菜单管理
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月8日 下午3:59:52   cbt-34201   Created.
 *           
 */
@Controller
@RequestMapping(value = "/dolphin/system/sysMenu")
public class SysMenuController {
	private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value = "/getSubTree")
	public  @ResponseBody TreeResult getSubTree(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String id = StrUtil.trimToEmpty(param.get("nodeId"));
		List<Map> resultList = new ArrayList<Map>();
		if(id.equals("")) {
			id = "";
			Map map = new HashMap();
			map.put("id","8888");
			map.put("parentId","");
			map.put("title","菜单管理");
			map.put("last",false);
			map.put("leaf",false);
			resultList.add(map);
		}else {
			List<SysMenuVo> list = sysMenuService.getSubMenuByPId(id);
			if(list != null && list.size() > 0) {
				for(SysMenuVo vo : list) {
					Map map = new HashMap();
					map.put("id",vo.getMenuId());
					map.put("parentId",vo.getMenuParentid());
					map.put("title",vo.getMenuTitle());
					map.put("isOpen",false);
					map.put("leaf",vo.getIsLeaf());
					String permissionType = vo.getMenuKind();
					if("MenuItem".equals(permissionType)) {
						map.put("last",true);
					}else {
						map.put("last",false);//WebFolder
					}
					map.put("appId",vo.getMenuAppId());
					map.put("dataType", permissionType);
					resultList.add(map);
				}
			}
		}
		return TreeResult.successWrapedResult(resultList);
	}
	
	@RequestMapping(value = "/getMenuById")
	public  @ResponseBody WrappedResult getMenuById(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String menuId = StrUtil.toString(param.get("menuId"));
		SysMenuVo sysMenuVo = sysMenuService.getMenuById(menuId);
		return WrappedResult.successWrapedResult(sysMenuVo);
	}
	
	@RequestMapping(value = "/saveMenuType")
	public  @ResponseBody WrappedResult saveMenuType(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		SysMenuVo sysMenuVo =  new SysMenuVo();
		BeanUtil.copyProperties(param, sysMenuVo);			
		SysMenuVo result = sysMenuService.saveMenuType(sysMenuVo);
		return WrappedResult.successWrapedResult(result);
	}
	
	@RequestMapping(value = "/deleteByMenuId")
	public  @ResponseBody WrappedResult deleteByMenuId(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		String menuId = StrUtil.toString(param.get("id"));
		List<SysMenuVo> list = sysMenuService.getSubMenuByPId(menuId);
		if(list != null && list.size() > 0){
			return WrappedResult.failedWrappedResult("存在下级目录不允许删除，请先删除下级！");
		}
		sysMenuService.deleteByMenuId(menuId);
		return WrappedResult.successWrapedResult("删除成功！");
	}
	
	@RequestMapping(value = "/saveMenu")
	public  @ResponseBody WrappedResult saveMenu(HttpServletResponse response, HttpServletRequest request,
			@RequestParam  Map<String, String> param)  {
		SysMenuVo sysMenuVo =  new SysMenuVo();
		BeanUtil.copyProperties(param, sysMenuVo);			
		SysMenuVo result = sysMenuService.saveMenu(sysMenuVo);
		return WrappedResult.successWrapedResult(result);
	}
}
