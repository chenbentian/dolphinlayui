/**
 *
 * @(#) TreeResult.java
 * @Package com.bt.dolphin.common.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月4日 下午2:59:11   cbt-34201   Created.
 *           
 */
public class TreeResult {
	
	  private Object data;
	  private Object status;
	  
	  public static TreeResult successWrapedResult(Object data){
		Map map = new HashMap();
		map.put("code", 200);
		map.put("message", "操作成功");
	    return new TreeResult(map, data);
	  }

	  public static TreeResult failedWrappedResult(String exMessage){
		Map map = new HashMap();
		map.put("code", 500);
		map.put("message", exMessage);
	    return new TreeResult(map, "");
	  }
	  
	  public TreeResult(Object status, Object data)
	  {
	    this.data = data;
	    this.status = status;
	  }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	  
	  
}
