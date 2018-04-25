package com.vode.aibuy.model;

import android.util.Log;

import com.google.gson.Gson;
import com.vode.aibuy.bean.Goods;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.bean.ShopCartGoods;
import com.vode.aibuy.bean.User;
import com.vode.aibuy.utils.RetrofitApi;
import com.vode.aibuy.utils.RetrofitInteface;
import com.vode.aibuy.utils.SignUtils;
import com.vode.aibuy.utils.UIUtils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cj on 2018/3/9.
 */

public class ModelClient {

    public static RetrofitInteface retrofit = RetrofitApi.build().create(RetrofitInteface.class);
    public static Gson gson=new Gson();


    public static void loadUser(String name, String pass,
                                final LoadDataInteface<User> user) {


        Map<String, String> map = SignUtils.getMap();

        map.put("username", name);
        map.put("password", pass);

        String result = SignUtils.getResult(map);

        retrofit.userLogin(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("vode",e.getMessage());
                        UIUtils.showToast("网络连接失败");
                    }

                    @Override
                    public void onNext(Result1 result1) {
                        /*这样写的原因在于，成功和失败返回数据类型不一致[]和user,那么就只能写成他们的父类object，然后再去判断
                        * ，也不可以直接获取，因为直接获取是解析后的字符串，不是一个json串*/
                        if (result1.getCode()==200) {

                            String s = gson.toJson(result1.getResult());

                            if (s.length()>2){
                                User body = gson.fromJson(s, User.class);
                                Log.w("vode",body.toString());
                                user.onDataLoaded(body);
                            }
                        }else {
                            UIUtils.showToast(result1.getMessage());
                        }
                    }

                });
    }

    public static void getSms(String phone){
        Map<String, String> map = SignUtils.getMap();
        map.put("mobile",phone);
        String result = SignUtils.getResult(map);

        retrofit.userSms(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showToast("网络连接失败");
                    }

                    @Override
                    public void onNext(Result1 result1) {
                        if (result1.getCode()==200) {
                            UIUtils.showToast("获取成功");
                        }else {
                            UIUtils.showToast(result1.getMessage());
                        }
                    }
                });

    }

    public static void checkPhone(String phone, String sms, final LoadDataInteface callback){
        Map<String, String> map = SignUtils.getMap();
        map.put("mobile",phone);
        map.put("verify",sms);
        String result = SignUtils.getResult(map);

        retrofit.checkPhone(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showError();
                        callback.onDataLoadFailed(e);
                    }

                    @Override
                    public void onNext(Result1 result1) {

                        if (result1.getCode()==200) {
                            callback.onDataLoaded(result1);
                            UIUtils.showToast("验证成功");
                        }else {
                            UIUtils.showToast(result1.getMessage());
                            callback.onDataLoadFailed(null);
                        }
                    }
                });

    }

    public static void resetPass(String password,final LoadDataInteface callback){

        Map<String, String> map = SignUtils.getMap();

        map.put("password",password);
        map.put("password_confirm",password);
        //map.put("user_id",user_id);

        String result = SignUtils.getResult(map);

        retrofit.resetPass(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataLoadFailed(e);
                        UIUtils.showError();
                    }

                    @Override
                    public void onNext(Result1 result1) {
                        callback.onDataLoaded(result1);

                        if (result1.getCode()==200){
                            UIUtils.showToast("重置成功");
                        }else {
                            UIUtils.showToast(result1.getMessage());
                        }
                    }
                });
    }

    public static void changePass(String password,
                                  String old_password,
                                  String new_password,
                                  String user_id) {

        Map<String, String> map = SignUtils.getMap();
        map.put("password", password);
        map.put("old_password", old_password);
        map.put("new_password", new_password);
        map.put("user_id", user_id);

        String result = SignUtils.getResult(map, 2);

        retrofit.userChange(result).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showError();
                    }

                    @Override
                    public void onNext(Result1 result1) {
                        if (result1.getCode()==200) {
                            UIUtils.showToast("修改成功");
                        }else {
                            UIUtils.showToast(result1.getMessage());
                        }
                    }
                });
    }

    public static void loginOut(String user_id, final LoadDataInteface callback){

        Map<String, String> map = SignUtils.getMap();
        map.put("user_id",user_id);


        String result = SignUtils.getResult(map, 2);


        retrofit.userOut(result).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.showError();
                        callback.onDataLoadFailed(e);
                    }

                    @Override
                    public void onNext(Result1 result1) {
                        if (result1.getCode()==200){
                            UIUtils.showToast("退出登录");
                            callback.onDataLoaded(result1);
                        }else {
                            UIUtils.showToast(result1.getMessage());
                            callback.onDataLoadFailed(null);
                        }
                    }
                });
    }

    public static void loadGoods(final LoadDataInteface<List<Goods>> loadDataInteface) {
        retrofit.getGoodsApi().enqueue(new Callback<List<Goods>>() {
            @Override
            public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                loadDataInteface.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                loadDataInteface.onDataLoadFailed(t);
            }
        });
    }

    public static void loadSearchGoods(final LoadDataInteface<List<Goods>> inteface) {

        retrofit.getGoodsBySearchApi().enqueue(new Callback<List<Goods>>() {
            @Override
            public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                inteface.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                inteface.onDataLoadFailed(t);
            }
        });
    }

    public static void loadCartGoods(final LoadDataInteface<List<ShopCartGoods>> inteface) {
        /*retrofit.getCartGoodsApi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<ShopCartGoods>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        inteface.onDataLoadFailed(e);
                    }

                    @Override
                    public void onNext(Response<List<ShopCartGoods>> listResponse) {
                        List<ShopCartGoods> body = listResponse.body();
                        inteface.onDataLoaded(body);
                    }
                });*/
    }

}
