package com.example.campuserrand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.campuserrand.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.campuserrand.entity.Task;

import java.util.List;

public interface TaskService extends IService<Task> {

    void publish(Task task);

    List<Task> list();

    void accept(Long id, Long userId);

    List<Task> myPublish(Long userId);

    List<Task> myAccept(Long userId);

    Task getById(Long id);

    void finish(Long id);

    void cancel(Long id);
}