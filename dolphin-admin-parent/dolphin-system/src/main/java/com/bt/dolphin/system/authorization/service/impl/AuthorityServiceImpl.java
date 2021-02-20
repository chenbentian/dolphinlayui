package com.bt.dolphin.system.authorization.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bt.dolphin.system.authorization.api.IAuthorityService;
import com.bt.dolphin.system.cache.api.ICache;
import com.bt.dolphin.system.menu.api.SysApplicationService;
import com.bt.dolphin.system.menu.dao.SysPermissionDao;
import com.bt.dolphin.system.menu.vo.SysPermissionVo;

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

	@Autowired
	@Qualifier("redisService")
	private ICache redisService;
	
	@Autowired
	private SysApplicationService sysApplicationService;
	
	@Autowired
	private SysPermissionDao sysPermissionDao;
	
	@Override
	public boolean hasPrivByUrl(String accountName, String url, String appName) {
		String appId = sysApplicationService.getAppIdByAppName(appName);
		/*if (this.accountService.isAdmin(accountName)) {
			return true;
		}*/

		String authAttrValue = "";
		if (this.redisService.containsKey("DOLPHIN_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url)) {
			authAttrValue = (String) this.redisService
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
			this.redisService.put("DOLPHIN_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, authAttrValue);

			return true;
		}
		
		int count = sysPermissionDao.hasPriv(accountName, url, appId);
		boolean rs = count > 0;
		System.out.println("DOLPHIN_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url + "  has priv:" + rs);

		if (rs) {
			this.redisService.put("DOLPHIN_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, "T");
		} else {
			this.redisService.put("DOLPHIN_PERMISSION_ATTRIBUTE_" + appName + "$" + accountName + "$" + url, "F");
		}
		return rs;
	}

	public String getValidUrlValue(String url, String appName, String attrCode) {
		String appId = "";
		if ((attrCode == null) || ("".equals(attrCode))) {
			appId = sysApplicationService.getAppIdByAppName(appName);
			SysPermissionVo bo = this.sysPermissionDao.getPermissionByUrlAndAppId(url, appId);
			if ((bo == null) || ("".equals(bo))) {
				return "F";
			}
			return "T";
		}

		String attrCodeValue = "";
		if (this.redisService.containsKey("DOLPHIN_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode)) {
			attrCodeValue = (String) this.redisService
					.get("DOLPHIN_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode);
		} else {
			appId = sysApplicationService.getAppIdByAppName(appName);
			attrCodeValue = this.sysPermissionDao.getPermissionAttrValueByUrlAndCode(appId, url, attrCode);

			if (attrCodeValue != null) {
				this.redisService.put("DOLPHIN_PERMISSION_ATTRCODE_" + appName + "$" + url + "$" + attrCode, attrCodeValue);
			}
		}

		return attrCodeValue;
	}

}
