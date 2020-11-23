package com.honpe.lxx.app.ui.main.oa.ui.position7.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position7.bean.ApproveBean;
import com.honpe.lxx.app.ui.main.oa.ui.position7.detail.adapter.ApproveDetailAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position7.detail.bean.ApproveDetailBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.JsonFormData;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/23 13:47
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveDetailFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Bundle bundle = new Bundle();
    ApproveDetailAdapter adapter;
    ApproveBean bean;
    List<ApproveDetailBean> mApproveDetailList = new ArrayList<>();

    List<ApproveDetailBean.DetailDataBean> listData;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int curPos;
    String[] stringItems = {"同意", "不同意"};

    String strType;

    public static ApproveDetailFragment newInstance(ApproveBean bean) {
        ApproveDetailFragment fragment = new ApproveDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ApproveBean", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        bundle = getArguments();
        if (bundle != null) {
            bean = (ApproveBean) bundle.getSerializable("ApproveBean");
            tvTitle.setText(bean.getTitle() + "审批");
            if (bean.getTitle().equals("请假")) {
                strType = HRtypes[0];
            } else if (bean.getTitle().equals("出差")) {
                strType = HRtypes[1];
            } else if (bean.getTitle().equals("外出")) {
                strType = HRtypes[2];
            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new ApproveDetailAdapter(getData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.addChildClickViewIds(R.id.tv_status);
        LatteLogger.d("bean", GsonBuildUtil.GsonBuilder(bean));
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                curPos = position;

                ApproveDetailBean itemApprove = mApproveDetailList.get(position);
                ActionSheetDialog dialog = new ActionSheetDialog(_mActivity, stringItems, view);
                dialog.lvBgColor(Color.WHITE)
                        .cancelText(Color.RED);
                dialog.isTitleShow(false).show();
                LatteLogger.d("testResult", GsonBuildUtil.GsonBuilder(itemApprove));
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            getApproveResult(position, itemApprove);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 点击审批流程
     */
    private void getApproveResult(int pos, ApproveDetailBean item) throws JSONException, ParseException {
        for (int i = 0; i < item.getDataList().size(); i++) {
            ApproveDetailBean.DetailDataBean mItem = item.getDataList().get(i);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("F_Id", mItem.getF_id());
            jsonObject.addProperty("strType", strType);
            if (bean.getTitle().equals("请假")) {
                jsonObject.addProperty("formData", JsonFormData.LeaveFormData(RqtBy, mItem.getId(), Dpt, mItem.getRettime(), mItem.getCatorgry(),
                        mItem.getLevel(), mItem.getStartTime(), mItem.getEndTime(),
                        mItem.getTimeLag(), mItem.getReason(), mItem.getGuid()));
            } else if (bean.getTitle().equals("出差")) {
                jsonObject.addProperty("formData", JsonFormData.DomFormData(RqtBy, mItem.getId(), mItem.getStartTime(), Dpt,
                        mItem.getLevel(), mItem.getCatorgry(), mItem.getAddress(), mItem.getStartTime(), mItem.getEndTime(),
                        mItem.getTimeLag(), mItem.getReason(), mItem.getGuid()));
            } else if (bean.getTitle().equals("外出")) {
                jsonObject.addProperty("formData", JsonFormData.GoOutFormData(
                        RqtBy, mItem.getId(), mItem.getStartTime(), Dpt, mItem.getLevel(), mItem.getCatorgry(),
                        mItem.getTimeLag(), mItem.getStartTime(),
                        mItem.getEndTime(), mItem.getReason(), mItem.getGuid()));
            }

            jsonObject.addProperty("F_TaskId", mItem.getF_taskId());
            jsonObject.addProperty("verifyType", pos + 1 + "");
            jsonObject.addProperty("description", stringItems[pos]);
            jsonObject.addProperty("EmployeeId", EmployeeId);
            LatteLogger.d("EasyHttp", GsonBuildUtil.GsonBuilder(jsonObject));
            EasyHttp.post(Constants.AppVerifyWorkFlow)
                    .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                    .execute(new SimpleCallBack<String>() {
                        @Override
                        public void onError(ApiException e) {

                        }

                        @Override
                        public void onSuccess(String s) {
                            try {
                                LatteLogger.d("AppVerifyWorkFlow", GsonBuildUtil.GsonBuilder(new JSONObject(s)));
                                JSONObject json = new JSONObject(s);
                                if (json.getInt("code") == 200) {
                                    ToastUtil.getInstance().showToast(json.getString("info"));
                                    Bundle bundle = new Bundle();
                                    bundle.putString("info", json.getString("info"));
                                    setFragmentResult(RESULT_OK, bundle);
                                } else {
                                    ToastUtil.getInstance().showToast(json.getString("info"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        LatteLogger.d("mApproveDetailList", mApproveDetailList.size() + "");

        if (mApproveDetailList.size() == 1) {
            mApproveDetailList.remove(item);
            adapter.notifyDataSetChanged();
            _mActivity.onBackPressed();
        } else {
            mApproveDetailList.remove(item);
            adapter.notifyDataSetChanged();
        }

    }

    private List<ApproveDetailBean> getData() {
        Map<String, List<ApproveDetailBean.DetailDataBean>> maps = new LinkedHashMap<>();

        for (int i = 0; i < bean.getHRData().size(); i++) {
            String key = bean.getHRData().get(i).getUserName() + " " + bean.getHRData().get(i).getTeam() + " " + bean.getHRData().get(i).getF_encode();

            listData = maps.get(key);
            if (listData == null) {
                listData = new ArrayList<>();
            }
            ApproveDetailBean.DetailDataBean detailBean = new ApproveDetailBean.DetailDataBean();

            detailBean.setStartTime(bean.getHRData().get(i).getStarttime());
            detailBean.setEndTime(bean.getHRData().get(i).getEndtime());
            detailBean.setReason(bean.getHRData().get(i).getReason());

            detailBean.setTimeLag(DateUtil.getTimePoor(sdf, bean.getHRData().get(i).getStarttime(), bean.getHRData().get(i).getEndtime()));
            LatteLogger.d("testDetail", detailBean.getTimeLag());
            detailBean.setAddress(bean.getHRData().get(i).getAddress());
            detailBean.setId(bean.getHRData().get(i).getId());
            detailBean.setF_id(bean.getHRData().get(i).getF_id());
            detailBean.setF_taskId(bean.getHRData().get(i).getTaskId());
            detailBean.setRettime(bean.getHRData().get(i).getRettime());
            detailBean.setCatorgry(bean.getHRData().get(i).getCatorgry());
            detailBean.setLevel(bean.getHRData().get(i).getLevel());
            detailBean.setGuid(bean.getHRData().get(i).getGuid());
            listData.add(detailBean);
            maps.put(key, listData);
        }

        for (String key : maps.keySet()) {
            ApproveDetailBean approveBean = new ApproveDetailBean();

            String[] temp = key.split(" ");
            approveBean.setName(temp[0]);
            approveBean.setDepart(temp[1]);
            approveBean.setF_encode(temp[2]);
            listData = maps.get(key);
            approveBean.setDataList(listData);
            mApproveDetailList.add(approveBean);
        }
        return mApproveDetailList;
    }
}






