package com.bt.dolphin.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class CoreSeqUtil {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@ Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmssSS");
		}
	};

	/**
	 * 
	 * 方法说明：获取12位appno 日期+6随机数: 200722+111111
	 *
	 * Author：        cbt               
	 * Create Date：   2020年7月22日 下午6:41:30
	 * History:  2020年7月22日 下午6:41:30   cbt-34201   Created.
	 *
	 * @return
	 *
	 */
	public static String getWorkflowAppNo() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");  
		String dataStr = date.format(formatter);
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		return dataStr + String.format("%05d", hashCodeV);
	}
	
	
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
	public static String getUuidS() {
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
