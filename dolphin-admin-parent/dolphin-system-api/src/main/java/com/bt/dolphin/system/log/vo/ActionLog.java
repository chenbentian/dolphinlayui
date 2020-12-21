package com.bt.dolphin.system.log.vo;

import java.io.Serializable;
import java.util.Date;


public class ActionLog implements Serializable {
    private Long id;
    private String name;
    private Byte type;
    private String ipaddr;
    private String clazz;
    private String method;
    private String model;
    private Long recordId;
    private String message;
    private Date createDate;
    private String operName;

    public ActionLog(){}
    /**
     * 封装日志对象
     * @param name 日志名称
     * @param message 日志消息
     */
    public ActionLog(String name, String message){
        this.name = name;
        this.message = message;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
    
    
}
