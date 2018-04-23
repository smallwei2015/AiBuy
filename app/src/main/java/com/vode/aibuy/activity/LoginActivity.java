package com.vode.aibuy.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.vode.aibuy.R;
import com.vode.aibuy.bean.Result1;
import com.vode.aibuy.bean.User;
import com.vode.aibuy.present.LoginPresent;
import com.vode.aibuy.utils.RetrofitInteface;
import com.vode.aibuy.utils.UIUtils;
import com.vode.aibuy.view.LoginView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity<LoginView,LoginPresent> implements LoginView{

    public EditText nameEdit;
    public EditText passEdit;
    public String name;
    public String pass;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_login);

        initTop(R.mipmap.left_white,"登录",-1);
        nameEdit = ((EditText) findViewById(R.id.name_edit));
        passEdit = ((EditText) findViewById(R.id.pass_edit));

        nameEdit.setText("17746547747");
        passEdit.setText("123456");
    }


    @NonNull
    @Override
    public LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    public void loginSucccess(User user) {

        UIUtils.showToast("登录成功"+user.getAccess_token());
        //UserManager.login();
    }

    private boolean checkSuccess() {

        name = nameEdit.getText().toString().trim();
        pass = passEdit.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            UIUtils.showToast("请输入用户名");
            return false;
        }

        if (TextUtils.isEmpty(pass)){
            UIUtils.showToast("请输入密码");
            return false;
        }
        return true;
    }

    public void btn_login(View view) {
        if (checkSuccess()) {
            getPresenter().loadData(name,pass);
        }
    }

    public void btn_register(View view) {
        Intent intent=new Intent(mActivity,RegisterActivity.class);
        startActivity(intent);
    }

    public void btn_forget(View view) {
        /*Intent intent=new Intent(mActivity,.class);
        startActivity(intent);*/

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smartcity.blueapp.com.cn:8088/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(RetrofitInteface.class).login2("18201402797","123456").subscribe(new Observer<Response<User1>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                UIUtils.showToast(""+e.getMessage());
            }

            @Override
            public void onNext(Response<User1> userResponse) {
                Log.w("vode",userResponse.getResult().toString());
            }
        });*/


        /*Map<String,String> map=new
        SignUtils.generateSign1()*/

        Retrofit retrofit1 = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://app.everygou.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        String content = "{\"sign\":\"89a6b661b69b37be66848a032b182d33\",\"appid\":\"Android\",\"version\":\"1.0\",\"params\":{\"username\":\"17746547747\",\"password\":\"123456\"}}";

        try {
            content= URLEncoder.encode(content,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        retrofit1.create(RetrofitInteface.class).test(content).enqueue(new Callback<Result1<User>>() {
            @Override
            public void onResponse(Call<Result1<User>> call, Response<Result1<User>> response) {

                Log.w("vode",call.request().url().toString());
                Result1 body1 = response.body();
                UIUtils.showToast(body1.getMessage()+body1.getCode());
            }

            @Override
            public void onFailure(Call<Result1<User>> call, Throwable t) {
                Log.w("vode",t.getMessage());
                UIUtils.showToast("网络连接失败"+t.getMessage());
            }
        });





    }
}
