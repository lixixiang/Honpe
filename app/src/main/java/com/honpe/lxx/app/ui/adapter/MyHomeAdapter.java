package com.honpe.lxx.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.utils.LatteLogger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by lxx at 2020/4/7 16:03
 * 描述:
 */
public class MyHomeAdapter extends BaseAdapter {

    private Context context;
    private List<MyHomeBean> mList;
    private LayoutInflater mInflater;

    public MyHomeAdapter(Context context, List<MyHomeBean> mList) {
        this.context = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.css_img_txt, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
          holder = (ViewHolder) convertView.getTag();
        }
        MyHomeBean bean = mList.get(position);
        LatteLogger.d("test",bean.isEnable()+"");
        if (!bean.isEnable()) {
            holder.ivIcon.setColorFilter(context.getResources().getColor(R.color.grey_home));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.grey_home));
        }
        int drawableId = context.getResources().getIdentifier(bean.getIco(),"mipmap", context.getPackageName());
        holder.ivIcon.setImageResource(drawableId);
        holder.tvTitle.setText(bean.getTitle());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView ivIcon;
        @BindView(R.id.tv)
        TextView tvTitle;
        @BindView(R.id.ll)
        LinearLayout llSub;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

















