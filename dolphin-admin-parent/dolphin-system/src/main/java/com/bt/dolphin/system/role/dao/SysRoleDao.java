/**
 *
 * @(#) RoleDao.java
 * @Package com.bt.dolphin.system.role.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.role.vo.SysRoleCondition;
import com.bt.dolphin.system.role.vo.SysRoleVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月31日 下午3:39:49   cbt-34201   Created.
 *           
 */
public interface SysRoleDao {
	
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
	public SysRoleVo getUserRoleByUserNoAndRoleCode(@Param("userNo") String userNo,@Param("roleCode") String roleCode);
	public SysRoleVo getRoleByRoleId(@Param("roleId") String roleId);

	public List<SysRoleVo> queryRoleList(SysRoleCondition condition);
	public void insertSysRole(SysRoleVo vo);
	
	public void updateSysRole(SysRoleVo vo);
	public void deleteByRoleId(@Param("roleId") String roleId);
}
