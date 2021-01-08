/**
 *
 * @(#) EhCacheCacheService.java
 * @Package com.bt.dolphin.system.cache.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.cache.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.cache.api.ICache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 *  类描述：ehcache缓存
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月29日 上午10:20:06   cbt-34201   Created.
 *           
 */
@Service(value="ehCacheCacheService")
public class EhCacheCacheService  implements ICache,ApplicationContextAware{
	
	
	private EhCacheCacheManager ehCacheCacheManager;
	private Cache cache;
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
	    this.ehCacheCacheManager = ((EhCacheCacheManager)applicationContext.getBean("ehCacheCacheManager"));
	    this.cache = (Cache)ehCacheCacheManager.getCacheManager().getCache("param");
	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		Element element = new Element(key, value);  
		cache.put(element);
	}

	@Override
	public void put(String key, Object value, int paramInt) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  

	}

	@Override
	public Object remove(String key) {
		// TODO Auto-generated method stub
	    return cache.remove(key);  
	}

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return cache.isKeyInCache(key);
	}

}
