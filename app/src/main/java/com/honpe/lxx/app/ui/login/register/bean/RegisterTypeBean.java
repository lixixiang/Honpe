package com.honpe.lxx.app.ui.login.register.bean;

import java.io.Serializable;

/**
 * FileName: FragmentBean
 * Author: asus
 * Date: 2020/11/19 9:53
 * Description:
 */
public class RegisterTypeBean implements Serializable {
    private String type;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
