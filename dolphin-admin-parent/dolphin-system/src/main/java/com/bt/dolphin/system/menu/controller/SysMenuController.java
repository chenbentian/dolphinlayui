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
import com.bt.dolphin.system.menu.api.SysMenuService;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

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
		String parentId = StrUtil.trimToEmpty(param.get("parentId"));
		List<Map> resultList = new ArrayList<Map>();
		if(id.equals("")) {
			id = "8888";
			Map map = new HashMap();
			map.put("id",id);
			map.put("parentId","");
			map.put("title","菜单管理");
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
}
