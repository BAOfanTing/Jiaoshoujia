package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.ResponeseResult;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@Tag(name="管理员信息界面")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 查询所有用户
     */
    @CrossOrigin
    @Operation(summary = "查询所有用户")
    @GetMapping("/list")
    public ResponeseResult list()
    {
        return ResponeseResult.success(adminService.listAll());
    }

    @Operation(summary = "修改管理员")
   @GetMapping("/update")
    public boolean update(@RequestBody Admin admin)
   {
       return adminService.updateById(admin);
   }
   @CrossOrigin
   @Operation(summary = "新增用户")
   @PostMapping("/add")
    public ResponeseResult add(@RequestBody Admin admin)
   {
       return adminService.save(admin) ? ResponeseResult.success() : ResponeseResult.fail();
   }
   @CrossOrigin
   @Operation(summary = "删除用户")
   @DeleteMapping("/delete")
    public boolean delete(int id)
   {
       return adminService.removeById(id);
   }


}
