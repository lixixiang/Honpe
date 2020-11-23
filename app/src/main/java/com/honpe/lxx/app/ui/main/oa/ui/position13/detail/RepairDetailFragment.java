package com.honpe.lxx.app.ui.main.oa.ui.position13.detail;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.adapter.RepairDetailAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairAddBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: RepairDetailFragment
 * Author: asus
 * Date: 2020/9/2 12:13
 * Description: 点击进入详情界面
 */
public class RepairDetailFragment extends BaseBackFragment {
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
    private RepairDetailAdapter adapter;
    List<MultiItemBean> list = new ArrayList<>();
    RepairBean.DataEntity entity;
    RepairAddBean modBean = new RepairAddBean();

    private String[] strTitles = {"详细内容", "详细地点", "损坏原因"};
    private String[] strTitles2 = {"行政负责人", "部门负责人"};
    private String[] strTitles3 = {"工务组确认", "预计时间", "原因说明", "工务主管"};
    private String[] strTitles4 = {"实际时间", "维修人"};
    private String[] hint1 = {"行政负责人签名审批", "申请部门负责人签名审批"};
    private String[] hint2 = {"请填写工务组确认安装/维修可行性", "请选择预计安装/修复时间", "请填写原因说明", "工务主管签名核准"};
    private String[] hint3 = {"请选择实际安装/修复时间", "维修人签名"};
    private String[] prises = {"很差", "满意"};
    private String[] prises2 = {"一般", "非常满意"};

    public static RepairDetailFragment newInstance(String title, RepairBean.DataEntity entity) {
        RepairDetailFragment fragment = new RepairDetailFragment();
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
            tvTitle.setText(title + getString(R.string.detail));
            entity = (RepairBean.DataEntity) bundle.getSerializable("entity");
        }
        LatteLogger.d("entity", GsonBuildUtil.GsonBuilder(entity));
        initToolbarNav(llBack);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        adapter = new RepairDetailAdapter(getData());
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        recyclerView.setLayoutManager(manager);
        adapter.addChildClickViewIds(R.id.tv_status);
        adapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                return list.get(position).getSpanSize();
            }
        });

        recyclerView.setAdapter(adapter);

    }

    private List<MultiItemBean> getData() {
        MultiItemBean item1 = new MultiItemBean();
        item1.setItemType(MultiItemBean.HEAD);
        item1.setSpanSize(3);
        item1.setType(entity.get维修类型());
        item1.setContent(entity.getF_TypeContext());
        if (entity.getRepairBillDetailsModel() != null) {
            item1.setStrDate(StringUtil.changeFontColor(entity.get申请日期() + " --> " + entity.getRepairBillDetailsModel().get(0).get要求时限(), R.color.grey, 17, 20));
            item1.setTimeLong(entity.getRepairBillDetailsModel().get(0).get要求时限());
            item1.setId(entity.getRepairBillDetailsModel().get(0).get维修单号());
        }

        item1.setApplyName(entity.get申请人());
        item1.setStrDepart(entity.get申请部门());
        list.add(item1);

        list.add(new MultiItemBean(MultiItemBean.TEXT, "安装/维修任务", 3));

        for (int i = 0; i < strTitles.length; i++) {
            MultiItemBean item3 = new MultiItemBean();
            item3.setItemType(MultiItemBean.TEXT_EDIT);
            item3.setSpanSize(3);
            item3.setStrTitle(strTitles[i]);
            if (i == 0 && entity.getRepairBillDetailsModel() != null) {
                item3.setContent(entity.getRepairBillDetailsModel().get(0).get问题描述());
            } else if (i == 1 && entity.getRepairBillDetailsModel() != null) {
                item3.setAddress(entity.getRepairBillDetailsModel().get(0).get位置());
            } else if (i == 2) {
                item3.setReason(entity.get报修原因());
            }
            list.add(item3);
        }
        list.add(new MultiItemBean(MultiItemBean.LINE, 3));

        for (int i = 0; i < strTitles2.length; i++) {
            MultiItemBean item4 = new MultiItemBean();
            item4.setItemType(MultiItemBean.TEXT_EDIT);
            item4.setSpanSize(3);
            item4.setStrTitle(strTitles2[i]);
            if (i == 0) {
                item4.setAdminSigh(entity.get行政审批());
            } else {
                item4.setDepartSign(entity.getF_DepartmentHead());
            }

            item4.setHint(hint1[i]);
            list.add(item4);
        }

        list.add(new MultiItemBean(MultiItemBean.LINE, 3));

        for (int i = 0; i < strTitles3.length; i++) {
            MultiItemBean item4 = new MultiItemBean();
            item4.setItemType(MultiItemBean.TEXT_EDIT);
            item4.setSpanSize(3);
            item4.setWorksConfirmed(entity.get电工鉴定());
            item4.setPresetTime(entity.get确认时间());
            item4.setDescription(entity.get鉴定结果());
            item4.setContent(entity.get核准人());
            item4.setStrTitle(strTitles3[i]);
            item4.setHint(hint2[i]);
            list.add(item4);
        }

        list.add(new MultiItemBean(MultiItemBean.LINE, 3));

        for (int i = 0; i < strTitles4.length; i++) {
            MultiItemBean item4 = new MultiItemBean();
            item4.setItemType(MultiItemBean.TEXT_EDIT);
            item4.setSpanSize(3);
            item4.setStrTitle(strTitles4[i]);
            item4.setContent(entity.get确认时间());
            item4.setContent1(entity.getRepairBillDetailsModel().get(0).get维修人());
            item4.setHint(hint3[i]);
            list.add(item4);
        }

        list.add(new MultiItemBean(MultiItemBean.LINE, 3));

        MultiItemBean item4 = new MultiItemBean();
        item4.setItemType(MultiItemBean.TEXT_EDIT);
        item4.setSpanSize(3);
        item4.setStrTitle("申请人");
        item4.setHint("申请人签名");
        item4.setContent(entity.get申请人());
        list.add(item4);

        list.add(new MultiItemBean(MultiItemBean.TEXT_EDIT, "评价", entity.getF_Evaluate(), "本次服务是否满意", 3));


        list.add(new MultiItemBean(MultiItemBean.TEXT_EDIT, "评价原因", entity.get验收结果(), "请填写您的宝贵意见", 3));

        list.add(new MultiItemBean(MultiItemBean.LINE, 3));

        list.add(new MultiItemBean(MultiItemBean.TEXT_EDIT, "行政意见", entity.getF_AdminDepart(), "请填写您的宝贵意见", 3));
        list.add(new MultiItemBean(MultiItemBean.TEXT_EDIT, "行政确认人", entity.getF_AdminConfirmer(), "行政负责人签名", 3));
        return list;
    }


    List<RepairAddBean.RepairBillDetailsModelEntity> DetailEntities = new ArrayList<>();


}











