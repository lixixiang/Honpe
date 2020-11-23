package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: StatisticsBean1
 * Author: asus
 * Date: 2020/8/11 13:31
 * Description: 员工报餐实体类
 */
public class StatisticsBean1 implements Serializable {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"MealDate":"2018-12-20","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":true},{"MealDate":"2018-12-21","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-22","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-23","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-24","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-25","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-26","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-27","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":false,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-28","UserID":"APPCS05","Week":null,"HasBreakFast":true,"HasLunch":true,"HasDinner":true,"HasMidnight":false},{"MealDate":"2018-12-29","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-30","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false},{"MealDate":"2018-12-31","UserID":"APPCS05","Week":null,"HasBreakFast":false,"HasLunch":false,"HasDinner":false,"HasMidnight":false}]
     */

    private int Status;
    private String Msg;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * MealDate : 2018-12-20
         * UserID : APPCS05
         * Week : null
         * HasBreakFast : true
         * HasLunch : true
         * HasDinner : true
         * HasMidnight : true
         */

        private String MealDate;
        private String UserID;
        private String Week;
        private boolean HasBreakFast;
        private boolean HasLunch;
        private boolean HasDinner;
        private boolean HasMidnight;
        private boolean HasHorizontal;
        private boolean HasVertical;

        public DataBean() {
        }

        public DataBean(String mealDate, String userID, String week, boolean hasBreakFast, boolean hasLunch, boolean hasDinner, boolean hasMidnight) {
            MealDate = mealDate;
            UserID = userID;
            Week = week;
            HasBreakFast = hasBreakFast;
            HasLunch = hasLunch;
            HasDinner = hasDinner;
            HasMidnight = hasMidnight;
        }

        public boolean isHasHorizontal() {
            return HasHorizontal;
        }

        public void setHasHorizontal(boolean hasHorizontal) {
            HasHorizontal = hasHorizontal;
        }

        public boolean isHasVertical() {
            return HasVertical;
        }

        public void setHasVertical(boolean hasVertical) {
            HasVertical = hasVertical;
        }

        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String MealDate) {
            this.MealDate = MealDate;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = "";
        }

        public String getWeek() {
            return Week;
        }

        public void setWeek(String Week) {
            this.Week = Week;
        }

        public boolean isHasBreakFast() {
            return HasBreakFast;
        }

        public void setHasBreakFast(boolean HasBreakFast) {
            this.HasBreakFast = HasBreakFast;
        }

        public boolean isHasLunch() {
            return HasLunch;
        }

        public void setHasLunch(boolean HasLunch) {
            this.HasLunch = HasLunch;
        }

        public boolean isHasDinner() {
            return HasDinner;
        }

        public void setHasDinner(boolean HasDinner) {
            this.HasDinner = HasDinner;
        }

        public boolean isHasMidnight() {
            return HasMidnight;
        }

        public void setHasMidnight(boolean HasMidnight) {
            this.HasMidnight = HasMidnight;
        }
    }
}
