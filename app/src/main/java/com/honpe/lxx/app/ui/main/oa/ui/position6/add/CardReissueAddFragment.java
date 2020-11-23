package com.honpe.lxx.app.ui.main.oa.ui.position6.add;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Session;


/**
 * created by lxx at 2019/12/3 15:10
 * 描述:
 */
public class CardReissueAddFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.et_trips_event)
    TextView etTripsEvent;
    @BindView(R.id.et_trips_type)
    TextView etTripsType;
    @BindView(R.id.et_result)
    EditText etResult;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private TimePickerView pvTime1;
    String[] timeTypes = {"时间1", "时间2", "时间3", "时间4", "时间5", "时间6"};
    private String session;
    private String time = null;
    private String carType = null;
    private String remark = null;
    private int type;

    public static CardReissueAddFragment newInstance() {
        CardReissueAddFragment fragment = new CardReissueAddFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.item_add_card_reissue;
    }

    @Override
    protected void initView() {
        initToolbarNav(homeBack);
        title.setText("补卡申请");
        StringUtil.HintUtil(etTripsEvent, "请选择");
        StringUtil.HintUtil(etTripsType, "请选择");
        session = (String) MyApplication.get(_mActivity, Session, "");
        selectTime();
        etResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                remark = etResult.getText().toString();
                if (s.length() > 0) {
                    Event<String> event = new Event<String>(FinalClass.C, remark);
                    EventBusUtil.sendEvent(event);
                } else {
                    applyNormal.setVisibility(View.VISIBLE);
                    applySucceed.setVisibility(View.GONE);
                }
            }
        });
    }

    private void selectTime() {
        Calendar selectedDate = Calendar.getInstance();
        pvTime1 = new TimePickerBuilder(_mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                etTripsEvent.setText(getTime(date));
                time = etTripsEvent.getText().toString();
                Event<String> event = new Event<String>(FinalClass.A, time);
                EventBusUtil.sendEvent(event);
            }
        }).setDate(selectedDate)
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        etTripsEvent.setText(getTime(date));
                        time = etTripsEvent.getText().toString();
                        Event<String> event = new Event<String>(FinalClass.A, time);
                        EventBusUtil.sendEvent(event);
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("补卡时间")
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime1.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime1.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom，底部显示
            }
        }
    }

    private String getTime(Date date) {
        return sf.format(date);
    }

    @OnClick({R.id.et_trips_event, R.id.et_trips_type, R.id.apply_normal, R.id.apply_succeed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_trips_event:
                pvTime1.show();
                break;
            case R.id.et_trips_type:
                final NormalListDialog dialog = new NormalListDialog(_mActivity, timeTypes);
                dialog.title("补卡类型")
                        .layoutAnimation(null)
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        etTripsType.setText(timeTypes[position]);
                        type = position + 1;
                        carType = etTripsType.getText().toString();
                        Event<String> event = new Event<String>(FinalClass.B, carType);
                        EventBusUtil.sendEvent(event);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.apply_normal:
                ToastUtil.getInstance().showToast("请填写完整数据，然后提交");
                break;
            case R.id.apply_succeed:
                getRequestResult();
                hideSoftInput();
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
                time = (String) event.getData();
                break;
            case FinalClass.B:
                carType = (String) event.getData();
                break;
            case FinalClass.C:
                remark = (String) event.getData();
                break;
            default:
                break;
        }
        LatteLogger.d("time==" + time + "carType==" + carType + "remark==" + remark);
        if (remark == null) {
            applyNormal.setVisibility(View.VISIBLE);
            applySucceed.setVisibility(View.GONE);
        } else {
            applyNormal.setVisibility(View.GONE);
            applySucceed.setVisibility(View.VISIBLE);
        }
    }

    private void getRequestResult() {
        EasyHttp.post(Constants.FULL_CARD + session)
                .params("F_Reason", etResult.getText().toString())
                .params("F_BkTime", etTripsEvent.getText().toString())
                .params("F_Type", type + "")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d(s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getInt("Status") == 0) {
                                ToastUtil.getInstance().showToast(obj.getString("Msg"));
                                Bundle bundle = new Bundle();
                                bundle.putString("msg", obj.getString("Msg"));
                                setFragmentResult(RESULT_OK, bundle);
                                _mActivity.onBackPressed();
                            } else {
                                ToastUtil.getInstance().showToast(obj.getString("Msg"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}





