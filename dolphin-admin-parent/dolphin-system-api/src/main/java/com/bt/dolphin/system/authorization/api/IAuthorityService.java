/**
 *
 * @(#) IAuthorityService.java
 * @Package com.bt.dolphin.system.authorization.api
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.authorization.api;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月25日 下午2:40:59   cbt-34201   Created.
 *           
 */
public interface IAuthorityService {
	
	  public boolean hasPrivByUrl(String accountName, String url, String appName);

}
