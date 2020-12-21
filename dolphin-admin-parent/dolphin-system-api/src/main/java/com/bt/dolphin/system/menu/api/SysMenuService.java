/**
 *
 * @(#) SysMenuService.java
 * @Package com.bt.dolphin.system.menu.service
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.api;

import java.util.List;
import java.util.Map;

import com.bt.dolphin.system.menu.vo.SysMenuVo;

/**
 *  类描述：菜单
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月4日 下午2:47:36   cbt-34201   Created.
 *           
 */
public interface SysMenuService {
	
	public Map<String, SysMenuVo> getObjMenuByAccount(String accountName, List<String> appNames, String id);
}
