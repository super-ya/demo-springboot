package com.example.demo.test;

import com.example.demo.pojo.User;
import lombok.Data;

/**
 * @Description
 * @Author yangdong@0easy.com
 * @Date Created in 2020/4/23 15:48
 **/
class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("四哥666888");
        Thing thing = new Thing("ab", "hello", user);

        Object clone = thing.clone();
        thing.setName("七哥");

        System.out.println(clone);
        System.out.println(thing);
        int[][][] arr = new int[0][-1][-1];
    }
}

@Data
public class Thing implements Cloneable {
    private String name;
    private String doThing;
    private User user;

    public Thing(String name, String doThing, User user) {
        this.name = name;
        this.doThing = doThing;
        this.user = user;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        Thing clone = (Thing) super.clone();
//        clone.user = (User) user.clone();
//        return clone;
        return super.clone();
    }
}
