/**
 *
 * @(#) PtccsSeqUtil.java
 * @Package com.ls.ptccs.commoncore.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.common.util;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年7月22日 下午6:28:14   cbt-34201   Created.
 *           
 */
public class IrsSeqUtil {
    private static final SimpleDateFormat dateFormatOne = new SimpleDateFormat("yyyyMMddHHmmssSS");

	/**
	 * 
	 * 方法说明：生成32位uuid
	 *
	 * Author：        cbt               
	 * Create Date：   2020年7月23日 上午11:19:29
	 * History:  2020年7月23日 上午11:19:29   cbt-34201   Created.
	 *
	 * @return
	 *
	 */
	public static String uuidS() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * 方法说明：生成16位不重复数字
	 *
	 * Author：       xiongwei            
	 * Create Date：   2017年2月28日 下午2:33:36
	 * History:  2017年2月28日 下午2:33:36  xiongwei   Created.
	 *
	 * @return
	 *
	 */
	public static String get16No() {
		int machineId = 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%015d", hashCodeV);
	}
}
