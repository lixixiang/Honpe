package com.honpe.lxx.app.widget.dialog;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.flyco.dialog.widget.base.BaseDialog;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position2.adapter.BaseStepAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position2.bean.LeaveNewBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.widget.BaseListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 17:05
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class CustomTypeDialog extends BaseDialog<CustomTypeDialog> {
    @BindView(R.id.rv_menu_list)
    RecyclerView rvMenuList;
    @BindView(R.id.tv_mob)
    TextView tvMob;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.rv_menu_list2)
    RecyclerView rvMenuList2;
    @BindView(R.id.rv_menu_list3)
    RecyclerView rvMenuList3;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_titleBar)
    TextView tvTitleBar;
    @BindView(R.id.font_1)
    TextView font1;
    @BindView(R.id.font_2)
    TextView font2;
    @BindView(R.id.font_3)
    TextView font3;

    private String  title;
    public onDeleteListener listener;
    public onMobClassListener mobListener;

    private LeaveNewBean dataBean;
    private List<LeaveNewBean> leaveNewBeans = new ArrayList<>();

    public void setOnDeleteListener(onDeleteListener listener) {
        this.listener = listener;
    }

    public void setOnMobListener(onMobClassListener listener) {
        this.mobListener = listener;
    }

    public CustomTypeDialog(Context context, LeaveNewBean dataBean, String title) {
        super(context);
        this.dataBean = dataBean;
        this.title = title;
    }

    @Override
    public View onCreateView() {
        widthScale(0.9f);
        View inflate = View.inflate(mContext, R.layout.popup_menu, null);
        ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        llTitleBar.setVisibility(View.GONE);
        toolbar.setVisibility(View.VISIBLE);
        tvTitleBar.setText(title + "详情");
        tvMob.setText("修改" + title);
        tvCancel.setText("取消" + title);
        rvMenuList2.setVisibility(View.GONE);
        rvMenuList3.setVisibility(View.GONE);

        LatteLogger.d("testGsonBguild", GsonBuildUtil.GsonBuilder(dataBean));

        rvMenuList.setLayoutManager(new LinearLayoutManager(mContext));
        if (dataBean.getCurrentStatus().contains("同意")||dataBean.getCurrentStatus().contains("不同意")) {
            tvMob.setBackgroundResource(R.drawable.select_grey_home_radius10);
            tvCancel.setBackgroundResource(R.drawable.select_grey_home_radius10);
            tvClose.setBackgroundResource(R.drawable.select_red_radius10);
            tvMob.setEnabled(false);
            tvCancel.setEnabled(false);
        }

        leaveNewBeans.add(dataBean);
        CustomDormAdapter adapter = new CustomDormAdapter(leaveNewBeans, title);
        rvMenuList.setAdapter(adapter);
    }


    @Override
    public void setUiBeforShow() {

    }

    @OnClick({R.id.tv_mob, R.id.tv_cancel, R.id.tv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mob:
                mobListener.onClickMobClass();
                dismiss();
                break;
            case R.id.tv_cancel:
                listener.onClickDelete();
                dismiss();
                break;
            case R.id.tv_close:
                dismiss();
                break;
        }
    }

    public interface onDeleteListener {
        void onClickDelete();
    }

    public interface onMobClassListener {
        void onClickMobClass();
    }

    public class CustomDormAdapter extends BaseQuickAdapter<LeaveNewBean, BaseViewHolder> {
        private String title;
        List<String> listString = new ArrayList<>();
        private int unFinishPos;

        public CustomDormAdapter(@Nullable List<LeaveNewBean> data, String title) {
            super(R.layout.css_leave2, data);
            this.title = title;
        }

        @Override
        protected void convert(BaseViewHolder helper, LeaveNewBean item) {
            helper.setText(R.id.tv_startTime, item.getStartTime());
            helper.setText(R.id.tv_endTime, item.getEndTime());
            helper.setText(R.id.tv2, item.getReason());

            helper.setText(R.id.tv_time_long, item.getLongTime()+" H");
            try {
                JSONArray array = new JSONArray(item.getCurrentStatus());
                for (int i = 0; i < array.length(); i++) {
                    unFinishPos = i + 1;
                    if (array.getJSONObject(i).getString("NodeRemarks").equals("")) {
                        String str = array.getJSONObject(i).getString("NodeName");
                        listString.add(str.substring(0,str.length() -2)+"尚未审批");
                        unFinishPos = i-1;
                    }else {
                        listString.add(array.getJSONObject(i).getString("NodeRemarks"));
                    }
                }
                BaseListView stepView = helper.getView(R.id.listView);
                BaseStepAdapter adapter = new BaseStepAdapter(mContext,listString);
                stepView.setAdapter(adapter);
               LatteLogger.d("unFinishPos",unFinishPos);
//                stepView.setStepsViewIndicatorComplectingPosition(unFinishPos)
//                        .reverseDraw(true)
//                        .setTextSize(16)
//                        .setStepViewTexts(listString)//总步骤
//                        .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
//                        .setStepsViewIndicatorCompletedLineColor
//                                (ContextCompat.getColor(mContext, R.color.background_e))//设置StepsViewIndicator完成线的颜色
//                        .setStepsViewIndicatorUnCompletedLineColor
//                                (ContextCompat.getColor(mContext, R.color.un_selector_text))//设置StepsViewIndicator未完成线的颜色
//                        .setStepViewComplectedTextColor
//                                (ContextCompat.getColor(mContext, R.color.blue_dark))//设置StepsView text完成的颜色
//                        .setStepViewUnComplectedTextColor
//                                (ContextCompat.getColor(mContext, R.color.google_red))//设置StepsView text未完成线的颜色
//                        .setStepsViewIndicatorCompleteIcon
//                                (ContextCompat.getDrawable(mContext, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
//                        .setStepsViewIndicatorDefaultIcon
//                                (ContextCompat.getDrawable(mContext, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
//                        .setStepsViewIndicatorAttentionIcon
//                                (ContextCompat.getDrawable(mContext, R.drawable.attention));//设置StepsViewIndicator AttentionIcon



            } catch (JSONException e) {
                e.printStackTrace();
            }
            LatteLogger.d("title=======", title);
            if (title.equals("请假") || title.equals("外出")) {
                helper.setGone(R.id.ll_destination, true);
            } else {
                helper.setText(R.id.tv_destination, item.getDestination());
            }
        }
    }
}

