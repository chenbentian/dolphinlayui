package com.bt.dolphin.common.util;

import java.util.List;

import com.bt.dolphin.common.data.URL;
import com.bt.dolphin.common.enums.ResultEnum;
import com.bt.dolphin.common.vo.QueryResultObject;
import com.bt.dolphin.common.vo.ResultVo;

import cn.hutool.core.util.StrUtil;

public class ResultUtils {

	public static QueryResultObject wrappQueryResult(List list, int count) {
		QueryResultObject queryResult = new QueryResultObject();
		queryResult.setCount(count);
		queryResult.setData(list);
		queryResult.setCode("0");
		queryResult.setMsg("查询成功");
		return queryResult;
	}

	public static ResultVo error(Integer code, String msg) {
		ResultVo resultVo = new ResultVo();
		resultVo.setMsg(msg);
		resultVo.setCode(code);
		return resultVo;
	}

	/**
	 * 操作有误，使用默认400错误码
	 * 
	 * @param msg
	 *            提示信息
	 */
	public static ResultVo error(String msg) {
		Integer code = ResultEnum.ERROR.getCode();
		return error(code, msg);
	}
	
	  /**
     * 操作成功
     * @param msg 提示信息
     * @param object 对象
     */
    public static ResultVo success(String msg, Object object){
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setMsg(msg);
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * 操作成功，返回url地址
     * @param msg 提示信息
     * @param url URL包装对象
     */
    public static ResultVo success(String msg, URL url){
        return success(msg, url.getUrl());
    }

}
