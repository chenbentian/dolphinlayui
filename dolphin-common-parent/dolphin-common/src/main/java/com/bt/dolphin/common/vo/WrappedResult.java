/**
 *
 * @(#) WrappedResult.java
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
 *  History:  2020年12月21日 下午4:23:38   cbt-34201   Created.
 *           
 */
public class WrappedResult {
	private boolean successful;
	  private Object resultValue;
	  private String resultHint;

	  public WrappedResult()
	  {
	  }

	  public static WrappedResult successWrapedResult(Object data)
	  {
	    return new WrappedResult(true, data, "");
	  }

	  public static WrappedResult failedWrappedResult(String exMessage)
	  {
	    return new WrappedResult(false, "", exMessage);
	  }

	  public WrappedResult(boolean isSuccess, Object data, String tip)
	  {
	    this.successful = isSuccess;
	    this.resultValue = data;
	    this.resultHint = tip;
	  }

	  public boolean isSuccessful() {
	    return this.successful;
	  }

	  public void setSuccessful(boolean successful) {
	    this.successful = successful;
	  }

	  public Object getResultValue() {
	    return this.resultValue;
	  }

	  public void setResultValue(Object resultValue) {
	    this.resultValue = resultValue;
	  }

	  public String getResultHint() {
	    return this.resultHint;
	  }

	  public void setResultHint(String resultHint) {
	    this.resultHint = resultHint;
	  }
}
