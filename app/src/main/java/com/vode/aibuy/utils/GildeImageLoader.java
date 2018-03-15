package com.vode.aibuy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vode.aibuy.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by cj on 2018/3/13.
 */

public class GildeImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        Glide.with(context).load(path).placeholder(R.color.bgcolor).into(imageView);

        /*//Picasso 加载图片简单用法
        Picasso.with(context).load(path).into(imageView);

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);*/
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        ImageView simpleDraweeView=new ImageView(context);
        return simpleDraweeView;
    }
}