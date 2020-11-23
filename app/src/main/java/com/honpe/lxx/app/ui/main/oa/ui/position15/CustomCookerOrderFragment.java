package com.honpe.lxx.app.ui.main.oa.ui.position15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position15.adapter.ApplyOrderMenuAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.ApplyCustomOrderFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.ApplyChildBean4;
import com.honpe.lxx.app.ui.main.oa.ui.position15.bean.ApplyChild;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.dialog.CustomMenuDialog;
import com.honpe.lxx.app.widget.font.FontTextView;
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
import jp.shts.android.library.TriangleLabelView;

import static com.honpe.lxx.app.api.FinalClass.CustomApplyCore;
import static com.honpe.lxx.app.api.FinalClass.CustomModCore;
import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.utils.DateUtil.dateToWeek;

/**
 * FileName: CustomCookerOrderFragment
 * Author: asus
 * Date: 2020/8/10 16:15
 * Description: 客户订餐
 */
public class CustomCookerOrderFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.ll_click)
    LinearLayout llClick;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_Year_Month)
    TextView tvYearMonth;
    @BindView(R.id.tv_font_date)
    FontTextView tvFontDate;
    @BindView(R.id.tv_time1)
    FontTextView tvTime1;
    @BindView(R.id.lab_time1)
    TriangleLabelView labTime1;
    @BindView(R.id.tv_person_num)
    FontTextView tvPersonNum;
    @BindView(R.id.lab_person_num)
    TriangleLabelView labPersonNum;
    @BindView(R.id.tv_time2)
    FontTextView tvTime2;
    @BindView(R.id.lab_time2)
    TriangleLabelView labTime2;
    @BindView(R.id.tv_person_num2)
    FontTextView tvPersonNum2;
    @BindView(R.id.lab_person_num2)
    TriangleLabelView labPersonNum2;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_custom_head)
    LinearLayout llCustomHead;
    @BindView(R.id.ll_background)
    LinearLayout llBackground;
    @BindView(R.id.tv_theme_tips)
    TextView tvThemeTips;
    @BindView(R.id.tv_theme_add)
    TextView tvThemeAdd;
    @BindView(R.id.tv_theme_title)
    TextView tvThemeTitle;
    @BindView(R.id.re_none_order)
    RelativeLayout reNoneOrder;


    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat YearMonth = new SimpleDateFormat("yy年MM月");
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String firstDay, lastDay, curDay, curMonth, today, msg, session;
    private ApplyOrderMenuAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    List<ApplyChild> mList = new ArrayList<>();

    public static CustomCookerOrderFragment newInstance(String title) {
        CustomCookerOrderFragment fragment = new CustomCookerOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_food;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        Bundle bundle = getArguments();
        if (bundle != null) {
            tvTitle.setText(bundle.getString("title"));
        }
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("申请");
        curMonth = sf.format(new Date());
        tvDate.setText(curMonth);
        firstDay = DateUtil.getFirstDayOfMonth(format);
        lastDay = DateUtil.getLastDayOfMonth(format);
        today = format.format(new Date());
        session = (String) MyApplication.get(_mActivity, Session, "");
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        StringUtil.getUnOrder(tvThemeTitle,tvThemeTips,tvThemeAdd,"暂无"+tvTitle.getText().toString(),"点击下方或右上方按钮可申请"+tvTitle.getText().toString(),
                "申请"+tvTitle.getText().toString());
        initWidget();

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getJsonData();
    }

    private void getJsonData() {
        LatteLogger.d("dateTime", firstDay + "  " + lastDay + "   " + session);
       disposable = EasyHttp.post(Constants.GETCUSTOMORDERLIST + session)
                .params("StartTime", firstDay) //日期
                .params("EndTime", lastDay)  //晚餐
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                startWithPop(LoginFragment.newInstance(CustomCookerOrderFragment.this.getTag()));
                                ToastUtil.getInstance().showToast("您的session过期了,请重新登录");
                            } else {
                                final ApplyChildBean4 bean = Convert.fromJson(s, ApplyChildBean4.class);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                if (bean.getData().size() >0) {
                                    recyclerView.setVisibility(View.VISIBLE);
                                    reNoneOrder.setVisibility(View.GONE);
                                } else {
                                    recyclerView.setVisibility(View.GONE);
                                    reNoneOrder.setVisibility(View.VISIBLE);
                                }
                                adapter = new ApplyOrderMenuAdapter(getNewJson(bean.getData()));
                                adapter.addChildClickViewIds(R.id.tv_time1,R.id.tv_person_num,R.id.tv_time2,R.id.tv_person_num2);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                        MenuDialog(adapter, position, bean, true);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void MenuDialog(final BaseQuickAdapter adapter, final int position, final ApplyChildBean4 bean, boolean b) {
        CustomMenuDialog dialog = new CustomMenuDialog(_mActivity, bean.getData().get(position).getDetails(), session, bean.getData().get(position).getOrderNo());
        dialog.show();
        dialog.setCanceledOnTouchOutside(b);
        dialog.setOnDeleteListener(new CustomMenuDialog.onDeleteListener() {
            @Override
            public void onClickDelete() {
                getJsonData();
                adapter.notifyItemRemoved(position);
            }
        });
        dialog.setOnMobListener(new CustomMenuDialog.onMobClassListener() {
            @Override
            public void onClickMobClass() {
                LatteLogger.d(bean.getData().get(position).getOrderNo());
                ApplyChildBean4.DataBean bean1 = new ApplyChildBean4.DataBean();
                bean1.setOrderNo(bean.getData().get(position).getOrderNo());
                bean1.setMealsDate(bean.getData().get(position).getMealsDate());
                bean1.setMealsNumber(bean.getData().get(position).getMealsNumber());
                bean1.setNotes(bean.getData().get(position).getNotes());
                bean1.setFoodAmount(bean.getData().get(position).getFoodAmount());
                bean1.setDetails(bean.getData().get(position).getDetails());
                bean1.setMealType(bean.getData().get(position).getMealType());
                bean1.setMealTimer(bean.getData().get(position).getMealTimer());
                getMob(bean1);
            }
        });
    }

    private void getMob(ApplyChildBean4.DataBean data) {
        startForResult(ApplyCustomOrderFragment.newInstance("修改点餐", "2", data), CustomModCore);
    }

    private List<ApplyChild> getNewJson(List<ApplyChildBean4.DataBean> data) {
        mList.clear();
        for (int i = 0; i < data.size(); i++) {
            ApplyChild item = new ApplyChild();
            Date start = DateUtil.setDate(data.get(i).getMealsDate());
            curDay = myDay.format(start);
            item.setDate(curDay + "/" + dateToWeek(data.get(i).getMealsDate()));
            item.setType(data.get(i).getMealType());
            if (item.getType().equals("午餐")) {
                item.setTime1(data.get(i).getMealTimer());
                item.setNum1(data.get(i).getMealsNumber() + "");
            } else if (item.getType().equals("晚餐")) {
                item.setTime2(data.get(i).getMealTimer());
                item.setNum2(data.get(i).getMealsNumber() + "");
            }
            mList.add(item);
        }
        return mList;
    }

    private void initWidget() {
        tvFontDate.setBackgroundResource(R.color.grey_table2);
        tvTime1.setBackgroundResource(R.color.grey_table2);
        tvTime2.setBackgroundResource(R.color.grey_table2);
        tvPersonNum.setBackgroundResource(R.color.grey_table2);
        tvPersonNum2.setBackgroundResource(R.color.grey_table2);
        tvFontDate.setText("日期");
        tvTime1.setText("时间1");
        tvTime2.setText("时间2");
        tvPersonNum.setText("人数");
        tvPersonNum2.setText("人数");
    }

    public int oneDay = 0; //这个初始日子

    @OnClick({R.id.tv_end, R.id.btn_up_pager, R.id.btn_next_pager,R.id.tv_theme_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
            case R.id.tv_theme_add:
                startForResult(ApplyCustomOrderFragment.newInstance("申请客户订单", "1"), CustomApplyCore);
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

    public void setOneMonth(int oneDay) {
        this.oneDay = oneDay;
        firstDay = DateUtil.getFirstDayOfMonth(format, oneDay);
        lastDay = DateUtil.getLastDayOfMonth(format, oneDay);
        tvDate.setText(DateUtil.getLastMonth(oneDay));
        today = format.format(new Date());
        getJsonData();

    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if (requestCode == CustomApplyCore && resultCode == RESULT_OK && data != null) {
            String strResult = data.getString("result");
            LatteLogger.d("testResult", strResult);
        } else if (requestCode == CustomModCore && resultCode == RESULT_OK && data != null) {
            String strResult = data.getString("result");
            LatteLogger.d("testMob", strResult);
        }
        getJsonData();
        adapter.notifyDataSetChanged();
    }
}
