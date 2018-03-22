package com.vode.aibuy.activity;

import com.vode.aibuy.R;
import com.vode.aibuy.userview.BigImageView;

import java.io.IOException;

public class DescriptionActivity extends BaseActivityWithoutMVP {

    public BigImageView img;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_description);
        initTop(R.mipmap.left_white,"",-1);

        img =  findViewById(R.id.img);

        try {
            img.setInputStream(getAssets().open("descript.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            InputStream inputStream = getAssets().open("descript.jpg");

            //获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;


            int windowWidth = UIUtils.getWindowWidth(mActivity);
            int windowHeight = UIUtils.getWindowHeight(mActivity);

            //int tagetheight=
            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(0, 0, width , windowHeight), options);
            img.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
