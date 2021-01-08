package com.bt.dolphin.common.exception;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bt.dolphin.common.exception.advice.ResultExceptionAdvice;
import com.bt.dolphin.common.util.ResultUtils;
import com.bt.dolphin.common.util.SpringContextUtil;
import com.bt.dolphin.common.vo.ResultVo;


/**
 * 全局统一异常处理
 * @author 小懒虫
 * @date 2018/8/14
 */
//@ControllerAdvice
public class ResultExceptionHandler {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultExceptionHandler.class);


    /** 拦截自定义异常 */
    @ExceptionHandler(ResultException.class)
    @ResponseBody
    public ResultVo resultException(ResultException e){
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /** 拦截表单验证异常 */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVo bindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        return ResultUtils.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /** 拦截未知的运行时异常 */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultVo runtimeException(RuntimeException e) {
        ResultExceptionAdvice resultExceptionAdvice = SpringContextUtil.getBean(ResultExceptionAdvice.class);
        resultExceptionAdvice.runtimeException(e);
        LOGGER.error("【系统异常】", e);
        return ResultUtils.error(500, e.getMessage());
    }
}
