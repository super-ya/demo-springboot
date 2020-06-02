package com.example.demo.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/22 17:48
 **/
@Data
public class User implements Cloneable{

    @NotNull(groups = {Update.class,Delete.class})
    private int id;

    @NotNull(groups = {Insert.class,Delete.class})
    private String name;

    @NotNull
    @Pattern(regexp = "[012]", groups = {Insert.class})
    private String dept;

    @NotNull(groups = {Insert.class})
    private String phone;

    @NotNull(groups = {Insert.class})
    private String website;

    public User() {

    }

    public User(String name) {
        this.name = name;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public interface Insert{}
    public interface Update{}
    public interface Delete{}
}