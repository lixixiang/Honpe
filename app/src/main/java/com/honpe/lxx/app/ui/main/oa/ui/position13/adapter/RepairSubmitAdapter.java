package com.honpe.lxx.app.ui.main.oa.ui.position13.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import java.util.List;

import static com.honpe.lxx.app.api.FinalClass.INTENT_REPAIR_DATA;

/**
 * FileName: RepairSubmitAda
 * Author: asus
 * Date: 2020/9/4 16:27
 * Description:
 */
public class RepairSubmitAdapter extends BaseMultiItemQuickAdapter<MultiItemBean, BaseViewHolder> {
    RepairBean.DataEntity entity = new RepairBean.DataEntity();
    String title;

    public RepairSubmitAdapter(String title, List<MultiItemBean> data) {
        super(data);
        addItemType(MultiItemBean.HEAD, R.layout.item_text_edit);
        addItemType(MultiItemBean.TEXT_EDIT, R.layout.item_text_edit);
        addItemType(MultiItemBean.TEXT, R.layout.item_text_edit);
        addItemType(MultiItemBean.RADIO, R.layout.item_text_edit);
        addItemType(MultiItemBean.LINE, R.layout.item_text_edit);
        this.title = title;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemBean item) {
        TextView tv = helper.getView(R.id.tv_title);
        EditText et = helper.getView(R.id.et_content);
        tv.setText(item.getStrTitle());
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);
        et.setEnabled(false);
        switch (helper.getItemViewType()) {
            case MultiItemBean.HEAD:

                switch (helper.getAdapterPosition()) {
                    case 0:
                        et.setText(item.getContent());
                        break;
                    case 1:
                        et.setText(item.getContent1());
                        break;
                    case 2:
                        et.setEnabled(true);
                        et.setText(item.getContent2());
                        break;
                    case 3:
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.setEnabled(true);
                        et.setText(item.getContent3());
                        et.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                entity.set电工鉴定(s.toString());
                                Event<RepairBean.DataEntity> event = new Event<RepairBean.DataEntity>(INTENT_REPAIR_DATA, entity);
                                EventBusUtil.sendEvent(event);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        break;
                }
                break;
            case MultiItemBean.TEXT_EDIT:
                switch (helper.getAdapterPosition()) {
                    case 0:
                        et.setText(item.getContent());
                        break;
                    case 1:
                        et.setEnabled(true);
                        et.setText(item.getContent1());
                        break;
                }
                break;
            case MultiItemBean.TEXT:
                switch (helper.getAdapterPosition()) {
                    case 0:
                        et.setText(item.getContent());
                        break;
                    case 1:
                        et.setText(item.getContent1());
                        break;
                    case 2:
                        et.setText(item.getContent2());
                        break;
                    case 3:
                        et.setEnabled(true);
                        et.setText(item.getContent3());
                        break;
                    case 4:
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.setEnabled(true);
                        et.setText(item.getContent4());
                        et.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                entity.set鉴定结果(s.toString());
                                Event<RepairBean.DataEntity> event = new Event<RepairBean.DataEntity>(INTENT_REPAIR_DATA, entity);
                                EventBusUtil.sendEvent(event);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        break;
                }
                break;
            case MultiItemBean.RADIO:
                switch (helper.getAdapterPosition()) {
                    case 0:
                        et.setText(item.getContent());
                        break;
                    case 1:
                        et.setEnabled(true);
                        et.setText(item.getContent1());
                        break;
                    case 2:
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.setEnabled(true);
                        et.setText(item.getContent2());
                        et.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                entity.setF_AdminDepart(s.toString());
                                Event<RepairBean.DataEntity> event = new Event<RepairBean.DataEntity>(INTENT_REPAIR_DATA, entity);
                                EventBusUtil.sendEvent(event);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        break;
                }
                break;
            case MultiItemBean.LINE:
                switch (helper.getAdapterPosition()) {
                    case 0:
                        et.setText(item.getContent());
                        break;
                    case 1:
                        et.setEnabled(true);
                        et.setText(item.getContent1());
                        break;
                    case 2:
                        et.setFocusable(true);
                        et.setFocusableInTouchMode(true);
                        et.setEnabled(true);
                        et.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                entity.set验收结果(s.toString());
                                Event<RepairBean.DataEntity> event = new Event<RepairBean.DataEntity>(INTENT_REPAIR_DATA, entity);
                                EventBusUtil.sendEvent(event);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        break;
                    case 3:
                        et.setEnabled(true);
                        et.setText(item.getContent3());
                        break;
                }
                break;
        }
    }
}
























