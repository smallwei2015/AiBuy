package com.vode.aibuy.bean;

import java.io.Serializable;

/**
 * Created by cj on 2018/3/9.
 */

public class User implements Serializable {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
