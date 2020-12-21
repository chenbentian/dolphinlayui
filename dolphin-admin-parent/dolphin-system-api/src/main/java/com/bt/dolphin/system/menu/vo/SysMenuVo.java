/**
 *
 * @(#) SysMenuVo.java
 * @Package com.bt.dolphin.system.menu.vo
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

package com.bt.dolphin.system.menu.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *  类描述：
 * 
 *  @author:  cbt-34201
 *  @version  $Id: Exp$ 
 *
 *  History:  2020年9月1日 下午5:04:34   cbt-34201   Created.
 *           
 */
public class SysMenuVo {
	private String menuId;
	private String menuParentid;//上级菜单ID 
	private String menuIndex;//菜单索引
	private String menuTitle;//菜单名称
	private String menuIcon;//菜单图标
	private String menuType;//菜单类型MenuPage,MenuFolder
	private String objId;//权限ID
	private String menuStatus;//菜单状态1,2
	private String actionAppContext;//应用系统根路径
	private String actionAuthorized;//是否为权限项T,F
	private String actionNormal;//是否为普通项T,F
	private String actionSystemic;//是否为系统项 
	private String actionSafe;//是否为安全项 
	private String actionAudit;//是否为审计项
	private String objAppId;//权限应用ID 
	private String objPath;//权限路径
	private String menuAppId;//权限应用ID 
	private String menuPath;//菜单路径 
	private String menuKind;
	private int openPosition;//是否在新窗口打开, 1：否 ；2：是',
	private int isLeaf;
	private String href; 
	private Boolean isOpen;
	
	private Map<String, SysMenuVo> subMenu = new HashMap<>();
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuParentid() {
		return menuParentid;
	}
	public void setMenuParentid(String menuParentid) {
		this.menuParentid = menuParentid;
	}
	public String getMenuIndex() {
		return menuIndex;
	}
	public void setMenuIndex(String menuIndex) {
		this.menuIndex = menuIndex;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	public String getActionAppContext() {
		return actionAppContext;
	}
	public void setActionAppContext(String actionAppContext) {
		this.actionAppContext = actionAppContext;
	}
	public String getActionAuthorized() {
		return actionAuthorized;
	}
	public void setActionAuthorized(String actionAuthorized) {
		this.actionAuthorized = actionAuthorized;
	}
	public String getActionNormal() {
		return actionNormal;
	}
	public void setActionNormal(String actionNormal) {
		this.actionNormal = actionNormal;
	}
	public String getActionSystemic() {
		return actionSystemic;
	}
	public void setActionSystemic(String actionSystemic) {
		this.actionSystemic = actionSystemic;
	}
	public String getActionSafe() {
		return actionSafe;
	}
	public void setActionSafe(String actionSafe) {
		this.actionSafe = actionSafe;
	}
	public String getActionAudit() {
		return actionAudit;
	}
	public void setActionAudit(String actionAudit) {
		this.actionAudit = actionAudit;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjPath() {
		return objPath;
	}
	public void setObjPath(String objPath) {
		this.objPath = objPath;
	}
	public String getMenuKind() {
		return menuKind;
	}
	public void setMenuKind(String menuKind) {
		this.menuKind = menuKind;
	}
	public Map<String, SysMenuVo> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(Map<String, SysMenuVo> subMenu) {
		this.subMenu = subMenu;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getOpenPosition() {
		return openPosition;
	}
	public void setOpenPosition(int openPosition) {
		this.openPosition = openPosition;
	}
	public String getObjAppId() {
		return objAppId;
	}
	public void setObjAppId(String objAppId) {
		this.objAppId = objAppId;
	}
	public String getMenuAppId() {
		return menuAppId;
	}
	public void setMenuAppId(String menuAppId) {
		this.menuAppId = menuAppId;
	}
	
	
	
}
