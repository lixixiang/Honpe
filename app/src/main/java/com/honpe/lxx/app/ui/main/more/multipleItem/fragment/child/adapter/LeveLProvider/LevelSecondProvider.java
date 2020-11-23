package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LeveLProvider;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean2;
import com.honpe.lxx.app.ui.main.oa.ui.position1.bean.CheckInBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LevelListAdapter.Level_2;

/**
 * FileName: LevelFirstProvider
 * Author: asus
 * Date: 2020/9/16 10:55
 * Description:
 */
public class LevelSecondProvider extends BaseNodeProvider {
    private String curDay, weekday;
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    private String[] mTypes = {"请假","出差","外出","加班"};
    @Override
    public int getItemViewType() {
        return Level_2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_table_1;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        LevelItemBean2 lv1 = (LevelItemBean2) baseNode;
        LatteLogger.d("testItemData", GsonBuildUtil.GsonBuilder(lv1));
        helper.setBackgroundResource(R.id.time1, R.color.white);
        helper.setBackgroundResource(R.id.time2, R.color.white);
        helper.setBackgroundResource(R.id.time3, R.color.white);
        helper.setBackgroundResource(R.id.time4, R.color.white);
        helper.setBackgroundResource(R.id.time5, R.color.white);
        helper.setBackgroundResource(R.id.time6, R.color.white);

        helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.black));
        helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.black));

        if (lv1 != null) {
            String[][] object = {new String[]{"星期", ""}};
            String[][] object2 = {new String[]{"T", " "}};
            String ymdDate = StringUtil.replace(lv1.getRecordDate(), object2);
            Date start = DateUtil.setDate(ymdDate);
            curDay = myDay.format(start);
            weekday = curDay + "/" + StringUtil.replace(lv1.getWeekName(), object);
            helper.setText(R.id.tv_time_head, weekday);


            if (lv1.getS1() > 0) {
                helper.setBackgroundResource(R.id.time1, R.color.google_red);
                helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("上班迟到" + lv1.getS1() + "分钟");
                    }
                });
            } else {

            }
            if (lv1.getS2() > 0) {
                helper.setBackgroundResource(R.id.time2, R.color.google_red);
                helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("下班早退" + lv1.getS2() + "分钟");
                    }
                });
            } else {

            }
            if (lv1.getS3() > 0) {
                helper.setBackgroundResource(R.id.time3, R.color.google_red);
                helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("上班迟到" + lv1.getS3() + "分钟");
                    }
                });
            } else {

            }
            if (lv1.getS4() > 0) {
                helper.setBackgroundResource(R.id.time4, R.color.google_red);
                helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("下班早退" + lv1.getS4() + "分钟");
                    }
                });
            } else {

            }
            if (lv1.getS5() > 0) {
                helper.setBackgroundResource(R.id.time5, R.color.google_red);
                helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time5).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("上班迟到" + lv1.getS5() + "分钟");
                    }
                });
            } else {

            }
            if (lv1.getS6() > 0) {
                helper.setBackgroundResource(R.id.time6, R.color.google_red);
                helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
                helper.getView(R.id.time6).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast("下班早退" + lv1.getS6() + "分钟");
                    }
                });
            } else {

            }

            if (("漏卡").equals(lv1.getTimer1())) {
                helper.setBackgroundResource(R.id.time1, R.color.google_yellow);
                helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
            }
            if (("漏卡").equals(lv1.getTimer2())) {
                helper.setBackgroundResource(R.id.time2, R.color.google_yellow);
                helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
            }
            if ("漏卡".equals(lv1.getTimer3())) {
                helper.setBackgroundResource(R.id.time3, R.color.google_yellow);
                helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
            }
            if ("漏卡".equals(lv1.getTimer4())) {
                helper.setBackgroundResource(R.id.time4, R.color.google_yellow);
                helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
            }
            if ("漏卡".equals(lv1.getTimer5())) {
                helper.setBackgroundResource(R.id.time5, R.color.google_yellow);
                helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
            }
            if ("漏卡".equals(lv1.getTimer6())) {
                helper.setBackgroundResource(R.id.time6, R.color.google_yellow);
                helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
            }
            helper.setText(R.id.time1, lv1.getTimer1());
            helper.setText(R.id.time2, lv1.getTimer2());
            helper.setText(R.id.time3, lv1.getTimer3());
            helper.setText(R.id.time4, lv1.getTimer4());
            helper.setText(R.id.time5, lv1.getTimer5());
            helper.setText(R.id.time6, lv1.getTimer6());

            if (lv1.getAbnormalRec().contains(StringUtil.useList2(mTypes, "请假"))) {
                changeStatus(helper,R.color.green,"请假",lv1);
            } else if (lv1.getAbnormalRec().contains(StringUtil.useList2(mTypes, "出差"))) {
                changeStatus(helper,R.color.orange,"出差",lv1);
            } else if (lv1.getAbnormalRec().contains(StringUtil.useList2(mTypes, "外出"))) {
                changeStatus(helper,R.color.violet_7B1FA2,"外出",lv1);
            } else if (lv1.getAbnormalRec().contains(StringUtil.useList2(mTypes, "加班"))) {
                changeStatus(helper,R.color.google_coffee,"加班",lv1);
            }

            LinearLayout llTable = helper.getView(R.id.ll_table);
            LinearLayout llContent = helper.getView(R.id.ll_content);

            if (lv1.getAbnormalRec().contains("记录") && (TextUtils.isEmpty(lv1.getTimer1()) &&
                    TextUtils.isEmpty(lv1.getTimer2()) &&
                    TextUtils.isEmpty(lv1.getTimer3()) &&
                    TextUtils.isEmpty(lv1.getTimer4()) &&
                    TextUtils.isEmpty(lv1.getTimer5()) &&
                    TextUtils.isEmpty(lv1.getTimer6()))) {
                llTable.setVisibility(View.GONE);
                llContent.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setText(R.id.tv_content, lv1.getAbnormalRec());
                helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_yellow));
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_yellow));
            } else if (lv1.getAbnormalRec().contains("休息") && (TextUtils.isEmpty(lv1.getTimer1()) &&
                    TextUtils.isEmpty(lv1.getTimer2()) &&
                    TextUtils.isEmpty(lv1.getTimer3()) &&
                    TextUtils.isEmpty(lv1.getTimer4()) &&
                    TextUtils.isEmpty(lv1.getTimer5()) &&
                    TextUtils.isEmpty(lv1.getTimer6()))) {
                llTable.setVisibility(View.GONE);
                llContent.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setText(R.id.tv_content, lv1.getAbnormalRec());
                helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_green));
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_green));
            } else if (lv1.getAbnormalRec().contains("法定") && ("漏卡".equals(lv1.getTimer1()) ||
                    "漏卡".equals(lv1.getTimer2()) ||
                    "漏卡".equals(lv1.getTimer3()) ||
                    "漏卡".equals(lv1.getTimer4()) ||
                    "漏卡".equals(lv1.getTimer5()) ||
                    "漏卡".equals(lv1.getTimer6()))) {
                llTable.setVisibility(View.GONE);
                llContent.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_time_head2, weekday);
                helper.setText(R.id.tv_content, lv1.getAbnormalRec());
                helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.google_green));
                helper.setTextColor(R.id.tv_time_head2, getContext().getResources().getColor(R.color.google_green));
            } else {
                llTable.setVisibility(View.VISIBLE);
                llContent.setVisibility(View.GONE);
            }
        }
    }

    private void changeStatus(BaseViewHolder helper, int bgColor, String strType, LevelItemBean2 item){
        if (("漏卡").equals(item.getTimer1())) {
            helper.setBackgroundResource(R.id.time1, R.color.google_yellow);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time1,strType);
            helper.setBackgroundResource(R.id.time1, bgColor);
            helper.setTextColor(R.id.time1, getContext().getResources().getColor(R.color.white));
        }
        if (("漏卡").equals(item.getTimer2())) {
            helper.setBackgroundResource(R.id.time2, R.color.google_yellow);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time2,strType);
            helper.setBackgroundResource(R.id.time2, bgColor);
            helper.setTextColor(R.id.time2, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer3())) {
            helper.setBackgroundResource(R.id.time3, R.color.google_yellow);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time3,strType);
            helper.setBackgroundResource(R.id.time3, bgColor);
            helper.setTextColor(R.id.time3, getContext().getResources().getColor(R.color.white));

        }
        if ("漏卡".equals(item.getTimer4())) {
            helper.setBackgroundResource(R.id.time4, R.color.google_yellow);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time4,strType);
            helper.setBackgroundResource(R.id.time4, bgColor);
            helper.setTextColor(R.id.time4, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer5())) {
            helper.setBackgroundResource(R.id.time5, R.color.google_yellow);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time5,strType);
            helper.setBackgroundResource(R.id.time5, bgColor);
            helper.setTextColor(R.id.time5, getContext().getResources().getColor(R.color.white));
        }
        if ("漏卡".equals(item.getTimer6())) {
            helper.setBackgroundResource(R.id.time6, R.color.google_yellow);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
            helper.setText(R.id.time6,strType);
            helper.setBackgroundResource(R.id.time6, bgColor);
            helper.setTextColor(R.id.time6, getContext().getResources().getColor(R.color.white));
        }
    }
}
