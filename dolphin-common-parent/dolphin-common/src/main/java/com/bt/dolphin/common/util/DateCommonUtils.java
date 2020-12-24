/**
 *
 * @(#) DateCommonUtils.java
 * @Package com.bt.dolphin.common.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  类描述：日期工具
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年12月23日 上午10:48:20   cbt-34201   Created.
 *           
 */
public class DateCommonUtils {
	
	/**
	 * 
	 * 方法说明：获取当前时间
	 *
	 * Author：        cbt               
	 * Create Date：   2020年12月23日 上午10:55:45
	 * History:  2020年12月23日 上午10:55:45   cbt-34201   Created.
	 *
	 * @return
	 *
	 */
	public static String getCurrentDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		return dateTime.format(formatter);
	}
}
