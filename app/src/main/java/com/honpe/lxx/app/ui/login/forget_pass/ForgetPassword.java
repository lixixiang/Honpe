package com.honpe.lxx.app.ui.login.forget_pass;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 10:24
 * @Author: 李熙祥
 * @Description: java类作用描述 忘记密码界面
 */
public class ForgetPassword extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.ll_tab_account)
    LinearLayout llTabAccount;
    @BindView(R.id.ll_success_submit)
    LinearLayout llSuccessSubmit;
    @BindView(R.id.apply_normal)
    Button applyNormal;
    @BindView(R.id.apply_succeed)
    Button applySucceed;
    @BindView(R.id.apply_relative)
    RelativeLayout applyRelative;

    private String userAccount = "";
    private SharedPreferences sp;

    public static ForgetPassword newInstance() {
        ForgetPassword fragment = new ForgetPassword();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_find_pass;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(getString(R.string.login_forget_pwd));
        applySucceed.setVisibility(View.VISIBLE);
        applyNormal.setVisibility(View.GONE);
        applySucceed.setText("提交帐号");
        sp = _mActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userAccount = sp.getString("USER_CODE2", "");
        tvAccount.setText(userAccount);
    }

    @OnClick({R.id.apply_succeed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_succeed:
                LatteLogger.d("dddddddddddd", userAccount);
                getJsonData(userAccount);
                break;
        }
    }

    private void getJsonData(String account) {
        String[][] object2 = {new String[]{"HonpeErp_", ""}};
        LatteLogger.d("getJsonData", StringUtil.replace(account, object2));
        EasyHttp.post(Constants.FIND_PASS)
                .params("usercode", StringUtil.replace(account, object2))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("result", result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        _mActivity.onBackPressed();
                    }
                });
    }

    @Override
    public boolean onBackPressedSupport() {

        return super.onBackPressedSupport();
    }
}




