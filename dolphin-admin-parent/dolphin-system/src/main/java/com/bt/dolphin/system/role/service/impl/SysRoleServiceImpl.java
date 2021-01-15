/**
 *
 * @(#) RoleServiceImpl.java
 * @Package com.bt.dolphin.system.role.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.constant.RoleConst;
import com.bt.dolphin.common.util.CoreSeqUtil;
import com.bt.dolphin.common.util.ResultUtils;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.menu.api.SysApplicationService;
import com.bt.dolphin.system.menu.vo.SysApplicationVo;
import com.bt.dolphin.system.role.api.SysRoleService;
import com.bt.dolphin.system.role.dao.SysRoleDao;
import com.bt.dolphin.system.role.vo.SysRoleCondition;
import com.bt.dolphin.system.role.vo.SysRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.StrUtil;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月27日 下午3:23:45   cbt-34201   Created.
 *           
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysApplicationService sysApplicationService;
	
	/**
	 * 
	 * 方法说明：是否是后台管理角色
	 *
	 * Author：        cbt               
	 * Create Date：   2020年8月31日 下午3:38:04
	 * History:  2020年8月31日 下午3:38:04   cbt-34201   Created.
	 *
	 * @return
	 *
	 */
	@Override
	public Boolean existUserAdminRole(String userNo){
		// TODO Auto-generated method stub
		SysRoleVo sysRoleVo = sysRoleDao.getUserRoleByUserNoAndRoleCode(userNo,RoleConst.SYS_ROLE_ADMIN_NAME);
		if(sysRoleVo != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public SysRoleVo getRoleByRoleId(String roleId) {
		SysRoleVo sysRoleVo = sysRoleDao.getRoleByRoleId(roleId);
		return sysRoleVo;
	}
	
	@Override
	public QueryResultObject queryRoleList(SysRoleCondition condition){
		int page = condition.getPageBegin()/condition.getRows()+1;
		PageHelper.startPage(page, condition.getRows());
		List<SysRoleVo> result = new ArrayList<SysRoleVo>();
		result = sysRoleDao.queryRoleList(condition);
		if(result != null && result.size() > 0) {
			for(SysRoleVo vo : result) {
				SysApplicationVo appVo = sysApplicationService.getAppByAppId(vo.getAppId());
				if(appVo != null ) {
					vo.setAppName(appVo.getAppTitle());
				}
			}
		}
		PageInfo<SysRoleVo> pageInfo = new PageInfo<SysRoleVo>(result);
		List<SysRoleVo> list = pageInfo.getList();
		return ResultUtils.wrappQueryResult(list, (int) pageInfo.getTotal());

	}

	
	@Override
	public void saveSysRole(SysRoleVo vo) {
		// TODO Auto-generated method stub
		String roleId = StrUtil.str(vo.getRoleId());
		if(StrUtil.isNotEmpty(roleId)) { //修改
			sysRoleDao.updateSysRole(vo);
		}else {//新增
			vo.setRoleId(CoreSeqUtil.getUuidS());
			sysRoleDao.insertSysRole(vo);
		}
	}

	@Override
	public void deleteByRoleId(String roleId) {
		// TODO Auto-generated method stub
		sysRoleDao.deleteByRoleId(roleId);
	}

}
