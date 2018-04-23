package com.vode.aibuy.bean;

/**
 * Created by cj on 2018/4/23.
 */

public class Result1<T> {

    /**
     * code : 100003
     * result : []
     * message : json格式错误
     */

    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
