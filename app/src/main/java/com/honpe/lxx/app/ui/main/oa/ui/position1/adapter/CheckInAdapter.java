package com.honpe.lxx.app.ui.main.oa.ui.position1.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 15:52
 * @Author: 李熙祥
 * @Description: java类作用描述 考勤打卡
 */
public class CheckInAdapter extends BaseQuickAdapter<CheckInBean.DataBean, BaseViewHolder> {
    private String curDay, weekday;
    private int type; // 1 代表 所有部门考勤
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String[] mTypes = {"请假","出差","外出","加班"};

    public CheckInAdapter(@Nullable List<CheckInBean.DataBean> data, int type) {
        super(R.layout.css_table_1, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CheckInBean.DataBean item) {
        helper.setBackgroundResource(R.id.time1, R.color.white);
        helper.setBackgroundResource(R.id.time2, R.color.white);
        helper.setBackgroundResource(R.id.time3, R.color.white);
        helper.setBackgroundResource(R.id.time4, R.color.white);
        helper.setBackgroundResource(R.id.time5, R.color.white);
        helper.setBackgroundResource(R.id.time6, R.color.white);

        helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.black));

        TypeValue(helper, item);


        if (item.getS1() > 0) {
            helper.setBackgroundResource(R.id.time1, R.color.google_red);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("上班迟到" + item.getS1() + "分钟");
                }
            });
        } else {

        }
        if (item.getS2() > 0) {
            helper.setBackgroundResource(R.id.time2, R.color.google_red);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("下班早退" + item.getS2() + "分钟");
                }
            });
        } else {

        }
        if (item.getS3() > 0) {
            helper.setBackgroundResource(R.id.time3, R.color.google_red);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("上班迟到" + item.getS3() + "分钟");
                }
            });
        } else {

        }
        if (item.getS4() > 0) {
            helper.setBackgroundResource(R.id.time4, R.color.google_red);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("下班早退" + item.getS4() + "分钟");
                }
            });
        } else {

        }
        if (item.getS5() > 0) {
            helper.setBackgroundResource(R.id.time5, R.color.google_red);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("上班迟到" + item.getS5() + "分钟");
                }
            });
        } else {

        }
        if (item.getS6() > 0) {
            helper.setBackgroundResource(R.id.time6, R.color.google_red);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast("下班早退" + item.getS6() + "分钟");
                }
            });
        } else {

        }

        if (("漏卡").equals(item.getTimer1())) {
            helper.setBackgroundResource(R.id.time1, R.color.google_yellow);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
        }
        if (("漏卡").equals(item.getTimer2())) {
            helper.setBackgroundResource(R.id.time2, R.color.google_yellow);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer3())) {
            helper.setBackgroundResource(R.id.time3, R.color.google_yellow);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer4())) {
            helper.setBackgroundResource(R.id.time4, R.color.google_yellow);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer5())) {
            helper.setBackgroundResource(R.id.time5, R.color.google_yellow);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer6())) {
            helper.setBackgroundResource(R.id.time6, R.color.google_yellow);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
        }

        helper.setText(R.id.time1, item.getTimer1());
        helper.setText(R.id.time2, item.getTimer2());
        helper.setText(R.id.time3, item.getTimer3());
        helper.setText(R.id.time4, item.getTimer4());
        helper.setText(R.id.time5, item.getTimer5());
        helper.setText(R.id.time6, item.getTimer6());

        if (item.getAbnormalRec().contains(StringUtil.useList2(mTypes, "请假"))) {
            changeStatus(helper,R.color.green,"请假",item);
        } else if (item.getAbnormalRec().contains(StringUtil.useList2(mTypes, "出差"))) {
            changeStatus(helper,R.color.orange,"出差",item);
        } else if (item.getAbnormalRec().contains(StringUtil.useList2(mTypes, "外出"))) {
            changeStatus(helper,R.color.violet_7B1FA2,"外出",item);
        } else if (item.getAbnormalRec().contains(StringUtil.useList2(mTypes, "加班"))) {
            changeStatus(helper,R.color.google_coffee,"加班",item);
        }

        LinearLayout llTable = helper.getView(R.id.ll_table);
        LinearLayout llContent = helper.getView(R.id.ll_content);

        if (item.getAbnormalRec().contains("记录") && (TextUtils.isEmpty(item.getTimer1()) &&
                TextUtils.isEmpty(item.getTimer2()) &&
                TextUtils.isEmpty(item.getTimer3()) &&
                TextUtils.isEmpty(item.getTimer4()) &&
                TextUtils.isEmpty(item.getTimer5()) &&
                TextUtils.isEmpty(item.getTimer6()))) {
            llTable.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
            if (type == 1) {
                helper.setText(R.id.tv_time_head2, item.getUserName());
            }else {
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_yellow));
            }
            helper.setText(R.id.tv_content, item.getAbnormalRec());
            helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_yellow));
        }
        else if (item.getAbnormalRec().contains("休息") && (TextUtils.isEmpty(item.getTimer1()) &&
                TextUtils.isEmpty(item.getTimer2()) &&
                TextUtils.isEmpty(item.getTimer3()) &&
                TextUtils.isEmpty(item.getTimer4()) &&
                TextUtils.isEmpty(item.getTimer5()) &&
                TextUtils.isEmpty(item.getTimer6()))) {
            llTable.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
            if (type == 1) {
                helper.setText(R.id.tv_time_head2, item.getUserName());
            }else {
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_green));
            }
            helper.setText(R.id.tv_content, item.getAbnormalRec());
            helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_green));
        }
        else if (item.getAbnormalRec().contains("法定") && ("漏卡".equals(item.getTimer1())||
                "漏卡".equals(item.getTimer2())||
                "漏卡".equals(item.getTimer3())||
                "漏卡".equals(item.getTimer4())||
                "漏卡".equals(item.getTimer5())||
                "漏卡".equals(item.getTimer6()))){
            llTable.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
            if (type == 1) {
                helper.setText(R.id.tv_time_head2, item.getUserName());
            }else {
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_green));
            }
            helper.setText(R.id.tv_content, item.getAbnormalRec());
            helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_green));
        }
        else {
            llTable.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.GONE);
        }
    }

    private void TypeValue(BaseViewHolder helper, CheckInBean.DataBean item) {
        if (type == 1) {
            helper.setText(R.id.tv_time_head, item.getUserName());
        }else {
            String[][] object = {new String[]{"星期", ""}};
            String[][] object2 = {new String[]{"T", " "}};

            String ymdDate = StringUtil.replace(item.getRecordDate(), object2);

            Date start = DateUtil.setDate(ymdDate);
            curDay = myDay.format(start);
            LatteLogger.d("dddddddd", curDay);
            weekday = curDay + "/" + StringUtil.replace(item.getWeekName(), object);
            helper.setText(R.id.tv_time_head, weekday);
        }
    }

    private void changeStatus(BaseViewHolder helper,int bgColor,String strType,CheckInBean.DataBean item){
        if (("漏卡").equals(item.getTimer1())) {
            helper.setBackgroundResource(R.id.time1, R.color.google_yellow);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time1,strType);
            helper.setBackgroundResource(R.id.time1, bgColor);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
        if (("漏卡").equals(item.getTimer2())) {
            helper.setBackgroundResource(R.id.time2, R.color.google_yellow);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time2,strType);
            helper.setBackgroundResource(R.id.time2, bgColor);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
        if ("漏卡".equals(item.getTimer3())) {
            helper.setBackgroundResource(R.id.time3, R.color.google_yellow);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time3,strType);
            helper.setBackgroundResource(R.id.time3, bgColor);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
        if ("漏卡".equals(item.getTimer4())) {
            helper.setBackgroundResource(R.id.time4, R.color.google_yellow);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time4,strType);
            helper.setBackgroundResource(R.id.time4, bgColor);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
        if ("漏卡".equals(item.getTimer5())) {
            helper.setBackgroundResource(R.id.time5, R.color.google_yellow);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time5,strType);
            helper.setBackgroundResource(R.id.time5, bgColor);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
        if ("漏卡".equals(item.getTimer6())) {
            helper.setBackgroundResource(R.id.time6, R.color.google_yellow);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time6,strType);
            helper.setBackgroundResource(R.id.time6, bgColor);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
            helper.getView(R.id.time6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.getInstance().showToast(item.getAbnormalRec());
                }
            });
        }
    }
}


