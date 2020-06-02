package com.example.demo.service;

import com.example.demo.annotation.Master;
import com.example.demo.dao.IUser;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/5/21 11:43
 **/
@Service
public class UserService {
    @Autowired
    IUser userDao;

    @Master
    public User getUser(int id){
        return userDao.getUser(id);
    };
    public User testTable(int id,String tableName){
        return userDao.testTable(id,tableName);
    }
    @Master
    public void save(User user){
         userDao.save(user);
    }
}
