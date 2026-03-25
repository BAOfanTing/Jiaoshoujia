package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("admin")
@Schema(description = "管理员信息实体类")
public class Admin {

    @TableId
    private Integer id;
    @Schema(description = "用户名")
    private String username;
    private String userpwd;
    private String name;
    private Integer age;
    private Integer sex;
    private String tel;
    private String headurl;


    //不是数据表里的字段
    @TableField(exist = false)
    private String captchaID;
    @TableField(exist = false)
    private String captchaCode;
    @TableField(exist = false)
    private String token;
}
