package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Admin;
import com.example.demo.exception.BusinessException;
import com.example.demo.response.R;
import com.example.demo.response.ResponseCode;
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

    @Operation(summary = "查询所有用户")
    @PostMapping("/list")
    @CrossOrigin
    public R<PageInfo<Admin>> list( @RequestBody Admin admin,@RequestParam int pageNum, @RequestParam int pageSize)
    {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(admin.getName()!=null,Admin::getName,admin.getName());
        System.out.println(admin.getName());
        queryWrapper.like(admin.getTel()!=null,Admin::getTel,admin.getTel());
        queryWrapper.orderByDesc(Admin::getId);
        PageHelper.startPage(pageNum,pageSize);
        List< Admin> list = adminService.list(queryWrapper);
        PageInfo<Admin> pageInfo = new PageInfo(list);
        return R.data(pageInfo);
    }


    @Operation(summary = "修改管理员")
    @PostMapping("/update")
    @CrossOrigin
    public R update(@RequestBody Admin admin)
   {
       adminService.updateById(admin);
       return R.success();
   }

   @Operation(summary = "新增用户")
   @PostMapping("/add")
   @CrossOrigin
    public R add(@RequestBody Admin admin)
   {
       LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
       queryWrapper.eq(Admin::getUsername,admin.getUsername());
       long count = adminService.count(queryWrapper);
       if(count>0)
       {
           throw new BusinessException(ResponseCode.USERNAME_EXIST);
       }
       adminService.save(admin);
       return  R.success();
   }

   @Operation(summary = "删除用户")
   @PostMapping("/delete")
   @CrossOrigin
    public R delete(@RequestParam List<Integer> ids)
   {
       adminService.removeByIds(ids);
       return R.success();
   }


}
