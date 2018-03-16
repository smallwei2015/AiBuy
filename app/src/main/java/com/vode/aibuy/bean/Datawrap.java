package com.vode.aibuy.bean;

import java.io.Serializable;

/**
 * Created by cj on 2018/3/16.
 */

public class Datawrap implements Serializable {
    private int type;
    private Object data;
    private String tag;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
