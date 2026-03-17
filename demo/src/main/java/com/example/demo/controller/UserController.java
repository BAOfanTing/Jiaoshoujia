package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

   @GetMapping("/{id}")
    public User getById(@PathVariable int id)
   {
       return userService.getById(id);
   }
   @PostMapping("/add")
    public boolean add(User user)
   {
       return userService.save(user);
   }

   @DeleteMapping("/delete/{id}")
    public boolean delete(int id)
   {
       return userService.removeById(id);
   }

   @GetMapping("/Search")
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
