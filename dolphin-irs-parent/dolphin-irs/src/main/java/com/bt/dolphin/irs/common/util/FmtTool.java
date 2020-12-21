package com.bt.dolphin.irs.common.util;

import java.text.NumberFormat;

public class FmtTool {
	/**
	 * 方法说明：格式化保留小数位
	 * Author: panhuibin
	 * Create Date: Mar 5, 2015 2:52:02 PM
	 * History: Mar 5, 2015 2:52:02 PM panhuibin Created
	 * @param num
	 * @param x
	 * @return
	 */
	public static String keepPoint(double num,int x ){
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(x);
        String tmp =nf.format(num);
        String[] array = tmp.split("\\.");
        if (x==0) {
			return tmp.replace(",", "");
		}
        if (array.length==1) {
        	tmp = tmp+".";
        	for (int i = 0; i < x; i++) {
				tmp+="0";
			}
        	return tmp.replace(",", "");
		}else if(array.length==2){
			if(array[1].length()<x){
				for (int i = 0; i < x-array[1].length(); i++) {
					tmp=tmp+"0";
				}
				return tmp.replace(",", "");
			}else if(array[1].length()==x){
				return tmp.replace(",", "");
			}
			return tmp.replace(",", "");
		}else {
			return tmp.replace(",", "");
		}
	}
}
