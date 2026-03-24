package com.example.demo.controller;


import com.example.demo.exception.BusinessException;
import com.example.demo.response.R;
import com.example.demo.response.ResponseCode;
import com.example.demo.util.CaptchCache;
import com.example.demo.util.Captcha;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {


    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private CaptchCache captchCache;

    @CrossOrigin
    @GetMapping("/getCaptcha")
    public R<Captcha> getCaptcha() {
        String captchaText = defaultKaptcha.createText();
        BufferedImage imgae = defaultKaptcha.createImage(captchaText);
        String base64Code = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(imgae, "jpg", os);
            base64Code = Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.CREATE_CAPTCHA_ERROR);
        }
        Captcha captcha = new Captcha();
        captcha.setCaptchaImage("data:image/png;base64,"+base64Code);
        String captchaID = UUID.randomUUID().toString().replace("-", "");
        captcha.setCaptchaID(captchaID);

        //缓存验证码
        captchCache.storeCaptcha(captchaID, captchaText);
        return R.data(captcha);


    }
}
