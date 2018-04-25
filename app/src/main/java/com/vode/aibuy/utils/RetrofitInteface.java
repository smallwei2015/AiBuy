package com.vode.aibuy.utils;

import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.bean.Repo;
import com.vode.aibuy.bean.Result;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.bean.ShopCartGoods;
import com.vode.aibuy.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by cj on 2018/3/9.
 */

public interface RetrofitInteface {

    @GET("api/data/Android/10/1")
    Call<Repo> getAndroidInfo();

    @FormUrlEncoded
    @POST("api.php/User/login")
    Call<Result1<User>> test(@Field("params") String params);

    @GET("api/data/1")
    Call<List<Goods>> getGoodsApi();

    @GET("api/data/1")
    Call<List<Goods>> getGoodsBySearchApi();

    @GET("api/data/1")
    Observable<Result<List<ShopCartGoods>>> getCartGoodsApi();

    @FormUrlEncoded
    @POST("api.php/User/login")
    Observable<Result1> userLogin(@Field("params") String params);

    @FormUrlEncoded
    @POST("api.php/Sms/sendsms")
    Observable<Result1> userSms(@Field("params") String params);

    @FormUrlEncoded
    @POST("api.php/User/user_register")
    Observable<Result1> userRegister(@Field("params") String content);


    @FormUrlEncoded
    @POST("api.php/User/save_user_password")
    Observable<Result1> userChange(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/user_logout")
    Observable<Result1> userOut(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/forget_pwd")
    Observable<Result1> checkPhone(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/set_pwd")
    Observable<Result1> resetPass(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/account")
    Observable<Result1> balance(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/coupun")
    Observable<Result1> coupon(@Field("params") String content);

    @FormUrlEncoded
    @POST("api.php/User/points")
    Observable<Result1> point(@Field("params") String content);
}
