package com.vode.aibuy.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.vode.aibuy.R;
import com.vode.aibuy.adapter.CommonAdapter;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.ItemData;
import com.vode.aibuy.utils.UIUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayActivity extends BaseActivityWithoutMVP {



    RecyclerView rec;
    TextView tv_money;
    TextView tv_number;

    public static int SDK_PAY_FLAG = 100;
    public static final String WEIXID = "wx028ab07dde83c8f2";

    /*API密钥：BLUEPACIFICFORRONGCHENGCHINA1515

    AppID: wx028ab07dde83c8f2
    AppSecret: 62982a3236c04175c3e21d7aa337c4c4*/

    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;

    //private IWXAPI msgApi = null;
    /*支付结果*/
    private String result;
    /*支付订单*/
    public String idNumber;

    private List<ItemData> datas;
    public RecyclerView.Adapter adapter;
    /*支付方式*/
    public int cPay = -1;
    public double money;
    private IWXAPI msgApi;
    /*1余额充值2旅游门票购买3商品购买*/
    public int payFlag;
    public BroadcastReceiver receiver;


    @Override
    public void initView() {
        setContentView(R.layout.activity_pay);
        initTop(R.mipmap.left_white, "支付", -1);


        rec.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<ItemData>(mActivity,datas) {

            @Override
            public void convert(ViewHolder<ItemData> holder, final ItemData item, int positon) {
                holder.setText(R.id.pay_name,item.getName());
                holder.setImageResource(R.id.pay_icon,item.getIcon());

                CheckBox check = holder.getView(R.id.pay_check);
                check.setTag(positon);
                check.setChecked(item.isChecked());
                check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int pos = (int) buttonView.getTag();
                        if (isChecked) {
                            /*根据位置判断选择支付方式*/
                            update(pos);
                        } else {
                            if (item.isChecked()) {
                                cPay = -1;
                            }
                        }
                    }
                });
            }

            @Override
            public int getDatasItemType(int position, ItemData item) {
                return R.layout.pay_item;
            }
        };
        rec.setAdapter(adapter);
    }

    private void update(int pos) {
        cPay = pos;
        for (int i = 0; i < datas.size(); i++) {
            ItemData itemData = datas.get(i);
            if (pos == i) {
                itemData.setChecked(true);
            } else {
                itemData.setChecked(false);
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void initData() {

        // 将该app注册到微信
        msgApi = WXAPIFactory.createWXAPI(mActivity, null);
        msgApi.registerApp(WEIXID);

        idNumber = getIntent().getStringExtra("id");
        money = getIntent().getDoubleExtra("money",0);
        payFlag = getIntent().getIntExtra("flag",-1);

        tv_number.setText("订单号："+idNumber);
        tv_money.setText("￥"+String.format("%.2f",money));

        datas = new ArrayList<>();
        datas.add(new ItemData("微信", R.mipmap.weixin));
        datas.add(new ItemData("支付宝", R.mipmap.zhifubao));
        datas.add(new ItemData("银联", R.mipmap.yinglian));
        /*余额充值不能使用余额支付的方式*/
        if (payFlag!=1) {
            datas.add(new ItemData("余额", R.mipmap.yue));
        }

        adapter.notifyDataSetChanged();


        registerWXReciver();

    }

    private void registerWXReciver() {

        IntentFilter filter = new IntentFilter();
        filter.addAction("rcchina_PAY_SUCCESS");
        filter.addAction("rcchina_PAY_FAILD");
        filter.addAction("rcchina_PAY_CANCEL");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String wxAction = intent.getAction();
                if (wxAction.equals("rcchina_PAY_SUCCESS")){
                    finish();
                }else if (wxAction.equals("rcchina_PAY_FAILD")){

                }else {

                }
            }
        };
        registerReceiver(receiver, filter);

    }


    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*************************************************
         *
         * 步骤3：处理银联手机支付控件返回的支付结果
         *
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
		 */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            msg = "支付成功！";
            result = "success";

        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
            result = "fail";

        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
            result = "cancel";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("银联支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if (result.equalsIgnoreCase("success")) {
                    finish();
                }
            }
        });
        builder.create().show();
    }

    private void payYinl(String singStr) {
        // “00” – 银联正式环境
        // “01” – 银联测试环境，该环境中不发生真实交易
        String serverMode = "00";


        try {
            String decodeRes = URLDecoder.decode(singStr, "utf-8");

            int ret = UPPayAssistEx.startPay(PayActivity.this, null, null, decodeRes, serverMode);
            if (ret == PLUGIN_NEED_UPGRADE || ret == PLUGIN_NOT_INSTALLED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setTitle("提示");
                builder.setMessage("完成购买需要安装银联支付控件，是否安装？");

                builder.setNegativeButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                UPPayAssistEx
                                        .installUPPayPlugin(PayActivity.this);
                            }
                        });

                builder.setPositiveButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void payWeix(String signStr) {



        /*JSONObject object = JSON.parseObject(signStr);

        if (object != null) {
            //UIUtils.showToast("weixin");
            PayReq request = new PayReq();

            request.appId = object.getString("appid");

            request.partnerId = object.getString("partnerid");

            request.prepayId = object.getString("prepayid");

            request.packageValue = object.getString("package");

            request.nonceStr = object.getString("noncestr");

            request.timeStamp = object.getString("timestamp");

            request.sign = object.getString("sign");

            msgApi.sendReq(request);
        }*/


    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            if (msg.what==SDK_PAY_FLAG){
                Map<String, String> result = (Map<String, String>) msg.obj;

                String resultStatus = result.get("resultStatus");
                /*9000 	订单支付成功
                8000 	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                4000 	订单支付失败
                5000 	重复请求
                6001 	用户中途取消
                6002 	网络连接出错
                6004*/

                String msgS="";

                if (resultStatus.equalsIgnoreCase("9000")) {
                    msgS = "支付成功！";
                    finish();
                } else if (resultStatus.equalsIgnoreCase("8000")) {
                    msgS = "支付处理中";

                } else if (resultStatus.equalsIgnoreCase("6001")) {
                    msgS = "用户取消了支付";
                }else {
                    msgS="支付失败";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("支付宝支付结果通知");
                builder.setMessage(msgS);
                builder.setInverseBackgroundForced(true);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        finish();
                    }
                });
                builder.create().show();


            }
        }
    };

    private void payAli(final String sign) {
        // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(sign, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    private void pay(int type, String orderNumer, int flag) {

        final ProgressDialog dialog=new ProgressDialog(mActivity);

        dialog.setMessage("正在加载订单信息...");
        dialog.show();

        if (payFlag==1){
            balancePay(type,orderNumer,dialog);
        }else if (payFlag==2){
            travePay(type,orderNumer,dialog);
        }else {
            goodsPay(type, orderNumer, dialog);
        }
    }

    private void travePay(int type, String orderNumer, ProgressDialog dialog) {
        goodsPay(type,orderNumer,dialog);
    }

    /*余额充值*/
    private void balancePay(final int type, String orderNumer, final ProgressDialog dialog) {

    }
    /*普通商品购买*/
    private void goodsPay(final int type, String orderNumer, final ProgressDialog dialog) {

    }

    public void btn_pay(View view) {
        if (cPay < 0) {
            UIUtils.showToast("请选择支付方式");
            return;
        } else if (cPay == 0) {
            pay(1, idNumber, 0);
        } else if (cPay == 1) {
            pay(2, idNumber, 0);
        } else if (cPay == 2) {
            pay(0, idNumber, 0);
        }else if (cPay==3){
            UIUtils.showToast("暂不支持余额购买");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

    }

}
