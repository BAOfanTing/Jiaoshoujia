package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.ResponeseResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/page")
    public Page<User> page(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize)
    {
        Page<User> page = new Page<>(pageNum,pageSize);
        return userService.page(page);
    }
    /**
     * 查询所有用户
     */
    @CrossOrigin
    @GetMapping("/list")
    public ResponeseResult list()
    {
        return ResponeseResult.success(userService.listAll());
    }
    @PostMapping("/listPage")
    /**
     * 分页查询用户列表
     * @param query 包含页码、每页大小及查询参数（如 name）的对象
     * @return 符合条件的用户列表
     */
    public List<User> listPage(@RequestBody QueryPageParam query)
    {
        // 打印分页参数
        System.out.println(query.getPageNum());
        System.out.println(query.getPageSize());

        // 获取查询参数映射
        HashMap param = query.getParam();
        System.out.println((String)param.get("name"));
        String name=(String)param.get("name");
        
        // 构建分页对象
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        
        // 构建动态查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);

        // 执行分页查询
        IPage result = userService.page(page,lambdaQueryWrapper);
        System.out.println("total="+result.getTotal());
        
        // 返回当前页记录
        return result.getRecords();
    }

    /**
     * 自定义分页查询
     * @param query
     * @return
     */

    @CrossOrigin
    @PostMapping("/listPageC")
    public ResponeseResult listPageC(@RequestBody QueryPageParam query)
    {
        // 获取查询参数映射
        HashMap param = query.getParam();
        System.out.println((String)param.get("name"));
        String name=(String)param.get("name");

        // 构建分页对象
        Page<User> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        // 构建动态查询条件
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(User::getName,name);

        // 执行分页查询
        IPage result = userService.pageC(page);
//        System.out.println("total="+result.getTotal());

        // 返回当前页记录
        return ResponeseResult.success(result.getRecords(),result.getTotal());
    }
   @GetMapping("/mod")
    public boolean mod(@RequestBody User user)
   {
       return userService.updateById(user);
   }
   @PostMapping("/add")
    public boolean add(User user)
   {
       return userService.save(user);
   }

   @DeleteMapping("/delete")
    public boolean delete(int id)
   {
       return userService.removeById(id);
   }

   @PostMapping("/saveOrMod")
   public boolean saveOrMod(@RequestBody User user)
   {
       return userService.saveOrUpdate(user);
   }

   @CrossOrigin
   @PostMapping("/Search")
    public List<User> search(@RequestParam(required = false) String name,@RequestParam(required = false) String no)
   {
       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       if(name != null && !name.isEmpty())
       {
          queryWrapper.like("name",name);
       }
       if(no != null && !no.isEmpty())
       {
           queryWrapper.like("no",no);
       }
       return userService.list(queryWrapper);
   }

}
