package com.vode.aibuy.model;

/**
 * Created by cj on 2018/3/9.
 */

public interface LoadDataInteface<T> {

    void onDataLoaded(T data);

    void onDataLoadFailed(Throwable e);

}
