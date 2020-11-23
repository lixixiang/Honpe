package com.honpe.lxx.app.ui.main.oa.ui.position7;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position7.adapter.MyApproveAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position7.bean.ApproveBean;
import com.honpe.lxx.app.ui.main.oa.ui.position7.bean.ApproveHRBean;
import com.honpe.lxx.app.ui.main.oa.ui.position7.detail.ApproveDetailFragment;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.JsonFormData;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/21 14:09
 * @Author: 李熙祥
 * @Description: java类作用描述 审批
 */
public class ApproveFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    String title;
    String[] approves = {"请假", "外出", "出差", "䃼卡", "加班"};
    int[] Icons = {R.mipmap.ic_approve_oa2, R.mipmap.ic_approve_oa4, R.mipmap.ic_approve_oa3, R.mipmap.ic_approve_oa6, R.mipmap.ic_approve_oa5};
    List<ApproveBean> mList = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static ApproveFragment newInstance(String title) {
        ApproveFragment fragment = new ApproveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initData();

        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            tvTitle.setText(title);
        }
        initToolbarNav(llBack);

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initWEB();
    }

    private void initWEB() {
        String strEmployeeId = (String) MyApplication.get(_mActivity, "EmployeeId", "");
        Date date = new Date();
        String curDate = format.format(date);

        String strStartDate = DateUtil.getSpecifiedMonthAfter(curDate, -2, "yyyy-MM-dd HH:mm:ss");
        String strEndDate = DateUtil.getSpecifiedMonthAfter(curDate, 1, "yyyy-MM-dd HH:mm:ss");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("EmployeeId", strEmployeeId);
        jsonObject.addProperty("queryJson", JsonFormData.showTaskList(strStartDate, strEndDate));
        jsonObject.addProperty("categoryId", "2");

        LatteLogger.d("obj", GsonBuildUtil.GsonBuilder(jsonObject));
        disposable = EasyHttp.post(Constants.WEB_LIST)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
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

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d("result", s);
                        try {
                            LatteLogger.d("result", GsonBuildUtil.GsonBuilder(new JSONObject(s)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ApproveHRBean bean = Convert.fromJson(s, ApproveHRBean.class);
                        if (bean.getCode() == 200) {
                            mList.clear();
                            recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                            MyApproveAdapter adapter = new MyApproveAdapter(getListData(bean));
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    startForResult(ApproveDetailFragment.newInstance(mList.get(position)), 200);
                                }
                            });
                        }
                    }
                });
    }

    private List<ApproveBean> getListData(ApproveHRBean bean) {
        Map<String, List<ApproveBean.DataBean>> maps = new HashMap<>();

        for (int i = 0; i < bean.getData().size(); i++) {
            if (bean.getData().get(i).getHR_AskforLeave() != null) {
                List<ApproveBean.DataBean> listLeave = maps.get("HR_AskforLeave");
                if (listLeave == null) {
                    listLeave = new ArrayList<>();
                }
                for (int j = 0; j < bean.getData().get(i).getHR_AskforLeave().size(); j++) {
                    ApproveHRBean.DataBean.HRAskforLeaveBean childBean = bean.getData().get(i).getHR_AskforLeave().get(j);
                    ApproveBean.DataBean HRBusBean = new ApproveBean.DataBean();
                    HRBusBean.setRqtby(childBean.getRqtby());
                    HRBusBean.setId(childBean.getId());
                    HRBusBean.setRettime(childBean.getRettime());
                    HRBusBean.setDpt(childBean.getDpt());
                    HRBusBean.setLevel(childBean.getLevel());
                    HRBusBean.setCatorgry(childBean.getCatorgry());
                    HRBusBean.setStarttime(childBean.getStarttime());
                    HRBusBean.setEndtime(childBean.getEndtime());
                    HRBusBean.setCounthours(childBean.getCounthours());
                    HRBusBean.setReason(childBean.getReason());
                    HRBusBean.setHoliday(childBean.getHoliday());
                    HRBusBean.setGuid(childBean.getLguid());
                    HRBusBean.setF_encode(childBean.getF_encode());
                    HRBusBean.setUserName(childBean.getF_realname());
                    HRBusBean.setTeam(childBean.getF_fullname());
                    HRBusBean.setTaskId(childBean.getF_taskid());
                    HRBusBean.setF_id(childBean.getF_id());
                    listLeave.add(HRBusBean);
                }
                maps.put("HR_AskforLeave", listLeave);
            } else if (bean.getData().get(i).getHR_GoOut() != null) {
                List<ApproveBean.DataBean> listGoOut = maps.get("HR_GoOut");
                if (listGoOut == null) {
                    listGoOut = new ArrayList<>();
                }
                for (int j = 0; j < bean.getData().get(i).getHR_GoOut().size(); j++) {
                    ApproveHRBean.DataBean.HRGoOutBean childBean = bean.getData().get(i).getHR_GoOut().get(j);
                    ApproveBean.DataBean HRBusBean = new ApproveBean.DataBean();
                    HRBusBean.setRqtby(childBean.getRqtby());
                    HRBusBean.setId(childBean.getId());
                    HRBusBean.setRettime(childBean.getRettime());
                    HRBusBean.setDpt(childBean.getDpt());
                    HRBusBean.setLevel(childBean.getLevel());
                    HRBusBean.setCatorgry(childBean.getCatorgry());
                    HRBusBean.setStarttime(childBean.getStarttime());
                    HRBusBean.setEndtime(childBean.getEndtime());
                    HRBusBean.setCountday(childBean.getCountday());
                    HRBusBean.setReason(childBean.getReason());
                    HRBusBean.setGuid(childBean.getCguid());
                    HRBusBean.setF_encode(childBean.getF_encode());
                    HRBusBean.setUserName(childBean.getF_realname());
                    HRBusBean.setTeam(childBean.getF_fullname());
                    HRBusBean.setTaskId(childBean.getF_taskid());
                    HRBusBean.setF_id(childBean.getF_id());
                    listGoOut.add(HRBusBean);
                }
                maps.put("HR_GoOut", listGoOut);
            } else if (bean.getData().get(i).getHR_BusinessTravel() != null) {
                List<ApproveBean.DataBean> listBus = maps.get("HR_BusinessTravel");
                if (listBus == null) {
                    listBus = new ArrayList<>();
                }
                for (int j = 0; j < bean.getData().get(i).getHR_BusinessTravel().size(); j++) {
                    ApproveHRBean.DataBean.HRBusinessTravelBean childBean = bean.getData().get(i).getHR_BusinessTravel().get(j);
                    ApproveBean.DataBean HRBusBean = new ApproveBean.DataBean();
                    HRBusBean.setRqtby(childBean.getRqtby());
                    HRBusBean.setId(childBean.getId());
                    HRBusBean.setRettime(childBean.getRettime());
                    HRBusBean.setDpt(childBean.getDpt());
                    HRBusBean.setLevel(childBean.getLevel());
                    HRBusBean.setCatorgry(childBean.getCatorgry());
                    HRBusBean.setStarttime(childBean.getStarttime());
                    HRBusBean.setEndtime(childBean.getEndtime());
                    HRBusBean.setAddress(childBean.getAddress());
                    HRBusBean.setCountday(childBean.getCountday());
                    HRBusBean.setReason(childBean.getReason());
                    HRBusBean.setF_encode(childBean.getF_encode());
                    HRBusBean.setGuid(childBean.getGguid()); //区别
                    HRBusBean.setUserName(childBean.getF_realname());
                    HRBusBean.setTeam(childBean.getF_fullname());
                    HRBusBean.setTaskId(childBean.getF_taskid());
                    HRBusBean.setF_id(childBean.getF_id());
                    listBus.add(HRBusBean);
                }
                maps.put("HR_BusinessTravel", listBus);
            }
        }

        for (String key : maps.keySet()) {
            if (key.equals("HR_AskforLeave") && !GsonBuildUtil.GsonBuilder(maps.get("HR_AskforLeave")).equals("[]")) {
                ApproveBean bean1 = new ApproveBean();
                bean1.setHRData(maps.get(key));
                bean1.setTitle(approves[0]);
                bean1.setIcons(Icons[0]);
                bean1.setTips(maps.get(key).size());
                bean1.setDate(DateUtil.getNewChatTime(DateUtil.setDate(format, maps.get(key).get(0).getRettime()).getTime()));
                mList.add(bean1);
            } else if (key.equals("HR_GoOut") && maps.get("HR_GoOut") != null && !GsonBuildUtil.GsonBuilder(maps.get("HR_GoOut")).equals("[]")) {
                ApproveBean bean2 = new ApproveBean();
                bean2.setHRData(maps.get(key));
                bean2.setTitle(approves[1]);
                bean2.setIcons(Icons[1]);
                bean2.setTips(maps.get(key).size());
                bean2.setDate(DateUtil.getNewChatTime(DateUtil.setDate(format, maps.get(key).get(0).getRettime()).getTime()));
                mList.add(bean2);
            } else if (key.equals("HR_BusinessTravel") && !GsonBuildUtil.GsonBuilder(maps.get("HR_BusinessTravel")).equals("[]")) {
                ApproveBean bean3 = new ApproveBean();
                bean3.setHRData(maps.get(key));
                bean3.setTitle(approves[2]);
                bean3.setIcons(Icons[2]);
                bean3.setTips(maps.get(key).size());
                bean3.setDate(DateUtil.getNewChatTime(DateUtil.setDate(format, maps.get(key).get(0).getRettime()).getTime()));
                mList.add(bean3);
            }
        }
        LatteLogger.d("testMListData", GsonBuildUtil.GsonBuilder(mList));
        return mList;
    }

    private void initData() {
        for (int i = 0; i < approves.length; i++) {
            ApproveBean bean = new ApproveBean();
            bean.setTitle(approves[i] + "审批");
            bean.setIcons(Icons[i]);
            mList.add(bean);
        }

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            initWEB();
        }
    }

}








