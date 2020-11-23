package com.honpe.lxx.app.ui.main.oa.ui.position9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position9.adapter.listener.OnInitSelectedPosition;
import com.honpe.lxx.app.ui.main.oa.ui.position9.bean.TagInfo;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 12:29
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class DriverTagAdapter extends BaseAdapter implements OnInitSelectedPosition {
    private Context mContext;
    private List<TagInfo> mDateList;

    public DriverTagAdapter(Context mContext, List<TagInfo> mDateList) {
        this.mContext = mContext;
        this.mDateList = mDateList;
    }

    @Override
    public int getCount() {
        return mDateList == null ? 0 : mDateList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        TextView textView = view.findViewById(R.id.tv_tag);
        TagInfo tagInfo = mDateList.get(position);
        textView.setText(tagInfo.getText());

        view.setTag(tagInfo);
        return view;
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
