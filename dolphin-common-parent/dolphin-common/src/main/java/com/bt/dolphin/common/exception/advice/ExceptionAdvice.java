package com.bt.dolphin.common.exception.advice;

/**
 * 异常通知器接口
 * @author cbt
 *
 */
public interface ExceptionAdvice {

    /**
     * 运行
     * @param e 异常对象
     */
    void run(RuntimeException e);
}
