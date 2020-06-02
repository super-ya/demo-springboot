package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/22 17:50
 **/
@Repository
public interface IUser {
    User getUser(int id);
    User testTable(int id,String tableName);
    void save(User user);
}