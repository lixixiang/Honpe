package com.honpe.lxx.app.ui.main.oa.ui.position8.entity;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: ChildEntity
 * Author: asus
 * Date: 2020/10/12 13:35
 * Description:
 */
public class ChildEntity extends BaseExpandNode {
    private int icon;
    private String time;
    private String type;
    private int tips;
    private List<BaseNode> childNode;

    public ChildEntity() {
        setExpanded(false);
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}