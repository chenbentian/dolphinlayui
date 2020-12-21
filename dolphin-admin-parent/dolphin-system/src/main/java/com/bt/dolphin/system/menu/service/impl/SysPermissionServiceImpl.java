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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.menu.api.SysPermissionService;
import com.bt.dolphin.system.menu.dao.SysPermissionDao;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

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
	public List<SysPermissionVo> getAppByAppName(String accountName,String appId) {
		return sysPermissionDao.getAppByAppName(accountName, appId);
	}
}
