package com.honpe.lxx.app.ui.main.more.multipleItem.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.DBName;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.CommonViewPagerDetailFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.AllDepartColorFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean.CheckInTotalBean;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DBUtils;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.NavigationTabStrip;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.DEPART_ID;
import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * 考勤管理 实时统计
 */
public class CheckInTotalFragment extends BaseBackFragment {
    @BindView(R.id.ic_left)
    ImageView icLeft;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ic_right)
    ImageView icRight;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.iv_right_detail)
    ImageView ivRightDetail;
    @BindView(R.id.nts_bottom)
    NavigationTabStrip ntsBottom;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.abl_bar)
    AppBarLayout ablBar;

    List<String> strList = new ArrayList<>();

    private String strDay, mCurDay, departId;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public int oneDay, totalCardNum, curCardNum, curLate, curEarly, curLegWork; //这个初始日子
    Map<String, List<CheckInTotalBean.Data>> maps = new LinkedHashMap<>();
    List<Fragment> fragments = new ArrayList<>();
    List<CheckInTotalBean.Data> detailCardNumList = new ArrayList<>();
    private String[] strTeams = {"漏卡", "迟到", "早退", "外勤"};
    private CheckInBean bean;
    private BaseFragmentPagerAdapter adapter;
    private int fKey; //fragment 标识位

    public static CheckInTotalFragment newInstance(String departId) {
        CheckInTotalFragment fragment = new CheckInTotalFragment();
        fragment.departId = departId;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_check_total;
    }

    @Override
    protected void initView() {
        strDay = sdf.format(new Date());
        mCurDay = sdf.format(new Date());
        if (!"".equals(departId)) {
            DBUtils.put(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, departId);
            departId = (String) DBUtils.get(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, "");
        } else {
            departId = (String) MyApplication.get(_mActivity,DEPART_ID,"");
        }

        LatteLogger.d("testDepartId", departId);
        tvDate.setText(strDay);
        icLeft.setColorFilter(getResources().getColor(R.color.grey_dark));
        ivRightDetail.setColorFilter(getResources().getColor(R.color.grey_dark));
        isClickDate();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        iniData();
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    private void iniData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Dept_Dep", departId);
        jsonObject.addProperty("StarDay", strDay);
        jsonObject.addProperty("EndDay", strDay);
        session = (String) MyApplication.get(_mActivity, Session, "");
        detailCardNumList.clear();
        strList.clear();
        maps.clear();

       disposable = EasyHttp.post(Constants.CHECK_IN_DATA + session)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        ProgressUtils.disLoadView(_mActivity, 1);
                        super.onStart();
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
                        LatteLogger.d("StringString",  GsonBuildUtil.GsonBuilder(s));
                        JSONObject obj = null;
                        int num = 0;
                        try {
                            obj = new JSONObject(s);
                            if (obj.getInt("Status") == -1) {
                                ToastUtil.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                ablBar.setVisibility(View.VISIBLE);
                                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new Convert.NullStringToEmptyAdapterFactory()).create();
                                bean = gson.fromJson(s, CheckInBean.class);
                                LatteLogger.d("testGsonData", GsonBuildUtil.GsonBuilder(bean));
                                totalCardNum = 0;
                                curCardNum = 0;
                                curLate = 0;
                                curEarly = 0;
                                curLegWork = 0;
                                for (int e = 0; e < strTeams.length; e++) {
                                    List<CheckInTotalBean.Data> listDataBean = maps.get(strTeams[e]);
                                    if (listDataBean == null) {
                                        listDataBean = new ArrayList<>();
                                    }
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        CheckInBean.DataBean item = bean.getData().get(i);
                                        if (!"".equals(item.getUserName())) {
                                            num++;
                                            if (e == 0) {
                                                if ((item.getTimer1().equals("漏卡")) || (item.getTimer2().equals("漏卡")) || (item.getTimer3().equals("漏卡")) ||
                                                        (item.getTimer4().equals("漏卡")) || (item.getTimer5().equals("漏卡")) || (item.getTimer6().equals("漏卡"))) {
                                                    curCardNum++; //漏卡
                                                    CheckInTotalBean.Data detailCardNumBean = new CheckInTotalBean.Data();
                                                    detailCardNumBean.setNo(bean.getData().get(i).getEmpNo());
                                                    detailCardNumBean.setUserName(bean.getData().get(i).getUserName());
                                                    detailCardNumBean.setTeam(bean.getData().get(i).getDptName());
                                                    listDataBean.add(detailCardNumBean);
                                                }
                                            } else if (e == 1) {
                                                if (item.getAbnormalRec().contains("迟到")) {
                                                    curLate++;
                                                    settingParam(bean, listDataBean, i);
                                                }
                                            } else if (e == 2) {
                                                if (item.getAbnormalRec().contains("早退")) {
                                                    curEarly++;
                                                    settingParam(bean, listDataBean, i);
                                                }
                                            } else if (e == 3) {
                                                if (item.getAbnormalRec().contains("外勤")) {
                                                    curLegWork++;
                                                    settingParam(bean, listDataBean, i);
                                                }
                                            }
                                        }
                                    }
                                    maps.put(strTeams[e], listDataBean);
                                }
                                viewPager.setCurrentItem(0);
                                if (viewPager.getAdapter() != null) {
                                    int i = 0;
                                    for (String key : maps.keySet()) {
                                        detailCardNumList = maps.get(key);
                                        strList.add(key);
                                        adapter.replaceFragment(i++, CommonViewPagerDetailFragment.newInstance(key, detailCardNumList));
                                    }
                                } else {
                                    for (String key : maps.keySet()) {
                                        detailCardNumList = maps.get(key);
                                        strList.add(key);
                                        fragments.add(CommonViewPagerDetailFragment.newInstance(key, detailCardNumList));
                                    }
                                    adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
                                    viewPager.setAdapter(adapter);
                                }


                                ntsBottom.setTitles(StringUtil.ListToArr(strList));
                                ntsBottom.setViewPager(viewPager, 0);

                                totalCardNum = num / 4;
                                tvSignNum.setText((totalCardNum - curCardNum) + " / " + totalCardNum);
                                tv1.setTextColor(getResources().getColor(R.color.blue_dark));
                                tv2.setTextColor(getResources().getColor(R.color.grey_dark));
                                tv3.setTextColor(getResources().getColor(R.color.grey_dark));
                                tv4.setTextColor(getResources().getColor(R.color.grey_dark));
                                tv1.setText(String.valueOf(curCardNum));
                                tv2.setText(String.valueOf(curLate));
                                tv3.setText(String.valueOf(curEarly));
                                tv4.setText(String.valueOf(curLegWork));

                                adapter.notifyDataSetChanged();

                                ntsBottom.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int i, float v, int i1) {

                                    }

                                    @Override
                                    public void onPageSelected(int i) {
                                        tv1.setTextColor(getResources().getColor(R.color.grey_dark));
                                        tv2.setTextColor(getResources().getColor(R.color.grey_dark));
                                        tv3.setTextColor(getResources().getColor(R.color.grey_dark));
                                        tv4.setTextColor(getResources().getColor(R.color.grey_dark));
                                        ntsBottom.setViewPager(viewPager, i);
                                        if (i == 0) {
                                            tv1.setTextColor(getResources().getColor(R.color.blue_dark));
                                        } else if (i == 1) {
                                            tv2.setTextColor(getResources().getColor(R.color.blue_dark));
                                        } else if (i == 2) {
                                            tv3.setTextColor(getResources().getColor(R.color.blue_dark));
                                        } else if (i == 3) {
                                            tv4.setTextColor(getResources().getColor(R.color.blue_dark));
                                        }
                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int i) {

                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void settingParam(CheckInBean bean, List<CheckInTotalBean.Data> listDataBean, int i) {
        CheckInTotalBean.Data detailCardNumBean = new CheckInTotalBean.Data();
        detailCardNumBean.setNo(bean.getData().get(i).getEmpNo());
        detailCardNumBean.setUserName(bean.getData().get(i).getUserName());
        detailCardNumBean.setTeam(bean.getData().get(i).getDptName());
        listDataBean.add(detailCardNumBean);
    }

    @OnClick({R.id.ic_left, R.id.ic_right, R.id.tv_color_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_left:
                oneDay = -1;
                setOneDay(oneDay);
                break;
            case R.id.ic_right:
                oneDay = 1;
                setOneDay(oneDay);
                break;
            case R.id.tv_color_in:
                ((CheckInManager) getParentFragment()).start(AllDepartColorFragment.newInstance("所有部门考勤",departId));
                break;
        }
    }

    private void setOneDay(int oneDay) {

        Date date = DateUtil.yearAddNum(DateUtil.setDate(tvDate.getText().toString()), oneDay);
        strDay = sdf.format(date);
        tvDate.setText(strDay);
        isClickDate();
        iniData();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.BACK_CHECK_IN_MANAGE:
                departId = (String) event.getData();
                iniData();
                break;
        }
    }

    public void isClickDate() {
        if (DateUtil.setDate(mCurDay).getTime() <= DateUtil.setDate(tvDate.getText().toString()).getTime()) {
            icRight.setClickable(false);
            icRight.setColorFilter(getResources().getColor(R.color.grey_home));
        } else if (DateUtil.setDate(mCurDay).getTime() > DateUtil.setDate(tvDate.getText().toString()).getTime()) {
            icRight.setClickable(true);
            icRight.setColorFilter(getResources().getColor(R.color.grey_dark));
        }
    }
}




