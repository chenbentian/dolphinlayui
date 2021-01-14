/**
 *
 * @(#) SysMenuServiceImpl.java
 * @Package com.bt.dolphin.system.menu.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.util.CoreSeqUtil;
import com.bt.dolphin.system.menu.api.SysApplicationService;
import com.bt.dolphin.system.menu.api.SysMenuService;
import com.bt.dolphin.system.menu.api.SysPermissionService;
import com.bt.dolphin.system.menu.dao.SysMenuDao;
import com.bt.dolphin.system.menu.vo.SysApplicationVo;
import com.bt.dolphin.system.menu.vo.SysMenuVo;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

import cn.hutool.core.util.StrUtil;

/**
 * 类描述： 系统菜单
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月4日 下午2:48:02 cbt-34201 Created.
 * 
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private SysApplicationService sysApplicationService;
	
	private Map<String, Object> conCurrentHashMap = new ConcurrentHashMap();

	@Override
	public Map<String, SysMenuVo> getObjMenuByAccount(String accountName, List<String> appNames, String id) {
		List<SysPermissionVo> permissionlist = sysPermissionService.getPermissionByAccoutAndAppId(accountName, null);
		List<SysApplicationVo> allApplication = sysApplicationService.getAllApplication();
		Map appsNameMap = new HashMap();
	    Map appsIdMap = new HashMap();
	    for (Iterator localIterator = allApplication.iterator(); localIterator.hasNext(); ) {
	      SysApplicationVo app = (SysApplicationVo)localIterator.next();
	      appsNameMap.put(app.getAppId(), app.getAppName());
	      appsIdMap.put(app.getAppName(), app.getAppId());
	    }
	    List appIds = null;
	    if ((appNames != null) && (appNames.size() > 0)) {
	      appIds = new ArrayList();
	      for (String appName : appNames) {
	        String appId = (String)appsIdMap.get(appName);
	        if (!StrUtil.isBlankIfStr(appId)) {
	          ((List)appIds).add(appId);
	        }
	      }
	      if (((List)appIds).size() <= 0) {
	    	  return null;
	      }
	    }
	    Map<String, SysMenuVo>  menuMap = getObjMenu(id, (List)appIds, permissionlist, appsNameMap,false);
	    this.conCurrentHashMap.clear();
	    return menuMap;
	}
	
	 public Map<String, SysMenuVo> getObjMenu(String parentId, List<String> appIds, List<SysPermissionVo> permissionlist, Map<String, String> appsMap, boolean isAdmin){
		 Map menuTreeMap = new HashMap();
		 String key = "";
		 SysMenuVo menuBoCon = new SysMenuVo();
		 List<SysMenuVo> subMenuList = sysMenuDao.getChildMenuByAppIds(parentId, appIds);
	     if (!isAdmin) {
	    	 for (SysMenuVo sysMenuVo : subMenuList) {
	    		 //有下级
	    		  if (sysMenuVo.getIsLeaf() > 0) {
	    			  sysMenuVo.getSubMenu().putAll(getObjMenu(sysMenuVo.getMenuId(), appIds, permissionlist, appsMap, isAdmin));
	    	      }
	    		  
	    		  if ("MenuItem".equals(sysMenuVo.getMenuKind())) {
	    			  SysApplicationVo sysApplicationVo = null;
	    	          key = "Platform(menu)-" + (String)appsMap.get(sysMenuVo.getMenuAppId()) + "=>";

	    	          if (!this.conCurrentHashMap.containsKey(key)){
	    	        	  sysApplicationVo = sysApplicationService.getAppByAppId(sysMenuVo.getObjAppId());
	    	            if (null != sysApplicationVo) {
	    	              this.conCurrentHashMap.put(key, sysApplicationVo);
	    	            }
	    	          }else{
	    	        	  sysApplicationVo = (SysApplicationVo)this.conCurrentHashMap.get(key);
	    	          }
	    	          
	    	          if (null != sysApplicationVo){
	    	              sysMenuVo.setHref(sysApplicationVo.getAppWebpath() + sysMenuVo.getObjPath());
	    		  	  }else{
	    		  		sysMenuVo.setHref(sysMenuVo.getObjPath());
	    		  	  } 
	    	          
	    	          if (2 == sysMenuVo.getOpenPosition()) {
	    	              sysMenuVo.setIsOpen(true);
	    	          }

	    	          for (SysPermissionVo sysPermissionVo : permissionlist){
	    	            if (sysPermissionVo.getPermissionId().equals(sysMenuVo.getObjId())) {
	    	              menuTreeMap.put(sysMenuVo.getMenuId(), sysMenuVo);
	    	              break;
	    	            }
	    	          }
	    		  } else if ((null != sysMenuVo.getSubMenu()) && ((sysMenuVo.getSubMenu()).size() > 0)) {
	    			  sysMenuVo.setHref(sysMenuVo.getObjPath());
	    			  menuTreeMap.put(sysMenuVo.getMenuId(), sysMenuVo);
	    	      }
	    	  }
	     }else {
	    	 
	     }
		 return menuTreeMap;
	 }

	@Override
	public List<SysMenuVo> getSubMenuByPId(String parentId) {
		// TODO Auto-generated method stub
		return sysMenuDao.getSubMenuByPId(parentId);
	}

	@Override
	public SysMenuVo getMenuById(String menuId) {
		// TODO Auto-generated method stub
		return sysMenuDao.getMenuById(menuId);
	}

	@Override
	public SysMenuVo saveMenuType(SysMenuVo vo) {
		// TODO Auto-generated method stub
		String id = StrUtil.nullToEmpty(vo.getMenuId());
		if(StrUtil.isEmptyOrUndefined(id)) {//新增
			vo.setMenuId(CoreSeqUtil.getUuidS());
			vo.setMenuStatus("01");
			vo.setMenuKind("MenuFolder");
			sysMenuDao.insertSysMenu(vo);
		}else {//修改
			sysMenuDao.updateSysMenu(vo);
		}
		return vo;
	}

	@Override
	public void deleteByMenuId(String menuId) {
		// TODO Auto-generated method stub
		sysMenuDao.deleteByMenuId(menuId);
	}

	@Override
	public SysMenuVo saveMenu(SysMenuVo vo) {
		// TODO Auto-generated method stub
		String id = StrUtil.nullToEmpty(vo.getMenuId());
		if(StrUtil.isEmptyOrUndefined(id)) {//新增
			vo.setMenuId(CoreSeqUtil.getUuidS());
			vo.setMenuStatus("01");
			vo.setMenuKind("MenuItem");
			sysMenuDao.insertSysMenu(vo);
		}else {//修改
			sysMenuDao.updateSysMenu(vo);
		}
		return vo;
	}

}
