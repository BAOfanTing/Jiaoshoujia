package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAll();

    IPage pageC(Page<User> page);

    IPage pageC1(Page<User> page, LambdaQueryWrapper<User> lambdaQueryWrapper);
}
