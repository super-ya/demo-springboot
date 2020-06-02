package com.example.demo.controller;

import com.example.demo.dao.IUser;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/27 9:39
 **/
@RestController
@RefreshScope
public class HelloController{
    @PostMapping("/hello")
    public String hello(@RequestBody @Validated(User.Insert.class) User user){

        return "hello";
    }

    @Autowired
    private IUser userMapper;
    @GetMapping("/user")
    public void contextLoads() {
        User user = userMapper.testTable(3,"user");
        System.out.println(user);//User(id=3, name=jack, dept=devp, phone=xxx, website=www.baidu.com)
    }
}
