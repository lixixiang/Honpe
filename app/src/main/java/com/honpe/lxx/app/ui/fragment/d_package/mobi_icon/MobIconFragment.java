package com.honpe.lxx.app.ui.fragment.d_package.mobi_icon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.soundcloud.android.crop.Crop;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.honpe.lxx.app.api.FinalClass.UPDATA_TO_ME;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 12:16
 * @Author: 李熙祥
 * @Description: java类作用描述  修改头像
 */
public class MobIconFragment extends BaseBackFragment {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    private String headIcon, userName;
    private Uri source, uricropFile;
    private String cropTempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cropHeadIcon.jpg";

    public static MobIconFragment newInstance(Bundle bundle) {
        MobIconFragment fragment = new MobIconFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mob_icon;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            session = bundle.getString("session");
            headIcon = bundle.getString("headIcon");
            userName = bundle.getString("userName");
        }

        initToolbarNav(llBack);
        tvTitle.setText("个人资料");
        tvUserName.setText(userName);
        LatteLogger.d("ddddddddddd",headIcon);

        if (!"".equals(headIcon)) {
            Glide.with(_mActivity).load(headIcon).into(ivCircle);
        }else {
            ivCircle.setImageResource(R.drawable.selector_men);
        }
    }


    @OnClick({R.id.iv_circle, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_circle:
                Crop.pickImage(getContext(), findFragment(MobIconFragment.class));
                break;
            case R.id.btn_sure:
                LatteLogger.d("source================>" + source);
                if (source != null) {
                    getUploadFile(source);
                } else {
                    _mActivity.onBackPressed();
                }
                break;
        }
    }

    private void getUploadFile(Uri source) {
        File file = Utils.UriToFile(source);
        String url = Constants.UploadFile + session;
        EasyHttp.post(url)
                .params("uploadtype", 1 + "")
                .params("image", file, file.getName(), null)
                .accessToken(true)
                .timeStamp(true)
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
                        ProgressUtils.disLoadView(_mActivity, 0);
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("uploadImage", s);
                        if (s.contains("Invalid Session.")||s.contains(sessionE)) {
                            start(LoginFragment.newInstance(""));
                            ToastUtil.getInstance().showToast(sessionPastDue);
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.getInt("Status") == 0) {
                                    JSONArray data = jsonObject.getJSONArray("Data");
                                    ToastUtil.getInstance().showToast(jsonObject.getString("Msg"));
                                    LatteLogger.d("datadata===============" + data.get(0));
                                    Event<Object> event = new Event<Object>(UPDATA_TO_ME, data.get(0));
                                    EventBusUtil.sendEvent(event);
                                }
                                _mActivity.onBackPressed();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            source = result.getData();
            beginCrop(source);
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        LatteLogger.d("beginCrop=============" + source);
        uricropFile = Uri.parse("file://" + cropTempPath);
        Crop.of(source, uricropFile).asSquare().start(getContext(), findFragment(MobIconFragment.class));
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            source = Crop.getOutput(result);
            LatteLogger.d("handleCrop=============" + source);
            ivCircle.setImageURI(source);
        } else if (resultCode == Crop.RESULT_ERROR) {
            ToastUtil.getInstance().showToast(Crop.getError(result).getMessage());
        }
    }

}

