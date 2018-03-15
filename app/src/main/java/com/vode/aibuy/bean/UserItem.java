package com.vode.aibuy.bean;

import java.io.Serializable;

/**
 * Created by cj on 2018/3/14.
 */

public class UserItem implements Serializable {
    private String title;
    private int src;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
