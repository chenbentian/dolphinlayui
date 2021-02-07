/**
 *
 * @(#) SqlTest.java
 * @Package com.bt.dolphin.system
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2021年1月25日 上午11:26:19   cbt-34201   Created.
 *           
 */
public class SqlTest {
	
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		 
		String sqltr = " lll小陈SDSDD少时诵诗书所所所所所所所所所所所所所所所所所所"
				+ "收到手机看接口设计空间可抵扣就空间看手机打开-------3333ddddddd"
				+ "dddddddddddddddss"
				+ "dddddddddddddddddddddddddds"
				+ "ddddddddddddddddd"
				+ "ddddddddddddddddddddd"
				+ "sssssssss"
				+ "ssssssssssssssssssssssssssssss"
				+ "ssssssssssssssssssssssssssss"
				+ "sssssssssssssssssssssss"
				+ "dddddddddddddddddddddd"
				+ "ssssssssss"
				+ "ddd所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所所";

		KeyPair pair = SecureUtil.generateKeyPair("SM2");
		byte[] privateKey = pair.getPrivate().getEncoded();
		byte[] publicKey = pair.getPublic().getEncoded();
		System.out.println("publicKey: "+ Base64.encode(publicKey));
		System.out.println("privateKey: "+Base64.encode(privateKey));
		SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
		// 公钥加密，私钥解密
		String encryptStr = sm2.encryptBcd(sqltr, KeyType.PublicKey);
		System.out.println("encryptStr: "+encryptStr);

		Map params = new HashMap();
		params.put("sqlParam", encryptStr);
		/*HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<String> entity = new HttpEntity<String>(JSONUtil.toJsonStr(params),headers);
		  ResponseEntity<Map> postExchange = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		  result = (Map)postExchange.getBody();*/
		
		String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
		System.out.println("decryptStr: "+decryptStr);

		String tt = StrUtil.str(Base64.decode("f8UD3eVLRQ2FA675NYbKwI3cDXTOyiqGC8qt6fXm38Y"),"utf-8"); 
		System.out.println("tt: "+tt);
		String dd = Base64.encode("arwear-app-jwtHS256");
		System.out.println("dd: "+dd);
		
	}
}
