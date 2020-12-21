/**
 *
 * @(#) DruidConfiguration.java
 * @Package com.bt.dolphin.irs.config.druid
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.config.druid;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月12日 下午11:19:06   cbt-34201   Created.
 *           
 */
@Configuration
public class DruidConfiguration {
	 @Bean
	    public ServletRegistrationBean statViewServle(){
	        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
	        //IP白名单
	        //servletRegistrationBean.addInitParameter("allow","192.168.1.12,127.0.0.1");
	        //IP黑名单
	        //servletRegistrationBean.addInitParameter("deny","192.168.4.23");
	        //控制台用户
	        servletRegistrationBean.addInitParameter("loginUsername","admin");
	        servletRegistrationBean.addInitParameter("loginPassword","adminirs88");
	        //是否能够重置数据
	        servletRegistrationBean.addInitParameter("resetEnable","false");
	        return servletRegistrationBean;
	    }
	    @Bean
	    public FilterRegistrationBean statFilter(){
	        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
	        //添加过滤规则
	        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	        return filterRegistrationBean;
	    }
}
