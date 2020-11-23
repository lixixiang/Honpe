package com.honpe.lxx.app.ui.main.oa.ui.position5.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position2.bean.LeaveShowListBean;
import com.honpe.lxx.app.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * created by lxx at 2019/12/3 14:30
 * 描述:
 */
public class OverTimeAdapter extends BaseQuickAdapter<LeaveShowListBean.DataBean, BaseViewHolder> {
    private SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat day = new SimpleDateFormat("dd");
    private SimpleDateFormat TimeMin = new SimpleDateFormat("HH:mm");
    private String strDays;

    public OverTimeAdapter(@Nullable List<LeaveShowListBean.DataBean> data) {
        super(R.layout.item_over_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaveShowListBean.DataBean item) {
        String[] startTime = item.getStartTime().split(" ");
        String[] endTime = item.getEndTime().split(" ");
        Date date = DateUtil.setDate(yearMonthDay, startTime[0]);
        strDays = day.format(date);

        String dayOfWeek = strDays + "/" + DateUtil.dateToWeek(startTime[0]);
        helper.setText(R.id.tv_font_date, dayOfWeek)
                .setText(R.id.tv_startTime, startTime[1])
                .setText(R.id.tv_overTime, endTime[1])
                .setText(R.id.tv_reason, item.getReason());


    }
}
