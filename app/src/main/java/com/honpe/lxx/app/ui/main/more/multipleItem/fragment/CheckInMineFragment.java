package com.honpe.lxx.app.ui.main.more.multipleItem.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean2;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LevelListAdapter;
import com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean.CheckInTotalBean;
import com.honpe.lxx.app.ui.main.oa.ui.position1.TotalQueryFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
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

import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.F_Name;
import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.api.FinalClass.UserName;

/**
 * 考勤管理 我的
 */
public class CheckInMineFragment extends BaseBackFragment {
    @BindView(R.id.id_card_no)
    TextView idCardNo;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_user_depart)
    TextView tvUserDepart;
    @BindView(R.id.tv_str_type)
    TextView tvStrType;
    @BindView(R.id.ll_right_tips)
    LinearLayout llRightTips;
    @BindView(R.id.ll_select_date)
    LinearLayout llSelectDate;
    @BindView(R.id.ic_left)
    ImageView icLeft;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ic_right)
    ImageView icRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_item_title2)
    TextView tvItemTitle;
    @BindView(R.id.tv_more)
    TextView tvDay;
    @BindView(R.id.iv_director2)
    ImageView ivDirector;
    @BindView(R.id.tv_time_head)
    TextView tvTimeHead;
    @BindView(R.id.time1)
    TextView time1;
    @BindView(R.id.time2)
    TextView time2;
    @BindView(R.id.time3)
    TextView time3;
    @BindView(R.id.time4)
    TextView time4;
    @BindView(R.id.time5)
    TextView time5;
    @BindView(R.id.time6)
    TextView time6;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.tv_time_head2)
    TextView tvTimeHead2;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_un_month)
    LinearLayout llUnMonth;
    @BindView(R.id.sc)
    ScrollView mSc;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String firstDay, lastDay, today, curMonth;
    private SimpleDateFormat yearMonth = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    public int oneDay;
    private String[] strItem = {"出勤天数", "休息天数", "迟到", "早退", "漏卡", "缺勤", "请假", "外勤", "加班"};
    private String[] mTypes = {"请假", "出差", "外出", "加班"};
    private LevelListAdapter adapter;
    private String curDay, weekday, myCode, key;
    CheckInTotalBean.Data JobData;
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    List<BaseNode> res = new ArrayList<>();

    public static CheckInMineFragment newInstance(String key, CheckInTotalBean.Data jobNo) {
        CheckInMineFragment fragment = new CheckInMineFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("jobNo", jobNo);
        bundle.putString("key", key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_check_mine;
    }

    @Override
    protected void initView() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        Bundle bundle = getArguments();
        if (bundle != null) {
            JobData = bundle.getParcelable("jobNo");
            key = bundle.getString("key");
            LatteLogger.d("testJobData", key + "    " + GsonBuildUtil.GsonBuilder(JobData));
        }

        if (JobData == null && TextUtils.isEmpty(key)) {

            myCode = (String) MyApplication.get(_mActivity, ENCODE, "");
            String userName = (String) MyApplication.get(_mActivity, UserName, "");
            String strDepart = (String) MyApplication.get(_mActivity, DEPART_NAME, "");
            String post = (String) MyApplication.get(_mActivity, F_Name, "");
            llTitleBar.setVisibility(View.GONE);
            tvId.setVisibility(View.GONE);
            idCardNo.setText(myCode);
            tvUserName.setText(userName);

            JobData = new CheckInTotalBean.Data();
            JobData.setUserName(tvUserName.getText().toString());
            JobData.setNo(idCardNo.getText().toString());
            tvUserDepart.setText(strDepart + ":" + post);
        } else {
            initToolbarNav(llBack);
            ImmersionBar.with(_mActivity).titleBar(R.id.toolbar).init();
            tvId.setVisibility(View.GONE);
            llTitleBar.setVisibility(View.VISIBLE);
            myCode = JobData.getNo();
            tvUserName.setText(JobData.getUserName());
            tvUserDepart.setText(JobData.getTeam());
            tvTitle.setText(tvUserName.getText() + "考勤");
            idCardNo.setText(JobData.getNo());
        }

        llSelectDate.setVisibility(View.VISIBLE);
        llRightTips.setVisibility(View.GONE);
        icLeft.setColorFilter(getResources().getColor(R.color.grey_dark));
        firstDay = DateUtil.getFirstDayOfMonth(yearMonthDay);
        lastDay = DateUtil.getLastDayOfMonth(yearMonthDay);
        today = yearMonthDay.format(new Date());
        curMonth = yearMonth.format(new Date());
        tvDate.setText(curMonth);
        isClickDate();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getTestNote();
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }


    private void getTestNote() {
        disposable = EasyHttp.post(Constants.PERSON_CHECK + session)
                .params("EmpNo", myCode)
                .params("StarDay", firstDay)
                .params("EndDay", lastDay)
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
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject obj = null;
                        LatteLogger.d("onSuccess",s);
                        try {
                            obj = new JSONObject(s);
                            if (obj.getInt("Status") == -1) {
                                mSc.setVisibility(View.GONE);
                            } else {
                                mSc.setVisibility(View.VISIBLE);
                                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new Convert.NullStringToEmptyAdapterFactory()).create();
                                final CheckInBean checkInBean = gson.fromJson(s, CheckInBean.class);
                                recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                adapter = new LevelListAdapter();
                                recyclerView.setAdapter(adapter);
                                adapter.setList(getData(checkInBean));
                                //防止滑动不流畅
                                recyclerView.setNestedScrollingEnabled(false);

                                for (int i = 0; i < strItem.length; i++) {
                                    if (key.equals(strItem[i])) {
                                        LatteLogger.d("testKey", key + "   " + strItem[i]);
                                        adapter.expand(i);
                                    } else {
                                        adapter.collapse(i);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                tvItemTitle.setText("今天打卡时间");
                                tvDay.setText("更多");
                                tvDay.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
                                ivDirector.setImageResource(R.mipmap.ic_right_grey);
                                ivDirector.setColorFilter(_mActivity.getResources().getColor(R.color.grey_home));
                                String[][] object = {new String[]{"星期", ""}};
                                String[][] object2 = {new String[]{"T", " "}};
                                for (int i = 0; i < checkInBean.getData().size(); i++) {

                                }
                                String ymdDate = StringUtil.replace(checkInBean.getData().get(checkInBean.getData().size() - 1).getRecordDate(), object2);

                                Date start = DateUtil.setDate(ymdDate);
                                curDay = myDay.format(start);
                                weekday = curDay + "/" + StringUtil.replace(checkInBean.getData().get(checkInBean.getData().size() - 1).getWeekName(), object);
                                tvTimeHead.setText(weekday);
                                time1.setBackgroundResource(R.color.white);
                                time2.setBackgroundResource(R.color.white);
                                time3.setBackgroundResource(R.color.white);
                                time4.setBackgroundResource(R.color.white);
                                time5.setBackgroundResource(R.color.white);
                                time6.setBackgroundResource(R.color.white);

                                time1.setTextColor(getResources().getColor(R.color.black));
                                time2.setTextColor(getResources().getColor(R.color.black));
                                time3.setTextColor(getResources().getColor(R.color.black));
                                time4.setTextColor(getResources().getColor(R.color.black));
                                time5.setTextColor(getResources().getColor(R.color.black));
                                time6.setTextColor(getResources().getColor(R.color.black));

                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS1() > 0) {
                                    time1.setBackgroundResource(R.color.google_red);
                                    time1.setTextColor(getResources().getColor(R.color.white));
                                    time1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("上班迟到" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS1() + "分钟");
                                        }
                                    });
                                } else {

                                }
                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS2() > 0) {
                                    time2.setBackgroundResource(R.color.google_red);
                                    time2.setTextColor(getResources().getColor(R.color.white));
                                    time2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("下班早退" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS2() + "分钟");
                                        }
                                    });
                                } else {

                                }
                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS3() > 0) {
                                    time3.setBackgroundResource(R.color.google_red);
                                    time3.setTextColor(getResources().getColor(R.color.white));
                                    time3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("上班迟到" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS3() + "分钟");
                                        }
                                    });
                                } else {

                                }
                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS4() > 0) {
                                    time4.setBackgroundResource(R.color.google_red);
                                    time4.setTextColor(getResources().getColor(R.color.white));
                                    time4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("下班早退" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS4() + "分钟");
                                        }
                                    });
                                } else {

                                }
                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS5() > 0) {
                                    time5.setBackgroundResource(R.color.google_red);
                                    time5.setTextColor(getResources().getColor(R.color.white));
                                    time5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("上班迟到" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS5() + "分钟");
                                        }
                                    });
                                } else {

                                }
                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getS6() > 0) {
                                    time6.setBackgroundResource(R.color.google_red);
                                    time6.setTextColor(getResources().getColor(R.color.white));
                                    time6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtil.getInstance().showToast("下班早退" + checkInBean.getData().get(checkInBean.getData().size() - 1).getS6() + "分钟");
                                        }
                                    });
                                } else {

                                }

                                if (("漏卡").equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer1())) {
                                    time1.setBackgroundResource(R.color.google_yellow);
                                    time1.setTextColor(getResources().getColor(R.color.white));
                                }
                                if (("漏卡").equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer2())) {
                                    time2.setBackgroundResource(R.color.google_yellow);
                                    time2.setTextColor(getResources().getColor(R.color.white));
                                }
                                if ("漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer3())) {
                                    time3.setBackgroundResource(R.color.google_yellow);
                                    time3.setTextColor(getResources().getColor(R.color.white));
                                }
                                if ("漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer4())) {
                                    time4.setBackgroundResource(R.color.google_yellow);
                                    time4.setTextColor(getResources().getColor(R.color.white));
                                }
                                if ("漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer5())) {
                                    time5.setBackgroundResource(R.color.google_yellow);
                                    time5.setTextColor(getResources().getColor(R.color.white));
                                }
                                if ("漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer6())) {
                                    time6.setBackgroundResource(R.color.google_yellow);
                                    time6.setTextColor(getResources().getColor(R.color.white));
                                }

                                time1.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer1());
                                time2.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer2());
                                time3.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer3());
                                time4.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer4());
                                time5.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer5());
                                time6.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer6());

                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains(StringUtil.useList2(mTypes, "请假"))) {
                                    changeStatus(R.color.green, "请假", checkInBean.getData().get(checkInBean.getData().size() - 1));
                                } else if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains(StringUtil.useList2(mTypes, "出差"))) {
                                    changeStatus(R.color.orange, "出差", checkInBean.getData().get(checkInBean.getData().size() - 1));
                                } else if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains(StringUtil.useList2(mTypes, "外出"))) {
                                    changeStatus(R.color.violet_7B1FA2, "外出", checkInBean.getData().get(checkInBean.getData().size() - 1));
                                } else if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains(StringUtil.useList2(mTypes, "加班"))) {
                                    changeStatus(R.color.google_coffee, "加班", checkInBean.getData().get(checkInBean.getData().size() - 1));
                                }

                                if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains("记录") && (TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer1()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer2()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer3()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer4()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer5()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer6()))) {
                                    llTable.setVisibility(View.GONE);
                                    llContent.setVisibility(View.VISIBLE);
                                    tvTimeHead2.setText(weekday);
                                    tvContent.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec());
                                    tvContent.setTextColor(getResources().getColor(R.color.google_yellow));
                                    tvTimeHead2.setTextColor(getResources().getColor(R.color.google_yellow));
                                } else if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains("休息") && (TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer1()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer2()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer3()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer4()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer5()) &&
                                        TextUtils.isEmpty(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer6()))) {
                                    llTable.setVisibility(View.GONE);
                                    llContent.setVisibility(View.VISIBLE);
                                    tvTimeHead2.setText(weekday);
                                    tvContent.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec());
                                    tvContent.setTextColor(getResources().getColor(R.color.google_green));
                                    tvTimeHead2.setTextColor(getResources().getColor(R.color.google_green));
                                } else if (checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec().contains("法定") && ("漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer1()) ||
                                        "漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer2()) ||
                                        "漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer3()) ||
                                        "漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer4()) ||
                                        "漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer5()) ||
                                        "漏卡".equals(checkInBean.getData().get(checkInBean.getData().size() - 1).getTimer6()))) {
                                    llTable.setVisibility(View.GONE);
                                    llContent.setVisibility(View.VISIBLE);
                                    tvTimeHead2.setText(weekday);
                                    tvContent.setText(checkInBean.getData().get(checkInBean.getData().size() - 1).getAbnormalRec());
                                    tvContent.setTextColor(getResources().getColor(R.color.google_green));
                                    tvTimeHead2.setTextColor(getResources().getColor(R.color.google_green));
                                } else {
                                    llTable.setVisibility(View.VISIBLE);
                                    llContent.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void changeStatus(int bgColor, String strType, CheckInBean.DataBean item) {
        if (("漏卡").equals(item.getTimer1())) {
            time1.setBackgroundResource(R.color.google_yellow);
            time1.setTextColor(getContext().getResources().getColor(R.color.white));
            time1.setText(strType);
            time1.setBackgroundResource(bgColor);
            time1.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        if (("漏卡").equals(item.getTimer2())) {
            time2.setBackgroundResource(R.color.google_yellow);
            time2.setTextColor(getContext().getResources().getColor(R.color.white));
            time2.setText(strType);
            time2.setBackgroundResource(bgColor);
            time2.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer3())) {
            time3.setBackgroundResource(R.color.google_yellow);
            time3.setTextColor(getContext().getResources().getColor(R.color.white));
            time3.setText(strType);
            time3.setBackgroundResource(bgColor);
            time3.setTextColor(getContext().getResources().getColor(R.color.white));

        }
        if ("漏卡".equals(item.getTimer4())) {
            time4.setBackgroundResource(R.color.google_yellow);
            time4.setTextColor(getContext().getResources().getColor(R.color.white));
            time4.setText(strType);
            time4.setBackgroundResource(bgColor);
            time4.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer5())) {
            time5.setBackgroundResource(R.color.google_yellow);
            time5.setTextColor(getContext().getResources().getColor(R.color.white));
            time5.setText(strType);
            time5.setBackgroundResource(bgColor);
            time5.setTextColor(getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer6())) {
            time6.setBackgroundResource(R.color.google_yellow);
            time6.setTextColor(getContext().getResources().getColor(R.color.white));
            time6.setText(strType);
            time6.setBackgroundResource(bgColor);
            time6.setTextColor(getContext().getResources().getColor(R.color.white));
        }
    }

    private List<BaseNode> getData(CheckInBean checkInBean) {

        Map<String, List<CheckInBean.DataBean>> maps = new LinkedHashMap<>();

        for (int e = 0; e < strItem.length; e++) {
            List<CheckInBean.DataBean> listDataBean = maps.get(strItem[e]);
            if (listDataBean == null) {
                listDataBean = new ArrayList<>();
            }

            for (int i = 0; i < checkInBean.getData().size(); i++) {
                CheckInBean.DataBean bean = checkInBean.getData().get(i);
                if (e == 0) {
                    if ((!"".equals(bean.getTimer1()) || !"".equals(bean.getTimer2()) || !"".equals(bean.getTimer3()) ||
                            !"".equals(bean.getTimer4()) || !"".equals(bean.getTimer5()) || !"".equals(bean.getTimer6()))&&"".equals(bean.getAbnormalRec())) {
                        listDataBean.add(bean);
                    }
                } else if (e == 1) {
                    if (bean.getAbnormalRec().equals("排班休息") && "".equals(bean.getTimer1()) && "".equals(bean.getTimer2()) && "".equals(bean.getTimer3()) &&
                            "".equals(bean.getTimer4()) && "".equals(bean.getTimer5()) && "".equals(bean.getTimer6())) {
                        listDataBean.add(bean);
                    }
                } else if (e == 2) {
                    if (bean.getAbnormalRec().contains("迟到")) {
                        listDataBean.add(bean);
                    }
                } else if (e == 3) {
                    if (bean.getAbnormalRec().equals("早退")) {
                        listDataBean.add(bean);
                    }
                } else if (e == 4) {
                    if (bean.getTimer1().equals("漏卡") || bean.getTimer2().equals("漏卡") || bean.getTimer3().equals("漏卡") ||
                            bean.getTimer4().equals("漏卡") || bean.getTimer5().equals("漏卡") || bean.getTimer6().equals("漏卡")) {
                        listDataBean.add(bean);
                    }
                } else if (e == 5) {
                    if (bean.getAbnormalRec().equals("缺勤无打卡记录") &&
                            "".equals(bean.getTimer1()) && "".equals(bean.getTimer2()) && "".equals(bean.getTimer3()) &&
                            "".equals(bean.getTimer4()) && "".equals(bean.getTimer5()) && "".equals(bean.getTimer6())) {
                        listDataBean.add(bean);
                    }
                } else if (e == 6) {
                    if (bean.getAbnormalRec().contains("请假")) {
                        listDataBean.add(bean);
                    }
                } else if (e == 7) {
                    if (bean.getAbnormalRec().equals("外勤")) {
                        listDataBean.add(bean);
                    }
                } else if (e == 8) {
                    if (bean.getAbnormalRec().equals("加班")) {
                        listDataBean.add(bean);
                    }
                }
            }
            maps.put(strItem[e], listDataBean);
        }

        for (String key : maps.keySet()) {
            List<CheckInBean.DataBean> listData  = maps.get(key);
            List<BaseNode> list = new ArrayList<>();
            for (int i = 0; i < listData.size(); i++) {
                LevelItemBean2 dataBean = new LevelItemBean2();
                CheckInBean.DataBean item = listData.get(i);
                dataBean.setEmpNo(listData.get(i).getEmpNo());
                dataBean.setWeekName(listData.get(i).getWeekName());
                dataBean.setRecordDate(listData.get(i).getRecordDate());
                dataBean.setT1(listData.get(i).getT1());
                dataBean.setT2(listData.get(i).getT2());
                dataBean.setT3(listData.get(i).getT3());
                dataBean.setT4(listData.get(i).getT4());
                dataBean.setT5(listData.get(i).getT5());
                dataBean.setT6(listData.get(i).getT6());
                dataBean.setTimer1(listData.get(i).getTimer1());
                dataBean.setTimer2(listData.get(i).getTimer2());
                dataBean.setTimer3(listData.get(i).getTimer3());
                dataBean.setTimer4(listData.get(i).getTimer4());
                dataBean.setTimer5(listData.get(i).getTimer5());
                dataBean.setTimer6(listData.get(i).getTimer6());
                dataBean.setS1(listData.get(i).getS1());
                dataBean.setS2(listData.get(i).getS2());
                dataBean.setS3(listData.get(i).getS3());
                dataBean.setS4(listData.get(i).getS4());
                dataBean.setS5(listData.get(i).getS5());
                dataBean.setS6(listData.get(i).getS6());
                dataBean.setRecordCount(listData.get(i).getRecordCount());
                dataBean.setHistoryRec(listData.get(i).getHistoryRec());
                dataBean.setAbnormalRec(item.getAbnormalRec());
                list.add(dataBean);
            }
            LevelItemBean lv1 = new LevelItemBean(key, listData.size() + "");
            lv1.setChildNode(list);
            res.add(lv1);
        }
        return res;
    }


    @OnClick({R.id.ic_left, R.id.ic_right, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_left:
                oneDay--;
                setOneDay(oneDay);
                break;
            case R.id.ic_right:
                oneDay++;
                setOneDay(oneDay);
                break;
            case R.id.tv_more:
                ((CheckInManager) getParentFragment()).start(TotalQueryFragment.newInstance(JobData));
                break;
        }
    }

    private void setOneDay(int oneDay) {
        firstDay = DateUtil.getFirstDayOfMonth(yearMonthDay, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(yearMonthDay, oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        isClickDate();
        getTestNote();
        adapter.notifyDataSetChanged();
    }

    public void isClickDate() {
        if (tvDate.getText().toString().equals(curMonth)) {
            icRight.setClickable(false);
            icRight.setColorFilter(getResources().getColor(R.color.grey_home));
            llUnMonth.setVisibility(View.VISIBLE);
        } else {
            icRight.setClickable(true);
            icRight.setColorFilter(getResources().getColor(R.color.grey_dark));
            llUnMonth.setVisibility(View.GONE);
        }
    }
}
