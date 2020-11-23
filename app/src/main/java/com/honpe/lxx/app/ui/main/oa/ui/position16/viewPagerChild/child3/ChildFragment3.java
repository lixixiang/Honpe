package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.main.more.adapter.BaseViewPagerAdapter;
import com.honpe.lxx.app.ui.main.more.adapter.FragmentViewPagerAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean3;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.FragmentPraise;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.NavigationTabStrip;
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

import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: ChildFragment
 * Author: asus
 * Date: 2020/8/11 11:59
 * Description: 员工评价
 */
public class ChildFragment3 extends BaseBackFragment {
    @BindView(R.id.tab)
    NavigationTabStrip tabStrip;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private String[] meals = {"早餐", "午餐", "晚餐", "夜宵"};
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String today,session;
    private List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentPagerAdapter adapter;
    StatisticsBean3 bean3;
    private int curPos;
    public static ChildFragment3 newInstance() {
        ChildFragment3 childFragment3 = new ChildFragment3();
        return childFragment3;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_to_eat3;
    }

    @Override
    protected void initView() {
        tabStrip.setTitles(meals);
        today = sdf.format(new Date());
         session = (String) MyApplication.get(_mActivity, Session, "");
        initNetRequest();
    }

    /**
     * 做当天的数据
     */
    private void initNetRequest() {
        EasyHttp.post(Constants.GETORDERLIST + session)
                .params("MealDate", today)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("Tag", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                            } else {
                                 bean3 = Convert.fromJson(s, StatisticsBean3.class);
                                for (int i = 0; i < meals.length; i++) {
                                    fragments.add(FragmentPraise.newInstance(bean3.getData().get(i)));
                                }
                                adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
                                viewPager.setAdapter(adapter);
                                tabStrip.setViewPager(viewPager, 0);
                                tabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                    }

                                    @Override
                                    public void onPageSelected(int position) {
                                        curPos = position;
                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int state) {

                                    }
                                });

                            }
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
            case FinalClass.C:
                if (viewPager.getAdapter() != null) {
                    int k = 0;
                    EasyHttp.post(Constants.GETORDERLIST + session)
                            .params("MealDate", today)
                            .execute(new SimpleCallBack<String>() {

                                @Override
                                public void onError(ApiException e) {

                                }

                                @Override
                                public void onSuccess(String s) {
                                    try {
                                        JSONObject obj = new JSONObject(s);
                                        if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                        } else {
                                            bean3 = Convert.fromJson(s, StatisticsBean3.class);
                                            int k = 0;
                                            adapter.replaceFragment(fragments.get(curPos),FragmentPraise.newInstance(bean3.getData().get(curPos)));
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            });
                }
                break;
        }
    }
}
