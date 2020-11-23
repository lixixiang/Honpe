package com.honpe.lxx.app.ui.main.appoint;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.fragment.d_package.bean.LoginBean;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.appoint.adapter.OrderAdapter;
import com.honpe.lxx.app.ui.main.appoint.add.AppointFragment;
import com.honpe.lxx.app.ui.main.appoint.bean.AppointBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.UserType;


/**
 * created by lxx at 2019/11/20 16:26
 * 描述:访客预约
 */
public class AppointHomeFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_theme_title)
    TextView tvThemeTitle;
    @BindView(R.id.tv_theme_tips)
    TextView tvThemeTips;
    @BindView(R.id.tv_theme_add)
    TextView tvThemeAdd;
    @BindView(R.id.re_none_order)
    RelativeLayout reNoneOrder;

    private int userType;
    private String result, mStartTime, mEndTime, strUrl;
    private OrderAdapter adapter;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    AppointBean.DataBean bean;

    public static AppointHomeFragment newInstance() {
        AppointHomeFragment fragment = new AppointHomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Utils.checkSession(this, session);
        tvTitle.setText("访客预约");
        initToolbarNav(llBack);
        StringUtil.getUnOrder(tvThemeTitle,tvThemeTips,tvThemeAdd,"暂无"+tvTitle.getText().toString(),
                "点击下方或右上方按钮可申请"+tvTitle.getText().toString(),
                "申请"+tvTitle.getText().toString());
        mStartTime = DateUtil.advanceToDelayed(sdf, -30);
        mEndTime = DateUtil.advanceToDelayed(sdf, 30);
        tvEnd.setVisibility(View.VISIBLE);
        userType = (int) MyApplication.get(_mActivity, UserType, -1);
        tvEnd.setText(getString(R.string.apply));

        if (userType == 0) { //管理者
            strUrl = Constants.MANAGER_LIST;
        } else if (userType == 1) { //本人
            strUrl = Constants.SHOW_APPOINT_LIST;
        }
        getRequestData(session);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getRequestData(String session) {
       disposable = EasyHttp.post(strUrl + session)
                .params("StartTime", mStartTime)
                .params("EndTime", mEndTime)
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
                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getInt("Status") == -1) {
                                startWithPop(LoginFragment.newInstance(getTag()));
                                ToastUtil.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final AppointBean bean = Convert.fromJson(s, AppointBean.class);
                                mList.clear();
                                if (bean.getStatus() == 0) {
                                    if (bean.getData().size() > 0) {
                                        LatteLogger.d("AppointHome",GsonBuildUtil.GsonBuilder(bean));
                                        recyclerView.setVisibility(View.VISIBLE);
                                        reNoneOrder.setVisibility(View.GONE);
                                        adapter = new OrderAdapter(getData(bean.getData()), userType, session);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                        recyclerView.setAdapter(adapter);
                                    } else {
                                        recyclerView.setVisibility(View.GONE);
                                        reNoneOrder.setVisibility(View.VISIBLE);
                                    }

                                    adapter.notifyDataSetChanged();
                                } else {
                                    ToastUtil.getInstance().showToast(bean.getMsg());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    List<AppointBean> mList = new ArrayList<>();
    List<AppointBean.DataBean> listData = new ArrayList<>();

    private List<AppointBean> getData(List<AppointBean.DataBean> dataBeans) {
        Map<String, List<AppointBean.DataBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < dataBeans.size(); i++) {
            String key = dataBeans.get(i).getVisitor() + " "
                    + dataBeans.get(i).getStaffDept();
            listData = maps.get(key);
            if (listData == null) {
                listData = new ArrayList<>();
            }
            AppointBean.DataBean bean = dataBeans.get(i);
            listData.add(bean);
            maps.put(key, listData);
        }
        for (String keys : maps.keySet()) {
            AppointBean bean = new AppointBean();
            bean.setMsg(keys);
            listData = maps.get(keys);
            bean.setData(listData);
            mList.add(bean);
        }
        LatteLogger.d(GsonBuildUtil.GsonBuilder(mList));
        return mList;
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.ME_info:
                LoginBean bean = (LoginBean) event.getData();
                session = bean.getLogonUser().getKeyValue();
                getRequestData(session);
                adapter.notifyDataSetChanged();
                break;
            case FinalClass.REFRESH_DATA_APPOINT:
                getRequestData(session);
                break;
        }
    }


    @OnClick({R.id.tv_end,R.id.re_none_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
            case R.id.re_none_order:
                //访客预约
                startForResult(AppointFragment.newInstance(bean), 110);
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        session = (String) MyApplication.get(_mActivity, FinalClass.Session, "");
        if (requestCode == 110 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            LatteLogger.d("end", result);
            getRequestData(session);
        } else if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            String start = sf.format(DateUtil.setDate(sf, result));
            mStartTime = start;
            LatteLogger.d("start", result);
            getRequestData(session);
        } else if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            result = data.getString("result");
            String end = sf.format(DateUtil.setDate(sf, result));
            mEndTime = end;
            getRequestData(session);
        } else if (requestCode == 1111 && resultCode == RESULT_OK && data != null) {
            getRequestData(session);
        }
    }

}





