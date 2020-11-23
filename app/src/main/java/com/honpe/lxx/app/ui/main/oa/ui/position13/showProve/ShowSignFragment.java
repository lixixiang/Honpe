package com.honpe.lxx.app.ui.main.oa.ui.position13.showProve;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.flyco.dialog.widget.NormalListDialog;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.adapter.RepairSubmitAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairAddBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.Add_RESULT_BACK_DATA;

/**
 * FileName: ShowSignFragment
 * Author: asus
 * Date: 2020/9/4 16:17
 * Description:不同的人进来审批  Add_RESULT_BACK_DATAv
 */
public class ShowSignFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public String title;
    List<MultiItemBean> list = new ArrayList<>();
    RepairBean.DataEntity entity;
    RepairAddBean modBean = new RepairAddBean();
    RepairSubmitAdapter adapter;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private TimePickerView pvTime1;
    List<RepairAddBean.RepairBillDetailsModelEntity> DetailEntities = new ArrayList<>();
    List<RepairBean.DataEntity.RepairBillDetailsModelEntity> ListData = new ArrayList<>();
    private String[] titles = {"维修单号", "确认人", "确认时间", "电工鉴定"};
    private String[] titles2 = {"维修单号", "完成时间"};
    private String[] titles3 = {"维修单号", "电工鉴定", "确认时间", "核准时间", "鉴定结果"};
    private String[] titles4 = {"维修单号", "审批时间", "处理意见"};
    private String[] titles5 = {"维修单号", "验收时间", "验收结果", "评价"};
    private String[] praises = {"很差", "一般", "满意", "非常满意"};

    public static ShowSignFragment newInstance(String title, RepairBean.DataEntity entity) {
        ShowSignFragment fragment = new ShowSignFragment();
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            tvTitle.setText(title);
            entity = (RepairBean.DataEntity) bundle.getSerializable("entity");
        }
        LatteLogger.d("entity", GsonBuildUtil.GsonBuilder(entity));
        initToolbarNav(llBack);
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setEnabled(false);
        tvEnd.setTextColor(Color.GRAY);
        tvEnd.setText("提交");
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        adapter = new RepairSubmitAdapter(title, getData());
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        recyclerView.setLayoutManager(manager);

        adapter.addChildClickViewIds(R.id.et_content);
        adapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                return list.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                EditText et = view.findViewById(R.id.et_content);

                if (title.contains("验收") && position == 3) {
                    selectedPriase(et);
                } else {
                    selectedTime(_mActivity, et);
                }
            }
        });
    }

    private void selectedPriase(EditText et) {
        NormalListDialog normalListDialog = new NormalListDialog(_mActivity, praises);
        normalListDialog.title("验收评价").layoutAnimation(null).show();
        normalListDialog.setOnOperItemClickL((parent, view1, position, id) -> {
            et.setText(praises[position]);
            entity.setF_Evaluate(et.getText().toString());
            AddNewData();
            normalListDialog.dismiss();
        });
    }

    private List<MultiItemBean> getData() {
        if (title.contains("维修")) {
            for (int i = 0; i < titles2.length; i++) {
                MultiItemBean bean = new MultiItemBean();
                bean.setItemType(MultiItemBean.TEXT_EDIT);
                bean.setSpanSize(3);
                bean.setStrTitle(titles2[i]);
                if (i == 0) {
                    bean.setContent(entity.get维修单号());
                } else {
                    bean.setContent1(entity.getRepairBillDetailsModel().get(0).get完成时间());
                }
                list.add(bean);
            }
        } else if (title.contains("部门")) {
            for (int i = 0; i < titles.length; i++) {
                MultiItemBean bean = new MultiItemBean();
                bean.setItemType(MultiItemBean.HEAD);
                bean.setSpanSize(3);
                bean.setStrTitle(titles[i]);
                bean.setContent(entity.get维修单号());
                bean.setContent1(entity.get申请人());
                bean.setContent2(entity.get电工鉴定());
                bean.setContent3(entity.get确认时间());
                list.add(bean);
            }
        } else if (title.contains("工务")) {
            for (int i = 0; i < titles3.length; i++) {
                MultiItemBean bean = new MultiItemBean();
                bean.setItemType(MultiItemBean.TEXT);
                bean.setSpanSize(3);
                bean.setStrTitle(titles3[i]);
                bean.setContent(entity.get维修单号());
                bean.setContent1(entity.get电工鉴定());
                bean.setContent2(entity.get确认时间());
                bean.setContent3(entity.get鉴定结果());
                bean.setContent4(entity.get核准时间());
                list.add(bean);
            }

        } else if (title.contains("行政")) {
            for (int i = 0; i < titles4.length; i++) {
                MultiItemBean bean = new MultiItemBean();
                bean.setItemType(MultiItemBean.RADIO);
                bean.setSpanSize(3);
                bean.setStrTitle(titles4[i]);
                bean.setContent(entity.get维修单号());
                bean.setContent1(entity.get行政审批时间());
                bean.setContent2(entity.getF_AdminDepart());
                list.add(bean);
            }
        } else if (title.contains("验收")) {
            for (int i = 0; i < titles5.length; i++) {
                MultiItemBean bean = new MultiItemBean();
                bean.setItemType(MultiItemBean.LINE);
                bean.setSpanSize(3);
                bean.setStrTitle(titles5[i]);
                bean.setContent(entity.get维修单号());
                bean.setContent1(entity.get验收时间());
                bean.setContent2(entity.get验收结果());
                bean.setContent3(entity.getF_Evaluate());
                list.add(bean);
            }
        }
        return list;
    }

    private void selectedTime(Context context, EditText et) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        pvTime1 = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                et.setText(DateUtil.getTime(sdf, date));
                DetailEntities.clear();
                if (title.contains("工务")) {
                    entity.set核准时间(et.getText().toString());
                } else if (title.contains("维修")) {
                    RepairBean.DataEntity.RepairBillDetailsModelEntity re = new RepairBean.DataEntity.RepairBillDetailsModelEntity();
                    re.set要求时限(entity.getRepairBillDetailsModel().get(0).get要求时限());
                    re.set维修人(userName);
                    re.set序号(entity.getRepairBillDetailsModel().get(0).get序号());
                    re.set要求时限(entity.getRepairBillDetailsModel().get(0).get要求时限());
                    re.set位置(entity.getRepairBillDetailsModel().get(0).get位置());
                    re.set问题描述(entity.getRepairBillDetailsModel().get(0).get问题描述());
                    re.set完成时间(et.getText().toString());
                    ListData.add(re);
                    entity.setRepairBillDetailsModel(ListData);
                } else if (title.contains("验收")) {
                    entity.set验收时间(et.getText().toString());
                } else if (title.contains("部门")) {
                    entity.set确认时间(et.getText().toString());
                }
                AddNewData();
            }
        }).setDate(selectedDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setTitleText("开始时间")
                .setSubCalSize(14)
                .setTitleSize(14)
                .setContentTextSize(14)
                .isDialog(true)
                .build();
        pvTime1.show();
    }

    @OnClick(R.id.tv_end)
    public void onViewClicked() {
        hideSoftKeyBoard();
        getRequestData();
        _mActivity.onBackPressed();
    }

    private void getRequestData() {
        AddNewData();
        LatteLogger.d("testData", GsonBuildUtil.GsonBuilder(modBean));

        EasyHttp.post(Constants.PERMISSION_REPAIR_LIST + session)
                .upJson(GsonBuildUtil.NullToString().toJson(modBean))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("testResult", s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt("Status") == 0) {
                                EventBusUtil.sendEvent(new Event(Add_RESULT_BACK_DATA));
                                _mActivity.onBackPressed();
                            }
                            ToastUtil.getInstance().showToast(object.getString("Msg"));
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

    RepairBean.DataEntity data;

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.INTENT_REPAIR_DATA:
                data = (RepairBean.DataEntity) event.getData();
                AddNewData();
                break;
            case FinalClass.REPAIR_CLICK:
                modBean = (RepairAddBean) event.getData();
                LatteLogger.d("modddd", GsonBuildUtil.GsonBuilder(modBean));
                if (title.contains("部门") && !"".equals(modBean.get确认时间()) && !"".equals(modBean.get电工鉴定())) {
                    tvEnd.setEnabled(true);
                    tvEnd.setTextColor(Color.WHITE);
                    break;
                } else if (title.contains("维修") && !"".equals(modBean.getRepairBillDetailsModel().get(0).get完成时间())) {
                    tvEnd.setEnabled(true);
                    tvEnd.setTextColor(Color.WHITE);
                    break;
                } else if (title.contains("工务") && !"".equals(modBean.get核准时间()) && !"".equals(modBean.get鉴定结果())) {
                    tvEnd.setEnabled(true);
                    tvEnd.setTextColor(Color.WHITE);
                    break;
                } else if (title.contains("验收") && !"".equals(modBean.get验收时间()) && !"".equals(modBean.get验收结果()) ) {
                    tvEnd.setEnabled(true);
                    tvEnd.setTextColor(Color.WHITE);
                    break;
                } else if (title.contains("行政") && !"".equals(modBean.getF_AdminDepart()) && !"".equals(modBean.get行政审批时间())) {
                    tvEnd.setEnabled(true);
                    tvEnd.setTextColor(Color.WHITE);
                    break;
                }
                break;
        }

    }

    private void AddNewData() {
        modBean.set报修原因(entity.get报修原因());

        modBean.setF_DepartmentHead(userName);
        if (title.contains("部门") && data != null) {
            modBean.set确认人(entity.get申请人());
            modBean.set电工鉴定(data.get电工鉴定());
        } else {
            modBean.set确认人(entity.get确认人());
            modBean.set电工鉴定(entity.get电工鉴定());
        }
        modBean.set确认时间(entity.get确认时间());
        modBean.set维修单号(entity.get维修单号());
        modBean.set报修性质(entity.get报修性质());
        modBean.set变更时间(entity.get变更时间());
        modBean.set变更人(entity.get变更人());
        DetailEntities.clear();
        for (int i = 0; i < entity.getRepairBillDetailsModel().size(); i++) {
            RepairBean.DataEntity.RepairBillDetailsModelEntity bean = entity.getRepairBillDetailsModel().get(i);
            RepairAddBean.RepairBillDetailsModelEntity item = new RepairAddBean.RepairBillDetailsModelEntity();
            item.set位置(bean.get位置());
            item.set完成时间(bean.get完成时间());
            item.set序号(bean.get序号());
            item.set维修人(bean.get维修人());
            item.set要求时限(bean.get要求时限());
            DetailEntities.add(item);
        }

        modBean.setRepairBillDetailsModel(DetailEntities);
        modBean.setF_TypeContext(entity.getF_TypeContext());
        modBean.setF_Evaluate(entity.getF_Evaluate());
        modBean.setF_DepartmentHead(entity.getF_DepartmentHead());

        if (title.contains("工务") && data != null) {
            modBean.set核准人(userName);
            modBean.set鉴定结果(data.get鉴定结果());
        } else {
            modBean.set核准人(entity.get核准人());
            modBean.set鉴定结果(entity.get鉴定结果());
        }

        modBean.set核准时间(entity.get核准时间());
        modBean.set维修类型(entity.get维修类型());
        if (title.contains("行政") && data != null) {
            modBean.set行政审批时间(data.get行政审批时间());
            modBean.setF_AdminConfirmer(userName);
            modBean.set行政审批(userName);
            modBean.setF_AdminDepart(data.getF_AdminDepart());
        } else {
            modBean.setF_AdminDepart(entity.getF_AdminDepart());
            modBean.setF_AdminConfirmer(entity.getF_AdminConfirmer());
            modBean.set行政审批(entity.get行政审批());
        }
        if (title.contains("验收") && data != null) {
            modBean.set验收结果(data.get验收结果());
            modBean.set验收人员(userName);
            modBean.setF_Evaluate(entity.getF_Evaluate());
        } else {
            modBean.set验收结果(entity.get验收结果());
            modBean.set验收人员(entity.get验收人员());
        }
        modBean.set验收时间(entity.get验收时间());
        Event<RepairAddBean> mobEntity = new Event<RepairAddBean>(FinalClass.REPAIR_CLICK, modBean);
        EventBusUtil.sendEvent(mobEntity);

    }
}








