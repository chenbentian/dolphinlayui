package com.bt.dolphin.common.enums;

import com.bt.dolphin.common.constant.StatusConst;

/**
 * 数据状态枚举-用于逻辑删除控制
 * @author 小懒虫
 * @date 2018/8/14
 */
public enum StatusEnum {

    /**
     * 正常的数据
     */
    OK(StatusConst.OK, "启用"),
    /**
     * 被冻结的数据，不可用
     */
    FREEZED(StatusConst.FREEZED, "冻结"),
    /**
     * 数据已被删除
     */
    DELETE(StatusConst.DELETE, "删除");

    private Byte code;

    private String message;

    StatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }

	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
}

