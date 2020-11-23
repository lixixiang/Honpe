package com.honpe.lxx.app.ui.fragment.d_package.setting;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 11:29
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class MobPassFragment extends BaseBackFragment implements TextWatcher {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.css_title)
    TextView cssTitle;
    @BindView(R.id.css_et)
    EditText cssEt;
    @BindView(R.id.css_iv_cha)
    ImageView cssIvCha;
    @BindView(R.id.css_title2)
    TextView cssTitle2;
    @BindView(R.id.css_et2)
    EditText cssEt2;
    @BindView(R.id.css_iv_cha2)
    ImageView cssIvCha2;
    @BindView(R.id.css_title3)
    TextView cssTitle3;
    @BindView(R.id.css_et3)
    EditText cssEt3;
    @BindView(R.id.css_iv_cha3)
    ImageView cssIvCha3;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.view_line3)
    View viewLine3;

    private String[] hints = {"填不写原密码", "填写新密码", "再次填写确认"};

    public static final MobPassFragment newInstance() {
        MobPassFragment fragment = new MobPassFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.mob_pass;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("修改密码");
        StringUtil.HintUtil(cssEt, hints[0]);
        StringUtil.HintUtil(cssEt2, hints[1]);
        StringUtil.HintUtil(cssEt3, hints[2]);
        cssEt.setHintTextColor(getResources().getColor(R.color.grey_home));
        cssEt2.setHintTextColor(getResources().getColor(R.color.grey_home));
        cssEt3.setHintTextColor(getResources().getColor(R.color.grey_home));
        cssIvCha.setColorFilter(getResources().getColor(R.color.white_a5));
        cssIvCha2.setColorFilter(getResources().getColor(R.color.white_a5));
        cssIvCha3.setColorFilter(getResources().getColor(R.color.white_a5));
        cssEt.addTextChangedListener(this);
        cssEt2.addTextChangedListener(this);
        cssEt3.addTextChangedListener(this);


        SpannableString spannableString = new SpannableString("密码必须是8-16位 数字/字母/字符两种组合");
        spannableString.setSpan(new ForegroundColorSpan
                (getResources().getColor(R.color.blue)), 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTips.setText(spannableString);

        //  initListener();
    }

    private void initListener() {
        cssEt2.setOnFocusChangeListener(new RePasswordListener());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (cssEt.getText().toString().length() > 0) {
            cssIvCha.setVisibility(View.VISIBLE);
        }
        if (cssEt2.getText().toString().length() > 0) {
            cssIvCha2.setVisibility(View.VISIBLE);
        }
        if (cssEt3.getText().toString().length() > 0) {
            cssIvCha3.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
    private boolean repasswordCursor = true;// 判读重复密码输入框是失去光标还是获得光标

    @OnClick({R.id.css_iv_cha, R.id.css_iv_cha2, R.id.css_iv_cha3,R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.css_iv_cha:
                cssIvCha.setVisibility(View.INVISIBLE);
                cssEt.setText("");
                break;
            case R.id.css_iv_cha2:
                cssIvCha2.setVisibility(View.INVISIBLE);
                cssEt2.setText("");
                break;
            case R.id.css_iv_cha3:
                cssIvCha3.setVisibility(View.INVISIBLE);
                cssEt3.setText("");
                break;
            case R.id.btn_sure:
                if (cssEt == null) {
                    LatteLogger.d("原始密码不能为空");
                } else {
                    getRequestPass();
                }
                break;
        }
    }

    private void getRequestPass() {
        EasyHttp.post(Constants.MOB_PASS_WORD + session)
                .params("oldpassword", cssEt.getText().toString())
                .params("newpassword", cssEt2.getText().toString())
                .params("confirmpassword", cssEt3.getText().toString())
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
                        ToastUtil.getInstance().showToast(e.getMessage());
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d(result);
                        if (result.contains(sessionE) || result.contains(sessionI)) {
                            start(LoginFragment.newInstance(""));
                            ToastUtil.getInstance().showToast(sessionPastDue);
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    /**
     * RePasswordListener
     *
     * @author renzhongfeng
     * 重复输入密码失去光标的监听类 2014/07/27
     */
    private class RePasswordListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (repasswordCursor = !repasswordCursor) {
                if (!checkPassword(cssEt.getText().toString(), cssEt2.getText().toString())) {
                    cssEt2.setText("");
                    //rePassword.requestFocus();
                    ToastUtil.getInstance().showToast("两次密码不一样，请重新输入");
                }
            }
            if (!StringUtil.isPass(cssEt.getText().toString())) {
                cssEt.setText("");
                ToastUtil.getInstance().showToast("密码为6-18位数字和字母组合");
            }
        }
    }

    public boolean checkPassword(String psw1, String psw2) {
        if (psw1.equals(psw2))
            return true;
        else
            return false;
    }
}
