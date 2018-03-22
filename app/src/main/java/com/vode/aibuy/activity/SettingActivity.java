package com.vode.aibuy.activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.vode.aibuy.R;
import com.vode.aibuy.model.CirclrTransformation;
import com.vode.aibuy.userview.BasePopUpWindow;
import com.vode.aibuy.utils.FileUtils;
import com.vode.aibuy.utils.UIUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingActivity extends BaseActivityWithoutMVP {

    private BasePopUpWindow window;
    public ImageView icon;
    public Uri imageUri;
    private Uri cropImageUri;

    @Override
    void initData() {

    }

    @Override
    void initView() {
        setContentView(R.layout.activity_setting);
        initTop(R.mipmap.left_white, "设置", -1,R.color.transparent);

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


        icon = ((ImageView) findViewById(R.id.icon_image));
    }


    public void setUserIcon(View view) {
        window = new BasePopUpWindow(mActivity);

        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.image_pic, null);

        inflate.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GalleryFinal.openCamera(100, UserCenterActivity.this);

                File file = new File(FileUtils.IMG_CACHE, "/temp/" + "take.jpg");

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                String authorities = "com.vode.aibuy.fileprovider";
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(mActivity, authorities, file);

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
                /*Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image*//*");
                startActivityForResult(intent, 1007);*/

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1007);
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


    public void startPhotoZoom(Uri uri) {
        File CropPhoto=new File(FileUtils.IMG_CACHE,"crop_image.jpg");
        try{
            if(CropPhoto.exists()){
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        cropImageUri=Uri.fromFile(CropPhoto);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, 1008);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1006){
            if (resultCode==RESULT_OK){
                try {
                    Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                    Glide.with(mActivity).load(imageUri).transform(new CirclrTransformation(mActivity)).into(icon);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //startPhotoZoom(imageUri);
            }
        }else if (requestCode==1007){
            if (resultCode == RESULT_OK) {
                if (Build.VERSION.SDK_INT >= 19) {  //4.4及以上的系统使用这个方法处理图片；
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);  //4.4及以下的系统使用这个方法处理图片
                }
            }
        }else if (requestCode==1008){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                //把图片显示到ImgeView
                Glide.with(mActivity).load(cropImageUri).transform(new CirclrTransformation(mActivity)).into(icon);
            }
        }
    }

    /**
     * 4.4及以上的系统使用这个方法处理图片
     *
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果document类型的Uri,则通过document来处理
            String docID = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docID.split(":")[1];     //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/piblic_downloads"), Long.valueOf(docID));

                imagePath = getImagePath(contentUri, null);

            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式使用
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri，直接获取路径即可
            imagePath = uri.getPath();

        }

        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Glide.with(mActivity).load(imagePath).transform(new CirclrTransformation(mActivity)).into(icon);
        } else {
            UIUtils.showToast("选择失败");
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
}
