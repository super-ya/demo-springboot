package com.example.demo;

import com.example.demo.annotation.Master;
import com.example.demo.dao.IUser;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DemoSpringbootApplicationTests {
    @Autowired
    private UserService userMapper;

    @Test
    public void contextLoads() {
        User user = userMapper.testTable(3,"user");
        System.out.println(user);//User(id=3, name=jack, dept=devp, phone=xxx, website=www.baidu.com)
    }

    @Test
    public void testRead() {
        for (int i = 1; i < 4; i++) {
            System.out.println(userMapper.getUser(i));
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("wangwu");
        userMapper.save(user);
    }

    @Test
    public void testReadFromMaster() {
        System.out.println(userMapper.getUser(1));
    }

}
