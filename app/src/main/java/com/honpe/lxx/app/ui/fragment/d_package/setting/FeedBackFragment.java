package com.honpe.lxx.app.ui.fragment.d_package.setting;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.utils.StringUtil;

import butterknife.BindView;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 11:21
 * @Author: 李熙祥
 * @Description: java类作用描述 用户反馈
 */
public class FeedBackFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_describe)
    EditText etDescribe;
    @BindView(R.id.btn_up_icon_5)
    Button btnUpIcon5;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private String title;

    public static FeedBackFragment newInstance(String title) {
        FeedBackFragment fragment = new FeedBackFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        Bundle bundle = getArguments();

        if (bundle != null) {
            title =  bundle.getString("title");
            tvTitle.setText(title);
        }
        etDescribe.clearFocus();

        StringUtil.HintUtil(etDescribe,getString(R.string.hint_feed_back));
        StringUtil.HintUtil(etEmail,getString(R.string.feedback_email));
        StringUtil.HintUtil(etPhone,getString(R.string.feedback_phone_no_hint));
        etDescribe.setHintTextColor(getResources().getColor(R.color.light_blue));
        etEmail.setHintTextColor(getResources().getColor(R.color.light_blue));
        etPhone.setHintTextColor(getResources().getColor(R.color.light_blue));
    }
}


