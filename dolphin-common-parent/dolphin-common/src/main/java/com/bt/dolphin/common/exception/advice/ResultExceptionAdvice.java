package com.bt.dolphin.common.exception.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.bt.dolphin.common.util.SpringContextUtil;

/**
 * 异常通知器
 * @author cbt
 * @date 2019/4/5
 */
@ControllerAdvice
public class ResultExceptionAdvice {
    /** 运行切入程序集合 */
    private List<ExceptionAdvice> proceed = new ArrayList<>();

    /** 添加切入程序 */
    public void putProceed(ExceptionAdvice advice){
        proceed.add(advice);
    }

    /** 执行异常通知 */
    public void runtimeException(RuntimeException e){
        for (ExceptionAdvice ea : proceed) {
            ExceptionAdvice advice = SpringContextUtil.getBean(ea.getClass());
            advice.run(e);
        }
    }
}
