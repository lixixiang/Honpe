package com.honpe.lxx.app.ui.main.oa.ui.position16;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.main.more.adapter.BaseViewPagerAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.ChildFragment1;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.ChildFragment2;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.ChildFragment3;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.FragmentPraise;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.widget.NavigationTabStrip;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: EmployeeWithOrderFragment
 * Author: asus
 * Date: 2020/8/11 11:34
 * Description: 员工点餐主界面
 */
public class EmployeeWithOrderFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.nts_bottom)
    NavigationTabStrip ntsBottom;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private String title, jsonString, session;
    private int curPos = 0;
    List<Fragment> fragments = new ArrayList<>();
    BaseFragmentPagerAdapter adapter;
    FragmentPraise.CommitMeatBean jsonBean;

    public static EmployeeWithOrderFragment newInstance(String title) {
        EmployeeWithOrderFragment fragment = new EmployeeWithOrderFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_employee_with_order;
    }

    @Override
    protected void initView() {
        init();
    }

    private void initViewPager() {
        fragments.add(ChildFragment1.newInstance());
        fragments.add(ChildFragment2.newInstance());
        fragments.add(ChildFragment3.newInstance());

        adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        ntsBottom.setViewPager(viewPager, 0);
        ntsBottom.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 员工报餐提交
     */
    private void getReportMeet() {
        JSONObject obj = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            obj.put("Data", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = String.valueOf(obj);
        LatteLogger.d("testBackData", params);

        LatteLogger.d("session", session);
        disposable = EasyHttp.post(Constants.YGREPORTMEET + session)
                .upJson(params)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("tag", s);
                        adapter.notifyDataSetChanged();
                        ToastUtil.getInstance().showToast("提交成功！");
                    }
                });
    }

    /**
     * 提交员工点餐菜品接口
     */
    private void getReportMeet2() {
       disposable = EasyHttp.post(Constants.YGCOMMITMIUE + session)
                .params("Data", jsonString)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        LatteLogger.d(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        adapter.notifyDataSetChanged();
//                        getInfo info = Convert.fromJson(s, getInfo.class);
//                        if (info.getStatus() == 0) {
//                            ToastUtil.getInstance().showToast("提交成功");
//                        } else {
//                            ToastUtil.getInstance().showToast(info.getMsg());
//                        }
                    }
                });
    }

    /**
     * 员工评论提交
     */
    private void getReportMeet3() {
        Gson gson = new Gson();
       jsonString =  gson.toJson(jsonBean);
        LatteLogger.d("testjsonString", jsonString);
        disposable = EasyHttp.post(Constants.GETCOMMENT + session)
                .upJson(jsonString)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d(s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            ToastUtil.getInstance().showToast(obj.getString("Msg"));
                            EventBusUtil.sendEvent(new Event(FinalClass.C));
                            adapter.replaceFragment(fragments.get(curPos),ChildFragment3.newInstance());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void init() {
        initToolbarNav(llBack);
        tvTitle.setText(title);
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("提交");
        session = (String) MyApplication.get(_mActivity, Session, "");

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EMPLOYEE_MAIN:
                jsonString = (String) event.getData();
                LatteLogger.d("testjsonString", GsonBuildUtil.GsonBuilder(jsonString));
                break;
            case FinalClass.EMPLOYEE_MAIN2:
                jsonString = (String) event.getData();
                LatteLogger.d("testjsonString2", GsonBuildUtil.GsonBuilder(jsonString));
                break;
            case FinalClass.EMPLOYEE_MAIN3:
                jsonBean = (FragmentPraise.CommitMeatBean) event.getData();
                LatteLogger.d("testjsonString3", GsonBuildUtil.GsonBuilder(jsonBean));
                break;
        }
    }

    @OnClick(R.id.tv_end)
    public void onViewClicked() {
        if (curPos == 0) {
            getReportMeet();
        } else if (curPos == 1) {
            getReportMeet2();
        } else if (curPos == 2) {
            getReportMeet3();
        }

        hideSoftKeyBoard();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Utils.checkSession(this, session);
        initViewPager();
    }
}
