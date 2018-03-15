package com.vode.aibuy.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vode.aibuy.R;

import java.lang.reflect.Field;

/**
 * Created by cj on 2018/3/12.
 */

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public static TextView addTag(BottomNavigationView navigationView,int pos) {
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(pos);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(navigationView.getContext()).inflate(R.layout.menu_badge, menuView, false);

        //添加到Tab上
        itemView.addView(badge);

        TextView count = badge.findViewById(R.id.tv_msg_count);

        return count;

    }
}