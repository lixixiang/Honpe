package com.honpe.lxx.app.ui.fragment.a_package.menu.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.ui.adapter.MyHomeAdapter;
import com.honpe.lxx.app.ui.fragment.a_package.menu.MenuManagerFragment;
import com.honpe.lxx.app.widget.BaseGridView;

import java.util.List;

/**
 * FileName: MenuParentAdapter
 * Author: asus
 * Date: 2020/8/28 9:03
 * Description:
 */
public class MenuParentAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private MenuManagerFragment fragment;
    private List<MyHomeBean> addressProvincesList;
    private LayoutInflater inflater;
    private BaseGridView toolbarGrid;
    private Context context;
    private boolean IsEdit;
    private MenuChildAdapter adapter;

    public MenuParentAdapter(Context context,MenuManagerFragment fragment, List<MyHomeBean> addressProvincesList) {
        this.context = context;
        this.fragment = fragment;
        this.addressProvincesList = addressProvincesList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return addressProvincesList.size();
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }


    @Override
    public Object getGroup(int groupPosition) {
        return addressProvincesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return addressProvincesList.get(groupPosition).getChilds();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolde groupViewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_cate_parent, null);
            groupViewHolde = new GroupViewHolde();
            groupViewHolde.tv_item_cate_name = (TextView) convertView.findViewById(R.id.tv_item_cate_name);
            convertView.setTag(groupViewHolde);
        } else {
            groupViewHolde = (GroupViewHolde) convertView.getTag();
        }
        groupViewHolde.tv_item_cate_name.setText(addressProvincesList.get(groupPosition).getTitle());
        return convertView;
    }
    class GroupViewHolde {
        TextView tv_item_cate_name;
        ImageView iv_items_cate_pic;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.items_cate_grid_child, null);
        toolbarGrid = convertView.findViewById(R.id.gv_toolbar);
        toolbarGrid.setNumColumns(4);// 设置每行列数
        toolbarGrid.setGravity(Gravity.CENTER);// 位置居中
        // toolbarGrid.setHorizontalSpacing(10);// 水平间隔
        adapter = new MenuChildAdapter(context ,fragment, addressProvincesList.get(groupPosition).getChilds(),
                IsEdit);
        toolbarGrid.setAdapter(adapter);// 设置菜单Adapter
        toolbarGrid.setOnItemClickListener(this);
        toolbarGrid.setOnItemLongClickListener(this);
        // }
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyHomeBean indexData = (MyHomeBean) parent.getItemAtPosition(position);
        if (indexData != null) {
            fragment.initUrl(indexData);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    public void setEdit() {
        IsEdit = true;
        notifyDataSetChanged();
    }

    public void endEdit() {
        IsEdit = false;
        notifyDataSetChanged();
    }
}
