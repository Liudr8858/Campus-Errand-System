package com.example.campuserrand.service;

import com.example.campuserrand.entity.User;

public interface UserService {

    void register(User user);

    User login(String phone, String password);

    User getByPhone(String phone);

    void updateById(User user);

}