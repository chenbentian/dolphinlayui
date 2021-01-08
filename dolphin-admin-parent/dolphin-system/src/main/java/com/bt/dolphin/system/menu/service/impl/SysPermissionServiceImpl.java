/**
 *
 * @(#) SysPermissionServiceImpl.java
 * @Package com.bt.dolphin.system.menu.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.constant.PermissionConst;
import com.bt.dolphin.common.util.CoreSeqUtil;
import com.bt.dolphin.system.menu.api.SysPermissionService;
import com.bt.dolphin.system.menu.dao.SysPermissionDao;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

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
		this.savePerExtend(PermissionConst.NORMAL, normal, vo.getPermissionId());
		this.savePerExtend(PermissionConst.AUTHORIZED, authorized, vo.getPermissionId());
		this.savePerExtend(PermissionConst.VISIBLE, visible, vo.getPermissionId());
		
		/*}catch(Exception e) {
			e.printStackTrace();
		}*/
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
	
	
	
}
