package com.example.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.sql.SQLOutput;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.example.demo.dao")
public class DemoSpringbootApplication implements AsyncConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringbootApplication.class, args);
    }

}
