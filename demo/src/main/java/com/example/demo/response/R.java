
package com.example.demo.response;

import lombok.Data;
import lombok.Getter;

/**
 * 统一响应结果类
 * 用于封装所有接口返回的数据格式
 * @param <T> 响应数据的类型
 */
@Data
@Getter
public class R<T> {
    /** 响应状态码 */
    private int code;
    /** 响应消息 */
    private String msg;
    /** 响应数据 */
    private T data;

    /**
     * 私有构造函数，仅传入状态码
     * @param code 响应状态码
     */
    private R(int code)
    {
        this.code = code;
    }

    /**
     * 私有构造函数，传入状态码和消息
     * @param code 响应状态码
     * @param msg 响应消息
     */
    private R(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 私有构造函数，传入完整的响应信息
     * @param code 响应状态码
     * @param msg 响应消息
     * @param data 响应数据
     */
    private R(int code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功响应（无数据）
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> R<T> success()
    {
        return new R<>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 返回成功响应（带自定义消息）
     * @param msg 成功消息内容
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> R<T> success(String  msg)
    {
        return new R<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 返回成功响应（带数据）
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 包含数据的成功响应对象
     */
    public static <T> R<T> success(T data)
    {
        return new R<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回失败响应（带消息和数据）
     * @param msg 失败消息内容
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 失败响应对象
     */
    public static <T> R<T> fail(String msg, T data)
    {
        return new R<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    /**
     * 返回失败响应（仅带消息）
     * @param msg 失败消息内容
     * @param <T> 数据类型
     * @return 失败响应对象
     */
    public static <T> R<T> fail(String msg)
    {
        return new R<>(ResponseCode.ERROR.getCode(), msg);
    }

    /**
     * 返回失败响应（自定义状态码和消息）
     * @param code 自定义状态码
     * @param msg 失败消息内容
     * @param <T> 数据类型
     * @return 自定义的失败响应对象
     */
    public static <T> R<T> fail(int code, String msg)
    {
        return new R<>(code, msg);
    }

    /**
     * 返回仅包含数据的成功响应
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 包含数据的成功响应对象
     */
    public static <T> R<T> data(T data)
    {
        return new R<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }
}