package com.example.demo.exception;

import com.example.demo.response.ResponseCode;
import lombok.Data;

/**
 * 业务异常类
 * 用于封装业务逻辑中抛出的异常信息，包含状态码和错误消息
 */
@Data
public class BusinessException extends RuntimeException
{
    /**
     * 异常状态码
     */
    private int code;

    /**
     * 异常消息
     */
    private String message;

    /**
     * 构造方法：使用状态码和消息初始化异常
     * @param code 状态码
     * @param message 错误消息
     */
    public BusinessException(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法：仅使用消息初始化异常，状态码默认为 0
     * @param message 错误消息
     */
    public BusinessException(String message)
    {
        this.message = message;
    }

    /**
     * 构造方法：使用响应码枚举初始化异常
     * @param responseCode 响应码枚举对象，包含预定义的状态码和消息
     */
    public BusinessException(ResponseCode responseCode)
    {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}

