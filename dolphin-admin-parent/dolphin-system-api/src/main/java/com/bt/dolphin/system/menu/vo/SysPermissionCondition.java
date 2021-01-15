/**
 *
 * @(#) SysPermissionCondition.java
 * @Package com.bt.dolphin.system.menu.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.vo;

import java.util.List;

import com.bt.dolphin.common.vo.QueryCondition;

/**
 *  类描述：权限
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月15日 上午11:26:20   cbt-34201   Created.
 *           
 */
public class SysPermissionCondition extends QueryCondition{
	
	private String roleId;
	private String queryType;
	private String permissionId;
	private String permissionName;
	private String permissionCode;
	private List<String> permissionIdList;
	
	
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<String> getPermissionIdList() {
		return permissionIdList;
	}
	public void setPermissionIdList(List<String> permissionIdList) {
		this.permissionIdList = permissionIdList;
	}
	
	
	
}
