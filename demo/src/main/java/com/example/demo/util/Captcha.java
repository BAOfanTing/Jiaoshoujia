package com.example.demo.util;


import lombok.Data;

@Data
public class Captcha {

    //验真码ID
    private String captchaID;
    //验证码字符串
    private String captchaImage;

}
