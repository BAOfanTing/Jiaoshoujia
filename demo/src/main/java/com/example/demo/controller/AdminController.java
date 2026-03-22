package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Admin;
import com.example.demo.response.R;
import com.example.demo.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/list")
    public R list(@RequestBody Admin  admin,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(admin.getName()!=null,Admin::getName,admin.getUsername());
        queryWrapper.like(admin.getTel()!=null,Admin::getTel,admin.getTel());
        PageHelper.startPage(pageNum,pageSize);
        List< Admin> list = adminService.list();
        PageInfo<Admin> pageInfo = new PageInfo(list);
        return R.data(pageInfo);
    }

    @Operation(summary = "修改管理员")
   @GetMapping("/update")
    public R update(@RequestBody Admin admin)
   {
       adminService.updateById(admin);
       return R.success();
   }
   @CrossOrigin
   @Operation(summary = "新增用户")
   @PostMapping("/add")
    public R add(@RequestBody Admin admin)
   {
       adminService.save(admin);
       return  R.success();
   }
   @CrossOrigin
   @Operation(summary = "删除用户")
   @DeleteMapping("/delete")
    public R delete(int id)
   {
       adminService.removeById(id);
       return R.success();
   }


}
