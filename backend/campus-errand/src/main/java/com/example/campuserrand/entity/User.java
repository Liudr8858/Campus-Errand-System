package com.example.campuserrand.entity;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String phone;
    private String name;
    private String password;
    private String address;

}