
package com.example.demo.exception;

import com.example.demo.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 用于统一捕获和处理所有 Controller 层抛出的异常
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 处理所有 Exception 类型的异常
     * @param e 捕获到的异常对象
     * @return 统一的失败响应结果，包含异常消息
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public R handleBussinessException(BusinessException e) {
        return R.fail(e.getCode(),e.getMessage());
    }
}