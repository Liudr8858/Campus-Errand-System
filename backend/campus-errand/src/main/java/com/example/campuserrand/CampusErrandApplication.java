package com.example.campuserrand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.campuserrand.mapper")
@SpringBootApplication
public class CampusErrandApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusErrandApplication.class, args);
    }

}
