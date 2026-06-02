package com.example.campuserrand.mapper;

import com.example.campuserrand.entity.Task;
import com.example.campuserrand.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.campuserrand.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task>{

    @Select("select * from task where status = 0 order by create_time desc ")
    List<Task> list();

    @Update("update task set status = 1, accept_user_id = #{userId} where id = #{id}")
    void accept(@Param("id") Long id, @Param("userId") Long userId);

    @Select("select * from task where user_id = #{userId} order by create_time desc")
    List<Task> myPublish(Long userId);

    @Select("select * from task where accept_user_id = #{userId} order by create_time desc")
    List<Task> myAccept(Long userId);

    @Select("select * from task where id = #{id}")
    Task getById(Long id);

    @Update("update task set status =2 where id =#{id}")
    void finish(Long id);

    @Update("update task set status = -1 where id = #{id}")
    void cancel(Long id);

    @Select("select * from user where phone =#{phone}")
    User getByPhone(String phone);

}