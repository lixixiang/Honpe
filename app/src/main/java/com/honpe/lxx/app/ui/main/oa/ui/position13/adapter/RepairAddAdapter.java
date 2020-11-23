package com.honpe.lxx.app.ui.main.oa.ui.position13.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairAddBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: MultipleItemQuickAdapter
 * Author: asus
 * Date: 2020/9/1 16:50
 * Description:
 */
public class RepairAddAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {

    List<RadioButton> listRB = new ArrayList<>();
    RepairAddBean.RepairBillDetailsModelEntity repairBean = new RepairAddBean.RepairBillDetailsModelEntity();
    List<RepairAddBean.RepairBillDetailsModelEntity> listModelEntity = new ArrayList<>();
    RepairBean.DataEntity bean;
    RepairAddBean entity = new RepairAddBean();
    public RepairAddAdapter(List<MultiItemBean> data, RepairBean.DataEntity entity) {
        super(data);
        addItemType(MultiItemBean.TEXT_RADIO, R.layout.item_text_radio);
        addItemType(MultiItemBean.TEXT, R.layout.css_text_1);
        addItemType(MultiItemBean.RADIO, R.layout.css_radiobutton);
        addItemType(MultiItemBean.TEXT_EDIT, R.layout.item_text_edit);
        if (entity != null) {
            bean = entity;
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {

        switch (helper.getItemViewType()) {
            case MultiItemBean.TEXT_RADIO:
                RadioGroup rg = helper.getView(R.id.rg);
                RadioButton left = helper.getView(R.id.rb_left);
                RadioButton right = helper.getView(R.id.rb_right);
               if (bean !=null&&bean.get维修类型()==1){
                   left.setChecked(true);
               }else if (bean !=null&&bean.get维修类型()==0){
                   right.setChecked(true);
               }
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButton = rg.findViewById(rg.getCheckedRadioButtonId());
                        int mType = -1;
                        if (radioButton.getText().toString().equals("内部")) {
                            mType = 1;
                        } else if (radioButton.getText().toString().equals("外部")) {
                            mType = 0;
                        }
                        entity.set维修类型(mType);
                        Event<RepairAddBean> event = new Event<RepairAddBean>(FinalClass.Add_REPAIR_LIST_TOAST, entity);
                        EventBusUtil.sendEvent(event);
                    }
                });

                break;
            case MultiItemBean.TEXT:
                if (helper.getAdapterPosition() == 1) {
                    helper.setText(R.id.tv_record_text, item.getStrTitle());
                } else if (helper.getAdapterPosition() == 10) {
                    LinearLayout llBG = helper.getView(R.id.ll_css1);
                    TextView textView = helper.getView(R.id.tv_record_text);
                    llBG.setBackgroundResource(R.color.grey_background);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(getContext(), 35));
                    llBG.setGravity(Gravity.BOTTOM);
                    llBG.setLayoutParams(params);
                    textView.setText(item.getStrTitle());
                    Utils.TextSize(textView, 40);
                }
                break;
            case MultiItemBean.RADIO:
                helper.setText(R.id.rb, item.getStrTitle());
                RadioButton radioButton = helper.getView(R.id.rb);
                listRB.add(radioButton);
                repairBean.set维修人("");
                repairBean.set序号(1);
                repairBean.set完成时间("");
                if (bean != null) {
                   if (bean.getRepairBillDetailsModel().get(0).get要求时限().equals(radioButton.getText().toString())){
                       radioButton.setChecked(true);
                      repairBean.set要求时限(bean.getRepairBillDetailsModel().get(0).get要求时限());
                   }
                }
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listModelEntity.clear();
                        for (int i = 0; i < listRB.size(); i++) {
                            listRB.get(i).setChecked(false);
                        }
                        if (helper.getAdapterPosition() >= 5 && helper.getAdapterPosition() <= 6) {
                            listRB.get(helper.getAdapterPosition() - 3).setChecked(true);
                            repairBean.set要求时限(listRB.get(helper.getAdapterPosition() - 3).getText().toString());
                        } else if (helper.getAdapterPosition() >= 7 && helper.getAdapterPosition() <= 9) {
                            if (helper.getAdapterPosition() == 9) {

                            }
                            listRB.get(helper.getAdapterPosition() - 4).setChecked(true);
                            repairBean.set要求时限(listRB.get(helper.getAdapterPosition() - 4).getText().toString());
                        } else if (helper.getAdapterPosition() >= 2 && helper.getAdapterPosition() <= 3) {
                            listRB.get(helper.getAdapterPosition() - 2).setChecked(true);
                            repairBean.set要求时限(listRB.get(helper.getAdapterPosition() - 2).getText().toString());
                        }
                        listModelEntity.add(repairBean);
                        entity.setRepairBillDetailsModel(listModelEntity);
                        Event<RepairAddBean> event = new Event<RepairAddBean>(FinalClass.Add_REPAIR_LIST_TOAST, entity);
                        EventBusUtil.sendEvent(event);
                    }
                });
                break;
            case MultiItemBean.TEXT_EDIT:
                TextView tvTitle = helper.getView(R.id.tv_title);
                EditText et = helper.getView(R.id.et_content);
                tvTitle.setText(item.getStrTitle());
                StringUtil.HintUtil(et, item.getHint());
                if (bean != null) {
                    if (helper.getAdapterPosition() == 11) {
                        et.setText(bean.getF_TypeContext());
                        entity.setF_TypeContext(et.getText().toString());
                    } else if (helper.getAdapterPosition() == 12) {
                        et.setText(bean.getRepairBillDetailsModel().get(0).get问题描述());
                        repairBean.set问题描述(bean.getRepairBillDetailsModel().get(0).get问题描述());
                    } else if (helper.getAdapterPosition() == 13) {
                        et.setText(bean.getRepairBillDetailsModel().get(0).get位置());
                        repairBean.set位置(bean.getRepairBillDetailsModel().get(0).get位置());
                    } else if (helper.getAdapterPosition() == 14) {
                        et.setText(bean.get报修原因());
                        entity.set报修原因(bean.get报修原因());
                    }
                }

                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        listModelEntity.clear();
                        switch (helper.getAdapterPosition()) {
                            case 11:
                                entity.setF_TypeContext(et.getText().toString());
                                break;
                            case 12:
                                repairBean.set问题描述(et.getText().toString());
                                break;
                            case 13:
                                repairBean.set位置(et.getText().toString());
                                break;
                            case 14:
                                entity.set报修原因(et.getText().toString());
                                break;
                        }
                        listModelEntity.add(repairBean);
                        entity.setRepairBillDetailsModel(listModelEntity);
//                        LatteLogger.d("testData", GsonBuildUtil.GsonBuilder(entity));
                        Event<RepairAddBean> event = new Event<RepairAddBean>(FinalClass.Add_REPAIR_LIST_TOAST, entity);
                        EventBusUtil.sendEvent(event);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
        }
    }
}









