/**
 *
 * @(#) ExceptionHandler.java
 * @Package com.bt.dolphin.common.exception
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月6日 下午4:08:25   cbt-34201   Created.
 *           
 */
@Component
public class GlobalExceptionHandler {
	
	 public String getStackTraceString(Throwable e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
	  }
	 
	  public void handle(ServletRequest servletRequest, ServletResponse servletResponse, Exception exception,String status){
		  HttpServletRequest request = (HttpServletRequest)servletRequest;
		  HttpServletResponse response = (HttpServletResponse)servletResponse;
		  if(status != null) {
			  response.setStatus(Integer.parseInt(status));
		  }
		  String stackTrace = getStackTraceString(exception);
		  System.out.println(stackTrace);
	  }
}
