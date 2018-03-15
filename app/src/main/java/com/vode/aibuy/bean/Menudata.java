package com.vode.aibuy.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cj on 2018/3/12.
 */

public class Menudata implements Serializable {

    int type;
    String title;
    List<Menudata> sons;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Menudata> getSons() {
        return sons;
    }

    public void setSons(List<Menudata> sons) {
        this.sons = sons;
    }
}
