package com.vode.aibuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.vode.aibuy.activity.LoginActivity;
import com.vode.aibuy.fragment.GoodsListFragment;
import com.vode.aibuy.fragment.MenuFragment;
import com.vode.aibuy.fragment.ShoppingCartFragment;
import com.vode.aibuy.fragment.UserCenterFragment;
import com.vode.aibuy.utils.BottomNavigationViewHelper;
import com.vode.aibuy.utils.PhoneUtils;
import com.vode.aibuy.utils.UIUtils;

public class MainActivity extends AppCompatActivity {


    public FragmentManager manager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            fragmentTransaction= manager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragmentTransaction.replace(R.id.main_contain,goodsFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentTransaction.replace(R.id.main_contain,menuFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_help:
                    PhoneUtils.callPhone(MainActivity.this,"10086");
                    return false;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.main_contain,cartFragment);
                    fragmentTransaction.commit();
                    tag.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_user:
                    fragmentTransaction.replace(R.id.main_contain,userCenterFragment);
                    fragmentTransaction.commit();
                    return true;
            }

            return false;
        }
    };
    public FragmentTransaction fragmentTransaction;
    public GoodsListFragment goodsFragment;
    public MenuFragment menuFragment;
    public ShoppingCartFragment cartFragment;
    public UserCenterFragment userCenterFragment;
    public long lastTime;
    public TextView tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        tag = BottomNavigationViewHelper.addTag(navigation, 3);
        tag.setText("2");
        tag.setVisibility(View.VISIBLE);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fragmentTransaction = manager.beginTransaction();

        goodsFragment = new GoodsListFragment();
        menuFragment = new MenuFragment();
        cartFragment = new ShoppingCartFragment();
        userCenterFragment = new UserCenterFragment();
        fragmentTransaction.add(R.id.main_contain, goodsFragment);
        fragmentTransaction.commit();


        //startActivity(new Intent(this, SearchActivity.class));
        //startActivity(new Intent(this, MallDetailActivity.class));
        //startActivity(new Intent(this, FeedBackActivity.class));

        //startActivity(new Intent(this, WithDrawActivity.class));

        //startActivity(new Intent(this, WalletActivity.class));

        //startActivity(new Intent(this, ConnectionActivity.class));
        //startActivity(new Intent(this, HotSearchActivity.class));
        //startActivity(new Intent(this, CheckPhoneActivity.class));
        //startActivity(new Intent(this, FlashActivity.class));

        //startActivity(new Intent(this, SettingActivity.class));
        //startActivity(new Intent(this, OrderActivity.class));
        //startActivity(new Intent(this, CommentActivity.class));

        //startActivity(new Intent(this, CommentDetailActivity.class));
        //startActivity(new Intent(this, CouponActivity.class));
        //startActivity(new Intent(this, DescriptionActivity.class));

        startActivity(new Intent(this, LoginActivity.class));
        //startActivity(new Intent(this, WebActivity.class));

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        long timeMillis = System.currentTimeMillis();
        if (timeMillis- lastTime <1000){
            finish();
        }else if (timeMillis-lastTime>3000){
            UIUtils.showToast("再次点击退出");
            lastTime =timeMillis;
        }else {
            lastTime =timeMillis;

        }

    }
}
