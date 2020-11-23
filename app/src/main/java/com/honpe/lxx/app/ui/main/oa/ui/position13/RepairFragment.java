package com.honpe.lxx.app.ui.main.oa.ui.position13;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.adapter.RepairAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position13.add.AddRepairFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.detail.RepairDetailFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.showProve.ShowSignFragment;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Add_RESULT_BACK_DATA;
import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: RepairFragment
 * Author: asus
 * Date: 2020/8/31 9:20
 * Description: 维修主界面
 */
public class RepairFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private String title;
    private String strPermission, curMonth, firstDay, lastDay, session;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public int oneDay = 0; //这个初始日子
    List<RepairBean.DataEntity> Data = new ArrayList<>();
    RepairAdapter adapter;
    private String[] strSelector = {"修改订单", "删除订单"};

    public static RepairFragment newInstance(String title) {
        RepairFragment fragment = new RepairFragment();
        fragment.title = title;
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_total_query;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            tvTitle.setText(title);
        }

        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText(getString(R.string.apply));
        session = (String) MyApplication.get(_mActivity, Session, "");
        firstDay = DateUtil.getFirstDayOfMonth(format);
        lastDay = DateUtil.getLastDayOfMonth(format);
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        refreshLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                start(AddRepairFragment.newInstance(title, null));
                break;
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        submitPermission();
        getMastRequestData();
    }

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(format, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(format, oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        LatteLogger.d("myDay", "firstDay==" + firstDay + "==lastDay==" + lastDay);
        if (tvDate.getText().toString().equals(curMonth)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setClickable(true);
        }
        getMastRequestData();
    }

    private void getMastRequestData() {
        JsonObject param = new JsonObject();
        param.addProperty("strWhere", "where 申请日期>=\'" + firstDay + " 00:01\'" + "and 申请日期<=\'" + lastDay + " 23:59\'");

      disposable =  EasyHttp.post(Constants.QUERY_THE_MAINTENANCE_LIST + session)
                .upJson(GsonBuildUtil.GsonBuilder(param))
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
                        ToastUtil.getInstance().showToast(e.getMessage());
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);

                        try {
                            JSONObject obj = new JSONObject(String.valueOf(s));
                            if (obj.getInt("Status") == -1) {
                                startWithPop(LoginFragment.newInstance(RepairFragment.this.getTag()));
                                ToastUtil.getInstance().showToast(obj.getString("Msg"));
                            } else {
                                Gson gson = new Gson();
                                RepairBean repairBean = Convert.fromJson(s, RepairBean.class);
                                LatteLogger.d("repairBeanResult", GsonBuildUtil.GsonBuilder(repairBean));
                                recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                                Utils.reverse(repairBean.getData());
                                adapter = new RepairAdapter(repairBean.getData(), strPermission);
                                recyclerView.setAdapter(adapter);
                                adapter.addChildClickViewIds(R.id.tv_status);

                                adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        TextView tvStatus = view.findViewById(R.id.tv_status);
                                        start(ShowSignFragment.newInstance(tvStatus.getText().toString(), repairBean.getData().get(position)));
                                    }
                                });

                                adapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        start(RepairDetailFragment.newInstance(title, repairBean.getData().get(position)));
                                    }
                                });

                                adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                        TextView tvStatus = view.findViewById(R.id.tv_status);
                                       if (!tvStatus.getText().toString().equals("订单完成")){
                                           if (userName.equals(repairBean.getData().get(position).get申请人()) && strPermission.contains("\"查询\",\"新增\",\"修改\"")) {
                                               NormalListDialog normalListDialog = new NormalListDialog(_mActivity, strSelector);
                                               normalListDialog.title("请选择").layoutAnimation(null).show();
                                               normalListDialog.setOnOperItemClickL((parent, view1, position1, id) -> {
                                                   switch (position1) {
                                                       case 0:
                                                           start(AddRepairFragment.newInstance(title, repairBean.getData().get(position)));
                                                           break;
                                                       case 1:
                                                           final NormalDialog dialog1 = new NormalDialog(_mActivity);
                                                           dialog1.isTitleShow(false)
                                                                   .content("是否要删除" + "\"" + repairBean.getData().get(position).get维修单号() + "\"" + "的维修单?")
                                                                   .cornerRadius(5)//
                                                                   .contentGravity(Gravity.CENTER)
                                                                   .btnTextColor(Color.RED, Color.BLUE)
                                                                   .btnPressColor(Color.LTGRAY)
                                                                   .show();
                                                           dialog1.setOnBtnClickL(new OnBtnClickL() {
                                                               @Override
                                                               public void onBtnClick() { //left
                                                                   dialog1.dismiss();
                                                               }
                                                           }, new OnBtnClickL() {
                                                               @Override
                                                               public void onBtnClick() {//right
                                                                   deleteOrder(session, repairBean.getData().get(position).get维修单号());
                                                                   dialog1.dismiss();
                                                               }
                                                           });
                                                           break;
                                                   }
                                                   normalListDialog.dismiss();
                                               });
                                           } else {
                                               ToastUtil.getInstance().showToast("只能删除自己的维修单!");
                                           }
                                       } else {
                                           ToastUtil.getInstance().showToast("维修单已经成功不可以删除!");
                                       }


                                        return true;
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void deleteOrder(String session, String repairId) {
        EasyHttp.post(Constants.DELETE_REPAIR_NO + session)
                .params("维修单号", repairId)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testDeleteResult", s);
                        try {
                            JSONObject object = new JSONObject(s);
                            ToastUtil.getInstance().showToast(object.getString("Msg"));
                            getMastRequestData();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
            case Add_RESULT_BACK_DATA:
                getMastRequestData();
                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout.isRefreshing()) {
                    getMastRequestData();
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 2000);
    }

    private void submitPermission() {
        EasyHttp.post(Constants.REPAIR_APPROVAL_PERMISSION + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("submitPermission", s);
                        strPermission = s;
                    }
                });
    }
}












