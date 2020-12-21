package com.bt.dolphin.desk.login.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bt.dolphin.common.enums.StatusEnum;

public class Menu implements Serializable {
    private Long id;
    private Long pid;
    private String pids;
    private String title;
    private String url;
  //  private String perms;
    private String icon;
    private Byte type;
    private Integer sort;
    private String remark;
    private Date createDate;
    private Date updateDate;
    private Byte status = StatusEnum.OK.getCode();

//    @ManyToMany(mappedBy = "menus")
//    @JsonIgnore
//    private Set<Role> roles = new HashSet<>(0);

    private Map<Long, Menu> children = new HashMap<>();

    public Menu(){

    }

    public Menu(Long id, String title, String pids) {
        this.id = id;
        this.title = title;
        this.pids = pids;
    }

    public void setPids(String pids) {
        if (pids.startsWith(",")){
            pids = pids.substring(1);
        }
        this.pids = pids;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Map<Long, Menu> getChildren() {
		return children;
	}

	public void setChildren(Map<Long, Menu> children) {
		this.children = children;
	}

	public String getPids() {
		return pids;
	}
    
    
}
