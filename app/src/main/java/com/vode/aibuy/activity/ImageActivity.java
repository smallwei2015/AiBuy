package com.vode.aibuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vode.aibuy.R;
import com.vode.aibuy.userview.PinchImageView;
import com.vode.aibuy.userview.PinchImageViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {

    public static final String TRA_NAME = "pic_tran";
    FrameLayout container;
    private PinchImageView pinchImageView;
    private PinchImageViewPager pager;
    private List<ImageView> mImages;
    private Serializable data;


    public void initView() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        pager=new PinchImageViewPager(this);
        mImages=new ArrayList<>();

        container=findViewById(R.id.news_image_container);
        Intent intent = getIntent();
        data = intent.getSerializableExtra("data");
        int position = intent.getIntExtra("position", -1);

        List<String> manyPic =initPics(data);

        for (int i = 0; i < manyPic.size(); i++) {
            ImageView imageView = new PinchImageView(this);
            Glide.with(this).load(manyPic.get(i)).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mImages.add(i, imageView);
        }
        pager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(final ViewGroup container,
                                          final int position) {
                container.addView(mImages.get(position));
                return mImages.get(position);
            }

            @Override
            public int getCount() {
                return mImages.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImages.get(position));
            }
        });
        if (position!=-1) {
            pager.setCurrentItem(position);
        }
        container.addView(pager);
    }

    private List<String> initPics(Serializable data) {

        if (data != null) {

        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_kind_image);
        initView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
