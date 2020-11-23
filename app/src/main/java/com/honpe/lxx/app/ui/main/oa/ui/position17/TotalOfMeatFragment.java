package com.honpe.lxx.app.ui.main.oa.ui.position17;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position17.entity.TotalOfMeatEntity;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: TotalOfMeatFragment
 * Author: asus
 * Date: 2020/10/21 14:10
 * Description: 用餐统计
 */
public class TotalOfMeatFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.ll_meat)
    LinearLayout llMeat;
    @BindView(R.id.ll_item_meat)
    LinearLayout llItemMeat;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_meal_total_date)
    TextView tvMealTotalDate;
    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_3)
    TextView item3;
    @BindView(R.id.item_4)
    TextView item4;
    @BindView(R.id.item_5)
    TextView item5;
    @BindView(R.id.item_6)
    TextView item6;
    @BindView(R.id.item_7)
    TextView item7;
    @BindView(R.id.item_8)
    TextView item8;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;

    private String[] titles = {"报餐", "用餐"};
    private String firstDay, lastDay, curMonth, today;
    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    private TotalOfMeatAdapter adapter;
    private int mIndex;
    List<String> mDateList = new ArrayList<>(); //记录一个月日子

    public static TotalOfMeatFragment newInstance(String title) {
        TotalOfMeatFragment fragment = new TotalOfMeatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", "所有部门用餐统计");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_leave;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        firstDay = DateUtil.getFirstDayOfMonth(YearMonthDay);
        lastDay = DateUtil.getLastDayOfMonth(YearMonthDay);
        curMonth = sfYearMonth.format(new Date());
        tvDate.setText(curMonth);
        mDateList = DateUtil.queryData(YearMonthDay, firstDay, lastDay);
        today = YearMonthDay.format(new Date());
        for (int i = 0; i < mDateList.size(); i++) {
            if (today.equals(mDateList.get(i))) {
                mIndex = i;
                break;
            }
        }

        initData(myCode);
    }

    private void initData(String encode) {
        disposable = EasyHttp.post(Constants.AppGetReportList)
                .params("enCode", encode)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
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
                        LatteLogger.d("result", s);
                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                        TotalOfMeatEntity entity = GsonBuildUtil.NullToString().fromJson(s, TotalOfMeatEntity.class);
                        adapter = new TotalOfMeatAdapter(entity.getData().getRows());
                        recyclerView.setAdapter(adapter);
                        recyclerView.scrollToPosition(mIndex);
                    }
                });
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        Bundle bundle = getArguments();
        tvTitle.setText(bundle.getString("title"));
        llMeat.setVisibility(View.VISIBLE);
        llItemMeat.setVisibility(View.VISIBLE);

        item1.setText(titles[0]);
        item2.setText(titles[1]);
        item3.setText(titles[0]);
        item4.setText(titles[1]);
        item5.setText(titles[0]);
        item6.setText(titles[1]);
        item7.setText(titles[0]);
        item8.setText(titles[1]);
        refreshLayout.setOnRefreshListener(this);


    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    initData(myCode);
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }

    public int oneDay = 0; //这个初始日子

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

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(YearMonthDay, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(YearMonthDay, oneDay);
        mDateList = DateUtil.queryData(YearMonthDay, firstDay, lastDay);
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
        initData(myCode);
    }
}





























