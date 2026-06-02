package com.example.campuserrand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.campuserrand.entity.Task;
import com.example.campuserrand.mapper.TaskMapper;
import com.example.campuserrand.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.campuserrand.entity.Task;
import com.example.campuserrand.mapper.TaskMapper;
import com.example.campuserrand.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl
extends ServiceImpl<TaskMapper,Task>
implements TaskService{

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void publish(Task task) {

        task.setId(System.currentTimeMillis());
        task.setStatus(0);
        task.setCreateTime(LocalDateTime.now());

        taskMapper.insert(task);

    }
    @Override
    public List<Task> list() {
        return taskMapper.list();
    }
    @Override
    public void accept(Long id, Long userId) {
        taskMapper.accept(id,userId);
    }
    @Override
    public List<Task> myPublish(Long userId){
        return taskMapper.myPublish(userId);
    }
    @Override
    public List<Task> myAccept(Long userId){
        return taskMapper.myAccept(userId);
    }
    @Override
    public Task getById(Long id){
        return taskMapper.getById(id);
    }

    @Override
    public void finish(Long id){
        taskMapper.finish(id);
    }
    @Override
    public void cancel(Long id){
        taskMapper.cancel(id);
    }
}