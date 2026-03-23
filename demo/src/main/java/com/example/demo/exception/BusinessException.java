package com.example.demo.exception;

import com.example.demo.response.ResponseCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException
{
    private int code;
    private String message;
    public BusinessException(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(ResponseCode responseCode)
    {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}

