/**
 *
 * @(#) ShiroSpringCacheManager.java
 * @Package com.bt.dolphin.shiro.config.redis
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.shiro.config.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import com.bt.dolphin.shiro.config.ShiroProjectProperties;

/**
 *  类描述：shire整合spring缓存
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月28日 下午12:42:29   cbt-34201   Created.
 *           
 */
@Component
public class ShiroSpringCacheManager implements CacheManager ,Destroyable{
	
	 /**
     * 将之上的RedisCacheManager的Bean拿出来 注入于此
     */
	
    private org.springframework.cache.CacheManager cacheManager;
    
    @Autowired
    private ShiroProjectProperties  properties;
    
    @Autowired
    @Qualifier("ehCacheCacheManager")
    private EhCacheCacheManager ehCacheCacheManager;
    
    @Autowired
    @Qualifier("redisCacheManager")
    private RedisCacheManager redisCacheManager;
     
    public org.springframework.cache.CacheManager getCacheManager() {
    	String cacheType = properties.getCacheType();
    	if("redis".equals(cacheType)) {
    		cacheManager = redisCacheManager;
    	}else if("ehcache".equals(cacheType)) {
    		cacheManager = ehCacheCacheManager;
    	}
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
	    
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		cacheManager = null;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		 if (name == null ){
	            return null;
	        }
	        // 新建一个ShiroSpringCache 将Bean放入并实例化
	        return new ShiroSpringCache<K,V>(name,getCacheManager());
	}

}
