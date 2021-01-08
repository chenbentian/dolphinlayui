/**
 *
 * @(#) CheckArr.java
 * @Package com.bt.dolphin.common.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.vo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月4日 下午5:00:44   cbt-34201   Created.
 *           
 */
public class CheckArr {
	 /** 复选框标记*/
	  private String type;
	  /** 复选框是否选中*/
	  private String checked;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	  
}
