package com.honpe.lxx.app.ui.login.register.bean;

import java.io.Serializable;

/**
 * FileName: SupplierBean
 * Author: asus
 * Date: 2020/11/19 10:51
 * Description:
 */
public class SupplierBean implements Serializable {
    private String strTitle;
    private String content;

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
