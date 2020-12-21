package com.bt.dolphin.system.param.vo;

import java.io.Serializable;

/**
 * 参数
 * @author cbt
 *
 */
public class SysParamVo implements Serializable {
	
    private String paramId;
    private String paramSortCode;
    private String paramCode; //参数编码
    private String paramName;//参数名称
    private String paramValueType;
    private String defaultValue;
    
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getParamSortCode() {
		return paramSortCode;
	}
	public void setParamSortCode(String paramSortCode) {
		this.paramSortCode = paramSortCode;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValueType() {
		return paramValueType;
	}
	public void setParamValueType(String paramValueType) {
		this.paramValueType = paramValueType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
    
    
    
    

}