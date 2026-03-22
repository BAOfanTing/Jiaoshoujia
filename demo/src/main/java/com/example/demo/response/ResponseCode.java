package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200, "成功"),
    ERROR(500, "操作失败"),
    USERNAME_EXIST(1001, "用户名已存在");

    private int code;
    private String message;

}
