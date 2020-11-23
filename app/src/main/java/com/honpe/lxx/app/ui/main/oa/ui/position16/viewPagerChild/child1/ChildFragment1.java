package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.adapter.MyListView;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean1;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: ChildFragment1
 * Author: asus
 * Date: 2020/8/11 11:57
 * Description: 员工报餐
 */
public class ChildFragment1 extends BaseBackFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.ck_breakfast)
    CheckBox ckBreakfast;
    @BindView(R.id.ck_lunch)
    CheckBox ckLunch;
    @BindView(R.id.ck_after)
    CheckBox ckAfter;
    @BindView(R.id.ck_night)
    CheckBox ckNight;
    @BindView(R.id.ck_all)
    CheckBox ckAll;
    @BindView(R.id.listView)
    ListView mListView;

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    List<StatisticsBean1.DataBean> mList = new ArrayList<>();
    List<String> mDateList = new ArrayList<>();
    String session, UserID, firstDay, lastDay, today;
    MyListView adapter;
    private int testBool, testBool2, testBool3, testBool4, mIsCheck;
    List<CheckBox> checkViews = new ArrayList<>();
    public static ChildFragment1 newInstance() {
        ChildFragment1 fragment1 = new ChildFragment1();
        return fragment1;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_to_eat;
    }

    @Override
    protected void initView() {
        getAllDate();
        getJsonData();
        //全选的点击监听
        ckBreakfast.setOnCheckedChangeListener(this);
        ckLunch.setOnCheckedChangeListener(this);
        ckAfter.setOnCheckedChangeListener(this);
        ckNight.setOnCheckedChangeListener(this);
        ckAll.setOnCheckedChangeListener(this);

    }

    private void getJsonData() {
        EasyHttp.post(Constants.YGREPORTMEETLIST + session)
                .params("StartTime", firstDay)
                .params("EndTime", lastDay)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {

                            } else {
                                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new Convert.NullStringToEmptyAdapterFactory()).create();
                                StatisticsBean1 bean = gson.fromJson(s, StatisticsBean1.class);
                                adapter = new MyListView(_mActivity, getData(bean.getData()), ckBreakfast, ckLunch, ckAfter, ckNight);

                                for (int i = 0; i < bean.getData().size(); i++) {
                                    if (bean.getData().get(i).isHasBreakFast()) {
                                        testBool++;
                                    }
                                    if (bean.getData().get(i).isHasLunch()) {
                                        testBool2++;
                                    }
                                    if (bean.getData().get(i).isHasDinner()) {
                                        testBool3++;
                                    }
                                    if (bean.getData().get(i).isHasMidnight()) {
                                        testBool4++;
                                    }
                                }
                                LatteLogger.d("testData", testBool + "   " + testBool2 + "    " + testBool3 + "     " + testBool4);
                                if (testBool == 30||testBool==31) {
                                    ckBreakfast.setChecked(true);
                                }
                                if (testBool2 == 30||testBool2==31) {
                                    ckLunch.setChecked(true);
                                }
                                if (testBool3 == 30||testBool3==31) {
                                    ckAfter.setChecked(true);
                                }
                                if (testBool4 == 30||testBool4==31) {
                                    ckNight.setChecked(true);
                                }

                                mListView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 获取本月所有日期
     *
     * @return
     */
    private List<StatisticsBean1.DataBean> getData(List<StatisticsBean1.DataBean> data) {
        mList.clear(); //防止叠加
        Map<String, List<StatisticsBean1.DataBean>> maps = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            String day = data.get(i).getMealDate();
            List<StatisticsBean1.DataBean> listDay = maps.get(day);
            if (listDay == null) {
                listDay = new ArrayList<>();
            }
            StatisticsBean1.DataBean item = new StatisticsBean1.DataBean();
            item.setHasBreakFast(data.get(i).isHasBreakFast());
            item.setHasLunch(data.get(i).isHasLunch());
            item.setHasDinner(data.get(i).isHasDinner());
            item.setHasMidnight(data.get(i).isHasMidnight());
            listDay.add(item);
            maps.put(day, listDay);
        }

        for (int i = 0; i < mDateList.size(); i++) {
            StatisticsBean1.DataBean item = new StatisticsBean1.DataBean();
            item.setMealDate(mDateList.get(i));
            item.setUserID("");
            item.setWeek(DateUtil.getWeek(mDateList.get(i)));
            for (String day : maps.keySet()) {
                if (day.equals(mDateList.get(i))) {
                    List<StatisticsBean1.DataBean> listDatas = maps.get(day);
                    for (int j = 0; j < listDatas.size(); j++) {
                        StatisticsBean1.DataBean isSelector = listDatas.get(j);
                        item.setHasBreakFast(isSelector.isHasBreakFast());
                        item.setHasLunch(isSelector.isHasLunch());
                        item.setHasDinner(isSelector.isHasDinner());
                        item.setHasMidnight(isSelector.isHasMidnight());
                    }
                }
            }
            mList.add(item);
            LatteLogger.d("testBean", GsonBuildUtil.GsonBuilder(mList));

        }
        return mList;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.REPORT_STAFF:
                StatisticsBean1.DataBean data = (StatisticsBean1.DataBean) event.getData();
                if (data != null) {
                    Event<String> event1 = new Event<String>(FinalClass.EMPLOYEE_MAIN, GsonBuildUtil.GsonBuilder(mList));
                    EventBusUtil.sendEvent(event1);
                }
                break;
        }
    }

    private void getAllDate() {
        session = (String) MyApplication.get(_mActivity, Session, "");
        UserID = (String) MyApplication.get(_mActivity, FinalClass.UserCode, "");
        firstDay = sdf1.format(new Date());
        lastDay = DateUtil.getBackDayOfMonth(sdf1);
        mDateList = DateUtil.getDays(firstDay, lastDay);
        checkViews.add(ckBreakfast);
        checkViews.add(ckLunch);
        checkViews.add(ckAfter);
        checkViews.add(ckNight);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsCheck = 0;
        for (int i = 0; i < checkViews.size(); i++) {
            if (checkViews.get(i).isChecked()) {
                mIsCheck ++;
                LatteLogger.d("testmIsCheck", mIsCheck + "");
                if (mIsCheck == 4) {
                    ckAll.setChecked(true);
                }
            }
        }

        switch (buttonView.getId()) {
            case R.id.ck_breakfast:
                if (isChecked) {

                    ckBreakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.selectVertical();
                        }
                    });
                } else {
                    ckAll.setChecked(false);
                    ckBreakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.cancelVertical();
                        }
                    });
                }
                break;
            case R.id.ck_lunch:
                if (isChecked) {
                    ckLunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.selectVertical2();
                        }
                    });
                } else {
                    ckAll.setChecked(false);
                    ckLunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.cancelVertical2();
                        }
                    });
                }
                break;
            case R.id.ck_after:
                if (isChecked) {
                    ckAfter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.selectVertical3();
                        }
                    });
                } else {
                    ckAll.setChecked(false);
                    ckAfter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.cancelVertical3();
                        }
                    });
                }
                break;
            case R.id.ck_night:
                if (isChecked) {
                    ckNight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.selectVertical4();
                        }
                    });
                } else {
                    ckAll.setChecked(false);
                    ckNight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.cancelVertical4();
                        }
                    });
                }
                break;
            case R.id.ck_all:
               if (isChecked) {
                   ckAll.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           ckBreakfast.setChecked(true);
                           ckLunch.setChecked(true);
                           ckAfter.setChecked(true);
                           ckNight.setChecked(true);
                           ckAll.setChecked(true);
                           adapter.selectAll();
                       }
                   });

                } else {
                   ckAll.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           ckBreakfast.setChecked(false);
                           ckLunch.setChecked(false);
                           ckAfter.setChecked(false);
                           ckNight.setChecked(false);
                           ckAll.setChecked(false);
                           adapter.cancelAll();
                           mIsCheck = 0;
                       }
                   });
                }
                break;
            default:
                break;
        }
    }
}



