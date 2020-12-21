/**
 *
 * @(#) SysApplicationVo.java
 * @Package com.bt.dolphin.system.menu.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.vo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:56:35   cbt-34201   Created.
 *           
 */
public class SysApplicationVo {
	  private String appId;
	  private String appParentid;
	  private String appName;//应用名称,应用
	  private String appTitle;//应用标题
	  private String appWebpath;//应用路径    
	  private String appComment;
	  private String appStatus;//应用状态 1可用 0不可用 
	  private String appType;//应用类型(0 普通应用/1 系统应用)  
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppParentid() {
		return appParentid;
	}
	public void setAppParentid(String appParentid) {
		this.appParentid = appParentid;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppTitle() {
		return appTitle;
	}
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}
	public String getAppWebpath() {
		return appWebpath;
	}
	public void setAppWebpath(String appWebpath) {
		this.appWebpath = appWebpath;
	}
	public String getAppComment() {
		return appComment;
	}
	public void setAppComment(String appComment) {
		this.appComment = appComment;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	  
	  
	  
}
