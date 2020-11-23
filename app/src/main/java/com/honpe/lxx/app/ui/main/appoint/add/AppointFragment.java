package com.honpe.lxx.app.ui.main.appoint.add;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.appoint.bean.AppointBean;
import com.honpe.lxx.app.ui.main.appoint.bean.DepartBean;
import com.honpe.lxx.app.ui.main.appoint.calerndar.MultiSelectCalendarView;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.dialog.CarNoInputDialog;
import com.honpe.lxx.app.widget.font.FontTextView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.REQ_MODIFY_FRAGMENT;


/**
 * @package: com.example.lxx.myalipay.fragment.a_pakeage.child
 * @date: 2018/5/9 9:58
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 申请访客预约单  老设计布局  R.layout.fragment_appointment
 */
public class AppointFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.et_me)
    EditText etMe;
    @BindView(R.id.icon_font_delete1)
    FontTextView iconFontDelete1;
    @BindView(R.id.ll_right_1)
    LinearLayout llRight1;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.icon_font_delete2)
    FontTextView iconFontDelete2;
    @BindView(R.id.ll_right_2)
    LinearLayout llRight2;
    @BindView(R.id.et_comply)
    EditText etComply;
    @BindView(R.id.icon_font_delete4)
    FontTextView iconFontDelete4;
    @BindView(R.id.ll_right_3)
    LinearLayout llRight3;
    @BindView(R.id.et_event)
    EditText etEvent;
    @BindView(R.id.font_talk)
    FontTextView fontTalk;
    @BindView(R.id.icon_font_delete5)
    FontTextView iconFontDelete5;
    @BindView(R.id.ll_right_4)
    LinearLayout llRight4;
    @BindView(R.id.et_car_num)
    TextView etCarNum;
    @BindView(R.id.icon_font_delete6)
    FontTextView iconFontDelete6;
    @BindView(R.id.ll_right_5)
    LinearLayout llRight5;
    @BindView(R.id.et_depart_name)
    EditText etDepartName;
    @BindView(R.id.icon_font_delete7)
    FontTextView iconFontDelete7;
    @BindView(R.id.ll_right_6)
    LinearLayout llRight6;
    @BindView(R.id.view7)
    FontTextView view7;
    @BindView(R.id.et_person_num)
    TextView etPersonNum;
    @BindView(R.id.tv_depart)
    TextView tvDepart;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_date_year_month_day)
    TextView tvYearMonthDay;
    @BindView(R.id.ll_date_year_month_day_tv)
    LinearLayout llDateYearMonthDayTv;
    @BindView(R.id.ll_date_year_month_day)
    LinearLayout llDateYearMonthDay;
    @BindView(R.id.tv_date_time_minute)
    TextView tvTimeMin;
    @BindView(R.id.ll_date_time_minute)
    LinearLayout llDateTimeMinute;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.svr)
    ScrollView svr;
    @BindView(R.id.root)
    LinearLayout root;

    private String session, sMe, sPhone, sUnit, sEvent, sCarNo, sBeName, sBeNum, sBeDayNum, sDate, sTime, DeptID;
    private int intType;
    private int intDay, intPersonNum = 1;

    private String[] persNums = {"1人", "2人", "3人", "4人", "5人", "5人以上", "清空"};
    private String[] dayNums = {"1天", "2天", "3天", "4天", "5天", "5天以上", "清空"};
    private String[] timeBound = {"08:00-09:00", "09:00-10:00", "10:00-11:00", "11:00-12:00",
            "13:30-14:30", "14:30-15:30", "15:30-16:30", "16:30-17:30", "清空"};
    private String[] startTimes = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:30", "14:30", "15:30", "16:30"};
    private String[] endTimes = {"09:00", "10:00", "11:00", "12:00", "13:30", "14:30", "15:30", "16:30", "17:30"};
    final ArrayList<DialogMenuItem> departList = new ArrayList<>();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
    private SimpleDateFormat sf1 = new SimpleDateFormat("yyyy/MM/dd/");
    private SimpleDateFormat sfTimeMinute = new SimpleDateFormat("HH:mm");

    public static final String DIALOG_DATA = "DialogData";
    private AppointBean.DataBean bean;


    public static AppointFragment newInstance(AppointBean.DataBean bean) {
        AppointFragment fragment = new AppointFragment();
        fragment.bean = bean;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_appointment;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("新增访客单");
        sMe = (String) MyApplication.get(_mActivity, FinalClass.UserName, "");
        intType = (int) MyApplication.get(_mActivity, FinalClass.UserType, -1);
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        if (bean != null) {
            etMe.setText(bean.getVisitor());
            etPhone.setText(bean.getTel());
            etComply.setText(bean.getCompany());
            etEvent.setText(bean.getReason());
            etCarNum.setText(bean.getCarNo());
            etDepartName.setText(bean.getStaffName());
            etPersonNum.setText(bean.getEntourageNum() + "");
            tvDepart.setText(bean.getStaffDept());
            tvDay.setText(bean.getVDays() + "");

            sMe = etMe.getText().toString();
            sPhone = etPhone.getText().toString();
            sUnit = etComply.getText().toString();
            sEvent = etEvent.getText().toString();
            sCarNo = etCarNum.getText().toString();
            sBeName = etDepartName.getText().toString();
            sBeNum = etPersonNum.getText().toString();
            // sBeDepart = tvDepart.getText().toString();
            DeptID = bean.getGuid();
            sBeDayNum = tvDay.getText().toString();
            String[] date = bean.getVisitDate().split(" ");

            Date d = DateUtil.setDate(sf1, date[0]);
            String dd = sf.format(d);
            tvYearMonthDay.setText(dd);

            tvTimeMin.setText(bean.getVisitTime());
            sDate = tvYearMonthDay.getText().toString();
            sTime = tvTimeMin.getText().toString();
        }

        etMe.addTextChangedListener(this);
        etPhone.addTextChangedListener(this); //手机号
        etComply.addTextChangedListener(this); //单位名称
        etEvent.addTextChangedListener(this); //来访事由
        etCarNum.addTextChangedListener(this);//车牌号码
        etDepartName.addTextChangedListener(this); //受访人姓名
        tvYearMonthDay.setText(sf.format(new Date()));
        Event<String> event0 = new Event<String>(FinalClass.A, sMe);
        EventBusUtil.sendEvent(event0);
        Event<String> event = new Event<String>(FinalClass.K, tvYearMonthDay.getText().toString());
        EventBusUtil.sendEvent(event);

        if (intType == 0) { //员工
            etDepartName.setText(sMe);
            StringUtil.HintUtil(etMe, "来访者姓名" + "(" + "必填" + ")"); //用户名
        } else {
            etMe.setText(sMe);
            StringUtil.HintUtil(etMe, "拜访者姓名" + "(" + "必填" + ")"); //用户名
        }

        Date nowTime = DateUtil.setDate(sfTimeMinute, sfTimeMinute.format(new Date()));
        for (int i = 0; i < startTimes.length; i++) {
            Date startTime = DateUtil.setDate(sfTimeMinute, startTimes[i]);
            Date EndTime = DateUtil.setDate(sfTimeMinute, endTimes[i]);
            if (isEffectiveDate(nowTime, startTime, EndTime)) {
                tvTimeMin.setText(startTimes[i] + "-" + endTimes[i]);
                Event<String> event2 = new Event<String>(FinalClass.L, tvTimeMin.getText().toString());
                EventBusUtil.sendEvent(event2);
                break;
            } else {
                tvTimeMin.setText("当天超过时间");
            }
        }
    }

    private boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String sUserName = etMe.getText().toString();
        sPhone = etPhone.getText().toString();
        sUnit = etComply.getText().toString();
        sEvent = etEvent.getText().toString();
        sCarNo = etCarNum.getText().toString();
        sBeName = etDepartName.getText().toString();
        if (s.length() > 0) {
            if (etMe.getText().toString().length() > 0) {
                iconFontDelete1.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.A, sUserName);
                EventBusUtil.sendEvent(event);
            }
            if (etPhone.getText().toString().length() > 0) {
                iconFontDelete2.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.B, sPhone);
                EventBusUtil.sendEvent(event);
            }

            if (etComply.getText().toString().length() > 0) {
                iconFontDelete4.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.D, sUnit);
                EventBusUtil.sendEvent(event);
            }
            if (etEvent.getText().toString().length() > 0) {
                iconFontDelete5.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.E, sEvent);
                EventBusUtil.sendEvent(event);
            }
            if (etCarNum.getText().toString().length() > 0) {
                iconFontDelete6.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.F, sCarNo);
                EventBusUtil.sendEvent(event);
            }
            if (etDepartName.getText().toString().length() > 0) {
                iconFontDelete7.setVisibility(View.VISIBLE);
                Event<String> event = new Event<String>(FinalClass.G, sBeName);
                EventBusUtil.sendEvent(event);
            }
        } else {
            if (etMe.getText().toString().length() <= 0) {
                iconFontDelete1.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.A, sMe);
                EventBusUtil.sendEvent(event);
            }
            if (etPhone.getText().toString().length() <= 0) {
                iconFontDelete2.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.B, sPhone);
                EventBusUtil.sendEvent(event);
            }

            if (etComply.getText().toString().length() <= 0) {
                iconFontDelete4.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.D, sUnit);
                EventBusUtil.sendEvent(event);
            }
            if (etEvent.getText().toString().length() <= 0) {
                iconFontDelete5.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.E, sEvent);
                EventBusUtil.sendEvent(event);
            }
            if (etCarNum.getText().toString().length() <= 0) {
                iconFontDelete6.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.F, sCarNo);
                EventBusUtil.sendEvent(event);
            }
            if (etDepartName.getText().toString().length() <= 0) {
                iconFontDelete7.setVisibility(View.GONE);
                Event<String> event = new Event<String>(FinalClass.G, sBeName);
                EventBusUtil.sendEvent(event);
            }
        }
    }

    @OnClick({R.id.icon_font_delete1, R.id.icon_font_delete2, R.id.icon_font_delete4,
            R.id.icon_font_delete5, R.id.icon_font_delete6, R.id.icon_font_delete7, R.id.et_person_num, R.id.tv_depart,
            R.id.tv_day, R.id.ll_date_year_month_day, R.id.ll_date_time_minute, R.id.btn_sure, R.id.et_car_num,
            R.id.font_talk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_font_delete1://姓名
                etMe.setText("");
                iconFontDelete1.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete2://手机号
                etPhone.setText("");
                iconFontDelete2.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete4://单位名称
                etComply.setText("");
                iconFontDelete4.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete5://来访事由
                etEvent.setText("");
                iconFontDelete5.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete6://车牌号码
                etCarNum.setText("");
                iconFontDelete6.setVisibility(View.GONE);
                break;
            case R.id.icon_font_delete7://受访人姓名
                etDepartName.setText("");
                iconFontDelete7.setVisibility(View.GONE);
                break;
            case R.id.et_person_num:
                final NormalListDialog dialog = new NormalListDialog(_mActivity, persNums);
                dialog.title("请选择")
                        .layoutAnimation(null)
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (persNums[position].equals("清空")) {
                            etPersonNum.setText("");
                        } else {
                            etPersonNum.setText(persNums[position]);
                        }
                        Event<String> event = new Event<String>(FinalClass.H, etPersonNum.getText().toString());
                        EventBusUtil.sendEvent(event);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.tv_depart:
                EasyHttp.post(Constants.departList + session)
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {

                            }

                            @Override
                            public void onSuccess(String s) {
                                LatteLogger.d("depart", s);
                                final DepartBean bean = Convert.fromJson(s, DepartBean.class);
                                if (bean.getStatus() == 0) {
                                    departList.clear();
                                    for (int i = 0; i < bean.getData().size(); i++) {
                                        DialogMenuItem item = new DialogMenuItem(bean.getData().get(i).getDeptName(), i);
                                        departList.add(item);
                                    }
                                    DialogMenuItem item = new DialogMenuItem("清空", bean.getData().size() + 1);
                                    departList.add(item);

                                    final NormalListDialog dialog1 = new NormalListDialog(_mActivity, departList);
                                    dialog1.title("请选择")
                                            .heightScale(0.85f)
                                            .itemTextSize(12)
                                            .titleTextSize_SP(12)
                                            .layoutAnimation(null)
                                            .show();
                                    dialog1.setOnOperItemClickL(new OnOperItemClickL() {
                                        @Override
                                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if (departList.get(position).mOperName.equals("清空")) {
                                                tvDepart.setText("");
                                            } else {
                                                tvDepart.setText(departList.get(position).mOperName);
                                                DeptID = bean.getData().get(position).getDeptID();
                                                LatteLogger.d("DeptID", DeptID);
                                            }
                                            Event<String> event = new Event<String>(FinalClass.I, DeptID);
                                            EventBusUtil.sendEvent(event);
                                            dialog1.dismiss();
                                        }
                                    });
                                } else { //失败
                                    ToastUtil.getInstance().showToast(bean.getMsg());
                                }
                            }
                        });
                break;
            case R.id.tv_day:
                final NormalListDialog dialog2 = new NormalListDialog(_mActivity, dayNums);
                dialog2.title("请选择")
                        .layoutAnimation(null)
                        .show();
                dialog2.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (dayNums[position].equals("清空")) {
                            tvDay.setText("");
                        } else {
                            tvDay.setText(dayNums[position]);
                        }
                        Event<String> event = new Event<String>(FinalClass.J, tvDay.getText().toString());
                        EventBusUtil.sendEvent(event);
                        dialog2.dismiss();
                    }
                });
                break;
            case R.id.ll_date_year_month_day:
                startForResult(MultiSelectCalendarView.newInstance(), REQ_MODIFY_FRAGMENT);
                break;
            case R.id.ll_date_time_minute:
                final NormalListDialog dialog1 = new NormalListDialog(_mActivity, timeBound);
                dialog1.title("请选择")
                        .layoutAnimation(null).show();
                dialog1.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        sTime = timeBound[position];
                        tvTimeMin.setText(sTime);
                        Event<String> event = new Event<String>(FinalClass.L, tvTimeMin.getText().toString());
                        EventBusUtil.sendEvent(event);
                        dialog1.dismiss();
                    }
                });
                break;
            case R.id.et_car_num:
                CarNoInputDialog fragment = CarNoInputDialog.newInstance();
                fragment.show(getChildFragmentManager(), DIALOG_DATA);
                fragment.setListener(carNo -> etCarNum.setText(carNo));
                break;
            case R.id.btn_sure:
                if (sBeDayNum.equals("1天")) {
                    intDay = 1;
                } else if (sBeDayNum.equals("2天")) {
                    intDay = 2;
                } else if (sBeDayNum.equals("3天")) {
                    intDay = 3;
                } else if (sBeDayNum.equals("4天")) {
                    intDay = 4;
                } else if (sBeDayNum.equals("5天")) {
                    intDay = 5;
                } else if (sBeDayNum.equals("5天以上")) {
                    intDay = 6;
                }
                if (sBeNum.equals("1人")) {
                    intPersonNum = 1;
                } else if (sBeNum.equals("2人")) {
                    intPersonNum = 2;
                } else if (sBeNum.equals("3人")) {
                    intPersonNum = 3;
                } else if (sBeNum.equals("4人")) {
                    intPersonNum = 4;
                } else if (sBeNum.equals("5人")) {
                    intPersonNum = 5;
                } else if (sBeNum.equals("5人以上")) {
                    intPersonNum = 6;
                }
                String mDate = sdf.format(DateUtil.setDate(sf, sDate));

                if (bean != null) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("ID",bean.getID());
                    jsonObject.addProperty("Visitor",sMe);//访问人姓名
                    jsonObject.addProperty("Tel",sPhone); //访问人电话
                    jsonObject.addProperty("Company",sUnit);//访问人单位
                    jsonObject.addProperty("Reason",sEvent);//访问事由
                    jsonObject.addProperty("CarNo",sCarNo);//车牌号码
                    jsonObject.addProperty("StaffName",sBeName);
                    jsonObject.addProperty("EntourageNum",intPersonNum);//访问人数
                    jsonObject.addProperty("StaffDept",DeptID);//被访问人部门（传入部门DeptID）
                    jsonObject.addProperty("VDays",intDay);//访问天数
                    jsonObject.addProperty("VisitDate",mDate);//访问日期
                    jsonObject.addProperty("VisitTime",sTime);//访问时间段（通过选择）
                    jsonObject.addProperty("CodeType",intType);

                    LatteLogger.d("tesst",GsonBuildUtil.GsonBuilder(jsonObject));

                    EasyHttp.post(Constants.SUBMIT_APPOINT + session)
                            .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                            .execute(new SimpleCallBack<String>() {
                                @Override
                                public void onError(ApiException e) {
                                    ToastUtil.getInstance().showToast(e.getMessage());
                                }

                                @Override
                                public void onSuccess(String s) {
                                    LatteLogger.d("submit", s);
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        if (jsonObject.getInt("Status") == 0) {
                                            ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                                            Bundle bundle = new Bundle();
                                            setFragmentResult(RESULT_OK, bundle);
                                            _mActivity.onBackPressed();
                                        } else {
                                            ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                } else {
                    EasyHttp.post(Constants.SUBMIT_APPOINT + session)
                            .params("Visitor", sMe)  //访问人姓名
                            .params("Tel", sPhone) //访问人电话
                            .params("Company", sUnit) //访问人单位
                            .params("Reason", sEvent) //访问事由
                            .params("CarNo", sCarNo) //车牌号码
                            .params("StaffName", sBeName)
                            .params("EntourageNum", intPersonNum + "") //访问人数
                            .params("StaffDept", DeptID)  //被访问人部门（传入部门DeptID）
                            .params("VDays", intDay + "")//访问天数
                            .params("VisitDate", mDate) //访问日期
                            .params("VisitTime", sTime) //访问时间段（通过选择）
                            .params("CodeType", intType + "")
                            .execute(new SimpleCallBack<String>() {
                                @Override
                                public void onError(ApiException e) {
                                    ToastUtil.getInstance().showToast(e.getMessage());
                                }

                                @Override
                                public void onSuccess(String s) {
                                    LatteLogger.d("submit", s);
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        if (jsonObject.getInt("Status") == 0) {
                                            ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                                            Bundle bundle = new Bundle();
                                            setFragmentResult(RESULT_OK, bundle);
                                            _mActivity.onBackPressed();
                                        } else {
                                            ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
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
            case FinalClass.A:
                sMe = (String) event.getData();
                break;
            case FinalClass.B:
                sPhone = (String) event.getData();
                break;
            case FinalClass.D:
                sUnit = (String) event.getData();
                break;
            case FinalClass.E:
                sEvent = (String) event.getData();
                break;
            case FinalClass.F:
                sCarNo = (String) event.getData();
                break;
            case FinalClass.G:
                sBeName = (String) event.getData();
                break;
            case FinalClass.H:
                sBeNum = (String) event.getData();
                break;
            case FinalClass.I:
                DeptID = (String) event.getData();
                break;
            case FinalClass.J:
                sBeDayNum = (String) event.getData();
                break;
            case FinalClass.K:
                sDate = (String) event.getData();
                break;
            case FinalClass.L:
                sTime = (String) event.getData();
                break;
        }

        if ("".equals(sPhone) || "".equals(sUnit) || "".equals(sEvent)
                || "".equals(sBeName) || "".equals(sBeNum) || "".equals(DeptID) || "".equals(sBeDayNum)
                || "".equals(sDate) || "".equals(sTime) && sPhone == null || sUnit == null || sEvent == null
                || sBeName == null || sBeNum == null || DeptID == null || sBeDayNum == null
                || sDate == null || sTime == null) {
            btnSure.setClickable(false);
            btnSure.setBackgroundResource(R.color.grey_home);
        } else {
            btnSure.setBackgroundResource(R.drawable.selector_green_darkgreen);
            btnSure.setClickable(true);
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            String result = data.getString("result");

            tvYearMonthDay.setText(sf.format(DateUtil.setDate(sdf, result)));
            Event<String> event = new Event<String>(FinalClass.K, tvYearMonthDay.getText().toString());
            EventBusUtil.sendEvent(event);
        }
    }
}










