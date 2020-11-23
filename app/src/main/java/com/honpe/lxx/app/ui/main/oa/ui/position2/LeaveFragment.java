package com.honpe.lxx.app.ui.main.oa.ui.position2;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position2.adapter.LeaveAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position2.add.AddLeaveFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position2.bean.LeaveNewBean;
import com.honpe.lxx.app.ui.main.oa.ui.position7.bean.ApproveHRBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.JsonFormData;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.dialog.CustomTypeDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.api.FinalClass.categoryId;
import static com.honpe.lxx.app.utils.DateUtil.dateToWeek;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 16:50
 * @Author: 李熙祥
 * @Description: java类作用描述 请假
 */
public class LeaveFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(R.id.sub1)
    TextView sub1;
    @BindView(R.id.sub2)
    TextView sub2;
    @BindView(R.id.sub3)
    TextView sub3;
    @BindView(R.id.sub4)
    TextView sub4;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    //tab
    private String session, EmployeeId, titles, firstDay, lastDay, curMonth, curDay, curMon, today, startTime, endTime;
    private int index; //HR 不同的类型 0代表 请假 1代表 出差 2 代表 外出

    List<String> mDateList = new ArrayList<>(); //记录一个月日子
    private SimpleDateFormat sfYearMonth = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat Day = new SimpleDateFormat("dd");
    SimpleDateFormat Mon = new SimpleDateFormat("MM");
    SimpleDateFormat yearMonthdayHourMin = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    SimpleDateFormat HourMin = new SimpleDateFormat("HH:mm");
    SimpleDateFormat Hour = new SimpleDateFormat("HH");
    private LeaveAdapter adapter;
    public static final String KEY_RESULT_TYPE = "type";

    private LinearLayoutManager linearLayoutManager;

    private boolean move = false;
    private int mIndex;
    private String types, mTypes;
    public static int CALENDAR = 1;
    private String[] HRTypes;

    public static LeaveFragment newInstance(String title, int index) {
        LeaveFragment fragment = new LeaveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_leave;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            titles = bundle.getString("title");
            index = bundle.getInt("index", -1);
            if (index == 0) {
                HRTypes = getResources().getStringArray(R.array.HR_leave_category);
            } else if (index == 1) {
                HRTypes = getResources().getStringArray(R.array.HR_dom_category);
            } else if (index == 2) {
                HRTypes = getResources().getStringArray(R.array.HR_goOut_category);
            }
        }
        llDate.setVisibility(View.VISIBLE);
        initToolbarNav(llBack);
        tvTitle.setText(titles);
        EmployeeId = (String) MyApplication.get(_mActivity, FinalClass.EmployeeId, "");
        curMonth = sfYearMonth.format(new Date());
        tvDate.setText(curMonth);
        session = (String) MyApplication.get(_mActivity, Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        firstDay = DateUtil.getFirstDayOfMonth(YearMonthDay);
        lastDay = DateUtil.getLastDayOfMonth(YearMonthDay);
        today = YearMonthDay.format(new Date());
        mDateList = DateUtil.queryData(YearMonthDay, firstDay, lastDay);
        swipeRefreshLayout.setOnRefreshListener(this);

        for (int i = 0; i < mDateList.size(); i++) {
            if (today.equals(mDateList.get(i))) {
                mIndex = i;
                break;
            }
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initRecyclerView() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("EmployeeId", EmployeeId);
        jsonObject.addProperty("queryJson", JsonFormData.showFormData(titles, firstDay, lastDay));
        jsonObject.addProperty("categoryId", categoryId);

        LatteLogger.d("initRecyclerView", GsonBuildUtil.GsonBuilder(jsonObject));

        disposable = EasyHttp.post(Constants.AppTaskList)
                .retryCount(0)
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
                        final ApproveHRBean bean = Convert.fromJson(s, ApproveHRBean.class);
                        LatteLogger.d("ApproveHRBean", GsonBuildUtil.GsonBuilder(bean));

//                        try {
//                            LatteLogger.d("ApproveHRBean", GsonBuildUtil.GsonBuilder(new JSONObject(s)));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                        mList.clear();

                        if (bean.getCode() == 200) {
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new LeaveAdapter(getDate(bean.getData()));
                            recyclerView.setAdapter(adapter);
                            if (curMMonth == month) {
                                recyclerView.scrollToPosition(mIndex);
                            }

                            adapter.addChildClickViewIds(R.id.tv_morning, R.id.tv_after, R.id.tv_dinner, R.id.tv_status);
                            llBackground.setBackgroundResource(R.color.grey_line_table);
                            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    types = "0";
                                    switch (view.getId()) {
                                        case R.id.tv_morning:
                                            if (mList.get(position).getMorning().equals("\ue611")) {
                                                startForResult(AddLeaveFragment.newInstance(titles, mList.get(position), index, types), 222);
                                            } else if (!TextUtils.isEmpty(mList.get(position).getMorning())) {
                                                showPopupWindow(adapter, position, true);
                                            }
                                            break;

                                        case R.id.tv_after:
                                            if (mList.get(position).getAfter().equals("\ue611")) {
                                                startForResult(AddLeaveFragment.newInstance(titles, mList.get(position), index, types), 222);
                                            } else if (!TextUtils.isEmpty(mList.get(position).getAfter())) {
                                                showPopupWindow(adapter, position, true);
                                            }
                                            break;
                                        case R.id.tv_dinner:
                                            if (mList.get(position).getDinner().equals("\ue611")) {
                                                startForResult(AddLeaveFragment.newInstance(titles, mList.get(position), index, types), 222);
                                            } else if (!TextUtils.isEmpty(mList.get(position).getDinner())) {
                                                showPopupWindow(adapter, position, true);
                                            }
                                            break;
                                    }
                                }
                            });
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.getInstance().showToast(bean.getInfo());
                        }
                    }
                });
    }

    /**
     * 显示PopupWindow
     */
    private void showPopupWindow(final BaseQuickAdapter adapter, final int position, boolean b) {
        String obj = GsonBuildUtil.GsonBuilder(mList.get(position));
        LatteLogger.d("dddddddddddd", "obj===" + obj);
        CustomTypeDialog dialog = new CustomTypeDialog(_mActivity, mList.get(position), titles);
        dialog.show();
        dialog.setCancelable(b);
        dialog.setOnDeleteListener(new CustomTypeDialog.onDeleteListener() {
            @Override
            public void onClickDelete() {
                DeleteItemData(position);
                initRecyclerView();
                adapter.notifyDataSetChanged();
            }
        });
        dialog.setOnMobListener(new CustomTypeDialog.onMobClassListener() {
            @Override
            public void onClickMobClass() {
                types = "1";
                startForResult(AddLeaveFragment.newInstance(titles, mList.get(position), index, types), 2221);
            }
        });
    }

    private void DeleteItemData(int position) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("F_Id", mList.get(position).get_id());
        jsonObject.addProperty("EmployeeId", EmployeeId);
        jsonObject.addProperty("strType", HRtypes[index]);

        LatteLogger.d("DeleteItemData", GsonBuildUtil.GsonBuilder(jsonObject));

        EasyHttp.post(Constants.AppTaskDelete)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String o) {
                        LatteLogger.d("DeleteItemData", o);
                        try {
                            JSONObject jo = new JSONObject(o);
                            ToastUtil.getInstance().showToast(jo.getString("info"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private String JsonStartTime, JsonEndTime, startHour, endHour;

    private int sTime, eTime, sDay, eDay, sMonth, eMonth, month, curMMonth;
    private List<LeaveNewBean> mList = new ArrayList<>(); //整个一个月的数据
    private Date eYearMonth, titleYearMonth;

    private List<LeaveNewBean> getDate(List<ApproveHRBean.DataBean> mShowList) {
        try {
            for (int i = 0; i < mDateList.size(); i++) {
                LeaveNewBean item = new LeaveNewBean();
                Date start = null;
                start = DateUtil.setDate(mDateList.get(i));
                curDay = Day.format(start);
                curMon = Mon.format(start);

                item.setDate(curDay + "/" + dateToWeek(mDateList.get(i)));

                Date ListDate = DateUtil.setDate(YearMonthDay, mDateList.get(i));
                Date todayDate = DateUtil.setDate(YearMonthDay, today);
                if (ListDate.getTime() >= todayDate.getTime()) {
                    if (item.getMorning() == null) {
                        item.setMorning("\ue611");
                    }
                    if (item.getAfter() == null) {
                        item.setAfter("\ue611");
                    }
                    if (item.getDinner() == null) {
                        item.setDinner("\ue611");
                    }
                } else {
                    item.setMorning("");
                    item.setAfter("");
                    item.setDinner("");
                }

                for (int j = 0; j < mShowList.size(); j++) {
                    if (mShowList.get(j).getHR_AskforLeave() != null) {
                        leaveData(mShowList, item, j);
                    } else if (mShowList.get(j).getHR_BusinessTravel() != null) {
                        businessData(mShowList, item, j);
                    } else if (mShowList.get(j).getHR_GoOut() != null) {
                        goOutData(mShowList, item, j);
                    }

                }
                mList.add(item);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mList;
    }

    private void goOutData(List<ApproveHRBean.DataBean> mShowList, LeaveNewBean item, int j) throws ParseException {
        for (int jj = 0; jj < mShowList.get(j).getHR_GoOut().size(); jj++) {
            ApproveHRBean.DataBean.HRGoOutBean bean = mShowList.get(j).getHR_GoOut().get(jj);
            JsonStartTime = bean.getStarttime();
            JsonEndTime = bean.getEndtime();
            //开始和结束日期
            sDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            sMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            int curMDay = Integer.parseInt(curDay);
            curMMonth = Integer.parseInt(curMon);

            eYearMonth = sfYearMonth.parse(sfYearMonth.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
            titleYearMonth = sfYearMonth.parse(tvDate.getText().toString());
            Date date = new Date();
            month = Integer.parseInt(Mon.format(date));

            startHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime));
            endHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime));
            sTime = Integer.parseInt(startHour);
            eTime = Integer.parseInt(endHour);


            if (titleYearMonth.getTime() < eYearMonth.getTime() || titleYearMonth.getTime() > eYearMonth.getTime()) { //跨月的比较
                if (curMDay >= sDay && curMMonth == sMonth) {   //当月跨天
                    goOutDay(item, bean, curMDay);
                }
            } else {  //同月的比较
                if (sMonth != eMonth) {
                    if (curMDay == eDay) {
                        currentGoOutDay(bean, item);//一天以内的方法
                    } else {
                        for (int e = curMDay; e <= eDay; e++) {
                            goOutDay(item, bean, curMDay);
                        }
                    }
                } else {
                    if (curMDay >= sDay && curMDay <= eDay && sDay < eDay) {
                        LatteLogger.d("testData", "  curDay  " + curDay + " sDay  " + sDay + "  eDay " + eDay
                                + "  sTime " + sTime + "  eTime " + eTime + "  curMDay   " + curMDay);
                        for (int e = sDay; e <= eDay; e++) {
                            goOutDay(item, bean, curMDay);
                        }
                    } else if (curMDay == eDay && sDay == eDay) {
                        currentGoOutDay(bean, item);//一天以内的方法
                    }
                }
            }
        }
    }

    private void currentGoOutDay(ApproveHRBean.DataBean.HRGoOutBean bean, LeaveNewBean item) {
        if (sTime >= 8 && sTime < 13) { //上午跨度到下午
            if (eTime >= 8 && eTime < 13) { //早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //早下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 13 && sTime < 18) { //下午跨度到晚上
            if (eTime >= 13 && eTime < 18) { //下午
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //下午晚班
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 18 && sTime < 24 || sTime >= 0 && sTime < 8) { //晚上跨度到早上
            if (eTime >= 18 && eTime <= 24 || eTime >= 0 && eTime < 8) { //晚班
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //晚早
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {//一天 从早上8点到晚上18点以后
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        goOutType(bean, item);
    }

    private void goOutDay(LeaveNewBean item, ApproveHRBean.DataBean.HRGoOutBean bean, int curMDay) {
        if (curMDay == sDay) {
            if (sTime >= 8 && sTime < 13) { //开始时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (sTime >= 13 && sTime < 18) {  //开始时间 中下
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //开始时间 晚上
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (curMDay == eDay) {
            LatteLogger.d("testCurMDay", curMDay + "    " + eDay);
            if (eTime >= 8 && eTime < 13) {  //结束时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (eTime >= 13 && eTime < 18) {  //结束时间 中下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //结束时间 晚上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        goOutType(bean, item);
    }

    private void goOutType(ApproveHRBean.DataBean.HRGoOutBean bean, LeaveNewBean item) {
        item.setStatus(bean.getF_state());
        item.setStartTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
        item.setEndTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
        item.set_id(bean.getF_id());
        item.setReason(bean.getReason());
        item.setLongTime(String.valueOf(bean.getCountday()));
        item.setUserName(bean.getF_realname());
        item.setType(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        item.setCurrentStatus(bean.getF_curstate());
        item.setGguid(bean.getCguid());
    }

    private void businessData(List<ApproveHRBean.DataBean> mShowList, LeaveNewBean item, int j) throws ParseException {
        for (int jj = 0; jj < mShowList.get(j).getHR_BusinessTravel().size(); jj++) {
            ApproveHRBean.DataBean.HRBusinessTravelBean bean = mShowList.get(j).getHR_BusinessTravel().get(jj);
            JsonStartTime = bean.getStarttime();
            JsonEndTime = bean.getEndtime();
            //开始和结束日期
            sDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            sMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            int curMDay = Integer.parseInt(curDay);
            curMMonth = Integer.parseInt(curMon);

            eYearMonth = sfYearMonth.parse(sfYearMonth.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
            titleYearMonth = sfYearMonth.parse(tvDate.getText().toString());
            Date date = new Date();
            month = Integer.parseInt(Mon.format(date));

            startHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime));
            endHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime));
            sTime = Integer.parseInt(startHour);
            eTime = Integer.parseInt(endHour);

            if (titleYearMonth.getTime() < eYearMonth.getTime() || titleYearMonth.getTime() > eYearMonth.getTime()) { //跨月的比较
                if (curMDay >= sDay && curMMonth == sMonth) {   //当月跨天
                    businessDay(item, bean, curMDay);
                }
            } else {  //同月的比较
                if (sMonth != eMonth) {
                    if (curMDay == eDay) {
                        currentBusinessDay(bean, item);//一天以内的方法
                    } else {
                        for (int e = curMDay; e <= eDay; e++) {
                            businessDay(item, bean, curMDay);
                        }
                    }
                } else {
                    if (curMDay >= sDay && curMDay <= eDay && sDay < eDay) {
                        LatteLogger.d("testData", "  curDay  " + curDay + " sDay  " + sDay + "  eDay " + eDay
                                + "  sTime " + sTime + "  eTime " + eTime + "  curMDay   " + curMDay);
                        for (int e = sDay; e <= eDay; e++) {
                            businessDay(item, bean, curMDay);
                        }
                    } else if (curMDay == eDay && sDay == eDay) {
                        currentBusinessDay(bean, item);//一天以内的方法
                    }
                }
            }
        }
    }

    private void currentBusinessDay(ApproveHRBean.DataBean.HRBusinessTravelBean bean, LeaveNewBean item) {
        if (sTime >= 8 && sTime < 13) { //上午跨度到下午
            if (eTime >= 8 && eTime < 13) { //早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //早下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 13 && sTime < 18) { //下午跨度到晚上
            if (eTime >= 13 && eTime < 18) { //下午
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //下午晚班
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 18 && sTime < 24 || sTime >= 0 && sTime < 8) { //晚上跨度到早上
            if (eTime >= 18 && eTime <= 24 || eTime >= 0 && eTime < 8) { //晚班
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //晚早
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {//一天 从早上8点到晚上18点以后
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        businessType(bean, item);
    }

    private void businessDay(LeaveNewBean item, ApproveHRBean.DataBean.HRBusinessTravelBean bean, int curMDay) {
        if (curMDay == sDay) {
            if (sTime >= 8 && sTime < 13) { //开始时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (sTime >= 13 && sTime < 18) {  //开始时间 中下
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //开始时间 晚上
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (curMDay == eDay) {
            LatteLogger.d("testCurMDay", curMDay + "    " + eDay);
            if (eTime >= 8 && eTime < 13) {  //结束时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (eTime >= 13 && eTime < 18) {  //结束时间 中下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //结束时间 晚上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        businessType(bean, item);
    }

    private void businessType(ApproveHRBean.DataBean.HRBusinessTravelBean bean, LeaveNewBean item) {
        item.setStatus(bean.getF_state());
        item.setStartTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
        item.setEndTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
        item.set_id(bean.getF_id());
        item.setReason(bean.getReason());
        item.setLongTime(bean.getCountday() + "");
        item.setUserName(bean.getF_realname());
        item.setType(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        item.setDestination(bean.getAddress());
        item.setCurrentStatus(bean.getF_curstate());
        item.setGguid(bean.getGguid());
    }


    private void leaveData(List<ApproveHRBean.DataBean> mShowList, LeaveNewBean item, int j) throws ParseException {
        for (int jj = 0; jj < mShowList.get(j).getHR_AskforLeave().size(); jj++) {
            ApproveHRBean.DataBean.HRAskforLeaveBean bean = mShowList.get(j).getHR_AskforLeave().get(jj);
            JsonStartTime = bean.getStarttime();
            JsonEndTime = bean.getEndtime();
            //开始和结束日期
            sDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eDay = Integer.parseInt(Day.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            sMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
            eMonth = Integer.parseInt(Mon.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));

            int curMDay = Integer.parseInt(curDay);
            curMMonth = Integer.parseInt(curMon);

            eYearMonth = sfYearMonth.parse(sfYearMonth.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
            titleYearMonth = sfYearMonth.parse(tvDate.getText().toString());
            Date date = new Date();
            month = Integer.parseInt(Mon.format(date));

            startHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime));
            endHour = Hour.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime));
            sTime = Integer.parseInt(startHour);
            eTime = Integer.parseInt(endHour);


            if (titleYearMonth.getTime() < eYearMonth.getTime() || titleYearMonth.getTime() > eYearMonth.getTime()) { //跨月的比较
                if (curMDay >= sDay && curMMonth == sMonth) {   //当月跨天
                    LeaveDay(item, bean, curMDay);
                }
            } else {  //同月的比较
                if (sMonth != eMonth) {
                    if (curMDay == eDay) {
                        currentLeaveDay(bean, item);//一天以内的方法
                    } else {
                        for (int e = curMDay; e <= eDay; e++) {
                            LeaveDay(item, bean, curMDay);
                        }
                    }
                } else {
                    if (curMDay >= sDay && curMDay <= eDay && sDay < eDay) {
                        LatteLogger.d("testData", "  curDay  " + curDay + " sDay  " + sDay + "  eDay " + eDay
                                + "  sTime " + sTime + "  eTime " + eTime + "  curMDay   " + curMDay);
                        for (int e = sDay; e <= eDay; e++) {
                            LeaveDay(item, bean, curMDay);
                        }
                    } else if (curMDay == eDay && sDay == eDay) {
                        currentLeaveDay(bean, item);//一天以内的方法
                    }
                }
            }
        }
    }

    /**
     * 同月不同日期的判断
     *
     * @param item
     * @param bean
     * @param curMDay
     */
    private void LeaveDay(LeaveNewBean item, ApproveHRBean.DataBean.HRAskforLeaveBean bean, int curMDay) {
        if (curMDay == sDay) {
            if (sTime >= 8 && sTime < 13) { //开始时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (sTime >= 13 && sTime < 18) {  //开始时间 中下
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //开始时间 晚上
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (curMDay == eDay) {
            LatteLogger.d("testCurMDay", curMDay + "    " + eDay);
            if (eTime >= 8 && eTime < 13) {  //结束时间 早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else if (eTime >= 13 && eTime < 18) {  //结束时间 中下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else {  //结束时间 晚上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        LeaveType(bean, item);
    }

    private void currentLeaveDay(ApproveHRBean.DataBean.HRAskforLeaveBean bean, LeaveNewBean item) {
        if (sTime >= 8 && sTime < 13) { //上午跨度到下午
            if (eTime >= 8 && eTime < 13) { //早上
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //早下
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 13 && sTime < 18) { //下午跨度到晚上
            if (eTime >= 13 && eTime < 18) { //下午
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //下午晚班
                item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else if (sTime >= 18 && sTime < 24 || sTime >= 0 && sTime < 8) { //晚上跨度到早上
            if (eTime >= 18 && eTime <= 24 || eTime >= 0 && eTime < 8) { //晚班
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            } else { //晚早
                item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
                item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            }
        } else {//一天 从早上8点到晚上18点以后
            item.setMorning(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setAfter(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
            item.setDinner(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        }
        LeaveType(bean, item);
    }

    private void LeaveType(ApproveHRBean.DataBean.HRAskforLeaveBean bean, LeaveNewBean item) {
        item.setStatus(bean.getF_state());
        item.setStartTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonStartTime)));
        item.setEndTime(yearMonthdayHourMin.format(DateUtil.setDate(yearMonthdayHourMin, JsonEndTime)));
        item.set_id(bean.getF_id());
        item.setReason(bean.getReason());
        item.setLongTime(String.valueOf(bean.getCounthours()));
        item.setUserName(bean.getF_realname());
        item.setType(HRTypes[Integer.parseInt(bean.getCatorgry()) - 1]);
        item.setCurrentStatus(bean.getF_curstate());
        item.setGguid(bean.getLguid());
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
        mDateList = DateUtil.queryData(YearMonthDay, firstDay, lastDay);
        LatteLogger.d("monday", firstDay + " 某月第一天 " + lastDay + "某月最后一天");
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        initRecyclerView();

    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager, R.id.ll_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_click:
                //  startForResult(CalendarFragment.newInstance(),CALENDAR);
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
        if (requestCode == 222 && resultCode == RESULT_OK && data != null) {
            String msg = data.getString("Msg");
            startTime = data.getString("startTime");
            endTime = data.getString("endTime");
            LatteLogger.d("myTime", startTime + "  " + endTime);
            initRecyclerView();
            adapter.notifyDataSetChanged();
        } else if (requestCode == 2221 && resultCode == RESULT_OK && data != null) {
            initRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

}