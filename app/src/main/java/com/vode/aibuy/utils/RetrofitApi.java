package com.vode.aibuy.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cj on 2018/3/9.
 */

public class RetrofitApi {

    public static final String BASE="http://app.everygou.cn/";
    public static final String BASE_TRUE="http://www.everygou.cn/";

    public static Retrofit build(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


}
