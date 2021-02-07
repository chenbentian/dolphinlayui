package com.bt.dolphin.system.role.vo;

import java.io.Serializable;

/**
 * 系统角色表 sy_role
 * @author cbt
 *
 */
public class SysRoleVo implements Serializable {
	
    private String roleId;
    private String userId;
    private String appId;
    private String appName;
    private String roleName;
    private String roleCode;
    private String roleComment ;
    private String roleRange;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
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
	public String getRoleComment() {
		return roleComment;
	}
	public void setRoleComment(String roleComment) {
		this.roleComment = roleComment;
	}
	public String getRoleRange() {
		return roleRange;
	}
	public void setRoleRange(String roleRange) {
		this.roleRange = roleRange;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
}
