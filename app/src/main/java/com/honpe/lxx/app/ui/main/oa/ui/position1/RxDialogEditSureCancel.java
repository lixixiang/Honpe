package com.honpe.lxx.app.ui.main.oa.ui.position1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BaseDialog;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBean;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.SettingTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 16:02
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class RxDialogEditSureCancel<C extends BaseDialog<C>> extends Dialog implements TextWatcher {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_sub1)
    LinearLayout llSub1;
    @BindView(R.id.tv_sub2)
    TextView tvSub2;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.ll_sub2)
    LinearLayout llSub2;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.iv_cha)
    ImageView ivCha;
    private String strSub2, strTitle, hint, InputUserNos;
    private Activity activity;

    public RxDialogEditSureCancel(Activity activity, String InputUserNos, String strTitle, String strSub2, String hint) {
        super(activity, R.style.comment_dialog);
        this.activity = activity;
        this.strSub2 = strSub2;
        this.strTitle = strTitle;
        this.hint = hint;
        this.InputUserNos = InputUserNos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_dialog);
        initWindowParams();
        ButterKnife.bind(this);
        initView();
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        //获取屏幕宽、高用
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.84); // 宽度设置为屏幕的0.65
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        llSub1.setVisibility(View.GONE);
        tvSub2.setText(strSub2);
        tvTitle.setText(strTitle);
        ivCha.setColorFilter(activity.getResources().getColor(R.color.black));
        etAddress.addTextChangedListener(this);
        etAddress.setText(InputUserNos);

        if (!"".equals(hint) && !TextUtils.isEmpty(hint)) {
            StringUtil.HintUtil(etAddress, hint);
        }
        if (strTitle == "考勤查询" || strTitle == "前端点超时" || strTitle == "后端点超时" ) {
            etAddress.setInputType(InputType.TYPE_CLASS_NUMBER);
            if (strTitle == "前端点超时") {
                if ("请输入".equals(hint)) {
                    etAddress.setText("5000");
                }
            } else if (strTitle == "后端点超时") {
                if ("请输入".equals(hint)) {
                    etAddress.setText("1800");
                }
            }
            etAddress.addTextChangedListener(new SettingTextWatcher(activity, etAddress, 0, 10000));
        }
    }

    @OnClick({R.id.no, R.id.yes,R.id.iv_cha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no:
                hideSoft(no);
                dismiss();
                break;
            case R.id.yes:
                hideSoft(yes);
                String message = etAddress.getText().toString();
                EventBean bean = new EventBean(message, strTitle);
                Event<EventBean> event = new Event<EventBean>(FinalClass.A, bean);
                EventBusUtil.sendEvent(event);
                dismiss();
                break;
            case R.id.iv_cha:
                etAddress.setText("");
                ivCha.setVisibility(View.GONE);
                break;
        }
    }

    public void hideSoft(View view) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            ivCha.setVisibility(View.VISIBLE);
        }
    }
}

