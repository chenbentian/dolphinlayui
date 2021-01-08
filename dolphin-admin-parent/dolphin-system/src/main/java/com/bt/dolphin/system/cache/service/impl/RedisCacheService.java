/**
 *
 * @(#) RedisCacheService.java
 * @Package com.bt.dolphin.system.cache.service.impl
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.cache.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.cache.api.ICache;

/**
 *  类描述：reids缓存
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月29日 上午10:16:13   cbt-34201   Created.
 *           
 */
@Service(value="redisService")
public class RedisCacheService implements ICache,ApplicationContextAware{
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(String key, Object value, int paramInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(String key) {
		// TODO Auto-generated method stub
		return redisTemplate.delete(key);
	}

	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		 return redisTemplate.hasKey(key);
	}

}
