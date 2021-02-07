package com.bt.dolphin.system.authorization.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bt.dolphin.system.authorization.api.IAuthorityService;

/**
 * 类描述：
 * 
 * @author: cbt-34201
 * @version $Id: Exp$
 *
 *          History: 2020年12月25日 下午2:41:10 cbt-34201 Created.
 * 
 */
@Service
@Transactional
public class AuthorityServiceImpl implements IAuthorityService {

	@Override
	public boolean hasPrivByUrl(String accountName, String url, String appName) {
	/*	String appId = this.applicationService.getAppIdByAppName(appName);

		if (this.accountService.isAdmin(accountName)) {
			return true;
		}

		String authAttrValue = "";
		if (this.cacheService.containsKey("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url)) {
			authAttrValue = (String) this.cacheService
					.get("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url);

			if ("T".equals(authAttrValue)) {
				return true;
			}

			String tmp = getValidUrlValue(url, appName, "AUTHORIZED");
			if ("F".equals(tmp)) {
				return true;
			}
			return false;
		}

		authAttrValue = getValidUrlValue(url, appName, "AUTHORIZED");

		if ("F".equals(authAttrValue)) {
			this.cacheService.put("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, authAttrValue);

			return true;
		}
		int count = this.accountPermissionRelaDao.hasPriv(accountName, url, appId);

		boolean rs = count > 0;
		System.out.println("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url + "has priv:" + rs);

		if (rs) {
			this.cacheService.put("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, "T");
		} else {
			this.cacheService.put("EU_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, "F");
		}

		return rs;*/
		return true;
	}

	public String getValidUrlValue(String url, String appName, String attrCode) {
		/*String appId = "";
		if ((attrCode == null) || ("".equals(attrCode))) {
			appId = this.applicationService.getAppIdByAppName(appName);
			PermissionBO bo = this.permissionDao.getPermissionByUrlAndAppId(url, appId);

			if ((bo == null) || ("".equals(bo))) {
				return "F";
			}
			return "T";
		}

		String attrCodeValue = "";
		if (this.cacheService.containsKey("EU_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode)) {
			attrCodeValue = (String) this.cacheService
					.get("EU_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode);
		} else {
			appId = this.applicationService.getAppIdByAppName(appName);
			attrCodeValue = this.permissionDao.getPermissionAttrValueByUrlAndCode(appId, url, attrCode);

			if (attrCodeValue != null) {
				this.cacheService.put("EU_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode, attrCodeValue);
			}
		}

		return attrCodeValue;*/
		return null;
	}

}
