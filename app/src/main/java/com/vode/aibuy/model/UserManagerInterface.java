package com.vode.aibuy.model;


import com.vode.aibuy.bean.User;

/**
 * Created by cj on 2017/6/27.
 */

public interface UserManagerInterface {
    void success(User user);
    void faild(User user);
}
