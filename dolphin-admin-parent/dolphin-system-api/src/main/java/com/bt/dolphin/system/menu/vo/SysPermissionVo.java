/**
 *
 * @(#) SysPermissionVo.java
 * @Package com.bt.dolphin.system.menu.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.vo;

/**
 * 类描述：权限项
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月4日 下午3:19:07 cbt-34201 Created.
 * 
 */
public class SysPermissionVo {

	private String permissionType;// 权限类型 1WebFolder 2WebPage 3WebMethod 4WebTabPage 5WebButton
	private String permissionId;
	private String appId;
	private String permissionParentid;
	private String permissionName;
	private String permissionCode;
	private String permissionPath;
	private String permissionIndex;
	private String permissionComment;
	private String isLeaf;

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPermissionParentid() {
		return permissionParentid;
	}

	public void setPermissionParentid(String permissionParentid) {
		this.permissionParentid = permissionParentid;
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

	public String getPermissionPath() {
		return permissionPath;
	}

	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}

	public String getPermissionIndex() {
		return permissionIndex;
	}

	public void setPermissionIndex(String permissionIndex) {
		this.permissionIndex = permissionIndex;
	}

	public String getPermissionComment() {
		return permissionComment;
	}

	public void setPermissionComment(String permissionComment) {
		this.permissionComment = permissionComment;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
