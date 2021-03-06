package com.honpe.lxx.app.ui.main.oa.ui.position9.history;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position9.activity.DetailListActivity;
import com.honpe.lxx.app.ui.main.oa.ui.position9.adapter.CarHistoryAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.CarSendCheckBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position10.history
 * 作者: lxx
 * 日期: 2019/4/7 16:29
 * 描述: 派车历史查询
 */
public class CarHistoryFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    CarHistoryAdapter adapter;
    private String CarNo, session, StartTime, EndTime, curMonth, driverTel;
    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat YearMonthDayHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final CarHistoryFragment newInstance(String carNo, String driverTel) {
        CarHistoryFragment fragment = new CarHistoryFragment();
        fragment.CarNo = carNo;
        fragment.driverTel = driverTel;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.common_toolbar_refresh_recycler;
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
        }
    }

    public int oneDay = 0; //这个初始日子

    public int getOneDay() {
        String countStr = tvDate.getText().toString().trim();
        if (countStr != null) {
            oneDay = Integer.valueOf(countStr);
        }
        return oneDay;
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        StartTime = DateUtil.getFirstDayOfMonth(YearMonthDay, oneDay);
        EndTime = DateUtil.getLastDayOfMonth(YearMonthDay, oneDay);
        getDate(CarNo, StartTime, EndTime);
        LatteLogger.d("monday", StartTime + " 某月第一天 " + EndTime + "某月最后一天" + " CarNo  " + CarNo + "  driverTel  " + driverTel);
        tvDate.setText(DateUtil.getLastMonth(oneDay));

    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText(CarNo + "车辆记录");
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        StartTime = DateUtil.getFirstDayOfMonth(YearMonthDay);
        EndTime = YearMonthDay.format(new Date());
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        refreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getDate(CarNo, StartTime, EndTime);
    }

    private void getDate(final String CarNo, String StartTime, String EndTime) {
        LatteLogger.d("开始" + StartTime + "结束" + EndTime + "车牌 " + CarNo);
        EasyHttp.post(Constants.CARAPPLYSEARCH + session)
                .params("CarNo", CarNo)
                .params("StartTime", StartTime)
                .params("EndTime", EndTime)
                .params("Status", "4")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("result", result);
                        final CarSendCheckBean bean = Convert.fromJson(result, CarSendCheckBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new CarHistoryAdapter(bean.getData());
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    testChildData(position, bean.getData().get(position), DetailListActivity.class);
                                }
                            });

                        } else {
                            ToastUtil.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }

    private void testChildData(int position, CarSendCheckBean.DataBean item, Class<?> cls) {
        Intent intent = new Intent(_mActivity, cls);
        intent.putExtra("PicUrl", item.getPicturesUrl());
        intent.putExtra("driverPhone", driverTel);
        intent.putExtra("CarNo", item.getCarNo());
        intent.putExtra("CarType", item.getCarName());
        intent.putExtra("CarStatus", item.getCarStatus());
        intent.putExtra("Driver", item.getDriver());
        intent.putExtra("DriverTel", driverTel);
        intent.putExtra("OrderSeq", "");
        intent.putExtra("SendCarNo", item.getSendCarNo());
        intent.putExtra("DeptName", item.getDeptName());
        intent.putExtra("UserName", item.getUserName());
        intent.putExtra("GroupName", item.getGroupName());
        intent.putExtra("AppName", item.getUserName());
        intent.putExtra("OrderTime", item.getOrderTime());
        intent.putExtra("UseCarTime", item.getUseCarTime());
        intent.putExtra("Entourage", item.getEntourage());
        intent.putExtra("Items", item.getItems());
        intent.putExtra("Reason", item.getReason());
        intent.putExtra("SetOutTime", item.getSetOutTime());
        intent.putExtra("RetTime", item.getRetTime());
        intent.putExtra("RetMileage", item.getRetMileage());
        intent.putExtra("Mileage", item.getMileage());
        intent.putExtra("Remarks", item.getRemarks());
        intent.putExtra("Tel", item.getTel());
        intent.putExtra("SenCarby", item.getSenCarby());
        intent.putExtra("SenCarTime", item.getSenCarTime());
        intent.putExtra("CancelStatus", item.getCancelStatus());
        intent.putExtra("UserCarDept", item.getUserCarDept());
        intent.putExtra("UserCarGroup", item.getUserCarGroup());
        intent.putExtra("Status", item.getStatus());
        intent.putExtra("OrderStatus", item.getCarStatus());
        intent.putExtra("Destination", item.getDestination());
        intent.putExtra("EstimatedRTime", item.getRetTime());
        intent.putExtra("EstimatedUseTime", item.getUseCarTime());

        ArrayList<String> opts = (ArrayList<String>) item.getOpts();
        intent.putStringArrayListExtra("opts", opts);
        _mActivity.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDate(CarNo, StartTime, EndTime);
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
