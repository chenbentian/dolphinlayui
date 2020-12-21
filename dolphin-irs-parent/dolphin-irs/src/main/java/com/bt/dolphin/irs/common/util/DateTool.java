package com.bt.dolphin.irs.common.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import com.alibaba.fastjson.JSONArray;


public class DateTool {
	/**
	 * 方法说明：格式化日期格式
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:48:30 PM
	 * History: Mar 5, 2015 2:48:30 PM panhuibin Created
	 * @param str
	 * @return
	 */
	public static String format(String str){
		String[] strs = str.split("-");
		str="";
		for (int i = 0; i < strs.length; i++) {
			str += strs[i];
		}
		return str;
	}
	/**
	 * 方法说明：获取对应当前日期
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:48:30 PM
	 * History: Mar 5, 2015 2:48:30 PM panhuibin Created
	 * @param str
	 * @return
	 */
	public static String getCurrentDate(String fmtstr){
		SimpleDateFormat fmt = new SimpleDateFormat(fmtstr);
		return fmt.format(Calendar.getInstance().getTime());
	}
	/**
	 * 方法说明：获取对应昨日日期
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:48:30 PM
	 * History: Mar 5, 2015 2:48:30 PM panhuibin Created
	 * @param str
	 * @return
	 */
	public static String getYesDate(String fmtstr){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		SimpleDateFormat fmt = new SimpleDateFormat(fmtstr);
		return fmt.format(c.getTime());
	}
	/**
	 * 方法说明：格式化日期
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:48:30 PM
	 * History: Mar 5, 2015 2:48:30 PM panhuibin Created
	 * @param str
	 * @return
	 */
	public static String format(String searchMonth, String str) {
		String[] strs = searchMonth.split("-");
		str="";
		for (int i = 0; i < 2; i++) {
			str += strs[i];
		}
		return str;
	}
	/**
	 * 方法说明：获取上N个月的年月份
	 * Author: panhuibin
	 * Create Date: Mar 22, 2014 2:13:49 PM
	 * History: Mar 22, 2014 2:13:49 PM panhuibin Created
	 * @return
	 */
	public static  String[] getLastNMonth(int n){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String[] strs= new String[n];
		//int j= 12;
		for(int i=n;i>0;i--){
			month--;
			if(month>0){
				strs[i-1] = year+""+(month<10?("0"+month):month);
			}else{
				month=12;
				year--;
				strs[i-1] = year+""+(month<10?("0"+month):month);
			}
			
		}
		return strs;
	}
	/**
	 * 方法说明：获取前N个月的日期数组集合
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static  JSONArray getLastNMonthJson(int n){
		JSONArray array = new JSONArray();
		String[] strs = getLastNMonth(n);
		for (int i = 0; i < strs.length; i++) {
			array.add(strs[i]);
		}
		return array;
	}
	/**
	 * 方法说明：获取前N个年的日期JSON数组集合
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static  JSONArray getLastNYearJson(int n){
		JSONArray array = new JSONArray();
		String[] strs = getLastNYear(n);
		for (int i = 0; i < strs.length; i++) {
			array.add(strs[i]);
		}
		return array;
	}
	
	/**
	 * 方法说明：获取前N个年的日期JSON数组集合(去年及去年以前的N年)
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static  JSONArray getLastNYearJson2(int n){
		JSONArray array = new JSONArray();
		String[] strs = getLastNYear2(n);
		for (int i = 0; i < strs.length; i++) {
			array.add(strs[i]);
		}
		return array;
	}
	
	
	/**
	 * 方法说明：获取前N个月的日期数组集合
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static String[] getLastNYear(int n) {
		Calendar c = Calendar.getInstance();
		String[] strs= new String[n];
		int year = c.get(Calendar.YEAR);
		for (int i = 0; i < n; i++) {
			strs[i] =year+"";
			year--;
		}
		Arrays.sort(strs);
		return strs;
	}
	
	/**
	 * 方法说明：获取前N个月的日期数组集合（去年及去年以前）
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static String[] getLastNYear2(int n) {
		Calendar c = Calendar.getInstance();
		String[] strs= new String[n];
		int year = c.get(Calendar.YEAR);
		for (int i = 0; i < n; i++) {
			year--;
			strs[i] =year+"";
		}
		Arrays.sort(strs);
		return strs;
	}
	
	
	/**
	 * 方法说明：获取去年日期
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:50:09 PM
	 * History: Mar 5, 2015 2:50:09 PM panhuibin Created
	 * @param n
	 * @return
	 */
	public static String getLastYear(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR)-1;
		return year+"";
	}
	public static String getLastNYearStr(int n){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR)-2;
		return year+"";
	}
}
