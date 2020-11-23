package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean1;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.dialog.MenuListDialog;
import com.honpe.lxx.app.widget.msgtips.Badge;
import com.honpe.lxx.app.widget.msgtips.QBadgeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.shts.android.library.TriangleLabelView;

/**
 * 包名: com.example.lxx.myalipay.ui.activity.interenal_staff.inner_self.inner_child.c_my_query.fragment.child.position16.childfragment1
 * 作者: lxx
 * 日期: 2019/4/11 11:30
 * 描述: 员工报餐
 */
public class MyListView extends BaseAdapter {
    private Activity mContext;
    List<StatisticsBean1.DataBean> mList;
    private LayoutInflater mInflater;

    SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
    CheckBox ckBreakfast, ckLunch, ckAfter, ckNight;
    private int testBool, testBool2, testBool3, testBool4;

    public MyListView(Activity mContext, List<StatisticsBean1.DataBean> mList, CheckBox ckBreakfast, CheckBox ckLunch, CheckBox ckAfter, CheckBox ckNight) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.ckBreakfast = ckBreakfast;
        this.ckLunch = ckLunch;
        this.ckAfter = ckAfter;
        this.ckNight = ckNight;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_check_box_table, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        StatisticsBean1.DataBean bean = mList.get(position);
        String mDate = sf.format(DateUtil.setDate(bean.getMealDate()));
        holder.tvDate.setText(mDate);
        holder.tvWeek.setText(String.valueOf(bean.getWeek()));

        if (bean.isHasBreakFast() && bean.isHasLunch()
                && bean.isHasDinner() && bean.isHasMidnight()) {
            bean.setHasHorizontal(true);
        } else {
            bean.setHasHorizontal(false);
        }

        holder.ckChildBreakfast.setId(position);
        holder.ckChildLunch.setId(position);
        holder.ckChildNight.setId(position);
        holder.ckChildNightSnack.setId(position);
        holder.ckChildAll.setId(position);

        holder.ckChildBreakfast.setChecked(bean.isHasBreakFast());
        holder.ckChildLunch.setChecked(bean.isHasLunch());
        holder.ckChildNight.setChecked(bean.isHasDinner());
        holder.ckChildNightSnack.setChecked(bean.isHasMidnight());
        holder.ckChildAll.setChecked(bean.isHasHorizontal());


