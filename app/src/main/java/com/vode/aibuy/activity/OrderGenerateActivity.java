package com.vode.aibuy.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.Address;
import com.vode.aibuy.bean.CartGoods;
import com.vode.aibuy.bean.Coupon;
import com.vode.aibuy.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerateActivity extends BaseActivityWithoutMVP {


    RecyclerView rec;

    TextView buy;

    TextView goods_count;
    TextView price;

    TextView address;
    TextView name;
    TextView phone;

    View cart_address;

    TextView cart_coupon;
    LinearLayout cart_all_coupon;
    LinearLayout cart_no_address;


    private List<CartGoods> datas;
    /*记录选择的id*/
    private List<Long> selectedData;
    private CommonAdapter<CartGoods> adapter;
    private boolean isLoading;
    private int cPage = 2;

    private int cSelectedCount = 0;
    public static final int SELECT_ADDRESS = 200;
    public static final int SELECT_COUPON = 100;
    public Address addressEx;
    public Coupon coupon;
    public double tPrice;
    public int flag;
    public double totalPrice;
    private View.OnClickListener listener;

    @Override
    public void initView() {
        setContentView(R.layout.activity_shopping_cart_pay);
        initTop(R.mipmap.left_white, "订单", -1);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart_all_coupon.getVisibility() == View.VISIBLE) {
                    cart_all_coupon.setVisibility(View.GONE);
                    cart_all_coupon.setAnimation(moveToViewBottom());
                }else if (cart_all_coupon.getVisibility() == View.GONE) {
                    cart_all_coupon.setVisibility(View.VISIBLE);
                    cart_all_coupon.setAnimation(moveToViewLocation());
                }
            }
        };

        cart_all_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(mActivity, CouponActivity.class);
                intent.putExtra("flag", 1);
                intent.putExtra("money",totalPrice);
                startActivityForResult(intent, SELECT_COUPON);*/
            }
        });


        datas = new ArrayList<>();
        selectedData = new ArrayList<>();

        rec.setLayoutManager(new LinearLayoutManager(mActivity));

        adapter=new CommonAdapter<CartGoods>(mActivity,datas) {

            @Override
            public void convert(ViewHolder<CartGoods> holder, CartGoods item, int positon) {

                holder.getView(R.id.goods_select).setVisibility(View.GONE);

                holder.setImageUrl(R.id.order_img,item.getPicsrc(),mActivity);

                holder.setText(R.id.order_des,item.getTitle());
                holder.setText(R.id.goods_price,"￥" + item.getPrice() + " x " + item.getCount());
                holder.setText(R.id.goods_total_price,"￥" + String.format("%.2f", item.getPrice() * item.getCount()));

                holder.setText(R.id.goods_count,item.getCount() + "");

                holder.getView(R.id.cart_bottom).setVisibility(View.GONE);
                holder.getView(R.id.goods_parent).setOnClickListener(listener);
            }

            @Override
            public int getDatasItemType(int position, CartGoods item) {
                return R.layout.goods_item;
            }
        };

        rec.setAdapter(adapter);

        rec.setOnScrollListener(new RecyclerView.OnScrollListener() {

            /*滑动到4dp才算滑动了*/
            private int mScrollThreshold = 4;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading) {
                        //getMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        //onScrollUp();
                        if (cart_all_coupon.getVisibility() == View.GONE) {
                            cart_all_coupon.setVisibility(View.VISIBLE);
                            cart_all_coupon.setAnimation(moveToViewLocation());
                        }

                    } else {
                        //onScrollDown();

                        if (cart_all_coupon.getVisibility() == View.VISIBLE) {
                            cart_all_coupon.setVisibility(View.GONE);
                            cart_all_coupon.setAnimation(moveToViewBottom());
                        }
                    }
                }

            }

        });

    }

    private void updateBottom() {

        tPrice =   0;

        for (int i = 0; i < datas.size(); i++) {
            CartGoods cartGoods = datas.get(i);

            double v = cartGoods.getPrice() * cartGoods.getCount();
            tPrice += v;

        }

        totalPrice = tPrice;

        if (coupon != null) {
            tPrice -= coupon.getCoupon();
            if (tPrice<0){
                tPrice=0;
            }
        }
        price.setText("￥" + String.format("%.2f", tPrice));


        cSelectedCount = datas.size();

        if (cSelectedCount < 99) {
            goods_count.setText(cSelectedCount + "");
        } else {
            goods_count.setText("99");
        }
    }


    @Override
    public void initData() {

        Intent intent = getIntent();
        datas = ((List<CartGoods>) intent.getSerializableExtra("goods"));
        addressEx= (Address) intent.getSerializableExtra("address");
        flag = intent.getIntExtra("flag",-1);

        adapter.notifyDataSetChanged();


        if (addressEx != null) {
            address.setText(addressEx.getDistrict() + "-" + addressEx.getReceiveAddress());
            name.setText("收件人：" + addressEx.getReceiveName());
            phone.setText("电话：" + addressEx.getReceivePhone());
        }else {
            cart_no_address.setVisibility(View.VISIBLE);
            cart_address.setVisibility(View.GONE);

            //UIUtils.showToast("请选择收货地址");
            cart_no_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, MyAddressActivity.class);
                    startActivityForResult(intent, SELECT_ADDRESS);
                }
            });
        }

        updateBottom();
    }

    public void btn_buy(View view) {
        if (datas != null && datas.size() > 0) {

            if (addressEx!=null) {
                generateOrder(datas);
            }else {
                UIUtils.showToast("请选择收货地址");
            }
        } else {
            UIUtils.showToast("请选择要购买的物品");
        }
    }

    private void generateOrder(List<CartGoods> goodses) {


        if(addressEx==null){
            UIUtils.showToast("请选择收货地址");
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(mActivity);


    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_ADDRESS) {
            if (data != null) {

                if (addressEx == null) {
                    cart_address.setVisibility(View.VISIBLE);
                    cart_no_address.setVisibility(View.GONE);
                }

                addressEx = (Address) data.getSerializableExtra("address");
                address.setText(addressEx.getDistrict() + "-" + addressEx.getReceiveAddress());
                name.setText("收件人：" + addressEx.getReceiveName());
                phone.setText("电话：" + addressEx.getReceivePhone());
            }else {

                if (addressEx==null) {
                    cart_address.setVisibility(View.GONE);
                    cart_no_address.setVisibility(View.VISIBLE);
                }
            }
        }else if (requestCode == SELECT_COUPON) {
            if (data != null) {

                int flag = data.getIntExtra("flag", 0);

                if (flag == 1) {
                    cart_coupon.setText("不使用优惠券");
                    cart_coupon.setTextColor(getResources().getColor(R.color.textlight));

                    coupon=null;
                } else {
                    coupon = (Coupon) data.getSerializableExtra("coupon");

                    if (true) {
                        cart_coupon.setText("￥" + coupon.getCoupon() + "优惠券");
                        cart_coupon.setTextColor(getResources().getColor(R.color.red));
                    }
                }
                updateBottom();
            }
        }
    }

}
