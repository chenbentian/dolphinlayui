/**
 *
 * @(#) ICache.java
 * @Package com.bt.dolphin.system.cache.api
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.cache.api;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月29日 上午10:14:38   cbt-34201   Created.
 *           
 */
public abstract interface ICache {
	
	  public abstract void put(String key, Object value);

	  public abstract void put(String key, Object value, int paramInt);
	  
	  public abstract Object get(String key);

	  public abstract Object remove(String key);

	  public abstract boolean containsKey(String key);


}
