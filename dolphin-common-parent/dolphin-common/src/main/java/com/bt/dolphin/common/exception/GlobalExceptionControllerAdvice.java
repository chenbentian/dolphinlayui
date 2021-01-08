/**
 *
 * @(#) GlobalExceptionHandler.java
 * @Package com.bt.dolphin.common.exception
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.bt.dolphin.common.vo.WrappedResult;

/**
 *  类描述：全局异常处理，请求处理好，请求前需要通过filter过滤器
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月6日 下午1:13:11   cbt-34201   Created.
 *           
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;
	
	@ExceptionHandler(value =Exception.class)
	@ResponseBody
	public WrappedResult exceptionHandler(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception exception){
	    
		if (this.globalExceptionHandler != null) {
	    	globalExceptionHandler.handle(request, response, exception);
	    }
	    return WrappedResult.failedWrappedResult(exception.getMessage());
    }
	
}
