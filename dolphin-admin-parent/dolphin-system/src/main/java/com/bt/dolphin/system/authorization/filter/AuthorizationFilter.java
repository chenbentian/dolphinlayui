/**
 *
 * @(#) AuthorizationFilter.java
 * @Package com.bt.dolphin.system.authorization
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 *//*

package com.bt.dolphin.system.authorization.filter;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.user.vo.SysUserVo;

*//**
 *  类描述：授权拦截器
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月25日 上午11:30:45   cbt-34201   Created.
 *           
 *//*
public class AuthorizationFilter  implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	
	 @Value("${ApplicationName}")
	 private String appName;
	 
    *//**
     * 在Controller请求执行之前，
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     *//*
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("zy");
        String requestURI = request.getRequestURI();
        if ((requestURI != null) && (!"".equals(requestURI)) && (!"/".equals(requestURI))){
          int index = requestURI.indexOf("?");
          if (index != -1) {
            requestURI = requestURI.substring(0, index);
          }
          String contextPath = request.getContextPath();
          requestURI = requestURI.substring(contextPath.length());
        }
        
        HandlerMethod hm;
        try{
          if ((handler instanceof HandlerMethod)) {
            hm = (HandlerMethod)handler;
          }else {
            return true;
          }
        }catch (Exception e) {
          this.logger.error(e.getMessage(), e);
          return true;
        }

        //不保护路径
        List unprotectedPaths = UnprotectedPathsCollection.getAllPaths();
        for (String s : unprotectedPaths) {
          if (requestURI.matches(s)) {
            return true;
          }
        }
        
        String accountName = null;
		SysUserVo user = ShiroUtil.getSubject();
		if(user != null) {
			accountName = user.getUserNo();
		}else {
			 throw new Exception("无法获取到Authentication认证信息！");
		}
		//配置不做授权校验
	    PermitAll classPermitAll = (PermitAll)hm.getMethod().getDeclaringClass().getAnnotation(PermitAll.class);
	    if (classPermitAll != null){
	       return true;
	    }
	    
	    PermitAll methodPermitAll = (PermitAll)hm.getMethodAnnotation(PermitAll.class);
	    if (methodPermitAll != null){
	    	 return true;
	    }
	    boolean flag = false;
	    boolean isAdmin = accountName=="sysadmin" ? true:false;
	    if (isAdmin) {
	      flag = true;
	    }else {
	   //   flag = this.authorityService.hasPrivByUrl(accountName, path, this.appName);
	    }
	    return flag;
    }

    *//**
     * Controller处理之后，渲染完成之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     *//*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    *//**
     * 整个请求完成执行完之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     *//*
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
*/