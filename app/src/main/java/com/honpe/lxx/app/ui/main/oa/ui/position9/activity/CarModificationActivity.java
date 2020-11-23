package com.honpe.lxx.app.ui.main.oa.ui.position9.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseActivity;
import com.honpe.lxx.app.bean.CarInfoBean;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.CommonOrderStatusBean;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.ItemDepartBean;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.ItemTeamBean;
import com.honpe.lxx.app.utils.GetMessageUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.dialog.AddressDialog;
import com.honpe.lxx.app.widget.font.FontTextView2;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.SEND_CAR_REFRESH;
import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 11:37
 * @Author: 李熙祥
 * @Description: java类作用描述 修改派车
 */
public class CarModificationActivity extends BaseActivity {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_start_point)
    EditText tvGo;
    @BindView(R.id.text_arrive)
    TextView tvTo;
    @BindView(R.id.edit_manage_phone)
    EditText etPhone;
    @BindView(R.id.text_num_people)
    EditText etNumPerson;
    @BindView(R.id.text_accessory_items)
    EditText etItems;
    @BindView(R.id.text_selected_message)
    EditText tvMessage;
    @BindView(R.id.text_return_date)
    TextView textReturnDate;
    @BindView(R.id.icon_from_delete)
    FontTextView2 icon_from_delete;
    @BindView(R.id.icon_to_delete)
    FontTextView2 icon_to_delete;
    @BindView(R.id.icon_phone_delete)
    FontTextView2 icon_phone_delete;
    @BindView(R.id.icon_num_people_delete)
    FontTextView2 icon_num_people_delete;
    @BindView(R.id.icon_accessory_items_delete)
    FontTextView2 icon_accessory_items_delete;
    @BindView(R.id.icon_message_delete)
    FontTextView2 icon_message_delete;
    @BindView(R.id.icon_date_delete)
    FontTextView2 iconDateDelete;
    @BindView(R.id.icon_depart_delete)
    FontTextView2 iconDepartDelete;
    @BindView(R.id.icon_team_delete)
    FontTextView2 iconTeamDelete;
    @BindView(R.id.text_date)
    TextView tvTime;
    @BindView(R.id.text_selected_depart)
    TextView tvDepart;
    @BindView(R.id.text_selected_team)
    TextView tvTeam;
    @BindView(R.id.apply_normal)
    Button apply_now;
    @BindView(R.id.apply_succeed)
    Button apply_now_true;
    @BindView(R.id.svr)
    ScrollView scrollView;
    @BindView(R.id.root)
    LinearLayout root;

    private TimePickerView pvTime, pvTime2;
    public final static int REQUEST_CODE = 100; //返回的结果码
    public final static int REQUEST_TEAM = 200; //返回组
    private String depart, team, time, iniTime, session, des, phone, mMessage, times, go_along_name, mygoods;
    //修改
    private String orderId, Reason,applyName,returnTime, Destination, UseCarTime, Entourage,Tel,Items,UserCarGroup,UserCarDept;
    private Message message;
    public final static int TO = 1;
    public final static int PHONE = 2;
    public final static int TEAM = 3;
    public final static int MESSAGE = 4;
    public final static int DEPART = 5;
    public final static int TIME = 6;
    public final static int GO_ALONG = 7;
    public final static int GOODS = 8;
    public final static int RETURN_TIME = 9;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    List<String> Lists = new ArrayList<>();
    private String[] teams;
    private Bundle bundle = new Bundle();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case TO: {
                    des = bundle.getString("des");
                }
                break;
                case PHONE: {
                    phone = bundle.getString("phone");
                }
                break;
                case TEAM: {
                    team = bundle.getString("team");
                    tvTeam.setText(team);
                }
                break;
                case MESSAGE: {
                    mMessage = bundle.getString("mMessage");
                }
                break;
                case DEPART: {
                    depart = bundle.getString("depart");
                    tvDepart.setText(depart);
                }
                break;
                case TIME: {
                    time = bundle.getString("time");
                    iniTime = bundle.getString("iniTime");
                    if (iniTime == null) {
                        tvTime.setText(time);
                    } else {
                        tvTime.setText(iniTime);
                    }
                    times = null;
                    if (iniTime != null) {
                        times = iniTime;
                    } else {
                        times = time;
                    }
                }
                break;
                case GO_ALONG: {
                    go_along_name = bundle.getString("go_along_name");
                }
                break;
                case GOODS: {
                    if (mygoods != null) {
                        mygoods = bundle.getString("mygoods");
                    } else {
                        mygoods = "nulls";
                    }
                }
                break;
            }

            if (des != null && phone != null && time != null && depart != null && team != null && mMessage != null) {
                apply_now.setVisibility(View.GONE);
                apply_now_true.setVisibility(View.VISIBLE);

            } else if (orderId != null && UseCarTime != null && Reason != null && UserCarDept != null && UserCarGroup != null && Destination!=null){
                apply_now_true.setVisibility(View.VISIBLE);
                apply_now.setVisibility(View.GONE);

            } else {
                apply_now.setVisibility(View.VISIBLE);
                apply_now_true.setVisibility(View.GONE);
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_car_apply_mobification;
    }

    @Override
    public void initView() {
        session = (String) MyApplication.get(mContext, Session, "");
        tvTitle.setText("修改申请单");
        initToolbarNav(llBack);
        tvGo.setText("公司");
        apply_now.setText("马上修改");
        apply_now_true.setText("马上修改");

        Date date = new Date();
        iniTime = sf.format(date);
        message = handler.obtainMessage(TIME);
        Bundle bundle = getIntent().getExtras();
        bundle.putString("iniTime", iniTime);
        message.setData(bundle);
        message.sendToTarget();

        orderId = bundle.getString("SendCarNo");
        Destination = bundle.getString("Destination");
        Tel = bundle.getString("Tel");
        Entourage = bundle.getString("Entourage");
        Items = bundle.getString("Items");
        UseCarTime = bundle.getString("UseCarTime");
        UserCarDept = bundle.getString("UserCarDept");
        UserCarGroup = bundle.getString("UserCarGroup");
        Reason = bundle.getString("Reason");
        returnTime = bundle.getString("EstimatedRTime");
        applyName = bundle.getString("UserName");

        tvTo.setText(Destination);
        etPhone.setText(Tel);
        etNumPerson.setText(Entourage);
        etItems.setText(Items);
        tvTime.setText(UseCarTime);
        textReturnDate.setText(returnTime);
        tvDepart.setText(UserCarDept);
        tvTeam.setText(UserCarGroup);
        tvMessage.setText(Reason);

        root.addOnLayoutChangeListener(mListener);
        mEtMsgOnKeyListener();
        selectedDate();
    }

    private void mEtMsgOnKeyListener() {
        hideSoftKeyboard();
        final Bundle bundle = new Bundle();
        tvTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    des = s.toString();
                    message = handler.obtainMessage(TO);
                    bundle.putString("des", des);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_to_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_to_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    phone = s.toString();
                    message = handler.obtainMessage(PHONE);
                    bundle.putString("phone", phone);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_phone_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_phone_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        tvMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mMessage = s.toString();
                    message = handler.obtainMessage(MESSAGE);
                    bundle.putString("mMessage", mMessage);
                    message.setData(bundle);
                    message.sendToTarget();
                    icon_message_delete.setVisibility(View.VISIBLE);
                } else {
                    icon_message_delete.setVisibility(View.GONE);
                    apply_now.setVisibility(View.VISIBLE);
                    apply_now_true.setVisibility(View.GONE);
                }
            }
        });
        etNumPerson.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    go_along_name = etNumPerson.getText().toString();
                    icon_num_people_delete.setVisibility(View.VISIBLE);
                } else {
                    go_along_name = etNumPerson.getText().toString();
                    icon_num_people_delete.setVisibility(View.GONE);
                }
                message = handler.obtainMessage(GO_ALONG);
                bundle.putString("go_along_name", go_along_name);
                message.setData(bundle);
                message.sendToTarget();
            }
        });
        etItems.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mygoods = etItems.getText().toString();
                    icon_accessory_items_delete.setVisibility(View.VISIBLE);
                } else {
                    mygoods = etItems.getText().toString();
                    icon_accessory_items_delete.setVisibility(View.GONE);
                }
                message = handler.obtainMessage(GOODS);
                bundle.putString("mygoods", mygoods);
                message.setData(bundle);
                message.sendToTarget();
            }
        });
        GetMessageUtil util = new GetMessageUtil();
        util.editChanged(tvGo, icon_from_delete);
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(root.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void selectedDate() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        pvTime2 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textReturnDate.setText(getTime(date));
                returnTime = getTime(date);
                message = handler.obtainMessage(RETURN_TIME);
                Bundle bundle = new Bundle();
                bundle.putString("returnTime", returnTime);
                message.setData(bundle);
                message.sendToTarget();
            }
        }).setDate(selectedDate).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {

            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("预计返回时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();


        pvTime = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvTime.setText(getTime(date));
                time = getTime(date);
                message = handler.obtainMessage(TIME);
                Bundle bundle = new Bundle();
                bundle.putString("time", time);
                message.setData(bundle);
                message.sendToTarget();

            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("出行时间")
                .setTitleSize(14)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        Dialog mDialog2 = pvTime2.getDialog();
        if (mDialog2 != null) {
            FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            params2.leftMargin = 20;
            params2.rightMargin = 20;
            pvTime2.getDialogContainerLayout().setLayoutParams(params2);

            Window dialogWindow = mDialog2.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);
            }
        }
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            params.leftMargin = 20;
            params.rightMargin = 20;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom，底部显示
            }
        }
    }

    /**
     * 选择日期
     */
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        return sf.format(date);
    }

    public View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            Log.i("OnLayoutChangeListener", oldLeft + "\t" + oldRight + "\t" + oldTop + "\t" + oldBottom);
            Log.i("OnLayoutChangeListener", left + "\t" + right + "\t" + top + "\t" + bottom);
            if (oldBottom != 0 && (oldBottom - bottom) > 150) {
                srcoll(oldBottom - bottom);
            }
        }
    };

    public void srcoll(int y) {
        int scrollY = scrollView.getScrollY();
        if (scrollY == 0) {
            scrollView.scrollBy(0, 9999);
        }
    }

    @OnClick({R.id.icon_from_delete, R.id.icon_to_delete, R.id.icon_phone_delete, R.id.icon_num_people_delete,
            R.id.icon_accessory_items_delete, R.id.text_date, R.id.text_selected_depart,
            R.id.text_selected_team, R.id.icon_message_delete, R.id.apply_normal, R.id.apply_succeed,R.id.text_arrive, R.id.text_return_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_arrive:
                AddressDialog dialog = new AddressDialog(_mActivity,des);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                break;
            case R.id.icon_from_delete:
                tvGo.setText("");
                break;
            case R.id.icon_to_delete:
                tvTo.setText("");
                break;
            case R.id.icon_phone_delete:
                etPhone.setText("");
                break;
            case R.id.icon_num_people_delete:
                etNumPerson.setText("");
                break;
            case R.id.icon_accessory_items_delete:
                etItems.setText("");
                break;
            case R.id.text_date:
                //出行时间
                pvTime.show();//弹出条件选择器
                break;
            case R.id.text_return_date:
                pvTime2.show();
                break;
            case R.id.text_selected_depart:
                EasyHttp.post(Constants.MANAGER_DEPART + session)
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {

                            }

                            @Override
                            public void onSuccess(String result) {
                                LatteLogger.d("result", result);
                                ItemDepartBean bean = Convert.fromJson(result, ItemDepartBean.class);
                                Lists.clear();
                                for (int i = 0; i < bean.getData().size(); i++) {
                                    Lists.add(bean.getData().get(i).getDeptName());
                                }

                                teams = Lists.toArray(new String[Lists.size()]);
                                final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                                dialog.title("选择房号")
                                        .titleTextSize_SP(12)
                                        .itemTextSize(12)
                                        .cornerRadius(5)
                                        .heightScale(0.7f)
                                        .layoutAnimation(null)
                                        .show();
                                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                    @Override
                                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tvDepart.setText(teams[position]);
                                        message = handler.obtainMessage(DEPART);
                                        bundle.putString("depart", tvDepart.getText().toString());
                                        message.setData(bundle);
                                        message.sendToTarget();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                break;
            case R.id.text_selected_team:
                EasyHttp.post(Constants.MANAGER_TEAM + session)
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException result) {

                            }

                            @Override
                            public void onSuccess(String result) {
                                final ItemTeamBean bean = Convert.fromJson(result, ItemTeamBean.class);
                                Lists.clear();
                                for (int i = 0; i < bean.getData().size(); i++) {
                                    Lists.add(bean.getData().get(i).getGroupName());
                                }
                                teams = Lists.toArray(new String[Lists.size()]);

                                final NormalListDialog dialog = new NormalListDialog(_mActivity, teams);
                                dialog.title("选择组别")
                                        .titleTextSize_SP(12)
                                        .itemTextSize(12)
                                        .layoutAnimation(null)
                                        .show();
                                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                                    @Override
                                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tvTeam.setText(teams[position]);
                                        message = handler.obtainMessage(TEAM);
                                        bundle.putString("team", tvTeam.getText().toString());
                                        message.setData(bundle);
                                        message.sendToTarget();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                break;
            case R.id.icon_message_delete:
                tvMessage.setText("");
                break;
            case R.id.apply_normal:
                ToastUtil.getInstance().showToast("你好像还有内容没填噢!@——@");
                break;
            case R.id.apply_succeed:
                //提交按钮
                getJsonData();
                break;
        }
    }

    private void getJsonData() {
        EasyHttp.post(Constants.MOBELORDER + session)
                .params("SendCarNo", orderId) //派车单号
                .params("UserCarGroup", tvTeam.getText().toString().trim()) //用车组别
                .params("UserCarDept", tvDepart.getText().toString().trim()) //用车部门
                .params("Reason", tvMessage.getText().toString().trim())  //派车事由
                .params("Tel", etPhone.getText().toString().trim())  //电话
                .params("Destination", tvTo.getText().toString().trim()) //目的地
                .params("Entourage", etNumPerson.getText().toString().trim())  //随车人员 可为空
                .params("Items", etItems.getText().toString().trim())  //随车物品  可为空
                .params("UseCarTime", tvTime.getText().toString().trim()) //用车时间
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("getJsonData",result);
                        CommonOrderStatusBean bean = Convert.fromJson(result, CommonOrderStatusBean.class);
                        EventBusUtil.sendEvent(new Event(SEND_CAR_REFRESH));
                        ToastUtil.getInstance().showToast(bean.getMsg());
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = new Bundle();
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            depart = data.getExtras().getString("result");
            message = handler.obtainMessage(DEPART);
            bundle.putString("depart", depart);
            message.setData(bundle);
            message.sendToTarget();
//            tvDepart.setText(depart);
            tvDepart.setTextColor(getResources().getColor(R.color.black));
        }
        if (requestCode == REQUEST_TEAM && resultCode == RESULT_OK && data != null) {
            team = data.getExtras().getString("resultTeam");
            message = handler.obtainMessage(TEAM);
            bundle.putString("team", team);
            message.setData(bundle);
            message.sendToTarget();
            //    tvTeam.setText(team);
            tvTeam.setTextColor(getResources().getColor(R.color.black));
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
                CarInfoBean.DataBean.OrderListBean item = (CarInfoBean.DataBean.OrderListBean) event.getData();
                LatteLogger.d("FinalClass", GsonBuildUtil.GsonBuilder(item));
                break;
        }
    }

}



