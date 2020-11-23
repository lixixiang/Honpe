package com.honpe.lxx.app.ui.main.oa.ui.position13.adapter;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RepairDetailAdapter
 * Author: asus
 * Date: 2020/9/2 12:29
 * Description: 维修详情适配
 */
public class RepairDetailAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {
    List<RadioButton> listRB = new ArrayList<>();
    RepairBean.DataEntity entity = new RepairBean.DataEntity();

    public RepairDetailAdapter(List<MultiItemBean> data) {
        super(data);
        addItemType(MultiItemBean.HEAD, R.layout.item_repair);
        addItemType(MultiItemBean.TEXT_EDIT, R.layout.item_text_edit);
        addItemType(MultiItemBean.TEXT, R.layout.css_text_1);
        addItemType(MultiItemBean.RADIO, R.layout.css_radiobutton);
        addItemType(MultiItemBean.LINE, R.layout.css_line);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {
        switch (helper.getItemViewType()) {
            case MultiItemBean.HEAD:
                TextView tvTypeName = helper.getView(R.id.tv_type_name);
                TextView tvContent = helper.getView(R.id.tv_repair_name);
                TextView tvStatus = helper.getView(R.id.tv_status);
                TextView tvTime = helper.getView(R.id.tv_time);
                TextView tvNo = helper.getView(R.id.tv_no);
                TextView tvDepart = helper.getView(R.id.tv_depart);
                TextView tvApplyName = helper.getView(R.id.tv_apply_name);
                LinearLayout ll_head_text = helper.getView(R.id.ll_head_text);
                ll_head_text.setPadding(50, 40, 40, 40);

              
                if (item.getType() == 0) {
                    tvTypeName.setText("外部维修" + "：");
                } else {
                    tvTypeName.setText("内部维修" + "：");
                }
                tvContent.setText(item.getContent());
                tvTime.setText(item.getStrDate());
                tvApplyName.setText(item.getApplyName());
                tvNo.setText(item.getId());
                tvDepart.setText(item.getStrDepart() + "：");
                tvStatus.setVisibility(View.GONE);
                break;
            case MultiItemBean.TEXT:
                LinearLayout llBG = helper.getView(R.id.ll_css1);
                TextView textView = helper.getView(R.id.tv_record_text);
                textView.setText(item.getStrTitle());

                if (helper.getAdapterPosition() == 1) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(getContext(), 35));
                    llBG.setGravity(Gravity.BOTTOM);
                    llBG.setLayoutParams(params);
                    llBG.setBackgroundResource(R.color.grey_background);
                    Utils.TextSize(textView, 40);
                } else {
                    llBG.setBackgroundResource(R.color.white);
                    llBG.setGravity(Gravity.CENTER_VERTICAL);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Utils.sp2px(getContext(), 14));
                }

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast(helper.getAdapterPosition());
                    }
                });
                break;
            case MultiItemBean.TEXT_EDIT:
                TextView tv = helper.getView(R.id.tv_title);
                EditText et = helper.getView(R.id.et_content);
                LinearLayout ll = helper.getView(R.id.ll_title_edit);
                tv.setText(item.getStrTitle());
                et.setFocusable(false);
                et.setFocusableInTouchMode(false);
                et.setEnabled(false);
                et.setTextColor(getContext().getResources().getColor(R.color.blue));
                switch (helper.getAdapterPosition()) {
                    case 2:
                        et.setText(item.getContent());
                        break;
                    case 3:
                        et.setText(item.getAddress());
                        break;
                    case 4:
                        et.setText(item.getReason());
                        break;
                    case 5:
                        break;
                    case 6:
                        et.setText(item.getAdminSigh());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 7:
                    case 8:
                    case 9:
                        et.setText(item.getWorksConfirmed());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 10:
                        et.setText(item.getPresetTime());
                        et.setEnabled(true);
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 11:
                        et.setText(item.getDescription());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 12:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 13:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 14:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 15:
                        et.setText(item.getContent1());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 17:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 18:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 19:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 21:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                    case 22:
                        et.setText(item.getContent());
                        StringUtil.HintUtil(et, item.getHint());
                        break;
                }

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.getInstance().showToast(helper.getAdapterPosition());
                    }
                });
                break;
            case MultiItemBean.RADIO:
                RadioButton radioButton = helper.getView(R.id.rb);
                radioButton.setText(item.getStrTitle());
                if (listRB.size() < 4) {
                    listRB.add(radioButton);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < listRB.size(); i++) {
                                listRB.get(i).setChecked(false);
                                listRB.get(i).setTextColor(Color.GRAY);
                            }
                            if (helper.getAdapterPosition() >= 19 && helper.getAdapterPosition() <= 20) {
                                listRB.get(helper.getAdapterPosition() - 19).setChecked(true);
                                listRB.get(helper.getAdapterPosition() - 19).setTextColor(Color.RED);
                            } else if (helper.getAdapterPosition() >= 22 && helper.getAdapterPosition() <= 23) {
                                listRB.get(helper.getAdapterPosition() - 20).setChecked(true);
                                listRB.get(helper.getAdapterPosition() - 20).setTextColor(Color.RED);
                            }
                        }
                    });
                }
                break;
        }
    }
}








