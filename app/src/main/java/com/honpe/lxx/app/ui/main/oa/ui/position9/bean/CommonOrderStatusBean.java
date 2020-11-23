package com.honpe.lxx.app.ui.main.oa.ui.position9.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 11:47
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class CommonOrderStatusBean {
    /**
     * Status : 0
     * Msg : 新增派车申请单成功!
     * Data : CL180918
     */

    private int Status;
    private String Msg;
    private String Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}

