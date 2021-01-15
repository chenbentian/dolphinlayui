/**
 *
 * @(#) RoleService.java
 * @Package com.bt.dolphin.system.role.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.api;

import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.system.role.vo.SysRoleCondition;
import com.bt.dolphin.system.role.vo.SysRoleVo;

/**
 *  类描述：系统角色
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月27日 下午3:23:18   cbt-34201   Created.
 *           
 */
public interface SysRoleService {
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
	public Boolean existUserAdminRole(String userNo);
	public SysRoleVo getRoleByRoleId(String roleId);
	public QueryResultObject queryRoleList(SysRoleCondition condition);
	public void saveSysRole(SysRoleVo vo);
	public void deleteByRoleId(String roleId);
}
