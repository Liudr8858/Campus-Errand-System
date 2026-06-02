package com.example.campuserrand.controller;

import com.example.campuserrand.common.Result;
import com.example.campuserrand.entity.User;
import com.example.campuserrand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.campuserrand.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){

        try{
            userService.register(user);
            return Result.success(null);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }

    }

    @PostMapping("/login")
    public Result login(@RequestParam String phone,
                        @RequestParam String password){

        System.out.println("phone="+phone +",password=" + password);

        User user = userService.login(phone,password);

        if(user == null){
            return Result.error("手机号或密码错误");
        }

        String token = JwtUtil.generateToken(phone);

        return Result.success(token);
    }

    @GetMapping("/info")
    public Result info(@RequestHeader("token") String token){

        String phone = JwtUtil.parseToken(token);

        User user = userService.getByPhone(phone);

        return Result.success(user);
    }
    @PostMapping("/updateAddress")
    public Result updateAddress(@RequestHeader("token") String token,
                                @RequestParam String address){

        // 解析token拿手机号
        String phone = JwtUtil.parseToken(token);

        // 根据手机号查用户
        User user = userService.getByPhone(phone);

        // 设置地址
        user.setAddress(address);

        // 更新数据库
        userService.updateById(user);

        return Result.success("更新成功");
    }

}