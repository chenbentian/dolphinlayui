/**
 *
 * @(#) SysCodeService.java
 * @Package com.bt.dolphin.system.code.api
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.code.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bt.dolphin.system.code.vo.CodeVo;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月21日 下午4:15:47   cbt-34201   Created.
 *           
 */
public interface SysCodeService {
	
	/**
	 * 
	 * 方法说明：获取标准代码
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月21日 下午4:46:21
	 * History:  2020年12月21日 下午4:46:21   cbt-34201   Created.
	 *
	 * @param codeType
	 * @return
	 *
	 */
	public List<CodeVo> getStandardCodes(String codeType);
	
	public CodeVo getStandardCodes(String codeType,String codeValue);
}
