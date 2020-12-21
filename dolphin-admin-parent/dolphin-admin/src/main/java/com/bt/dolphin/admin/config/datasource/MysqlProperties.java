/**
 *
 * @(#) MysqlProperties.java
 * @Package com.bt.dolphin.admin.config.datasource
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.admin.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月7日 上午11:18:52   cbt-34201   Created.
 *           
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.dolphin")
public class MysqlProperties {
	
	private String url;
	private String username;
	private String password;
	private String driveClassName;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriveClassName() {
		return driveClassName;
	}
	public void setDriveClassName(String driveClassName) {
		this.driveClassName = driveClassName;
	}
	
	
}
