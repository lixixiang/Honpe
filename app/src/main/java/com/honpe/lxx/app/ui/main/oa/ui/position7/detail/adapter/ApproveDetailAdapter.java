package com.honpe.lxx.app.ui.main.oa.ui.position7.detail.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position7.detail.bean.ApproveDetailBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/23 13:56
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveDetailAdapter extends BaseQuickAdapter<ApproveDetailBean, BaseViewHolder> {

    ApproveDetailChildAdapter adapter;

    public ApproveDetailAdapter(@Nullable List<ApproveDetailBean> data) {
        super(R.layout.item_approve_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApproveDetailBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_depart, "(" + item.getDepart() + ")");
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ApproveDetailChildAdapter(item.getDataList());
        recyclerView.setAdapter(adapter);

    }
}








