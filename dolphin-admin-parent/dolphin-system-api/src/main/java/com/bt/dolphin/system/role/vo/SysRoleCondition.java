/**
 *
 * @(#) SysRoleCondition.java
 * @Package com.bt.dolphin.system.role.api
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.role.vo;

import com.bt.dolphin.common.vo.QueryCondition;

/**
 * 类描述：角色管理
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2021年1月14日 下午3:46:42 cbt-34201 Created.
 * 
 */
public class SysRoleCondition extends QueryCondition {

	private String appId;
	private String roleName;
	private String roleCode;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
}
