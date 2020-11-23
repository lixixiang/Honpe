package com.honpe.lxx.app.ui.main.oa.ui.position6;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position6.adapter.BaseChild04Adapter;
import com.honpe.lxx.app.ui.main.oa.ui.position6.add.CardReissueAddFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position6.bean.CardQueryBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Session;


/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position6
 * 作者: lxx
 * 日期: 2019/4/26 17:17
 * 描述: 补卡
 */
public class CardQueryFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_end)
    TextView ivAdd;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private String strTitle, curMonth, firstDay, lastDay, session;
    private List<CardQueryBean> CardList = new ArrayList<>();

    private BaseChild04Adapter adapter;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static CardQueryFragment newInstance(String title) {
        CardQueryFragment fragment = new CardQueryFragment();
        fragment.strTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_total_query;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText(strTitle);
        session = (String) MyApplication.get(_mActivity, Session, "");
        firstDay = DateUtil.getFirstDayOfMonth(format);
        lastDay = DateUtil.getLastDayOfMonth(format);
        curMonth = sf.format(new Date());

        tvDate.setText(curMonth);
        ivAdd.setVisibility(View.VISIBLE);
        ivAdd.setText(getString(R.string.apply));

        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getMastRequestData();
    }

    private void getMastRequestData() {
        disposable = EasyHttp.post(Constants.MASTED_CARD + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("manager", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getInt("Status") == -1) {
                                start(LoginFragment.newInstance(CardQueryFragment.TAG));
                                ToastUtil.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                                CardList.clear();
                                if (bean.getStatus() == 0) {
                                    if (bean.getData().size() > 0) {
                                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                        adapter = new BaseChild04Adapter(getData(bean.getData()));
                                        recyclerView.setAdapter(adapter);
                                    } else {
                                        getRequestJsonData();
                                    }
                                } else {
                                    ToastUtil.getInstance().showToast(bean.getMsg());
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getRequestJsonData() {
        EasyHttp.post(Constants.FULL_CARD_LIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("person", s);
                        CardQueryBean bean = Convert.fromJson(s, CardQueryBean.class);
                        if (bean.getStatus() == 0) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            adapter = new BaseChild04Adapter(getData(bean.getData()));
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private List<CardQueryBean> getData(List<CardQueryBean.DataBean> data) {
        Map<String, List<CardQueryBean.DataBean>> maps = new HashMap<>();

        for (int i = 0; i < data.size(); i++) {
            if (TextUtils.isEmpty(data.get(i).getPicUrl()) || "".equals(data.get(i).getPicUrl())) {
                data.get(i).setPicUrl("dd");
            }
            String myName = data.get(i).getF_CreateUserName() + " " + data.get(i).getPicUrl();

            List<CardQueryBean.DataBean> listBean = maps.get(myName);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            CardQueryBean.DataBean bean = data.get(i);
            listBean.add(bean);
            maps.put(myName, listBean);
            LatteLogger.d("GsonBuildUtil", GsonBuildUtil.GsonBuilder(myName));
        }

        Set<String> keySet = maps.keySet();
        for (String key : keySet) {
            CardQueryBean bean = new CardQueryBean();
            bean.setMsg(key);
            List<CardQueryBean.DataBean> listDatas = maps.get(key);
            List<CardQueryBean.DataBean> listDatas2 = new ArrayList<>();

            for (int i = 0; i < listDatas.size(); i++) {
                CardQueryBean.DataBean bean1 = new CardQueryBean.DataBean();
                bean1.setF_AuditTime(listDatas.get(i).getF_AuditTime());
                bean1.setF_CreateUserName(listDatas.get(i).getF_CreateUserName());
                bean1.setF_Reason(listDatas.get(i).getF_Reason());
                bean1.setPicUrl(listDatas.get(i).getPicUrl());
                listDatas2.add(bean1);
                bean.setData(listDatas2);
            }
            bean.setData(listDatas);
            CardList.add(bean);
        }
        LatteLogger.d(GsonBuildUtil.GsonBuilder(CardList));
        return CardList;
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
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        LatteLogger.d("myDay", "firstDay==" + firstDay + "==lastDay==" + lastDay);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getMastRequestData();
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                startForResult(CardReissueAddFragment.newInstance(), 111);
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

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK && data != null) {
            getMastRequestData();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getMastRequestData();
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }

}
