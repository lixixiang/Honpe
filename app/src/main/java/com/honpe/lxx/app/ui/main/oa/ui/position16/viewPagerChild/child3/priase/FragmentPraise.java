package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean3;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.child.FragmentChild;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.EMPLOYEE_MAIN3;
import static com.honpe.lxx.app.api.FinalClass.EMPLOYEE_THIRD;

/**
 * FileName: FragmentPraise
 * Author: asus
 * Date: 2020/8/12 15:20
 * Description: 员工点评
 */
public class FragmentPraise extends BaseBackFragment {
    StatisticsBean3.DataBean bean = new StatisticsBean3.DataBean();
    @BindView(R.id.rb_good)
    RadioButton rbGood;
    @BindView(R.id.rb_normal)
    RadioButton rbNormal;
    @BindView(R.id.rb_bad)
    RadioButton rbBad;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.et_appraise)
    EditText etAppraise;
    @BindView(R.id.tv_comment_time)
    TextView tvCommentTime;
    @BindView(R.id.tv_excellence_rate)
    TextView tvExcellenceRate;
    @BindView(R.id.child_fragment)
    FrameLayout childFragment;

    private String[] praise = {"优", "良", "差"};
    private int curPos;
    CommitMeatBean commitMeatBean;
    public static FragmentPraise newInstance(StatisticsBean3.DataBean bean) {
        FragmentPraise fragmentPriase = new FragmentPraise();
        fragmentPriase.bean = bean;
        return fragmentPriase;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_priase;
    }

    @Override
    protected void initView() {
        LatteLogger.d("testDDDD", GsonBuildUtil.GsonBuilder(bean));
        rbGood.setText(praise[0]+"("+bean.getGreat()+")");
        rbNormal.setText(praise[1]+"("+bean.getGood()+")");
        rbBad.setText(praise[2]+"("+bean.getBad()+")");
        tvCommentTime.setText(bean.getCommentSum() + "");
        tvExcellenceRate.setText(bean.getExcellenceRate() + "%");

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < group.getChildCount(); index++) {
                    RadioButton rb = (RadioButton) group.getChildAt(index);
                    if (rb.isChecked()) {
                        ToastUtil.getInstance().showToast("快来评价一下吧");
                        etAppraise.setVisibility(View.VISIBLE);
                        curPos = index;
                        changeFragment(FragmentChild.newInstance(bean.getDetails(),praise[index]));
                        break;
                    } else {
                        etAppraise.setVisibility(View.GONE);
                    }
                }
            }
        });

        etAppraise.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    commitMeatBean = new CommitMeatBean(bean.getMealDate(),bean.getMealTimes(), praise[curPos], etAppraise.getText().toString());
                    Event<CommitMeatBean> event = new Event<CommitMeatBean>(EMPLOYEE_MAIN3, commitMeatBean);
                    EventBusUtil.sendEvent(event);
                }
            }
        });
    }

    private void changeFragment(Fragment fm) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.child_fragment, fm);
        ft.commit();
    }

    public  class CommitMeatBean {
        private String MealDate;
        private String MealTimes;
        private String Score;
        private String Content;

        public CommitMeatBean() {
        }

        public CommitMeatBean(String mealDate, String mealTime, String score, String content) {
            MealDate = mealDate;
            MealTimes = mealTime;
            Score = score;
            Content = content;
        }


        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String mealDate) {
            MealDate = mealDate;
        }

        public String getMealTime() {
            return MealTimes;
        }

        public void setMealTime(String mealTime) {
            MealTimes = mealTime;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String score) {
            Score = score;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case EMPLOYEE_THIRD:
                break;
        }
    }
}

