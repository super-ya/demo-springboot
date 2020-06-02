package com.example.demo.pool;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/26 14:34
 **/
public class DBConfig {
     static String jdbcDriver="com.mysql.jdbc.Driver";
     static String jdbcUrl="jdbc:mysql://localhost:3306/test_db?useSSL=false&serverTimezone=UTC";
     static String jdbcUsername="root";
     static String jdbcPassword="123456";

     static int initCount=10;
     static int growStep=2;
     static int maxCount=50;
}
