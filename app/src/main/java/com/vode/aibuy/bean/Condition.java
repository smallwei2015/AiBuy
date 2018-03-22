package com.vode.aibuy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cj on 2018/3/21.
 */

public class Condition implements Serializable {

    private String title;
    private int type;
    private int state;
    private List<Condition> sons;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Condition> getSons() {
        return sons;
    }

    public void setSons(List<Condition> sons) {
        this.sons = sons;
    }
}
