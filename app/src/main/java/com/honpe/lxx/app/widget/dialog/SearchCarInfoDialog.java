package com.honpe.lxx.app.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BaseDialog;
import com.honpe.lxx.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: SearchCarInfoDialog
 * Author: asus
 * Date: 2020/10/22 10:04
 * Description:
 */
public class SearchCarInfoDialog extends BaseDialog<SearchCarInfoDialog> {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub1)
    TextView tvSub1;
    @BindView(R.id.tv_content1)
    EditText tvContent1;
    @BindView(R.id.ll_sub1)
    LinearLayout llSub1;
    @BindView(R.id.tv_sub2)
    TextView tvSub2;
    @BindView(R.id.tv_content2)
    EditText tvContent2;
    @BindView(R.id.ll_sub2)
    LinearLayout llSub2;
    @BindView(R.id.no)
    Button no;
    @BindView(R.id.yes)
    Button yes;

   private String title,sub1,sub2;

    public SearchCarInfoDialog(Context context,String title,String sub1,String sub2) {
        super(context);
        this.title = title;
        this.sub1 = sub1;
        this.sub2 = sub2;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflate = View.inflate(mContext, R.layout.dialog_search_car_info, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tvTitle.setText(title);
        tvSub1.setText(sub1);
        tvSub2.setText(sub2);

    }
}

























