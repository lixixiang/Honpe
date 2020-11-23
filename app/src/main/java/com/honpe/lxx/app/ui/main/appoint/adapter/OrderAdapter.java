package com.honpe.lxx.app.ui.main.appoint.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.appoint.bean.AppointBean;
import com.honpe.lxx.app.ui.main.appoint.detail.AppointDetailActivity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.widget.BaseListView;

import java.util.List;

import static com.honpe.lxx.app.MyApplication.getContext;


/**
 * created by lxx at 2019/11/25 9:16
 * 描述: 访客预约
 */
public class OrderAdapter extends BaseQuickAdapter<AppointBean, BaseViewHolder> {

    private int userType;
    AppointChildListAdapter adapter;
    private String session;
    public OrderAdapter(@Nullable List<AppointBean> data, int userType, String session) {
        super(R.layout.item_appoint_head, data);
        this.userType = userType;
        this.session = session;
    }

    @Override
    protected void convert(BaseViewHolder helper, AppointBean item) {
        String[] strs = item.getMsg().split(" ");
        TextView tvStatus = helper.getView(R.id.tv_status);
        helper.setText(R.id.tv_title, strs[0])
                .setText(R.id.tv_com, "(" + strs[1] + ")");
        LatteLogger.d("ddddddd", strs[1]);
        if (userType == 1) {
            tvStatus.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setVisibility(View.GONE);
        }
        BaseListView listView = helper.getView(R.id.listView);
        LinearLayout linearLayout = helper.getView(R.id.ll_appointment_header);
        adapter = new AppointChildListAdapter(getContext(), item.getData(), userType, session,linearLayout,tvStatus);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AppointDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AppointBean", item.getData().get(position));
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
    }
}
