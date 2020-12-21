package com.bt.dolphin.desk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/**
 * 桌面配置
 * @author cbt
 *
 */
@Component
@ConfigurationProperties(prefix = "dolphin.desk")
public class DeskProperties {

    /** 是否开启验证码 */
    private boolean captchaOpen = false;

    /** xss防护设置 */
    private DeskProperties.Xxs xxs = new DeskProperties.Xxs();

    /**
     * xss防护设置
     */
    public static class Xxs {
        /** xss防护开关 */
        private boolean enabled = true;

        /** 拦截规则，可通过“,”隔开多个 */
        private String urlPatterns = "/*";

        /** 默认忽略规则（无需修改） */
        private String defaultExcludes = "/favicon.ico,/img/*,/js/*,/css/*,/Eextend/*,/system/*";

        /** 忽略规则，可通过“,”隔开多个 */
        private String excludes = "";

        /**
         * 拼接忽略规则
         */
        public String getExcludes() {
            if (!StringUtils.isEmpty(excludes.trim())) {
                return defaultExcludes + "," + excludes;
            }
            return defaultExcludes;
        }
    }

	public boolean isCaptchaOpen() {
		return captchaOpen;
	}

	public void setCaptchaOpen(boolean captchaOpen) {
		this.captchaOpen = captchaOpen;
	}

	public DeskProperties.Xxs getXxs() {
		return xxs;
	}

	public void setXxs(DeskProperties.Xxs xxs) {
		this.xxs = xxs;
	}
    
    
}
