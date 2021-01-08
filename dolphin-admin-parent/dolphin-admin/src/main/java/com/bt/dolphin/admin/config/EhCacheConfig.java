/**
 *
 * @(#) EhCacheConfig.java
 * @Package com.bt.dolphin.admin.config
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.admin.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;


/**
 *  类描述：EhCache配置
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月28日 下午12:32:56   cbt-34201   Created.
 *           
 */
@Configuration
@EnableCaching
public class EhCacheConfig {
	  /**
     * EhCache的配置
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
    	EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
        ehCacheCacheManager.setTransactionAware(true);
        ehCacheCacheManager.setCacheManager(cacheManager);
        return ehCacheCacheManager;
    }

    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        System.out.println("《========【开启ehcache】 ======== 》 ");
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
   
}
