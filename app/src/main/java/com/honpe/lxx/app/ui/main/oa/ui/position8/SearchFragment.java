package com.honpe.lxx.app.ui.main.oa.ui.position8;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position5.add.AddOverTimeFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.SearchEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.fragment.ChildSearchFragment;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.NavigationTabStrip;
import com.honpe.lxx.app.widget.msgtips.QBadgeView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.REFRESH_SEARCH_APPROVE;

/**
 * FileName: SearchFragment
 * Author: asus
 * Date: 2020/10/10 16:40
 * Description: 审批查询
 */
public class SearchFragment extends BaseBackFragment {
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
    @BindView(R.id.tab)
    NavigationTabStrip tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tab1)
    TextView tvTab1;
    @BindView(R.id.tab2)
    TextView tvTab2;
    @BindView(R.id.tab3)
    TextView tvTab3;
    @BindView(R.id.ll_tabs)
    LinearLayout llTabs;

    private SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String titles, firstDay, lastDay, curMonth;
    private String[] tabTitle = {"申请单", "待审批", "已审批"};
    private BaseFragmentPagerAdapter viewpagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String mType;
    private int tips;
    private List<TextView> textViews = new ArrayList<>();
    List<SearchEntity.DataEntity.RowsEntity> rowsEntity = new ArrayList<>();
    Bundle bundle = new Bundle();

    public static SearchFragment newInstance(String title) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        firstDay = DateUtil.getFirstDayOfMonth(YearMonthDay);
        lastDay = DateUtil.getLastDayOfMonth(YearMonthDay);
        initRecyclerView("");

        initToolbarNav(llBack);
        curMonth = sfYearMonth.format(new Date());
        tvDate.setText(curMonth);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tvTitle.setText(bundle.getString("title"));
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
        firstDay = DateUtil.getFirstDayOfMonth(YearMonthDay, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(YearMonthDay, oneDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        initRecyclerView("1");
    }

    /**
     * @param e 用于刷新数据，防止重新添加fragment
     */
    private void initRecyclerView(String e) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("StartTime", firstDay);
        jsonObject.addProperty("EndTime", lastDay);
        jsonObject.addProperty("keyword", "");
        jsonObject.addProperty("departmentId", departId);

        LatteLogger.d("initRecyclerView", GsonBuildUtil.GsonBuilder(jsonObject));

        if (EmployeeId == null) {
            startWithPop(LoginFragment.newInstance(SearchFragment.TAG));
        } else {
            disposable = EasyHttp.post(Constants.AppSearchProveList)
                    .params("EmployeeId", EmployeeId)
                    .params("queryJson", String.valueOf(jsonObject))
                    .execute(new SimpleCallBack<String>() {
                        @Override
                        public void onStart() {
                            super.onStart();
                            ProgressUtils.disLoadView(_mActivity,1);
                        }

                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                            ProgressUtils.disLoadView(_mActivity,0);
                        }

                        @Override
                        public void onError(ApiException e) {

                        }

                        @Override
                        public void onSuccess(String s) {
                            SearchEntity jsonBean = Convert.fromJson(s, SearchEntity.class);
                            LatteLogger.d("testOnSuccess", GsonBuildUtil.GsonBuilder(jsonBean));
                            LatteLogger.d("onSuccess", s);
                            textViews.clear();
                            tvTab1.setText(tabTitle[0]);
                            tvTab2.setText(tabTitle[1]);
                            tvTab3.setText(tabTitle[2]);
                            HashMap<String, List<SearchEntity.DataEntity.RowsEntity>> maps = new LinkedHashMap<>();
                            if (jsonBean.getData().getRows().size() > 0) {
                                viewpager.setVisibility(View.VISIBLE);
                                llTabs.setVisibility(View.VISIBLE);
                                    for (int j = 0; j < tabTitle.length; j++) {
                                    tips = 0;
                                    rowsEntity.clear();
                                    for (int i = 0; i < jsonBean.getData().getRows().size(); i++) {
                                        if (j == 0){
                                            tips++;
                                            new QBadgeView(_mActivity).bindTarget(tvTab1).setGravityOffset(1f,0f,true).setBadgeTextSize(10f, true).setBadgeText("共 "+tips);
                                        } else if (j == 1) {
                                            if (jsonBean.getData().getRows().get(i).getF_CurState().equals("等待审批")) {
                                                tips++;
                                                new QBadgeView(_mActivity).bindTarget(tvTab2).setGravityOffset(10f,0f,true).setBadgeBackgroundColor(getResources().getColor(R.color.google_yellow)).setBadgeTextSize(10f, true).setBadgeNumber(tips);
                                            }
                                        } else if (j == 2) {
                                            if (jsonBean.getData().getRows().get(i).getF_CurState().equals("通过") ||
                                                    jsonBean.getData().getRows().get(i).getF_CurState().equals("不通过")) {
                                                tips++;
                                                new QBadgeView(_mActivity).bindTarget(tvTab3).setGravityOffset(10f,0f,true).setBadgeBackgroundColor(getResources().getColor(R.color.google_yellow)).setBadgeTextSize(10f, true).setBadgeNumber(tips);
                                            }
                                        }
                                    }
                                }
                                for (int i = 0; i < jsonBean.getData().getRows().size(); i++) {
                                    String mType = jsonBean.getData().getRows().get(i).getF_SchemeName();
                                    List<SearchEntity.DataEntity.RowsEntity> rowsEntity = maps.get(mType);
                                    if (rowsEntity == null) {
                                        rowsEntity = new ArrayList<>();
                                    }
                                    rowsEntity.add(jsonBean.getData().getRows().get(i));
                                    maps.put(mType,rowsEntity);
                                }

                                bundle.putSerializable("map", maps);
                                if ("".equals(e)) {
                                    for (int i = 0; i < tabTitle.length; i++) {
                                        fragments.add(ChildSearchFragment.newInstance(bundle));
                                    }
                                    viewpagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
                                    viewpager.setAdapter(viewpagerAdapter);
                                } else {
                                    for (int i = 0; i < tabTitle.length; i++) {
                                        viewpagerAdapter.replaceFragment(i++, ChildSearchFragment.newInstance(bundle));
                                    }
                                }
                                tab.setTitles(tabTitle);
                                tab.setViewPager(viewpager, 0);
                            } else {
                                viewpager.setVisibility(View.GONE);
                                llTabs.setVisibility(View.GONE);
                            }

                            tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    maps.clear();
                                    tips = 0;
                                    for (int i = 0; i < jsonBean.getData().getRows().size(); i++) {
                                        if (position == 1) {
                                            if (jsonBean.getData().getRows().get(i).getF_CurState().equals("等待审批")) {
                                                mType = jsonBean.getData().getRows().get(i).getF_SchemeName();
                                                rowsEntity = maps.get(mType);
                                                if (rowsEntity == null) {
                                                    rowsEntity = new ArrayList<>();
                                                }
                                                tips++;
                                                new QBadgeView(_mActivity).bindTarget(tvTab2).setGravityOffset(10f,0f,true).setBadgeBackgroundColor(getResources().getColor(R.color.google_yellow))
                                                        .setBadgeTextSize(10f, true).setBadgeNumber(tips);
                                                rowsEntity.add(jsonBean.getData().getRows().get(i));
                                                maps.put(mType, rowsEntity);
                                            }

                                        } else if (position == 2) {
                                            if (jsonBean.getData().getRows().get(i).getF_CurState().equals("通过") ||
                                                    jsonBean.getData().getRows().get(i).getF_CurState().equals("不通过")) {
                                                mType = jsonBean.getData().getRows().get(i).getF_SchemeName();
                                                rowsEntity = maps.get(mType);
                                                if (rowsEntity == null) {
                                                    rowsEntity = new ArrayList<>();
                                                }
                                                tips++;
                                                new QBadgeView(_mActivity).bindTarget(tvTab3).setGravityOffset(10f,0f,true).setBadgeBackgroundColor(getResources().getColor(R.color.google_yellow)).setBadgeTextSize(10f, true).setBadgeNumber(tips);
                                                rowsEntity.add(jsonBean.getData().getRows().get(i));
                                                maps.put(mType, rowsEntity);
                                            }

                                        } else {
                                            mType = jsonBean.getData().getRows().get(i).getF_SchemeName();
                                            rowsEntity = maps.get(mType);
                                            if (rowsEntity == null) {
                                                rowsEntity = new ArrayList<>();
                                            }
                                            tips++;
                                            new QBadgeView(_mActivity).bindTarget(tvTab1).setGravityOffset(1f,0f,true).setBadgeTextSize(10f, true).setBadgeText("共 "+tips);
                                            rowsEntity.add(jsonBean.getData().getRows().get(i));
                                            maps.put(mType, rowsEntity);
                                        }
                                    }
                                    LatteLogger.d("testMag", rowsEntity.size());
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("map", maps);
                                    bundle.putInt("curPos", position);
                                    viewpagerAdapter.replaceFragment(fragments.get(position), ChildSearchFragment.newInstance(bundle));
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                        }
                    });
        }
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                startForResult(AddOverTimeFragment.newInstance(titles, null, "1", "3"), 444);
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
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case REFRESH_SEARCH_APPROVE:
                String e = (String) event.getData();
                LatteLogger.d("REFRESH_SEARCH_APPROVE", GsonBuildUtil.GsonBuilder(e));
                 initRecyclerView(e);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         ProgressUtils.disLoadView(_mActivity,0);
                     }
                 },1000);
                break;
        }
    }
}





