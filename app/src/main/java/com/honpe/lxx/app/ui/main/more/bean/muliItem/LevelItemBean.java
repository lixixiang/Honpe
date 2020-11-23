package com.honpe.lxx.app.ui.main.more.bean.muliItem;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: LevelItemBean
 * Author: asus
 * Date: 2020/8/8 10:26
 * Description: 多级列表分派日期
 */
public class LevelItemBean extends BaseExpandNode {
    public String title;
    private String dayNum;
    private List<BaseNode> childNode;

    public LevelItemBean() {
    }

    public LevelItemBean(String title, String dayNum) {
        this.title = title;
        this.dayNum = dayNum;
        setExpanded(false);
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
