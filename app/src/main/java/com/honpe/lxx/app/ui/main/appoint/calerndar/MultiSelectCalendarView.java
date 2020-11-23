package com.honpe.lxx.app.ui.main.appoint.calerndar;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.appoint.adapter.CalendarAdapter;
import com.lxx.mcalendar.Calendar;
import com.lxx.mcalendar.CalendarView;

import java.text.SimpleDateFormat;

import butterknife.BindView;

/**
 * @package: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child
 * @date: 2018/10/17 9:54
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：员工点餐复选日历
 */
public class MultiSelectCalendarView extends BaseBackFragment implements CalendarView.OnCalendarSelectListener {

    @BindView(R.id.tv_title_day)
    TextView tvTitleDay;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    private String StartTime;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    public static MultiSelectCalendarView newInstance() {
        MultiSelectCalendarView fragment = new MultiSelectCalendarView();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_multi_select_calendar;
    }


    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("来访日期");
        tvTitleDay.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");
        calendarView.setOnCalendarSelectListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(new CalendarAdapter(_mActivity));

    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {
    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        tvTitleDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        tvYear.setText(calendar.getYear() + "年");
        tvLunar.setText("农历："+calendar.getLunar());
        if (isClick) {
            StartTime = calendar.getYear()+"-" + calendar.getMonth() + "-" + calendar.getDay();
            Bundle bundle = new Bundle();
            bundle.putString("result",StartTime);
            setFragmentResult(RESULT_OK, bundle);
            _mActivity.onBackPressed();
        }
    }
}