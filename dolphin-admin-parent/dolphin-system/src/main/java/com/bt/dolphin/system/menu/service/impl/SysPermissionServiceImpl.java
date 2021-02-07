/**
 *
 * @(#) SysPermissionServiceImpl.java
 * @Package com.bt.dolphin.system.menu.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.constant.PermissionConst;
import com.bt.dolphin.common.util.CoreSeqUtil;
import com.bt.dolphin.common.util.ResultUtils;
import com.bt.dolphin.common.util.StringClass;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.menu.api.SysPermissionService;
import com.bt.dolphin.system.menu.dao.SysPermissionDao;
import com.bt.dolphin.system.menu.vo.SysPermissionCondition;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;
import com.bt.dolphin.system.role.dao.SysRoleDao;
import com.bt.dolphin.system.role.vo.SysRoleVo;
import com.bt.dolphin.system.user.dao.SysUserDao;
import com.bt.dolphin.system.user.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;


/**
 *  类描述：权限项
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:51:20   cbt-34201   Created.
 *           
 */
@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService{
	
	@Autowired
	private SysPermissionDao sysPermissionDao;
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	/**
	 * 
	 * 方法说明：根据账号和应用id获取权限项
	 *
	 * Author：        cbt               
	 * Create Date：   2020年9月4日 下午4:10:11
	 * History:  2020年9月4日 下午4:10:11   cbt-34201   Created.
	 *
	 * @param accountName
	 * @param appId
	 * @return
	 *
	 */
	@Override
	public List<SysPermissionVo> getPermissionByAccoutAndAppId(String accountName,String appId) {
		return sysPermissionDao.getPermissionByAccoutAndAppId(accountName, appId);
	}
	
	@Override
	public SysPermissionVo getPermissionById(String permissionId) {
		return sysPermissionDao.getPermissionById(permissionId);
	};
	
	@Override
	public SysPermissionVo getPermissionAndArrByAppId(String permissionId) {
		SysPermissionVo sysPermissionVo = sysPermissionDao.getPermissionById(permissionId);
		List<SysPermissionVo> attrList = sysPermissionDao.getPermissionExtendById(permissionId);
		if(attrList != null && attrList.size() > 0) {
			for(SysPermissionVo vo : attrList) {
				String attrCode  = vo.getAttrCode();
				String attrValue  = vo.getAttrValue();
				if(PermissionConst.NORMAL.equals(attrCode) && "T".equals(attrValue)) {
					sysPermissionVo.setNormal(true);
				}else if(PermissionConst.AUTHORIZED.equals(attrCode) && "T".equals(attrValue)) {
					sysPermissionVo.setAuthorized(true);
				}else if(PermissionConst.VISIBLE.equals(attrCode) && "T".equals(attrValue)) {
					sysPermissionVo.setVisible(true);
				}
			}
		}
		return sysPermissionVo;
	};

	@Override
	public List<SysPermissionVo> getSubPermissByPId(String pId) {
		// TODO Auto-generated method stub
		return sysPermissionDao.getSubPermissByPId(pId);
	}
	
	@Override
	public SysPermissionVo savePermissionType(SysPermissionVo vo) {
		// TODO Auto-generated method stub
		String id = StrUtil.nullToEmpty(vo.getPermissionId());
		if(StrUtil.isEmptyOrUndefined(id)) {//新增
			vo.setPermissionId(CoreSeqUtil.getUuidS());
			vo.setPermissionType("1");
			vo.setPermissionStatus("1");
			sysPermissionDao.insertSysPermission(vo);
		}else {//修改
			sysPermissionDao.updateSysPermission(vo);
		}
		return vo;
	}
	
	@Override
	public void deleteBypermissionId(String permissionId) {
		// TODO Auto-generated method stub
		sysPermissionDao.deleteBypermissionId(permissionId);
	}
	
	@Override
	public SysPermissionVo savePermission(SysPermissionVo vo) {
	//	try {
		// TODO Auto-generated method stub
		String id = StrUtil.nullToEmpty(vo.getPermissionId());
		Boolean normal = vo.getNormal();
		Boolean authorized = vo.getAuthorized();
		Boolean visible = vo.getVisible();
		if(StrUtil.isEmptyOrUndefined(id)) {//新增
			vo.setPermissionId(CoreSeqUtil.getUuidS());
			vo.setPermissionStatus("1");
			sysPermissionDao.insertSysPermission(vo);
		}else {//修改
			sysPermissionDao.updateSysPermission(vo);
			sysPermissionDao.delPermissionExtendById(vo.getPermissionId());
		}
		if(normal != null) {
			this.savePerExtend(PermissionConst.NORMAL, normal, vo.getPermissionId());
		}
		if(authorized != null) {
			this.savePerExtend(PermissionConst.AUTHORIZED, authorized, vo.getPermissionId());
		}
		if(visible != null) {
			this.savePerExtend(PermissionConst.VISIBLE, visible, vo.getPermissionId());
		}
		return vo;
	}
	
	public void savePerExtend(String attrCode,Boolean attrType,String permissionId) {
		SysPermissionVo arrtVo = new SysPermissionVo();
		arrtVo.setPerExId(CoreSeqUtil.getUuidS());
		arrtVo.setPermissionId(permissionId);
		arrtVo.setAttrCode(attrCode);
		if(attrType) {
			arrtVo.setAttrValue("T");
		}else {
			arrtVo.setAttrValue("F");
		}
		sysPermissionDao.insertSysPermissionExtend(arrtVo);
	}
	
	
	@Override
	public QueryResultObject permisRoleQueryList(SysPermissionCondition condition){
		int page = condition.getPageBegin()/condition.getRows()+1;
		PageHelper.startPage(page, condition.getRows());
		List<SysPermissionVo> result = new ArrayList<SysPermissionVo>();
		String permisPid = condition.getPermissionId();
		List<String> permisIdList = new ArrayList<String>();
		if(StrUtil.isNotEmpty(permisPid)) {
			this.getItemPermisIdList(permisPid,permisIdList);
			if(permisIdList.size() > 0) {
				condition.setPermissionIdList(permisIdList);
			}
		}
		result = sysPermissionDao.permisRoleQueryList(condition);
		PageInfo<SysPermissionVo> pageInfo = new PageInfo<SysPermissionVo>(result);
		List<SysPermissionVo> list = pageInfo.getList();
		return ResultUtils.wrappQueryResult(list, (int) pageInfo.getTotal());
	}
	
	public void getItemPermisIdList(String permisPid,List<String> permisIdList) {
		if(StrUtil.isNotEmpty(permisPid)) {
			List<SysPermissionVo> subList = sysPermissionDao.getSubPermissByPId(permisPid);
			if(subList != null && subList.size() > 0) {
				for(SysPermissionVo vo : subList) {
					int isLeaf =  vo.getIsLeaf();
					String permisType =  vo.getPermissionType();
					String permisId =  vo.getPermissionId();
					if(isLeaf > 0) {
						getItemPermisIdList(permisId,permisIdList);
					}else {
						if("2".equals(permisType)) {//1WebFolder 2WebPage 3WebMethod 4WebTabPage 5WebButton  
							permisIdList.add(permisId);
						}
					}
				}
			}

		}
	}
	
	@Override
	public void savePermisRole(Map<String, Object> param) {
		String roleId = (String)param.get("roleId");
		List<Map> list = (List<Map>)param.get("rowsData"); 
		if(list != null && list.size() > 0) {
			for(Map map : list) {
				String permissionId = StringClass.getString(map.get("permissionId"));
				sysPermissionDao.insertRolePermisRela(permissionId, roleId);
			}
		}
		
		List<SysRoleVo> userList = sysRoleDao.getUserRoleRela(roleId, null);
		if(userList != null && userList.size() > 0) {
			for(SysRoleVo roleVo : userList) {
				String userId = StringClass.getString(roleVo.getUserId());
				if(list != null && list.size() > 0) {
					for(Map map : list) {
						String permissionId = StringClass.getString(map.get("permissionId"));
						int i = sysPermissionDao.countUserPermisRelaExist(permissionId, roleId, userId);
						if(i<1) {
							String permissionName = StringClass.getString(map.get("permissionName"));
							String permissionPath = StringClass.getString(map.get("permissionPath"));
							String appId = StringClass.getString(map.get("appId"));
							SysPermissionVo perVo = new SysPermissionVo();
							perVo.setUserId(userId);
							SysUserVo userVo = sysUserDao.getUserByUserId(userId);
							perVo.setUserNo(userVo.getUserNo());
							perVo.setPermissionId(permissionId);
							perVo.setPermissionName(permissionName);
							perVo.setPermissionPath(permissionPath);
							perVo.setAppId(appId);
							sysPermissionDao.insertUserPermisRela(perVo);
						}
					}
				}
				
			}
		}
	}
	
	@Override
	public void delPermisRole(Map<String, Object> param) {
		String roleId = (String)param.get("roleId");
		List<Map> list = (List<Map>)param.get("rowsData"); 
		if(list != null && list.size() > 0) {
			for(Map map : list) {
				String permissionId = StringClass.getString(map.get("permissionId"));
				sysPermissionDao.delPermisRole(permissionId, roleId);
			}
		}

		List<SysRoleVo> userList = sysRoleDao.getUserRoleRela(roleId, null);
		if(userList != null && userList.size() > 0) {
			for(SysRoleVo roleVo : userList) {
				String userId = StringClass.getString(roleVo.getUserId());
				if(list != null && list.size() > 0) {
					for(Map map : list) {
						String permissionId = StringClass.getString(map.get("permissionId"));
						int i = sysPermissionDao.countUserPermisRelaExist(permissionId, roleId, userId);
						if(i>=1) {
							sysPermissionDao.delUserPermisRela(permissionId, userId);
						}
					}
				}
				
			}
		}
	}
}
