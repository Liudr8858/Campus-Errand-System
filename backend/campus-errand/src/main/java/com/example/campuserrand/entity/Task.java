package com.example.campuserrand.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;

    // 发布人
    private Long userId;
    // 接单人
    private Long acceptUserId;

    // 标题
    private String title;

    // 任务描述
    private String description;

    // 价格
    private Double price;

    // 状态
    private Integer status;

    // 创建时间
    private LocalDateTime createTime;

    private String address;

    private String phone;

}