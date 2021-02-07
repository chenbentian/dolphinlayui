/**
 *
 * @(#) AuthorizationConfig.java
 * @Package com.bt.dolphin.system.authorization.filter
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.authorization.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bt.dolphin.system.authorization.api.IAuthorityService;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2021年2月5日 下午3:40:22 cbt-34201 Created.
 * 
 */
@Configuration
public class AuthorizationConfig {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationConfig.class);
	
	@Value("${dolphin.shiro.unprotectedUrls}")
	private String unprotectedUrls;
	
	@Value("${ApplicationName}")
	private String applicationName;
	
	@Autowired
	private IAuthorityService authorityService;

	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
		logger.info("init 权限项，拦截器 ==========================");
		AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
		authorizationInterceptor.setUnprotectedUrls(unprotectedUrls);
		authorizationInterceptor.setAuthorityService(authorityService);
		authorizationInterceptor.setAppName(applicationName);
		return authorizationInterceptor;
	}
	
	
}
