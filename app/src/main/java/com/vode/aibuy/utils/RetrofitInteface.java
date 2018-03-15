package com.vode.aibuy.utils;

import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.bean.Repo;
import com.vode.aibuy.bean.ShopCartGoods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by cj on 2018/3/9.
 */

public interface RetrofitInteface {

    @GET("api/data/Android/10/1")
    Call<Repo> getAndroidInfo();

    @GET("api/data/1")
    Call<List<Goods>> getGoodsApi();

    @GET("api/data/1")
    Call<List<Goods>> getGoodsBySearchApi();

    @GET("api/data/1")
    Observable<Response<List<ShopCartGoods>>> getCartGoodsApi();
}
