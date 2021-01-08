/**
 *
 * @(#) SysUserVo.java
 * @Package com.bt.dolphin.system.user.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *  类描述：系统用户sy_user
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 下午5:22:13   cbt-34201   Created.
 *           
 */
public class SysUserVo implements Serializable{
	
	private String userId;
    private String userNo;
    private String userName;
    private String userPassword;
    private String userType;
    private String userTypeName;
    private String userStatus;
    private String userStatusName;
    private Date userCreatetime;
    private String userOrg;
    private String directOrgCode;
    private String salt;//密码盐
    private String picture;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getUserCreatetime() {
		return userCreatetime;
	}
	public void setUserCreatetime(Date userCreatetime) {
		this.userCreatetime = userCreatetime;
	}
	public String getUserOrg() {
		return userOrg;
	}
	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
	public String getDirectOrgCode() {
		return directOrgCode;
	}
	public void setDirectOrgCode(String directOrgCode) {
		this.directOrgCode = directOrgCode;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getUserStatusName() {
		return userStatusName;
	}
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
    
    
    
}
