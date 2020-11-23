package com.honpe.lxx.app.ui.main.oa.ui.position9.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.CarSendCheckBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 12:13
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class CarHistoryAdapter extends BaseQuickAdapter<CarSendCheckBean.DataBean, BaseViewHolder> {

    private SimpleDateFormat sf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat sf1 = new SimpleDateFormat("MM-dd HH:mm");
    private SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm");
    private Date curTime, OutTime, backTime;
    private String userCarTime, outFactoryTime, backFactoryTime;
    private TextView carNo,applyPerson,destination,reason,depart,status;
    public CarHistoryAdapter(@Nullable List<CarSendCheckBean.DataBean> data) {
        super(R.layout.item_send_car, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarSendCheckBean.DataBean item) {
        carNo = helper.getView(R.id.tv_order_no);//申请单
        applyPerson = helper.getView(R.id.tv_applyPerson); //申请人
        reason = helper.getView(R.id.tv_orderCause);//原因
        destination = helper.getView(R.id.tv_destination);//目的地
        depart = helper.getView(R.id.tv_depart);//部门
        status = helper.getView(R.id.tv_status);//状态

        carNo.setText(item.getCarNo());
        applyPerson.setText(item.getUserName());
        reason.setText(item.getReason());
        destination.setText(item.getDestination());
        depart.setText(item.getDeptName() + "-");

        String userName = (String) MyApplication.get(getContext(), FinalClass.UserName, "");

        try {
            curTime = sf0.parse(item.getUseCarTime());
            userCarTime = sf1.format(curTime);
            helper.setText(R.id.tv_user_car_time, userCarTime);//用车时间
            if (item.getUserName().equals(userName)) {
                helper.setText(R.id.tv_applyPerson, "自己");
            }else{
                helper.setText(R.id.tv_applyPerson, item.getUserName());
                ((TextView) helper.getView(R.id.tv_applyPerson)).setTextColor(getContext().getResources().getColor(R.color.grey_aeaeae));
            }

            if (item.getStatus() == 4) {
                status.setText("已完成");
                status.setTextColor(getContext().getResources().getColor(R.color.white));
                status.setBackgroundResource(R.drawable.select_grey_radius10);

                OutTime = sf0.parse(item.getSetOutTime());
                backTime = sf0.parse(item.getRetTime());
                outFactoryTime = sf2.format(OutTime);
                backFactoryTime = sf2.format(backTime);
                helper.setText(R.id.tv_out_time, outFactoryTime);//出厂时间
                helper.setText(R.id.tv_back_factory_time, backFactoryTime);//返厂时间
                long OutTimes = OutTime.getTime();
                long backTimes = backTime.getTime();
                //转小时分钟
                int minutes = (int) ((backTimes - OutTimes)/(1000 * 60));
                int hours = (int) Math.floor(minutes / 60);
                int minute = minutes % 60;
                System.out.println(hours + "小时" + minute + "分钟");
                if (backFactoryTime != "") {
                    helper.setText(R.id.tv_user_car_time, hours + "小时" + minute + "分钟");//用车时间
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


