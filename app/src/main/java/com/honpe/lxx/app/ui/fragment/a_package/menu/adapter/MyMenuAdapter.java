package com.honpe.lxx.app.ui.fragment.a_package.menu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.AppConfig;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.ui.fragment.a_package.menu.MenuManagerFragment;
import com.honpe.lxx.app.widget.drag.DragAdapterInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: MyMenuAdapter
 * Author: asus
 * Date: 2020/8/27 13:39
 * Description: 我的应用适配器
 */
public class MyMenuAdapter extends BaseAdapter implements DragAdapterInterface {
    private boolean IsEdit = false;
    private List<MyHomeBean> datas = new ArrayList<>();
    private Context context;
    private MyApplication appContext;
    private MenuManagerFragment fragment;

    public MyMenuAdapter(Context context, MenuManagerFragment fragment, MyApplication appContext, List<MyHomeBean> datas) {
        this.context = context;
        this.appContext = appContext;
        this.datas = datas;
        this.fragment = fragment;
    }

    public void setDatas(List<MyHomeBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHomeBean bean = datas.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.DelMenu(datas.get(position),position);
                datas.remove(position);
                String key = AppConfig.KEY_USER_TEMP;
                appContext.saveObject((Serializable) datas, key);
            }
        });
        if (!bean.isEnable()) {
            holder.iconImg.setColorFilter(context.getResources().getColor(R.color.grey_home));
            holder.nameTv.setTextColor(context.getResources().getColor(R.color.grey_home));
        }
        if (IsEdit) {
            holder.deleteImg.setVisibility(View.VISIBLE);
        }else {
            holder.deleteImg.setVisibility(View.GONE);
        }

        //获取资源图片
        int drawableId = context.getResources().getIdentifier(bean.getIco(), "mipmap", context.getPackageName());
        holder.iconImg.setImageResource(drawableId);
        holder.nameTv.setText(bean.getTitle());
        holder.itemContainer.setBackgroundColor(Color.WHITE);
        return convertView;
    }

    @Override
    public void reOrder(int startPosition, int endPosition) {
        if (endPosition < datas.size()) {
            MyHomeBean object = datas.remove(startPosition);
            datas.add(endPosition, object);
            String key = AppConfig.KEY_USER_TEMP;
            appContext.saveObject((Serializable) datas, key);
            notifyDataSetChanged();
        }
    }

    public void setEdit() {
        IsEdit = true;
        notifyDataSetChanged();
    }

    public void getDatas() {
        for (MyHomeBean data : datas) {
            // DebugLog.i("点击 Item " + data.getId());
        }
    }

    public void endEdit() {
        IsEdit = false;
        notifyDataSetChanged();
    }
    public boolean getEditStatue() {
        return IsEdit;
    }

    static  class ViewHolder {
        @BindView(R.id.icon_img)
        ImageView iconImg;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.delete_img)
        ImageView deleteImg;
        @BindView(R.id.item_container)
        FrameLayout itemContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
