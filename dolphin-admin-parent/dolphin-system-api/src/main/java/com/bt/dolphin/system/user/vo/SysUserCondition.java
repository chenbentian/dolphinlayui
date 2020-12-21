/**
 *
 * @(#) SysUserCondition.java
 * @Package com.bt.dolphin.system.user.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.user.vo;

import com.bt.dolphin.common.vo.QueryCondition;

/**
 * 类描述：系统用户管理
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年9月11日 下午5:23:45 cbt-34201 Created.
 * 
 */
public class SysUserCondition extends QueryCondition{
	private String userNo;
	private String userName;
	private String userStatus;
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	
}
