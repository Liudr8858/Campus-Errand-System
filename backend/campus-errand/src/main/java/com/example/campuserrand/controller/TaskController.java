package com.example.campuserrand.controller;

import com.example.campuserrand.common.Result;
import com.example.campuserrand.entity.Task;
import com.example.campuserrand.service.TaskService;
import com.example.campuserrand.service.UserService;
import com.example.campuserrand.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.campuserrand.entity.User;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/publish")
    public Result publish(@RequestBody Task task,
                          @RequestHeader("token") String token){

        String phone = JwtUtil.parseToken(token);
        User user = userService.getByPhone(phone);

        task.setUserId(user.getId()); // 🔥 关键

        taskService.publish(task);

        return Result.success("发布成功");
    }
    @GetMapping("/list")
    public Result list(){
        System.out.println("进入list接口了");

        return Result.success(taskService.list());

    }
    @PostMapping("/accept")
    public Result accept(@RequestParam Long id,
                         @RequestHeader("token") String token){

        String phone = JwtUtil.parseToken(token);
        User user = userService.getByPhone(phone);

        taskService.accept(id, user.getId());

        return Result.success("接单成功");
    }
    @GetMapping("/myPublish")
    public Result myPublish(@RequestHeader("token") String token){

        String phone = JwtUtil.parseToken(token);
        User user = userService.getByPhone(phone);

        return Result.success(taskService.myPublish(user.getId()));
    }
    @GetMapping("/myAccept")
    public Result myAccept(@RequestHeader("token") String token){

        String phone = JwtUtil.parseToken(token);
        User user = userService.getByPhone(phone);

        return Result.success(taskService.myAccept(user.getId()));
    }
    @GetMapping("/detail")
    public Result detail(@RequestParam Long id,
                         @RequestHeader("token") String token){

        // 当前登录用户
        String phone = JwtUtil.parseToken(token);
        User currentUser = userService.getByPhone(phone);

        // 任务
        Task task = taskService.getById(id);

        // 🔥 判断是否可以查看隐私信息
        boolean canView = false;

        // 情况1：自己发布的
        if(task.getUserId().equals(currentUser.getId())){
            canView = true;
        }

        // 情况2：自己接的单
        if(task.getAcceptUserId() != null &&
                task.getAcceptUserId().equals(currentUser.getId())){
            canView = true;
        }

        // ❗不能看 → 隐藏信息
        if(!canView){
            task.setAddress(null);
            task.setPhone(null);
        }

        return Result.success(task);
    }
    @PostMapping("/finish")
    public Result finish(@RequestParam Long id,
                         @RequestHeader("token") String token){

        // 当前登录用户
        String phone = JwtUtil.parseToken(token);
        User user = userService.getByPhone(phone);

        // 获取任务
        Task task = taskService.getById(id);

        // ❗校验：必须是接单人才能完成
        if(task.getAcceptUserId() == null ||
                !task.getAcceptUserId().equals(user.getId())){
            return Result.error("无权限操作");
        }

        // 设置为已完成
        task.setStatus(2);

        taskService.updateById(task);

        return Result.success("任务已完成");
    }
    @PostMapping("/cancel")
    public Result cancel(@RequestParam Long id){

        taskService.cancel(id);

        return Result.success(null);

    }
    @PostMapping("/delete")
    public Result delete(@RequestParam Long id){

        taskService.removeById(id);

        return Result.success("删除成功");
    }
}