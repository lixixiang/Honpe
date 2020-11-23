package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart;

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

import com.flyco.dialog.widget.NormalListDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.DBName;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.SelectorDepartFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position1.RxDialogEditSureCancel;
import com.honpe.lxx.app.ui.main.oa.ui.position1.adapter.CheckInAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DBUtils;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBean;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.SEARCH_DEPART_DETAIL;
import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: AllDepartColorFragment
 * Author: asus
 * Date: 2020/8/10 11:44
 * Description: 所有部门的人员考勤情况
 */
public class AllDepartColorFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
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

    private CheckInBean bean;
    private String session, strDay, mCurDay, InputUserNos, departId, title;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public int oneDay; //这个初始日子
    List<CheckInBean.DataBean> mList = new ArrayList<>();
    CheckInAdapter adapter;
    private String[] strSearchStyle = {"姓名、工号查询", "分组筛选"};


    public static AllDepartColorFragment newInstance(String title) {
        AllDepartColorFragment fragment = new AllDepartColorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AllDepartColorFragment newInstance(String title, String departId) {
        AllDepartColorFragment fragment = new AllDepartColorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("departId", departId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_total_query;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            departId = bundle.getString("departId");
            if (!"".equals(departId)) {
                DBUtils.put(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, departId);
            }
        }

        initToolbarNav(llBack);
        tvTitle.setText(title);
        tvEnd.setVisibility(View.VISIBLE); //右侧搜索
        if (TextUtils.isEmpty(departId) || "".equals(departId)) {
            departId = (String) DBUtils.get(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, "");
        }
        strDay = sdf.format(new Date());
        mCurDay = sdf.format(new Date());
        btnUpPager.setText("前一天");
        btnNextPager.setText("后一天");
        tvDate.setText(strDay);
        isClickDate();
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        iniData(departId);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    iniData(departId);
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }

    private void iniData(String departId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Dept_Dep", departId);
        jsonObject.addProperty("StarDay", strDay);
        jsonObject.addProperty("EndDay", strDay);
        session = (String) MyApplication.get(_mActivity, Session, "");
        LatteLogger.d("sessionW", session);
        EasyHttp.post(Constants.CHECK_IN_DATA + session)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
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

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("requestData", s);
                        mList.clear();

                        llDate.setVisibility(View.VISIBLE);
                        tvTime.setText("姓名");
                        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new Convert.NullStringToEmptyAdapterFactory()).create();
                        bean = gson.fromJson(s, CheckInBean.class);
                        for (int i = 0; i < bean.getData().size(); i++) {
                            if (!"".equals(bean.getData().get(i).getUserName())) {
                                CheckInBean.DataBean dataBean = bean.getData().get(i);
                                mList.add(dataBean);
                            }
                        }
                        adapter = new CheckInAdapter(mList, 1);
                        LatteLogger.d("jsonBean", GsonBuildUtil.GsonBuilder(bean));
                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.tv_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay = -1;
                setOneDay(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay = 1;
                setOneDay(oneDay);
                break;
            case R.id.tv_end:

                NormalListDialog normalListDialog = new NormalListDialog(_mActivity, strSearchStyle);
                normalListDialog.title(getString(R.string.search) + getString(R.string.style)).layoutAnimation(null).show();
                normalListDialog.setOnOperItemClickL((parent, view1, position, id) -> {
                    switch (position) {
                        case 0:
                            final RxDialogEditSureCancel editDialog = new RxDialogEditSureCancel(_mActivity, InputUserNos, "查询个人", "输入工号或姓名查询:", "请输入");
                            editDialog.show();
                            break;
                        case 1:
//                            FilterDialog filterDialog = new FilterDialog(_mActivity, session);
//                            filterDialog.show();
                            start(SelectorDepartFragment.newInstance(1));
                            break;
                    }
                    normalListDialog.dismiss();
                });


                break;
        }
    }

    private void setOneDay(int oneDay) {
        Date date = DateUtil.yearAddNum(DateUtil.setDate(tvDate.getText().toString()), oneDay);
        strDay = sdf.format(date);
        tvDate.setText(strDay);
        isClickDate();
        iniData(departId);
        adapter.notifyDataSetChanged();
    }

    private void isClickDate() {
        if (DateUtil.setDate(mCurDay).getTime() <= DateUtil.setDate(tvDate.getText().toString()).getTime()) {
            btnNextPager.setClickable(false);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setBackgroundResource(android.R.color.transparent);
        } else if (DateUtil.setDate(mCurDay).getTime() > DateUtil.setDate(tvDate.getText().toString()).getTime()) {
            btnNextPager.setClickable(true);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        Event<String> event = new Event<String>(FinalClass.BACK_CHECK_IN_MANAGE, departId);
        EventBusUtil.sendEvent(event);
        return super.onBackPressedSupport();
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
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getUserName().equals(InputUserNos) || bean.getData().get(i).getEmpNo().equals(InputUserNos)) {
                        recyclerView.scrollToPosition(i);
                        adapter.notifyDataSetChanged();
                    }
                }
                LatteLogger.d("InputUserNos", InputUserNos);
                break;
            case SEARCH_DEPART_DETAIL:
                departId = (String) event.getData();
                LatteLogger.d("departId", departId);
                iniData(departId);
                DBUtils.put(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, departId);
                break;

        }
    }
}
