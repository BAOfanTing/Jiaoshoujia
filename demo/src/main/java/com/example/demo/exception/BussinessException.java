package com.example.demo.exception;

import com.example.demo.response.ResponseCode;
import lombok.Data;

@Data
public class BussinessException extends RuntimeException
{
    private int code;
    private String message;
    public BussinessException(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public BussinessException( String message)
    {
        this.message = message;
    }

    public BussinessException(ResponseCode responseCode)
    {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

}

