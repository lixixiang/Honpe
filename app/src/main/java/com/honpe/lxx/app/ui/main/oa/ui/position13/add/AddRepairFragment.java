package com.honpe.lxx.app.ui.main.oa.ui.position13.add;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.adapter.RepairAddAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairAddBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Add_RESULT_BACK_DATA;

/**
 * FileName: AddRepairFragment
 * Author: asus
 * Date: 2020/8/31 15:45
 * Description: 新增维修单
 */
public class AddRepairFragment extends BaseBackFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.font_icon_right)
    FontTextView4 fontIconRight;

    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_theme_title)
    TextView tvThemeTitle;
    @BindView(R.id.tv_theme_tips)
    TextView tvThemeTips;
    @BindView(R.id.tv_theme_add)
    TextView tvThemeAdd;

    private String title;
    private String[] strRadio = {"即时", "一天", "一月",};
    private String[] strRadio2 = {"半天", "一周", "其他"};
    private String[] strTitle2 = {"报修名称", "详细内容", "详细地点", "损坏原因"};
    private String[] stringHint = {"请填写报修名称", "请填写安装/维修项目详细内容", "请填写安装/维修项目详细地点", "请填写安装/维修项目详细原因"};
    List<MultiItemBean> list = new ArrayList<>();
    RepairAddBean repairBean;
    RepairBean.DataEntity entity;
    List<RepairAddBean.RepairBillDetailsModelEntity> listModelEntity = new ArrayList<>();
