package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean2;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.adapter.BaseSelectStatisticsAdapter;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
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

import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.utils.DateUtil.dateToWeek;

/**
 * FileName: ChildFragment2
 * Author: asus
 * Date: 2020/8/11 11:58
 * Description: 选材统计
 */
public class ChildFragment2 extends BaseBackFragment {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_date)
    RelativeLayout llDate;
    @BindView(R.id.ll_selector_date)
    LinearLayout llSelectorDate;
    @BindView(R.id.tv_theme_title)
    TextView tvThemeTitle;
    @BindView(R.id.tv_theme_tips)
    TextView tvThemeTips;
    @BindView(R.id.tv_theme_add)
    TextView tvThemeAdd;
    @BindView(R.id.re_none_order)
    RelativeLayout reNoneOrder;
    @BindView(R.id.iv_left_grey)
    ImageView ivLeftGrey;
    @BindView(R.id.iv_right_grey)
    ImageView ivRightGrey;

    List<BaseNode> list = new ArrayList<>();

    String session, firstDay, LastDay, toDay, StartTime;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public int oneDay = 1; //这个初始日子

    public static ChildFragment2 newInstance() {
        ChildFragment2 childFragment2 = new ChildFragment2();
        return childFragment2;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_to_eat2;
    }

    @Override
    protected void initView() {
        StringUtil.getUnOrder(tvThemeTitle, tvThemeTips, tvThemeAdd, "很抱歉，暂无材料统计数据", "", "");
        tvThemeTips.setVisibility(View.GONE);
        tvThemeAdd.setVisibility(View.GONE);
        initData();
        GetNetRequest();

    }

    private void GetNetRequest() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        EasyHttp.post(Constants.YGORDERMENULIST + session)
                .params("StartTime", tvDate.getText().toString())
                .params("EndTime", tvDate.getText().toString())
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("fragment2", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                            } else {
                                StatisticsBean2 bean = Convert.fromJson(s, StatisticsBean2.class);

                                LatteLogger.d("testGson", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getData().size() > 0) {
                                    llDate.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    reNoneOrder.setVisibility(View.GONE);
                                    BaseSelectStatisticsAdapter sectionAdapter = new BaseSelectStatisticsAdapter();
                                    final GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
                                    recyclerView.setLayoutManager(manager);

                                    recyclerView.setAdapter(sectionAdapter);
                                    sectionAdapter.setList(getJsonData(bean.getData()));
                                } else {
                                    llDate.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.GONE);
                                    reNoneOrder.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private List<BaseNode> getJsonData(List<StatisticsBean2.DataBean> data) {
        list.clear();
        for (int i = 0; i < data.size(); i++) {
            StatisticsBean2.DataBean bean = data.get(i);
            List<BaseNode> items = new ArrayList<>();
            for (int j = 0; j < bean.getDishesDetails().size(); j++) {
                StatisticsBean2.DataBean.DishesDetailsBean bean1 = bean.getDishesDetails().get(j);
                items.add(bean1);
            }
            bean.setChildNode(items);
            list.add(bean);
        }
        return list;
    }

    private void initData() {
        firstDay = DateUtil.getFirstDayOfWeek(sdf, sdf.format(new Date()));
        LastDay = DateUtil.getLastDayOfWeek(sdf, sdf.format(new Date()));
        toDay = sdf.format(new Date());
        tvDate.setText(toDay);
        tvWeek.setText(dateToWeek(tvDate.getText().toString()));
        ivLeftGrey.setColorFilter(Color.BLACK);
        ivRightGrey.setColorFilter(Color.BLACK);
    }

    @OnClick({R.id.iv_left_grey, R.id.iv_right_grey, R.id.ll_selector_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_grey:
                oneDay = -1;
                setOneDay(oneDay);
                break;
            case R.id.iv_right_grey:
                oneDay = 1;
                setOneDay(oneDay);
                break;
            case R.id.ll_selector_date:

                break;
        }
    }

    public void setOneDay(int oneDay) {
        Date date = DateUtil.yearAddNum(DateUtil.setDate(tvDate.getText().toString()), oneDay);
        toDay = sdf.format(date);
        tvDate.setText(toDay);
        tvWeek.setText(dateToWeek(tvDate.getText().toString()));
        GetNetRequest();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.EMPLOYEE_SECOND:
                StatisticsBean2.DataBean.DishesDetailsBean data = (StatisticsBean2.DataBean.DishesDetailsBean) event.getData();

                if (data != null) {
                    Event<String> event1 = new Event<String>(FinalClass.EMPLOYEE_MAIN2, GsonBuildUtil.GsonBuilder(list));
                    EventBusUtil.sendEvent(event1);
                }
                break;
        }
    }
}
