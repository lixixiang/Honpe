package com.honpe.lxx.app.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 11:53
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class UpdateBean {
    /**
     * Status : 0
     * Msg : 成功！
     * Data : {"ver":"3","description":"更新描述!"}
     * Total : 1
     */

    private int Status;
    private String Msg;
    private DataBean Data;
    private int Total;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public static class DataBean {
        /**
         * ver : 3
         * description : 更新描述!
         */

        private String ver;
        private String description;



        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}


