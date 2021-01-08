package com.bt.dolphin.admin.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
		//excludePathPatterns：用于设置不需要拦截的过滤规则
	//	registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**"));
	}
}
