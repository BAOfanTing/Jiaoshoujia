package com.example.demo.util;


import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CaptchCache {
    //缓存验证码
    private static ConcurrentHashMap<String, String> captchaMap = new ConcurrentHashMap<>();

    //存储验证码
    public void storeCaptcha(String captchaID, String captcha) {
        captchaMap.put(captchaID, captcha);
    }

    //移除验证码
    public void removeCaptcha(String captchaID) {
        captchaMap.remove(captchaID);
    }

    public boolean validateCaptcha(String captchaID, String captcha) {
        //获取存储的验证码
        String storedCaptcha = captchaMap.get(captchaID);
        //验证码不存在
        if(storedCaptcha == null)
        {
            return false;
        }
        else if(storedCaptcha.equals(captcha))
        {
            //验证码正确
            removeCaptcha(captchaID);
            return true;
        }
        else
        {
            //验证码错误
            return false;
        }
    }

}


