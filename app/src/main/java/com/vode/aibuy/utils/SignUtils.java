package com.vode.aibuy.utils;

import com.vode.aibuy.model.UserManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cj on 2018/4/23.
 */

public class SignUtils {

    private static Map<String,String> map;

    public static Map<String, String> getMap() {

        if (map==null){
            map=new HashMap<>();
        }else {
            map.clear();
        }
        return map;
    }

    public static final String generateSign1(Map<String,String> paras){
        StringBuilder sb=new StringBuilder();

        for (Map.Entry<String, String> entry : paras.entrySet()) {
            sb.append(entry.getKey()+"-"+entry.getValue());
        }
        sb.append("airui-@#~……%￥·&*$^!");
        return sb.toString();
    }

    public static final String generateSign2(String token,Map<String,String> paras){
        StringBuilder sb=new StringBuilder(token);

        for (Map.Entry<String, String> entry : paras.entrySet()) {
            sb.append(entry.getKey()+"-"+entry.getValue());
        }

        sb.append("airui-@#~……%￥·&*$^!");
        return sb.toString();
    }

    public static final String generateSign3(){
        return "airui-@#~……%￥·&*$^!";
    }

    public static String getMD5(String info) {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString().toLowerCase();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

    //{"sign":"89a6b661b69b37be66848a032b182d33","appid":"iOS\/Android","version":"1.0","params":{"mobile":"17746547747"}}
    public static final String generateJson(String sign, String version, Map<String,String> params){

        StringBuilder builder=new StringBuilder();

        builder.append("{");

        builder.append("\"sign\":\""+sign);
        builder.append("\",\"appid\":\"Android");
        builder.append("\",\"version\":\""+version);
        builder.append("\",\"params\":"+getParaJsonStr(params));

        builder.append("}");

        return builder.toString();
    }

    private static String getParaJsonStr(Map<String, String> params) {

        StringBuilder builder=new StringBuilder();

        builder.append("{");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append("\""+entry.getKey()+"\":\""+entry.getValue()+"\",");
        }

        builder.deleteCharAt(builder.length()-1);
        builder.append("}");

        return builder.toString();
    }

    public static String getResult(Map<String,String> map){

        String s = SignUtils.generateSign1(map);
        String md5 = SignUtils.getMD5(s);
        String s1 = SignUtils.generateJson(md5, "1.0", map);

        try {
            s1= URLEncoder.encode(s1,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s1;

    }

    public static String getResult(Map<String,String> map,int flag){
        String s;
        if (flag==2) {
            s= SignUtils.generateSign2(UserManager.getUser().getAccess_token(),map);
        }else if (flag==3){
            s=SignUtils.generateSign3();
        }else {
            s = SignUtils.generateSign1(map);
        }
        String md5 = SignUtils.getMD5(s);
        String s1 = SignUtils.generateJson(md5, "1.0", map);

        try {
            s1= URLEncoder.encode(s1,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s1;

    }

}
