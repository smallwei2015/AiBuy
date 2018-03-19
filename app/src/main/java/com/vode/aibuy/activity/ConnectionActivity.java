package com.vode.aibuy.activity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vode.aibuy.R;
import com.vode.aibuy.adapter.ConnectionAdapter;
import com.vode.aibuy.adapter.OnItemClickListener;
import com.vode.aibuy.adapter.ViewHolder;
import com.vode.aibuy.bean.ConnectionData;
import com.vode.aibuy.present.ConnectionPresent;
import com.vode.aibuy.utils.UIUtils;
import com.vode.aibuy.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class ConnectionActivity extends BaseActivity<BaseView<List<ConnectionData>>, ConnectionPresent> implements BaseView<List<ConnectionData>> {

    public RecyclerView rc;
    public ArrayList<ConnectionData> datas;
    public ConnectionAdapter adapter;
    public LinearLayoutManager layout;

    @Override
    void initData() {

        adapter.showItemView();


        for (int i = 0; i < 3; i++) {
            ConnectionData e = new ConnectionData();
            e.setState(0);
            e.setType(0);
            ArrayList<ConnectionData> sons = new ArrayList<>();
            for (int j = 0; j < 10; j++) {

                ConnectionData e1 = new ConnectionData();
                e1.setType(1);
                sons.add(e1);

            }
            e.setSons(sons);
            datas.add(e);
        }
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_connection);
        initTop(R.mipmap.left_white, "我的人脉", -1);

        rc = ((RecyclerView) findViewById(R.id.connection_rec));
        layout = new LinearLayoutManager(mActivity);
        rc.setLayoutManager(layout);
        rc.addItemDecoration(new RecyclerView.ItemDecoration() {
            public int dividerHeight = 10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);


                int position = parent.getChildAdapterPosition(view);

                //ConnectionData data = (ConnectionData) view.getTag();
                /*RecyclerView.LayoutManager manager = parent.getLayoutManager();
                int childPosition = parent.getChildAdapterPosition(view);
                int itemCount = parent.getAdapter().getItemCount();*/
                if (position > 0) {
                    ConnectionData data = datas.get(position);

                    /*if (data != null) {
                        if (data.getType() == 0) {
                            //outRect.bottom = 20;
                            dividerHeight=20;
                        } else {
                            //outRect.bottom = 5;
                            dividerHeight=5;
                        }
                    }*/
                    outRect.bottom = dividerHeight;

                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

                Paint dividerPaint = new Paint();
                dividerPaint.setColor(parent.getContext().getResources().getColor(R.color.bglight));

                int childCount = parent.getChildCount();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + dividerHeight;
                    c.drawRect(left, top, right, bottom, dividerPaint);
                }


            }
        });
        datas = new ArrayList<>();
        adapter = new ConnectionAdapter(mActivity, datas);
        adapter.setOnItemClickListener(new OnItemClickListener<ConnectionData>() {
            @Override
            public void onItemClick(ViewHolder<ConnectionData> holder, ConnectionData item, int position) {
                if (item.getType() == 0) {
                    UIUtils.showToast("click");
                }
            }
        });
        rc.setAdapter(adapter);
    }


    @NonNull
    @Override
    public ConnectionPresent createPresenter() {
        return new ConnectionPresent();
    }

    @Override
    public void loadSuccess(List<ConnectionData> datas) {

    }

    @Override
    public void loadFaild(Throwable e) {

    }
}
