package com.honpe.lxx.app.ui.main.oa.ui.position7.detail.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position7.detail.bean.ApproveDetailBean;
import com.honpe.lxx.app.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/23 14:22
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveDetailChildAdapter extends BaseQuickAdapter<ApproveDetailBean.DetailDataBean, BaseViewHolder> {
    SimpleDateFormat sdfStartTime = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    SimpleDateFormat sdfEndTime = new SimpleDateFormat("MM.dd HH:MM");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ApproveDetailChildAdapter(@Nullable List<ApproveDetailBean.DetailDataBean> data) {
        super(R.layout.item_approve_detail_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApproveDetailBean.DetailDataBean item) {
        String strStartTime = sdfStartTime.format(DateUtil.setDate(sdf, item.getStartTime()));
        String strEndTime = sdfEndTime.format(DateUtil.setDate(sdf, item.getEndTime()));

        helper.setText(R.id.tv_id, helper.getLayoutPosition()+1 +"、");
        helper.setText(R.id.tv_startTime,"时间："+ strStartTime);
        helper.setText(R.id.tv_reason,"事由："+ item.getReason());
        helper.setText(R.id.tv_endTime,strEndTime);
        helper.setText(R.id.tv_time_lag, item.getTimeLag()+"小时");

        if (item.getAddress() != null) {
            helper.setText(R.id.tv_reason, "事由：" + item.getReason() + "\n" +
                    "地点：" + item.getAddress());
        }

        if (helper.getLayoutPosition() % 2 == 1) {
            helper.setBackgroundColor(R.id.fl_background,getContext().getResources().getColor(R.color.green_thin3));
        }
    }
}










