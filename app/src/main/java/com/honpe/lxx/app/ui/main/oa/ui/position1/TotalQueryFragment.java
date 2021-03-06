package com.honpe.lxx.app.ui.main.oa.ui.position1;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean.CheckInTotalBean;
import com.honpe.lxx.app.ui.main.oa.ui.position1.adapter.CheckInAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBean;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 15:46
 * @Author: 李熙祥
 * @Description: java类作用描述 考勤
 */
public class TotalQueryFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;


    private CheckInAdapter checkInAdapter;
    private String session, firstDay, lastDay, curMonth, curDay, today;
    List<String> mDateList = new ArrayList<>();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String InputUserNos = "";
    private LinearLayoutManager linearLayoutManager;
    private int mIndex;
    private String title;
    private CheckInTotalBean.Data data;
    private Bundle bundle = new Bundle();

    public static TotalQueryFragment newInstance(Bundle bundle) {
        TotalQueryFragment fragment = new TotalQueryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TotalQueryFragment newInstance(CheckInTotalBean.Data data) {
        TotalQueryFragment fragment = new TotalQueryFragment();
        fragment.data = data;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_total_query;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getTestNote(InputUserNos, firstDay, lastDay);
    }

    @Override
    protected void initView() {
        LatteLogger.d("myData",GsonBuildUtil.GsonBuilder(data));
        bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
        }

        llDate.setVisibility(View.VISIBLE);
        initToolbarNav(llBack);
        if (data != null) {
            tvTitle.setText(data.getUserName() + "考勤");
        } else {
            tvTitle.setText(title);
        }
        tvEnd.setText("查找");
        tvEnd.setVisibility(View.VISIBLE);
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);

        firstDay = DateUtil.getFirstDayOfMonth(format);
        lastDay = DateUtil.getLastDayOfMonth(format);
        mDateList = DateUtil.queryData(format, firstDay, lastDay);
        LatteLogger.d("day", firstDay + "  " + lastDay);
        today = format.format(new Date());
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
        refreshLayout.setOnRefreshListener(this);
        for (int i = 0; i < mDateList.size(); i++) {
            if (today.equals(mDateList.get(i))) {
                mIndex = i;
                break;
            }
        }
    }

    private void getTestNote(String myNo, String firstDay, String lastDay) {//"2495"
        if (data != null) {
            myNo = data.getNo();
        }

        String finalMyNo = myNo;
        disposable = EasyHttp.post(Constants.PERSON_CHECK + session)
                .params("EmpNo", myNo)
                .params("StarDay", firstDay)
                .params("EndDay", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity, 0);
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getInt("Status") == -1) {
                                startWithPop(LoginFragment.newInstance(TotalQueryFragment.TAG));
                                ToastUtil.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final CheckInBean checkInBean = Convert.fromJson(s, CheckInBean.class);
                                LatteLogger.d("getTestNote", GsonBuildUtil.GsonBuilder(checkInBean));
                                if (checkInBean.getStatus() == 0) {
                                    if (!"".equals(finalMyNo) || !TextUtils.isEmpty(finalMyNo)) {
                                        for (int i = 0; i <checkInBean.getData().size() ; i++) {
                                            if (checkInBean.getData().get(i).getUserName() !=null) {
                                                tvTitle.setText(checkInBean.getData().get(i).getUserName()+title);
                                            }
                                        }
                                    }

                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.scrollToPosition(mIndex);
                                    checkInAdapter = new CheckInAdapter(checkInBean.getData(),0);
                                    recyclerView.setAdapter(checkInAdapter);
                                    checkInAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
        firstDay = DateUtil.getFirstDayOfMonth(format, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(format, oneDay);
        mDateList = DateUtil.queryData(format, firstDay, lastDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
        getTestNote(InputUserNos, firstDay, lastDay);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                EventBean InputUserNo = (EventBean) event.getData();
                InputUserNos = InputUserNo.getContent();
                LatteLogger.d("InputUserNos", InputUserNos);
                getTestNote(InputUserNos, firstDay, lastDay);
                break;
        }
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                final RxDialogEditSureCancel editDialog = new RxDialogEditSureCancel(_mActivity, InputUserNos, "考勤查询", "输入员工工号:", "请输入");
                editDialog.setCanceledOnTouchOutside(true);
                editDialog.show();
                break;
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


    /**
     * 做一个刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getTestNote(InputUserNos, firstDay, lastDay);
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }
}


