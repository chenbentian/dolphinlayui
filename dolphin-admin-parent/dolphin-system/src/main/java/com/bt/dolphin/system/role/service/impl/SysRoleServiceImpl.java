/**
 *
 * @(#) RoleServiceImpl.java
 * @Package com.bt.dolphin.system.role.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.common.constant.RoleConst;
import com.bt.dolphin.system.role.api.SysRoleService;
import com.bt.dolphin.system.role.dao.SysRoleDao;
import com.bt.dolphin.system.role.vo.SysRoleVo;

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

}
