package com.bt.dolphin.admin.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bt.dolphin.system.authorization.filter.AuthorizationInterceptor;

/**
 * 类描述：mvc配置
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年12月21日 下午5:57:06 cbt-34201 Created.
 * 
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${dolphin.shiro.loginUrl}")
	private String loginUrl;
	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
		//excludePathPatterns：用于设置不需要拦截的过滤规则
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", loginUrl);
    }
}
