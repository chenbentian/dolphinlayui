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
	private int isLeaf;
	private String permissionStatus;
	
	private String perExId;
	private String attrCode;
	private String attrValue;
	private Boolean normal;//普通业务项
	private Boolean visible;//菜单业务项
	private Boolean authorized;//授权业务项
	
	private String userId;
	private String userNo;

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

	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(String permissionStatus) {
		this.permissionStatus = permissionStatus;
	}
	

	public Boolean getNormal() {
		return normal;
	}

	public void setNormal(Boolean normal) {
		this.normal = normal;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getPerExId() {
		return perExId;
	}

	public void setPerExId(String perExId) {
		this.perExId = perExId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	
	
}
