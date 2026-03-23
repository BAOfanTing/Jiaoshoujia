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
     * 当 Controller 中抛出普通异常时，使用此方法进行统一处理
     * @param e 捕获到的异常对象，包含异常信息
     * @return 统一的失败响应结果 R 对象，包含异常消息
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        return R.fail(e.getMessage());
    }

    /**
     * 处理业务异常 BusinessException
     * 专门用于处理业务逻辑中抛出的自定义业务异常，返回包含状态码和消息的响应
     * @param e 捕获到的业务异常对象，包含业务错误码和错误消息
     * @return 统一的失败响应结果 R 对象，包含错误状态码和错误消息
     */
    @ExceptionHandler(BusinessException.class)
    public R handleBussinessException(BusinessException e) {
        return R.fail(e.getCode(),e.getMessage());
    }
}
