package com.example.campuserrand.service.impl;

import com.example.campuserrand.entity.User;
import com.example.campuserrand.mapper.UserMapper;
import com.example.campuserrand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class UserServiceImpl implements UserService {




    @Override
    public User login(String phone, String password) {

        User user = userMapper.findByPhone(phone);

        if(user == null){
            return null;
        }

        if(!user.getPassword().equals(password)){
            return null;
        }

        return user;
    }
    @Override
    public void register(User user){

        // 🔥 先查手机号是否存在
        User existUser = userMapper.selectOne(
                new QueryWrapper<User>().eq("phone", user.getPhone())
        );

        if(existUser != null){
            throw new RuntimeException("手机号已存在");
        }

        // 插入新用户
        userMapper.insert(user);
    }

    @Override
    public User getByPhone(String phone){
        return userMapper.findByPhone(phone);
    }
    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateById(User user){
        userMapper.updateById(user);
    }
}