package com.bt.dolphin.common.vo;


/**
 * 
 * @author cbt
 *
 * @param <T>
 */
public class ResultVo<T> {

    /** 状态码 */
	private Integer code;

    /** 提示信息 */
    private String msg;

    /** 响应数据 */
    private T data;
    
    private int count;

    

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    
    
}
