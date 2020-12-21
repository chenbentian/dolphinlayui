package com.bt.dolphin.system.user.vo;

import java.io.Serializable;

public class SysResourceVo implements Serializable {

    private Long id;

    private String url;

    private String name;
    private String permission;
    private ResType type;
    private String group;
    private String remark;
    public enum ResType {
        /**
         * 可以做菜单栏的导航链接
         */
        NAV_LINK,

        /**
         * 其他类型
         */
        OTHER
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public ResType getType() {
		return type;
	}
	public void setType(ResType type) {
		this.type = type;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    

}