//    BaseRadioGridAdapter adapter;
//    BaseTaskAdapter taskAdapter;

    public static AddRepairFragment newInstance(String title, RepairBean.DataEntity entity) {
        AddRepairFragment fragment = new AddRepairFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putSerializable("entity", entity);
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

        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            entity = (RepairBean.DataEntity) bundle.getSerializable("entity");
            tvTitle.setText(title + getString(R.string.apply));
        }


        tvEnd.setVisibility(View.VISIBLE);
        if (entity != null) {
            tvEnd.setText("修改");
        }else {
            tvEnd.setText("提交");
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initRecyclerData();
    }

    private void initRecyclerData() {
        final RepairAddAdapter multipleItemAdapter = new RepairAddAdapter(getData(), entity);
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                return list.get(position).getSpanSize();
            }
        });

        recyclerView.setAdapter(multipleItemAdapter);
    }

    private List<MultiItemBean> getData() {
        MultiItemBean item1 = new MultiItemBean();
        item1.setItemType(MultiItemBean.TEXT_RADIO);
        item1.setSpanSize(3);
        item1.setStrTitle("安装/维修");
        item1.setRadioContent1("内部");
        item1.setRadioContent2("外部");
        list.add(item1);
        for (int i = 0; i < 3; i++) {
            list.add(new MultiItemBean(MultiItemBean.TEXT, "要求时限", 1));
            list.add(new MultiItemBean(MultiItemBean.RADIO, strRadio[i], 1));
            list.add(new MultiItemBean(MultiItemBean.RADIO, strRadio2[i], 1));
        }

        list.add(new MultiItemBean(MultiItemBean.TEXT, "安装/维修任务", 3));
        for (int i = 0; i < strTitle2.length; i++) {
            MultiItemBean item2 = new MultiItemBean();
            item2.setItemType(MultiItemBean.TEXT_EDIT);
            item2.setSpanSize(3);
            item2.setStrTitle(strTitle2[i]);
            item2.setHint(stringHint[i]);
            list.add(item2);
        }
        return list;
    }


    @OnClick(R.id.tv_end)
    public void onViewClicked() {
            AddRepair();
    }

    private void AddRepair() {
        String requestHead = "";

        if (entity != null) {
            requestHead = Constants.MOB_MINE;
            repairBean.set维修单号(entity.get维修单号());
            if ("[]".equals(GsonBuildUtil.GsonBuilder(repairBean.getRepairBillDetailsModel()))) {
                listModelEntity.clear();
                for (int i = 0; i < entity.getRepairBillDetailsModel().size(); i++) {
                   RepairBean.DataEntity.RepairBillDetailsModelEntity test = entity.getRepairBillDetailsModel().get(i);
                    RepairAddBean.RepairBillDetailsModelEntity modelEntity = new RepairAddBean.RepairBillDetailsModelEntity();
                    modelEntity.set完成时间(test.get完成时间());
                    modelEntity.set问题描述(test.get问题描述());
                    modelEntity.set位置(test.get位置());
                    modelEntity.set要求时限(test.get要求时限());
                    modelEntity.set维修人(test.get维修人());
                    modelEntity.set序号(test.get序号());
                    listModelEntity.add(modelEntity);
                }
                repairBean.setRepairBillDetailsModel(listModelEntity);
            }
        } else {
            requestHead =Constants.REPAIR_APPROVAL_ADD;
        }
        LatteLogger.d("submit", GsonBuildUtil.NullToString().toJson(repairBean));
        EasyHttp.post(requestHead + session)
                .retryCount(0)
                .upJson(GsonBuildUtil.NullToString().toJson(repairBean))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testJsonData", s);
                        try {
                            JSONObject object = new JSONObject(s);
                            EventBusUtil.sendEvent(new Event(Add_RESULT_BACK_DATA));
                            ToastUtil.getInstance().showToast(object.getString("Msg"));
                            _mActivity.onBackPressed();
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
            case FinalClass.Add_REPAIR_LIST_TOAST:
                repairBean = (RepairAddBean) event.getData();
                newModeEntity();
                break;
        }
    }

    private void newModeEntity() {
        listModelEntity.clear();

        repairBean.set验收人员("");
        repairBean.set电工鉴定("");
        repairBean.set鉴定结果("");
        repairBean.set确认时间("");
        repairBean.set验收结果("");
        repairBean.setF_Evaluate("");
        repairBean.setF_AdminConfirmer("");
        repairBean.setF_DepartmentHead("");
        repairBean.set确认人("");
        repairBean.setF_AdminDepart("");
        repairBean.set核准人("");
        repairBean.set行政审批("");
        repairBean.set维修单号(repairBean.get维修单号());
        repairBean.set维修类型(repairBean.get维修类型());
        repairBean.set验收人员(repairBean.get验收人员());
        repairBean.set电工鉴定(repairBean.get电工鉴定());
        repairBean.set鉴定结果(repairBean.get鉴定结果());
        repairBean.set确认时间(repairBean.get确认时间());
        repairBean.set验收结果(repairBean.get验收结果());
        repairBean.setF_Evaluate(repairBean.getF_Evaluate());
        repairBean.setF_AdminConfirmer(repairBean.getF_AdminConfirmer());
        repairBean.setF_DepartmentHead(repairBean.getF_DepartmentHead());
        repairBean.set确认人(repairBean.get确认人());
        repairBean.setF_AdminDepart(repairBean.getF_AdminDepart());
        repairBean.set核准人(repairBean.get核准人());
        repairBean.set行政审批(repairBean.get行政审批());
        repairBean.set报修原因(repairBean.get报修原因());
        repairBean.setF_TypeContext(repairBean.getF_TypeContext());
        repairBean.set变更时间(repairBean.get变更时间());
        for (int i = 0; i < repairBean.getRepairBillDetailsModel().size(); i++) {
            RepairAddBean.RepairBillDetailsModelEntity test = repairBean.getRepairBillDetailsModel().get(i);
            RepairAddBean.RepairBillDetailsModelEntity modelEntity = new RepairAddBean.RepairBillDetailsModelEntity();
            modelEntity.set完成时间(test.get完成时间());
            modelEntity.set问题描述(test.get问题描述());
            modelEntity.set位置(test.get位置());
            modelEntity.set要求时限(test.get要求时限());
            modelEntity.set维修人(test.get维修人());
            modelEntity.set序号(test.get序号());
            listModelEntity.add(modelEntity);
        }
        repairBean.setRepairBillDetailsModel(listModelEntity);
        LatteLogger.d("repairBean", GsonBuildUtil.NullToString().toJson(repairBean));
    }
}















