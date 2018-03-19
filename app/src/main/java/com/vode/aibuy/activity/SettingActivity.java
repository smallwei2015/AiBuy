package com.vode.aibuy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.vode.aibuy.R;
import com.vode.aibuy.userview.BasePopUpWindow;
import com.vode.aibuy.utils.UIUtils;

import java.io.File;

public class SettingActivity extends BaseActivityWithoutMVP {

    private BasePopUpWindow window;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_setting);
        initTop(R.mipmap.left_white, "设置", -1);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        int statusBarHeight = UIUtils.getStatusBarHeight(mActivity);
        if (statusBarHeight > 0) {
            View toolbar = findViewById(R.id.toolbar);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();

            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + statusBarHeight, 0, 0);
        }
    }


    public void setUserIcon(View view) {
        window = new BasePopUpWindow(mActivity);

        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.image_pic, null);

        inflate.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GalleryFinal.openCamera(100, UserCenterActivity.this);

                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                String authorities = "com.vode.aibuy.fileprovider";
                //通过FileProvider创建一个content类型的Uri
                Uri imageUri =
                        FileProvider.getUriForFile(mActivity, authorities, file);

                Intent intent = new Intent();
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
                startActivityForResult(intent, 1006);
            }
        });

        inflate.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GalleryFinal.openGallerySingle(200, UserCenterActivity.this);
            }
        });


        window.setContentView(inflate);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        window.setOutsideTouchable(false);
        window.setFocusable(true);

        window.showAsDropDown(view);
    }
}
