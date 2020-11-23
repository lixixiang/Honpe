package com.honpe.lxx.app.ui.fragment.d_package.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.bean.UpdateBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.UPDATA_CLIENT;
import static com.honpe.lxx.app.api.FinalClass.UPDATA_NONEED;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 11:11
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class SettingFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lv)
    ListView lv;

    private SimpleAdapter dapter;
    private String[] titles = {"意见反馈", "检测新版本", "修改密码", "退出登录"};
    private String mTitle;
    //自动更新
    private UpdateBean updateBean;

    public static SettingFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString("title");
            tvTitle.setText(mTitle);
        }
        initToolbarNav(llBack);
        List<Map<String, String>> imageList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", titles[i]);
            imageList.add(map);
        }
        //创建适配器
        dapter = new SimpleAdapter(_mActivity, imageList,
                R.layout.css_text_1, new String[]{"title"}, new int[]{R.id.tv_record_text});
        lv.setAdapter(dapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        start(FeedBackFragment.newInstance(titles[position]));
                        break;
                    case 1:
                        jsonData();
                        break;
                    case 2:
                        start(MobPassFragment.newInstance());
                        break;
                    case 3:
                        NormalDialog dialog = new NormalDialog(_mActivity);
                        dialog.isTitleShow(false)
                                .bgColor(Color.WHITE)
                                .cornerRadius(5)
                                .content("是否确定退出程序?")//
                                .contentGravity(Gravity.CENTER)//
                                .contentTextColor(Color.BLACK)//
                                .dividerColor(Color.parseColor("#222222"))//
                                .btnTextSize(15.5f, 15.5f)
                                .btnTextColor(getResources().getColor(R.color.google_red), getResources().getColor(R.color.google_blue))//
                                .btnPressColor(getResources().getColor(R.color.background_e))//
                                .widthScale(0.85f)//
                                .show();
                        dialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                MyApplication.clear(_mActivity);
                                EventBusUtil.sendEvent(new Event(FinalClass.EXIT_APP));
                                _mActivity.onBackPressed();
                                dialog.dismiss();
                            }
                        });
                        break;
                }
            }
        });
    }

    /**
     * 检测版本
     */
    private void jsonData() {
        EasyHttp.post(Constants.APPDownload)
                .params("app_ver", String.valueOf(Utils.getVersionCode(_mActivity)))
                .params("app_desc", String.valueOf(Utils.getVersionCode(_mActivity)))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (e.getCode() == 1009) {
                            new ConnNetworkState(_mActivity).checkNetworkState();
                        } else {
                            ToastUtil.getInstance().showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("progress", result);
                        updateBean = Convert.fromJson(result, UpdateBean.class);
                        LatteLogger.d("ddddddd", GsonBuildUtil.GsonBuilder(updateBean));
                        if (updateBean.getStatus() == 0) {
                            final int num = Integer.valueOf(updateBean.getData().getVer());
                            if (num == Utils.getVersionCode(_mActivity)) {
                                Log.i("version", "版本一致");
                                EventBusUtil.sendEvent(new Event(UPDATA_NONEED));
                            } else if (Utils.getVersionCode(_mActivity) < num) {
                                Log.i("version", "版本不一致");
                                EventBusUtil.sendEvent(new Event(UPDATA_CLIENT));
                            }
                        } else {
                            ToastUtil.getInstance().showToast(updateBean.getMsg());
                        }
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case UPDATA_NONEED:
                ToastUtil.getInstance().showToast("已经更新到最新版本：" + Utils.getVersionDes(_mActivity));
                break;
        }
    }
}
