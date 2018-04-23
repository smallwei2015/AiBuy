package com.vode.aibuy.bean;

import java.io.Serializable;

/**
 * Created by cj on 2018/3/9.
 */

public class User implements Serializable {
    private long appuserId;
    private String phone;
    private String nickName;
    private String userName;
    private String headIcon;
    private int sex;//1男2女3没有选过
    private String recommendCode;
    private long expiration;
    private int userType;
    private String communityName;
    /**
     * user_id : 2
     * mobile : 17746547747
     * head_pic : /Public/upload/head_pic/2017/10-26/59f1a04553589.jpg
     * nickname : 阿海-副账号
     * level : 注册会员
     * pay_points : 51.81
     * user_money : 287.22
     * is_plus_time : 1509981504
     * access_token : fcb94ea2cd9ae6030e12c598a2f708bb
     * other : {"order_count":18,"goods_collect_count":2,"comment_count":2,"coupon_count":24}
     */

    private int user_id;
    private String mobile;
    private String head_pic;
    private String nickname;
    private String level;
    private String pay_points;
    private String user_money;
    private int is_plus_time;
    private String access_token;
    private OtherBean other;


    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public long getAppuserId() {
        return appuserId;
    }

    public void setAppuserId(long appuserId) {
        this.appuserId = appuserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPay_points() {
        return pay_points;
    }

    public void setPay_points(String pay_points) {
        this.pay_points = pay_points;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public int getIs_plus_time() {
        return is_plus_time;
    }

    public void setIs_plus_time(int is_plus_time) {
        this.is_plus_time = is_plus_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class OtherBean {
        /**
         * order_count : 18
         * goods_collect_count : 2
         * comment_count : 2
         * coupon_count : 24
         */

        private int order_count;
        private int goods_collect_count;
        private int comment_count;
        private int coupon_count;

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public int getGoods_collect_count() {
            return goods_collect_count;
        }

        public void setGoods_collect_count(int goods_collect_count) {
            this.goods_collect_count = goods_collect_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(int coupon_count) {
            this.coupon_count = coupon_count;
        }
    }
}
