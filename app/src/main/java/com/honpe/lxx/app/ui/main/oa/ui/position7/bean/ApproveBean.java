package com.honpe.lxx.app.ui.main.oa.ui.position7.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/21 14:19
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveBean implements Serializable {
    private String mTitle;
    private int mTips;
    private String mDate;
    private int Icons;

    private List<DataBean> HRData;



    public List<DataBean> getHRData() {
        return HRData;
    }

    public void setHRData(List<DataBean> HRData) {
        this.HRData = HRData;
    }

    public int getIcons() {
        return Icons;
    }

    public void setIcons(int icons) {
        Icons = icons;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getTips() {
        return mTips;
    }

    public void setTips(int mTips) {
        this.mTips = mTips;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public static class DataBean {
        /**
         * rqtby : 7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e
         * id : G200715002
         * rettime : 2020-07-15 16:22:39
         * dpt : a0d42e75-a70b-4cef-8b0f-52456fdf640f
         * level : 1
         * catorgry : 1
         * starttime : 2020-07-15 16:22:00
         * endtime : 2020-07-15 18:22:00
         * address : 测试
         * countday : 1
         * reason : 测试
         */
        private String rqtby;
        private String id;
        private String taskId;
        private String rettime;
        private String dpt;
        private String level;
        private String catorgry;
        private String starttime;
        private String endtime;
        private String address;
        private int countday;
        private String reason;
        private String userName;
        private String team;
        private String F_id;
        private String F_encode; //用户 员工编号

        // HRBusinessTravelBean   * gguid : 127e8016-5335-80ce-60c5-53af2c8dd606
        private String guid;

        // HRGoOutBean  cguid : 62f36dde-663d-88ba-a4e6-7d712bc2ae40


        //  HRAskforLeaveBean
        //  counthours : 2.0
        private double counthours;
        // holiday : 0.0
        private double holiday;
        // lguid : 94f9c049-1afd-0302-0fc4-016ac8306267


        public String getF_encode() {
            return F_encode;
        }

        public void setF_encode(String f_encode) {
            F_encode = f_encode;
        }

        public String getF_id() {
            return F_id;
        }

        public void setF_id(String f_id) {
            F_id = f_id;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getRqtby() {
            return rqtby;
        }

        public void setRqtby(String rqtby) {
            this.rqtby = rqtby;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRettime() {
            return rettime;
        }

        public void setRettime(String rettime) {
            this.rettime = rettime;
        }

        public String getDpt() {
            return dpt;
        }

        public void setDpt(String dpt) {
            this.dpt = dpt;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCatorgry() {
            return catorgry;
        }

        public void setCatorgry(String catorgry) {
            this.catorgry = catorgry;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCountday() {
            return countday;
        }

        public void setCountday(int countday) {
            this.countday = countday;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public double getCounthours() {
            return counthours;
        }

        public void setCounthours(double counthours) {
            this.counthours = counthours;
        }

        public double getHoliday() {
            return holiday;
        }

        public void setHoliday(double holiday) {
            this.holiday = holiday;
        }

    }
}
