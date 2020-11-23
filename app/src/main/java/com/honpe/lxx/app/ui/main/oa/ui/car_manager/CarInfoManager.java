package com.honpe.lxx.app.ui.main.oa.ui.car_manager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.main.appoint.calerndar.MultiSelectCalendarView;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.child.CarInfoFragment;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.widget.NavigationTabStrip;
import com.honpe.lxx.app.widget.dialog.CarNoInputDialog;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.REQ_MODIFY_FRAGMENT;
import static com.honpe.lxx.app.ui.main.appoint.add.AppointFragment.DIALOG_DATA;

/**
 * FileName: CarManager
 * Author: asus
 * Date: 2020/10/21 17:33
 * Description: 车辆进出管理
 */
public class CarInfoManager extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.nts_bottom)
    NavigationTabStrip ntsBottom;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_3)
    TextView item3;
    @BindView(R.id.item_4)
    TextView item4;
    @BindView(R.id.item_5)
    TextView item5;
    @BindView(R.id.item_6)
    TextView item6;
    @BindView(R.id.ll_tab_car_info)
    LinearLayout llTabCarInfo;
    @BindView(R.id.tv_content1)
    EditText etContent1;
    @BindView(R.id.tv_content2)
    EditText tvContent2;
    @BindView(R.id.rg_date)
    RelativeLayout rgDate;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_up_pager)
    Button btnUpPager;
    @BindView(R.id.btn_next_pager)
    Button btnNextPager;
    @BindView(R.id.ll_click)
    LinearLayout llClick;

    private String[] titles = {"车辆信息", "车辆进出"};
    private String[] nets = {Constants.AppGetParkCard, Constants.AppCarInOut};
    private BaseFragmentPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private int curPos = 0;
    Bundle b = new Bundle();
    private SimpleDateFormat yearMonthDate = new SimpleDateFormat("yyyy-MM-dd");
    private String firstDay, lastDay, today;
    private int oneDay;
    List<String> mDateList = new ArrayList<>();

    public static CarInfoManager newInstance(String title) {
        CarInfoManager fragment = new CarInfoManager();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_viewpager_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        item1.setBackgroundResource(R.color.colorBackground);
        item2.setBackgroundResource(R.color.colorBackground);
        item3.setBackgroundResource(R.color.colorBackground);
        item4.setBackgroundResource(R.color.colorBackground);
        item5.setBackgroundResource(R.color.colorBackground);
        item6.setBackgroundResource(R.color.colorBackground);
        item2.setText("姓名");
        item3.setText("车牌号码");
        item4.setText("注册时间");
        item5.setText("有效时间");
        item6.setText("失效时间");
        llTabCarInfo.setVisibility(View.VISIBLE);
        etContent1.addTextChangedListener(this);
        tvContent2.addTextChangedListener(this);
        rgDate.setVisibility(View.GONE);
        today = yearMonthDate.format(new Date());
        tvDate.setText(today);
        btnUpPager.setText("前一天");
        btnNextPager.setText("后一天");
        if (tvDate.getText().toString().equals(today)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        b.putInt("curPos",curPos);
        b.putString("UserName", etContent1.getText().toString());
        b.putString("CardNo", tvContent2.getText().toString());
        b.putString("StartTime", tvDate.getText().toString());
        b.putString("EndTime", tvDate.getText().toString());
        for (int i = 0; i < titles.length; i++) {
            b.putString("net",nets[i]);
            fragments.add(CarInfoFragment.newInstance(b));
        }
        adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        ntsBottom.setTitles(titles);
        ntsBottom.setViewPager(viewpager);
        ntsBottom.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curPos = position;
                etContent1.setText("");
                tvContent2.setText("");
                switch (position) {
                    case 1:
                        item2.setText("车牌号码");
                        item3.setText("车主姓名");
                        item4.setText("进厂时间");
                        item5.setText("出厂时间");
                        item6.setText("停车时长");
                        rgDate.setVisibility(View.VISIBLE);

                        break;
                    default:
                        item2.setText("姓名");
                        item3.setText("车牌号码");
                        item4.setText("注册时间");
                        item5.setText("有效时间");
                        item6.setText("失效时间");
                        rgDate.setVisibility(View.GONE);
                        break;
                }
                hideSoftInput();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (curPos == 0) {
            b.putString("net",nets[curPos]);
            b.putInt("curPos",curPos);
            b.putString("UserName", etContent1.getText().toString());
            b.putString("CardNo", tvContent2.getText().toString());
            adapter.replaceFragment(fragments.get(curPos), CarInfoFragment.newInstance(b));
        }else {
            b.putString("net",nets[curPos]);
            b.putInt("curPos",curPos);
            b.putString("StartTime", tvDate.getText().toString());
            b.putString("EndTime", tvDate.getText().toString());
            b.putString("UserName", etContent1.getText().toString());
            b.putString("CardNo", tvContent2.getText().toString());
            adapter.replaceFragment(fragments.get(curPos), CarInfoFragment.newInstance(b));
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.REFRESH_CAR_INFO_INTO:
                adapter.replaceFragment(fragments.get(curPos), CarInfoFragment.newInstance(b));
                break;
        }
    }

    @OnClick({R.id.btn_up_pager, R.id.btn_next_pager,R.id.ll_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_pager:
                oneDay--;
                setOneMonth(oneDay);
                break;
            case R.id.btn_next_pager:
                oneDay++;
                setOneMonth(oneDay);
                break;
            case R.id.ll_click:
                startForResult(MultiSelectCalendarView.newInstance(), REQ_MODIFY_FRAGMENT);
                break;
        }
    }

    public void setOneMonth(int oneDay) {
        tvDate.setText(DateUtil.getOneDay(oneDay));
        if (tvDate.getText().toString().equals(today)) {
            btnNextPager.setBackgroundResource(android.R.color.transparent);
            btnNextPager.setTextColor(getResources().getColor(R.color.grey_home));
            btnNextPager.setClickable(false);
        } else {
            btnNextPager.setBackgroundResource(R.drawable.btn_blue_checked_change);
            btnNextPager.setTextColor(getResources().getColor(R.color.white));
            btnNextPager.setClickable(true);
        }
        b.putString("net",nets[curPos]);
        b.putString("StartTime", tvDate.getText().toString());
        b.putString("EndTime", tvDate.getText().toString());
        b.putString("UserName", etContent1.getText().toString());
        b.putString("CardNo", tvContent2.getText().toString());
        adapter.replaceFragment(fragments.get(curPos), CarInfoFragment.newInstance(b));
    }
}














