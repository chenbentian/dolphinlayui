/**
 *
 * @(#) JsonUtil.java
 * @Package com.bt.dolphin.irs.common.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.irs.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年8月12日 下午2:39:17   cbt-34201   Created.
 *           
 */
public class JsonUtil {
	
	 private static SerializerFeature[] features = new SerializerFeature[] {
			 SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty,
			 SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat
		};
	
	
	 public static String toJSONString(Object object) {
		 return JSON.toJSONString(object, features);
	 }
	
}
