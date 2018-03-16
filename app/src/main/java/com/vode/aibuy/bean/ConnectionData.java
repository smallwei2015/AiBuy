package com.vode.aibuy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cj on 2018/3/16.
 */

public class ConnectionData implements Serializable {


    private String title;
    private int state;
    private int type;
    private List<ConnectionData> sons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ConnectionData> getSons() {
        return sons;
    }

    public void setSons(List<ConnectionData> sons) {
        this.sons = sons;
    }
}
