package com.honpe.lxx.app.ui.main.oa.ui.car_manager.child;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.adapter.CarInfoManagerAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.adapter.CarIntoManagerAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.entity.CarInfoEntity;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.entity.CarIntoEntity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;

/**
 * FileName: CarInfoFragment
 * Author: asus
 * Date: 2020/10/22 12:05
 * Description:
 */
public class CarInfoFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private String strUserName, strCardNo;

    Bundle bundle;

    public static CarInfoFragment newInstance(Bundle b) {
        CarInfoFragment fragment = new CarInfoFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initView() {
        bundle = getArguments();
        initData();
        refreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("EmployeeId", EmployeeId);
        JsonObject object = new JsonObject();
        strUserName = bundle.getString("UserName");
        strCardNo = bundle.getString("CardNo");
        if (bundle.getInt("curPos") == 0) {
            object.addProperty("UserName", strUserName);
            object.addProperty("CardNo", strCardNo);
            String st = String.valueOf(object).replace("\"", "\"");
            jsonObject.addProperty("queryJson", st);
            net1(jsonObject);

        } else {
            object.addProperty("StartTime", bundle.getString("StartTime") + " 00:00:00");
            object.addProperty("EndTime", bundle.getString("EndTime") + " 23:59:59");
            object.addProperty("CarUser", strUserName);
            object.addProperty("CardNo", strCardNo);
            String st = String.valueOf(object).replace("\"", "\"");
            jsonObject.addProperty("queryJson", st);
            net2(jsonObject);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 30) {
                    hideSoftKeyBoard();
                }
            }
        });
    }


    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    initData();
                    refreshLayout.setRefreshing(false);
                    EventBusUtil.sendEvent(new Event(FinalClass.REFRESH_CAR_INFO_INTO));
                }
            }
        }, 2000);
    }

    private void net1(JsonObject jsonObject) {
        disposable = EasyHttp.post(Constants.AppGetParkCard)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .retryCount(0)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testCarManager", s);
                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                        if (bundle.getInt("curPos") == 0) {
                            CarInfoEntity carInfoEntity = Convert.fromJson(s, CarInfoEntity.class);
                            LatteLogger.d("ddddddd", GsonBuildUtil.GsonBuilder(carInfoEntity));
                            CarInfoManagerAdapter adapter = new CarInfoManagerAdapter(carInfoEntity.getData());
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
    }

    private void net2(JsonObject jsonObject) {
        disposable = EasyHttp.post(Constants.AppCarInOut)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .retryCount(0)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testCarManager", s);
                        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));

                        CarIntoEntity entity = Convert.fromJson(s, CarIntoEntity.class);
                        LatteLogger.d("CarIntoEntity", GsonBuildUtil.GsonBuilder(entity));
                        CarIntoManagerAdapter adapter = new CarIntoManagerAdapter(entity.getData());
                        recyclerView.setAdapter(adapter);

                    }
                });
    }

}
