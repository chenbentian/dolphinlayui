package com.bt.dolphin.shiro.config;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.bt.dolphin.shiro.config.redis.ShiroSpringCacheManager;
import com.bt.dolphin.shiro.filters.UserAuthFilter;
import com.bt.dolphin.shiro.realm.AuthRealm;
import com.bt.dolphin.shiro.remember.RememberMeManager;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, ShiroProjectProperties properties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 添加自定义拦截器，重写user认证方式，处理session超时问题
         */
        HashMap<String, Filter> myFilters = new HashMap<>(16);
        myFilters.put("userAuth", new UserAuthFilter());
        shiroFilterFactoryBean.setFilters(myFilters);

        /**
         *  过滤规则（注意优先级）
         *  —anon 无需认证(登录)可访问
         * 	—authc 必须认证才可访问
         * 	—perms[标识] 拥有资源权限才可访问
         * 	—role 拥有角色权限才可访问
         * 	—user 认证和自动登录可访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/login", "anon");
        filterMap.put("/api/qq", "anon");
        filterMap.put("/api/qqCallback", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/noAuth", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/Eextend/**", "anon");
        filterMap.put("/lib/**", "anon");
        filterMap.put("/frontend/**", "anon");
        filterMap.put("/article/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/system/user/picture/**", "anon");

        // 通过yml配置文件方式配置的[anon]忽略规则
        String[] excludes = properties.getExcludes().split(",");
        for (String exclude : excludes) {
            if (!StringUtils.isEmpty(exclude.trim())) {
                filterMap.put(exclude, "anon");
            }
        }
        // 拦截根目录下所有路径，需要放行的路径必须在之前添加
        filterMap.put("/**", "userAuth");

        // 设置过滤规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/dolpin/desk/login");
        // 未授权错误页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthRealm authRealm,
                                                                  DefaultWebSessionManager sessionManager,
                                                                  CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * 自定义的Realm  ehcache缓存
     */
   /* @Bean
    public AuthRealm getRealm(EhCacheManager ehCacheManager) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(ehCacheManager);
        return authRealm;
    }*/
    
    @Bean
    public AuthRealm getRealm(ShiroSpringCacheManager redisCacheManager) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(redisCacheManager);
        return authRealm;
    }

    /**
     * 缓存管理器-使用Ehcache实现缓存
     */
   /* @Bean
    public EhCacheManager ehCacheManager(CacheManager cacheManager) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }*/
    
    /**
     * session管理器  Ehcache缓存
     */
    /*@Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(EhCacheManager cacheManager, ShiroProjectProperties properties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setGlobalSessionTimeout(properties.getGlobalSessionTimeout() * 1000);
        sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.validateSessions();
        // 去掉登录页面地址栏jsessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    */
    
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(ShiroSpringCacheManager redisCacheManager, ShiroProjectProperties properties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(redisCacheManager);
        sessionManager.setGlobalSessionTimeout(properties.getGlobalSessionTimeout() * 1000);
        sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.validateSessions();
        // 去掉登录页面地址栏jsessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        RememberMeManager manager = new RememberMeManager();
        manager.setCipherKey(Base64.decode("WcfHGU25gNnTxTlmJMeSpw=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 创建一个简单的Cookie对象
     */
    @Bean
    public SimpleCookie rememberMeCookie(ShiroProjectProperties properties) {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        // cookie记住登录信息时间，默认7天
        simpleCookie.setMaxAge(properties.getRememberMeTimeout() * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
