package com.bt.dolphin.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目-shiro会话配置项
 * @author 小懒虫
 * @date 2018/11/6
 */
@Component
@ConfigurationProperties(prefix = "dolphin.shiro")
public class ShiroProjectProperties {

    /** cookie记住登录信息时间，默认7天 */
    private Integer rememberMeTimeout = 7;

    /** Session会话超时时间，默认30分钟 */
    private Integer globalSessionTimeout = 1800;

    /** Session会话检测间隔时间，默认15分钟 */
    private Integer sessionValidationInterval = 900;

    /** 忽略的路径规则，多个规则使用","逗号隔开 */
    private String excludes = "";

	public Integer getRememberMeTimeout() {
		return rememberMeTimeout;
	}

	public void setRememberMeTimeout(Integer rememberMeTimeout) {
		this.rememberMeTimeout = rememberMeTimeout;
	}

	public Integer getGlobalSessionTimeout() {
		return globalSessionTimeout;
	}

	public void setGlobalSessionTimeout(Integer globalSessionTimeout) {
		this.globalSessionTimeout = globalSessionTimeout;
	}

	public Integer getSessionValidationInterval() {
		return sessionValidationInterval;
	}

	public void setSessionValidationInterval(Integer sessionValidationInterval) {
		this.sessionValidationInterval = sessionValidationInterval;
	}

	public String getExcludes() {
		return excludes;
	}

	public void setExcludes(String excludes) {
		this.excludes = excludes;
	}
    
    
}
