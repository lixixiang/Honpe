package com.honpe.lxx.app.ui.main.oa.ui.position2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.utils.LatteLogger;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: BaseStepAdapter
 * Author: asus
 * Date: 2020/10/8 23:13
 * Description: 审批步骤
 */
public class BaseStepAdapter extends BaseAdapter {
    private Context context;
    private List<String> stepList;
    private LayoutInflater inflater;

    public BaseStepAdapter(Context context, List<String> stepList) {
        this.context = context;
        this.stepList = stepList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stepList.size();
    }


    @Override
    public Object getItem(int position) {
        return stepList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_step, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String str = stepList.get(position);
        LatteLogger.d("testStep", str);
        ViewGroup.LayoutParams para;
        para = holder.ivStatus.getLayoutParams();
        para.height = DensityUtil.dp2px(context, 20);
        para.width = DensityUtil.dp2px(context, 20);
        holder.ivStatus.setLayoutParams(para);
        holder.tvStep.setText(position + 1 + "");
        holder.tvContent.setText(str);
        if (str.contains("尚未")) {
            holder.tvContent.setTextColor(context.getResources().getColor(R.color.grey_home));
            holder.tvContent.setText(str);
        } else {
            holder.tvContent.setTextColor(context.getResources().getColor(R.color.green));
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvTopLine)
        View tvTopLine;
        @BindView(R.id.tv_step)
        TextView tvStep;
        @BindView(R.id.iv_status)
        FrameLayout ivStatus;
        @BindView(R.id.ivBottomLine)
        View ivBottomLine;
        @BindView(R.id.rl_left)
        RelativeLayout rlLeft;
        @BindView(R.id.tx_action)
        LinearLayout txAction;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.re_time_line)
        LinearLayout reTimeLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
