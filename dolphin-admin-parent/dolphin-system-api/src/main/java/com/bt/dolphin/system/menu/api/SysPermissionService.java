/**
 *
 * @(#) SysPermissionService.java
 * @Package com.bt.dolphin.system.menu.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.api;

import java.util.List;

import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.menu.vo.SysPermissionCondition;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

/**
 *  类描述：权限项
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:50:56   cbt-34201   Created.
 *           
 */
public interface SysPermissionService {
	

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
	public List<SysPermissionVo> getPermissionByAccoutAndAppId(String accountName,String appId);
	public SysPermissionVo getPermissionById(String permissionId);
	public SysPermissionVo getPermissionAndArrByAppId(String permissionId);
	
	public List<SysPermissionVo> getSubPermissByPId(String pId);
	
	public SysPermissionVo savePermissionType(SysPermissionVo vo);
	public void deleteBypermissionId(String permissionId);
	
	public SysPermissionVo savePermission(SysPermissionVo vo);
	public QueryResultObject permisRoleQueryList(SysPermissionCondition condition);


}
