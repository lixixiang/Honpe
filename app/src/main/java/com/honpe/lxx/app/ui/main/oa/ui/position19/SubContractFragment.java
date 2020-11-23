package com.honpe.lxx.app.ui.main.oa.ui.position19;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position19.adapter.SubContractAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position19.bean.ProcureSureBean;
import com.honpe.lxx.app.ui.main.oa.ui.position19.bean.SubContractBean;
import com.honpe.lxx.app.ui.main.oa.ui.position19.fragment.ApproveSubContractFragment;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.BottomPopupOption;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.D;
import static com.honpe.lxx.app.api.FinalClass.ME_info;

/**
 * FileName: SubContractFragment
 * Author: asus
 * Date: 2020/8/14 22:33
 * Description: 委外加工查询
 */
public class SubContractFragment extends BaseBackFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String StartTime, OverTime, title;
    private BottomPopupOption bottomPopupOption;
    private SubContractAdapter adapter;
    private ProcureSureBean bean;
    private List<SubContractBean> mList = new ArrayList<>();
    private int[] icons = {
            R.mipmap.sub_contract01, R.mipmap.sub_contract02, R.mipmap.sub_contract03, R.mipmap.sub_contract04,
            R.mipmap.sub_contract05, R.mipmap.sub_contract06, R.mipmap.sub_contract07, R.mipmap.sub_contract08,
            R.mipmap.sub_contract09, R.mipmap.sub_contract10, R.mipmap.sub_contract11, R.mipmap.sub_contract12,
            R.mipmap.sub_contract13, R.mipmap.sub_contract14, R.mipmap.sub_contract15, R.mipmap.sub_contract16};


    public static SubContractFragment newInstance(String title) {
        SubContractFragment fragment = new SubContractFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, -50);
        StartTime = sf.format(calendar.getTime());
        OverTime = sf.format(new Date());
        calendar.clear();
        LatteLogger.d("时间", "开始时间===" + StartTime + "====结束时间===" + OverTime);
        bottomPopupOption = new BottomPopupOption(_mActivity);
        bottomPopupOption.setItemText("同意", "不同意");
        LatteLogger.d("netRequest", "StartTime===" + StartTime + "==OverTime==" + OverTime);

    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case ME_info:
            case D:
                netRequest();
                break;
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        netRequest();
    }


    private void netRequest() {
        disposable = EasyHttp.post(Constants.MANAGER_OUTSIDE_APPLYLIST + session)
                .params("StartTime", StartTime)
                .params("EndTime", OverTime)
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
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("netRequest", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                startWithPop(LoginFragment.newInstance(SubContractFragment.this.getTag()));
                            } else {
                                bean = Convert.fromJson(result, ProcureSureBean.class);
                                ivNoOrder.setVisibility(View.GONE);
                                LatteLogger.d("dddd", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getStatus() == -1) {
                                    ToastUtil.getInstance().showToast(bean.getMsg());
                                } else {
                                    if (bean.getData().size() == 0) {
                                        ivNoOrder.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                        return;
                                    }
                                    adapter = new SubContractAdapter(getJsonDatas(bean.getData()), icons);
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(new OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            start(ApproveSubContractFragment.newInstance(mList.get(position)));
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    List<ProcureSureBean.DataBean> linkedData;

    private List<SubContractBean> getJsonDatas(List<ProcureSureBean.DataBean> data) {
        mList.clear();
        LinkedHashMap<String, List<ProcureSureBean.DataBean>> maps = new LinkedHashMap<>();

        for (int i = 0; i < data.size(); i++) {
            String strDepart = data.get(i).getDeptName();
            List<ProcureSureBean.DataBean> listDay = maps.get(strDepart);
            if (listDay == null) {
                listDay = new ArrayList<>();
            }
            ProcureSureBean.DataBean item = new ProcureSureBean.DataBean();
            item.setOrderNo(data.get(i).getOrderNo());
            item.setApplyNo(data.get(i).getApplyNo());
            item.setApplyUserID(data.get(i).getApplyUserID());
            item.setApplyUserName(data.get(i).getApplyUserName());
            item.setDeptName(data.get(i).getDeptName());
            item.setGroupName(data.get(i).getGroupName());
            item.setApplyTimer(data.get(i).getApplyTimer());
            item.setOrderTimer(data.get(i).getOrderTimer());
            item.setOrderGroup(data.get(i).getOrderGroup());
            item.setOrderDeliveryDate(data.get(i).getOrderDeliveryDate());
            item.setOrderFollower(data.get(i).getOrderFollower());
            item.setOutWorkType(data.get(i).getOutWorkType());
            item.setApplyNotes(data.get(i).getApplyNotes());
            item.setSupplier(data.get(i).getSupplier());
            item.setAmount(data.get(i).getAmount());
            item.setDeliveryDate(data.get(i).getDeliveryDate());
            item.setPuConfirm(data.get(i).getPuConfirm());
            item.setPuConfirmTimer(data.get(i).getPuConfirmTimer());
            item.setConfirmResult(data.get(i).getConfirmResult());
            item.setConfirmNotes(data.get(i).getConfirmNotes());
            item.setCompletedFlow(data.get(i).getCompletedFlow());
            listDay.add(item);
            maps.put(strDepart, listDay);
        }

        for (String depart : maps.keySet()) {
            SubContractBean bean = new SubContractBean();
            bean.setDepart(depart);
            linkedData = maps.get(depart);
            bean.setTime(linkedData.get(linkedData.size() - 1).getApplyTimer());
            bean.setTips(linkedData.size() + 1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            if (bean.getDepart().equals(depart)) {
                List<SubContractBean.DataBean> subData = new ArrayList<>();
                for (int i = 0; i < linkedData.size(); i++) {
                    SubContractBean.DataBean item = new SubContractBean.DataBean();
                    item.setOrderNo(linkedData.get(i).getOrderNo());
                    item.setApplyNo(linkedData.get(i).getApplyNo());
                    item.setApplyUserID(linkedData.get(i).getApplyUserID());
                    item.setApplyUserName(linkedData.get(i).getApplyUserName());
                    item.setDeptName(linkedData.get(i).getDeptName());
                    item.setGroupName(linkedData.get(i).getGroupName());
                    item.setApplyTimer(linkedData.get(i).getApplyTimer());
                    item.setOrderTimer(linkedData.get(i).getOrderTimer());
                    item.setOrderGroup(linkedData.get(i).getOrderGroup());
                    item.setOrderDeliveryDate(linkedData.get(i).getOrderDeliveryDate());
                    item.setOrderFollower(linkedData.get(i).getOrderFollower());
                    item.setOutWorkType(linkedData.get(i).getOutWorkType());
                    item.setApplyNotes(linkedData.get(i).getApplyNotes());
                    item.setSupplier(linkedData.get(i).getSupplier());
                    item.setAmount(linkedData.get(i).getAmount());
                    item.setDeliveryDate(linkedData.get(i).getDeliveryDate());
                    item.setPuConfirm(linkedData.get(i).getPuConfirm());
                    item.setPuConfirmTimer(linkedData.get(i).getPuConfirmTimer());
                    item.setConfirmResult(linkedData.get(i).getConfirmResult());
                    item.setConfirmNotes(linkedData.get(i).getConfirmNotes());
                    item.setCompletedFlow(linkedData.get(i).getCompletedFlow());
                    subData.add(item);
                }
                bean.setListBean(subData);
            }
            mList.add(bean);
        }

        LatteLogger.d("ProcureSureBean", GsonBuildUtil.GsonBuilder(mList));
        return mList;
    }
}

