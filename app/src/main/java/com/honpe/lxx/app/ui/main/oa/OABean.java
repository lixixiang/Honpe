package com.honpe.lxx.app.ui.main.oa;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 14:44
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class OABean implements MultiItemEntity, Serializable {

    public static final int SINGLE_TEXT = 1;
    public static final int TEXT_IMG = 2;

    private int itemType;
    private String content;
    private int icon;
    private String title;

    public OABean(int type) {
        this.itemType = type;
    }

    public OABean(int type, String content) {
        this.itemType = type;
        this.content = content;
    }

    public OABean(int type, int icon, String title) {
        this.itemType = type;
        this.icon = icon;
        this.title = title;
    }

    public int getType() {
        return itemType;
    }

    public void setType(int itemType) {
        this.itemType = itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}

