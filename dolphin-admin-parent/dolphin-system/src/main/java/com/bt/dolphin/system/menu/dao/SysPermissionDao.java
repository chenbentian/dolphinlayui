/**
 *
 * @(#) SysPermissionDao.java
 * @Package com.bt.dolphin.system.menu.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.menu.vo.SysPermissionVo;
import com.bt.dolphin.system.user.vo.SysUserVo;

/**
 *  类描述：系统权限项
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:54:08   cbt-34201   Created.
 *           
 */
public interface SysPermissionDao {
	
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
	public List<SysPermissionVo> getPermissionByAccoutAndAppId(@Param("accountName") String accountName,@Param("appId") String appId);
	
	public SysPermissionVo getPermissionById(@Param("permissionId") String permissionId);
	
	public List<SysPermissionVo> getSubPermissByPId(@Param("pId") String pId);
	
	public void insertSysPermission(SysPermissionVo vo);
	
	public void updateSysPermission(SysPermissionVo vo);
	
	public void deleteBypermissionId(@Param("permissionId") String permissionId);
	
	public List<SysPermissionVo> getPermissionExtendById(@Param("permissionId") String permissionId);
	public void insertSysPermissionExtend(SysPermissionVo vo);
	public void delPermissionExtendById(@Param("permissionId") String permissionId);

}