        //需求设置第一行不可点击
        if (position == 0) {
            holder.tvDate.setTextColor(mContext.getResources().getColor(R.color.grey_home));
            holder.tvWeek.setTextColor(mContext.getResources().getColor(R.color.grey_home));
            holder.labelView.setTriangleBackgroundColor(mContext.getResources().getColor(R.color.grey_home));
            holder.ckChildBreakfast.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_home)));
            holder.ckChildLunch.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_home)));
            holder.ckChildNight.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_home)));
            holder.ckChildNightSnack.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_home)));
            holder.ckChildAll.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_home)));
            holder.ckChildBreakfast.setEnabled(false);
            holder.ckChildLunch.setEnabled(false);
            holder.ckChildNight.setEnabled(false);
            holder.ckChildNightSnack.setEnabled(false);
            holder.ckChildAll.setEnabled(false);
        } else {
            holder.tvDate.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.tvWeek.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.labelView.setTriangleBackgroundColor(mContext.getResources().getColor(R.color.green));
            if (holder.ckChildBreakfast.isChecked()) {
                holder.ckChildBreakfast.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
            } else {
                holder.ckChildBreakfast.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
            }
            if (holder.ckChildLunch.isChecked()) {
                holder.ckChildLunch.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
            } else {
                holder.ckChildLunch.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
            }
            if (holder.ckChildNight.isChecked()) {
                holder.ckChildNight.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
            } else {
                holder.ckChildNight.setButtonTintList(StringUtil.createColorStateList(mContext.getResources().getColor(R.color.grey_dark), Color.GREEN, Color.GREEN, Color.GREEN));
            }
            if (holder.ckChildNightSnack.isChecked()) {
                holder.ckChildNightSnack.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
            } else {
                holder.ckChildNightSnack.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
            }
            if (holder.ckChildAll.isChecked()) {
                holder.ckChildAll.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
            } else {
                holder.ckChildAll.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
            }

            holder.ckChildBreakfast.setEnabled(true);
            holder.ckChildLunch.setEnabled(true);
            holder.ckChildNight.setEnabled(true);
            holder.ckChildNightSnack.setEnabled(true);
            holder.ckChildAll.setEnabled(true);
        }

        final ViewHolder finalHolder = holder;
        //点击时把值修改下
        holder.ckChildBreakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasBreakFast(isChecked);
                mList.set(buttonView.getId(), dat);
                testBool = 0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isHasBreakFast()) {
                        testBool++;
                    }
                }
                if (testBool == 30 || testBool == 31) {
                    ckBreakfast.setChecked(true);
                } else {
                    ckBreakfast.setChecked(false);
                }

                if (isChecked) {
                    finalHolder.ckChildBreakfast.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
                } else {
                    finalHolder.ckChildBreakfast.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
                }

                if (finalHolder.ckChildBreakfast.isChecked() && finalHolder.ckChildLunch.isChecked()
                        && finalHolder.ckChildNight.isChecked() && finalHolder.ckChildNightSnack.isChecked()) {
                    finalHolder.ckChildAll.setChecked(true);
                    dat.setHasHorizontal(true);
                } else {
                    finalHolder.ckChildAll.setChecked(false);
                    dat.setHasHorizontal(false);
                }
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);

            }
        });


        holder.ckChildLunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasLunch(isChecked);
                mList.set(buttonView.getId(), dat);
                testBool2 = 0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isHasLunch()) {
                        testBool2++;
                    }
                }
                if (testBool2 == 30 || testBool2 == 31) {
                    ckLunch.setChecked(true);
                } else {
                    ckLunch.setChecked(false);
                }
                if (isChecked) {
                    finalHolder.ckChildLunch.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
                } else {
                    finalHolder.ckChildLunch.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
                }

                if (finalHolder.ckChildBreakfast.isChecked() && finalHolder.ckChildLunch.isChecked()
                        && finalHolder.ckChildNight.isChecked() && finalHolder.ckChildNightSnack.isChecked()) {
                    finalHolder.ckChildAll.setChecked(true);
                    dat.setHasHorizontal(true);
                } else {
                    finalHolder.ckChildAll.setChecked(false);
                    dat.setHasHorizontal(false);
                }
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasDinner(isChecked);
                mList.set(buttonView.getId(), dat);
                testBool3 = 0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isHasDinner()) {
                        testBool3++;
                    }
                }
                if (testBool3 == 30 || testBool3 == 31) {
                    ckAfter.setChecked(true);
                } else {
                    ckAfter.setChecked(false);
                }

                if (isChecked) {
                    finalHolder.ckChildNight.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
                } else {
                    finalHolder.ckChildNight.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
                }

                if (finalHolder.ckChildBreakfast.isChecked() && finalHolder.ckChildLunch.isChecked()
                        && finalHolder.ckChildNight.isChecked() && finalHolder.ckChildNightSnack.isChecked()) {
                    finalHolder.ckChildAll.setChecked(true);
                    dat.setHasHorizontal(true);
                } else {
                    finalHolder.ckChildAll.setChecked(false);
                    dat.setHasHorizontal(false);
                }
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildNightSnack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasMidnight(isChecked);
                mList.set(buttonView.getId(), dat);
                testBool4 = 0;
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isHasMidnight()) {
                        testBool4++;
                    }
                }
                if (testBool4 == 30 || testBool4 == 31) {
                    ckNight.setChecked(true);
                } else {
                    ckNight.setChecked(false);
                }
                if (isChecked) {
                    finalHolder.ckChildNightSnack.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
                } else {
                    finalHolder.ckChildNightSnack.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
                }

                if (finalHolder.ckChildBreakfast.isChecked() && finalHolder.ckChildLunch.isChecked()
                        && finalHolder.ckChildNight.isChecked() && finalHolder.ckChildNightSnack.isChecked()) {
                    finalHolder.ckChildAll.setChecked(true);
                    dat.setHasHorizontal(true);
                } else {
                    finalHolder.ckChildAll.setChecked(false);
                    dat.setHasHorizontal(false);
                }
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.ckChildAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StatisticsBean1.DataBean dat = mList.get(buttonView.getId());
                dat.setHasHorizontal(isChecked);

                mList.set(buttonView.getId(), dat);

                if (isChecked) {
                    finalHolder.ckChildAll.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.green)));
                } else {
                    finalHolder.ckChildAll.setButtonTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.grey_dark)));
                }
                if (isChecked) {
                    finalHolder.ckChildAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dat.setHasLunch(true);
                            dat.setHasDinner(true);
                            dat.setHasMidnight(true);
                            dat.setHasBreakFast(true);
                            finalHolder.ckChildBreakfast.setChecked(dat.isHasBreakFast());
                            finalHolder.ckChildLunch.setChecked(dat.isHasLunch());
                            finalHolder.ckChildNight.setChecked(dat.isHasDinner());
                            finalHolder.ckChildNightSnack.setChecked(dat.isHasMidnight());
                            finalHolder.ckChildAll.setChecked(dat.isHasHorizontal());
                        }
                    });

                } else {
                    finalHolder.ckChildAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dat.setHasLunch(false);
                            dat.setHasDinner(false);
                            dat.setHasMidnight(false);
                            dat.setHasBreakFast(false);
                            finalHolder.ckChildBreakfast.setChecked(dat.isHasBreakFast());
                            finalHolder.ckChildLunch.setChecked(dat.isHasLunch());
                            finalHolder.ckChildNight.setChecked(dat.isHasDinner());
                            finalHolder.ckChildNightSnack.setChecked(dat.isHasMidnight());
                            finalHolder.ckChildAll.setChecked(dat.isHasHorizontal());
                        }
                    });
                }
                Event<StatisticsBean1.DataBean> event = new Event<StatisticsBean1.DataBean>(FinalClass.REPORT_STAFF,
                        new StatisticsBean1.DataBean(dat.getMealDate(), ""
                                , dat.getWeek(), dat.isHasBreakFast(), dat.isHasLunch(), dat.isHasDinner(), dat.isHasMidnight()));
                EventBusUtil.sendEvent(event);
            }
        });

        holder.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuListDialog dialog = new MenuListDialog(mContext,bean.getMealDate(),finalHolder.tvDate.getText().toString());
                dialog.show();
                Window window = dialog.getWindow();
                window.setWindowAnimations(R.style.MenuAnim);

                //设置显示位置
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
        return view;
    }

    //全选
    public void selectAll() {
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                if (i != 0) {
                    mList.get(i).setHasBreakFast(true);
                    mList.get(i).setHasLunch(true);
                    mList.get(i).setHasDinner(true);
                    mList.get(i).setHasMidnight(true);
                    mList.get(i).setHasHorizontal(true);
                }
            }
            this.notifyDataSetChanged();
        }
    }

    //取消全选
    public void cancelAll() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0) {
                mList.get(i).setHasBreakFast(false);
                mList.get(i).setHasLunch(false);
                mList.get(i).setHasDinner(false);
                mList.get(i).setHasMidnight(false);
                mList.get(i).setHasHorizontal(false);
            }
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 早餐
    public void selectVertical() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0) {
                mList.get(i).setHasBreakFast(true);
            }
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 早餐
    public void cancelVertical() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0) {
                mList.get(i).setHasBreakFast(false);
            }
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 午餐
    public void selectVertical2() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0) {
                mList.get(i).setHasLunch(true);
            }
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 午餐
    public void cancelVertical2() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0) {
                mList.get(i).setHasLunch(false);
            }
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 晚餐
    public void selectVertical3() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0)
                mList.get(i).setHasDinner(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 晚餐
    public void cancelVertical3() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0)
                mList.get(i).setHasDinner(false);
        }
        this.notifyDataSetChanged();
    }

    //选择垂直 夜宵
    public void selectVertical4() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0)
                mList.get(i).setHasMidnight(true);
        }
        this.notifyDataSetChanged();
    }

    //取消垂直 夜宵
    public void cancelVertical4() {
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0)
                mList.get(i).setHasMidnight(false);
        }
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_date)
        TextView tvDate;
        @BindView(R.id.tv_item_week)
        TextView tvWeek;
        @BindView(R.id.ck_child_breakfast)
        CheckBox ckChildBreakfast;
        @BindView(R.id.ck_child_lunch)
        CheckBox ckChildLunch;
        @BindView(R.id.ck_child_night)
        CheckBox ckChildNight;
        @BindView(R.id.ck_child_night_snack)
        CheckBox ckChildNightSnack;
        @BindView(R.id.ck_child_all)
        CheckBox ckChildAll;
        @BindView(R.id.item1)
        LinearLayout item1;
        @BindView(R.id.item2)
        LinearLayout item2;
        @BindView(R.id.item3)
        LinearLayout item3;
        @BindView(R.id.item4)
        LinearLayout item4;
        @BindView(R.id.item5)
        LinearLayout item5;
        @BindView(R.id.lab)
        TriangleLabelView labelView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

















