package com.honpe.lxx.app.ui.fragment.a_package.menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.ui.fragment.a_package.menu.MenuManagerFragment;

import java.util.List;

/**
 * FileName: MenuChildAdapter
 * Author: asus
 * Date: 2020/8/28 9:25
 * Description:
 */
public class MenuChildAdapter extends BaseAdapter {
    private List<MyHomeBean> menuList;
    private MenuManagerFragment fragment;
    private Context context;
    private boolean IsEdit = false;
    public MenuChildAdapter(Context context,MenuManagerFragment fragment, List<MyHomeBean> list, boolean isEdit2) {
        this.fragment = fragment;
        this.context = context;
        this.menuList = list;
        this.IsEdit = isEdit2;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.items_cate_child, null);
            viewHodler = new ViewHodler();
            viewHodler.tv_item_cate_child_name = (TextView) convertView.findViewById(R.id.tv_item_cate_child_name);

            viewHodler.deleteImg = (ImageView) convertView.findViewById(R.id.delete_img);
            viewHodler.iconImg = (ImageView) convertView.findViewById(R.id.icon_img);

            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        final MyHomeBean menuEntity = menuList.get(position);
        if (!menuEntity.isEnable()) {
            viewHodler.iconImg.setColorFilter(context.getResources().getColor(R.color.grey_home));
            viewHodler.tv_item_cate_child_name.setTextColor(context.getResources().getColor(R.color.grey_home));
        }
        if (IsEdit) {
            viewHodler.deleteImg.setVisibility(View.VISIBLE);
            if (menuEntity.isSelect()) {
                viewHodler.deleteImg.setBackgroundResource(R.mipmap.menu_select);
            } else {
                viewHodler.deleteImg.setBackgroundResource(R.mipmap.menu_add);
            }
        } else {
            viewHodler.deleteImg.setVisibility(View.GONE);
        }

        viewHodler.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // datas.remove(position);
                // String key = AppConfig.KEY_USER;
                // appContext.saveObject((Serializable) datas, key);
                if (!menuEntity.isSelect()) {
                    MenuManagerFragment.AddMenu(menuEntity);
                }
            }
        });

        //获取资源图片
        int drawableId = context.getResources().getIdentifier(menuEntity.getIco(),"mipmap", context.getPackageName());
        viewHodler.iconImg.setImageResource(drawableId);

//		Glide.with(mContext).load(menuEntity.getIco()).placeholder(R.mipmap.ic_launcher)
//				.error(R.mipmap.ic_launcher).into(viewHodler.iconImg);

        viewHodler.tv_item_cate_child_name.setText(menuEntity.getTitle());
        return convertView;
    }

    private class ViewHodler {
        private TextView tv_item_cate_child_name;
        private ImageView deleteImg, iconImg;
    }

}
