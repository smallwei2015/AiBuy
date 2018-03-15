package com.vode.aibuy.bean;

import java.io.Serializable;

/**
 * Created by cj on 2018/3/12.
 */

public class Goods implements Serializable {
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
