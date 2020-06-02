package com.example.demo.aop;

import com.example.demo.config.dbcluster.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/5/21 11:11
 **/
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.demo.annotation.Master) " +
            "&& (execution(* com.example.demo.service..*.select*(..)) " +
            "|| execution(* com.example.demo.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.example.demo.annotation.Master) " +
            "|| execution(* com.example.demo.service..*.insert*(..)) " +
            "|| execution(* com.example.demo.service..*.add*(..)) " +
            "|| execution(* com.example.demo.service..*.update*(..)) " +
            "|| execution(* com.example.demo.service..*.edit*(..)) " +
            "|| execution(* com.example.demo.service..*.delete*(..)) " +
            "|| execution(* com.example.demo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


}