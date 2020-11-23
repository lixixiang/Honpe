package com.honpe.lxx.app.ui.main.oa.ui.position9.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 12:28
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class TagInfo {
    private boolean isChecked;
    private String text;
    private boolean isSelect;
    private int positon=0;

    public TagInfo() {
    }

    public TagInfo(boolean isChecked, int positon) {
        this.isChecked = isChecked;
        this.positon = positon;
    }

    public TagInfo(boolean isChecked, String text) {
        this.isChecked = isChecked;
        this.text = text;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public TagInfo(String text) {
        this.isChecked = true;
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

