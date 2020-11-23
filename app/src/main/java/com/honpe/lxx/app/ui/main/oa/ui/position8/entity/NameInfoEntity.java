package com.honpe.lxx.app.ui.main.oa.ui.position8.entity;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: NameInfoEntity
 * Author: asus
 * Date: 2020/10/12 18:01
 * Description:
 */
public class NameInfoEntity extends BaseExpandNode implements Serializable {
    private List<BaseNode> childNode;
    private int tips; //个人有几条申请
    private int _id; //序号
    private String name;
    private List<SearchEntity.DataEntity.RowsEntity> rowsEntities;

    public List<SearchEntity.DataEntity.RowsEntity> getRowsEntities() {
        return rowsEntities;
    }

    public void setRowsEntities(List<SearchEntity.DataEntity.RowsEntity> rowsEntities) {
        this.rowsEntities = rowsEntities;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public NameInfoEntity() {
        setExpanded(false);
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
