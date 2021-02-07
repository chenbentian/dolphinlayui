/**
 *
 * @(#) CoreSmUtil.java
 * @Package com.ls.arwear.commoncore.util
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 *  类描述：国密加密
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月27日 下午2:15:44   cbt-34201   Created.
 *           
 */
public class CoreSmUtil {
	
	private static final String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEOL0HAxQV4GJInYtpRDtF8yeARgyTODoE6rW8uFlhadvWR+hxhkCQMECpzVSdwSD5zX3ro5BZ2VgLdf9Zx/3bew==";
			//FrameworkPropertyConfigurer.getPlatformconfig("SM2.privateKey");
	private static final String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg3rw34TisNNPCj2Fx1qHEydBrYhaB6PBr3xdLtb/lj+qgCgYIKoEcz1UBgi2hRANCAAQ4vQcDFBXgYkidi2lEO0XzJ4BGDJM4OgTqtby4WWFp29ZH6HGGQJAwQKnNVJ3BIPnNfeujkFnZWAt1/1nH/dt7";

			//FrameworkPropertyConfigurer.getPlatformconfig("SM2.publicKey");

	public static String decryptStr(String encryptStr) {
		SM2 sm2 = SmUtil.sm2(privateKey, null);
		String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
		return decryptStr;
	}
	
	public static String encryptStr(String decryptStr) {
		SM2 sm2 = SmUtil.sm2(null, publicKey);
		String encryptStr = sm2.encryptBcd(decryptStr, KeyType.PublicKey);
		return encryptStr;
	}
	
	


}
