package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: StatisticsBean2
 * Author: asus
 * Date: 2020/8/12 9:13
 * Description: 统计选材界面
 */
public class StatisticsBean2 {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"MealDate":"2019-01-18","MealTimes":"早餐","DishesDetails":[{"Dishes":"绿豆粥","IsSelected":0},{"Dishes":"黑米粥 ","IsSelected":1},{"Dishes":"腊八粥","IsSelected":0},{"Dishes":"梅菜包","IsSelected":0},{"Dishes":"面包","IsSelected":0},{"Dishes":"凉拌面 ","IsSelected":0},{"Dishes":"烧卖","IsSelected":0},{"Dishes":"刀削面","IsSelected":0}]},{"MealDate":"2019-01-18","MealTimes":"午餐","DishesDetails":[{"Dishes":"香煎鸡扒","IsSelected":0},{"Dishes":"蚂蚁上树","IsSelected":0},{"Dishes":"圆椒炒肉","IsSelected":0},{"Dishes":"油麦菜","IsSelected":0},{"Dishes":"乌鸡准山汤","IsSelected":1},{"Dishes":"辣子鸡","IsSelected":1},{"Dishes":"西兰花炒肉","IsSelected":0},{"Dishes":"豆皮炒肉","IsSelected":0},{"Dishes":"薯叶","IsSelected":0},{"Dishes":"海带结骨头汤","IsSelected":0}]},{"MealDate":"2019-01-18","MealTimes":"晚餐","DishesDetails":[{"Dishes":"红烧带鱼","IsSelected":1},{"Dishes":"粉丝肉沫","IsSelected":0},{"Dishes":"地瓜炒肉","IsSelected":0},{"Dishes":"通菜","IsSelected":1},{"Dishes":"萝卜肉片汤","IsSelected":0},{"Dishes":"萝卜炖牛腩","IsSelected":1},{"Dishes":"青瓜炒肉","IsSelected":0},{"Dishes":"莲藕炒肉","IsSelected":0},{"Dishes":"生菜","IsSelected":0},{"Dishes":"黄花蛋汤","IsSelected":0}]},{"MealDate":"2019-01-18","MealTimes":"夜宵","DishesDetails":[{"Dishes":"红烧带鱼","IsSelected":0},{"Dishes":"粉丝肉沫","IsSelected":0},{"Dishes":"地瓜炒肉","IsSelected":0},{"Dishes":"通菜","IsSelected":0},{"Dishes":"萝卜肉片汤","IsSelected":0},{"Dishes":"萝卜炖牛腩","IsSelected":0},{"Dishes":"青瓜炒肉","IsSelected":0},{"Dishes":"莲藕炒肉","IsSelected":0},{"Dishes":"生菜","IsSelected":0},{"Dishes":"黄花蛋汤","IsSelected":0}]}]
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


    public static class DataBean extends BaseExpandNode {
        /**
         * MealDate : 2019-01-18
         * MealTimes : 早餐
         * DishesDetails : [{"Dishes":"绿豆粥","IsSelected":0},{"Dishes":"黑米粥 ","IsSelected":1},{"Dishes":"腊八粥","IsSelected":0},{"Dishes":"梅菜包","IsSelected":0},{"Dishes":"面包","IsSelected":0},{"Dishes":"凉拌面 ","IsSelected":0},{"Dishes":"烧卖","IsSelected":0},{"Dishes":"刀削面","IsSelected":0}]
         */
        private String MealDate;
        private String MealTimes;
        private List<BaseNode> childNode;

        private List<DishesDetailsBean> DishesDetails;

        public void setChildNode(List<BaseNode> childNode) {
            this.childNode = childNode;
        }

        public DataBean() {
        }

        public DataBean(String mealDate, String mealTimes, List<DishesDetailsBean> dishesDetails) {
            MealDate = mealDate;
            MealTimes = mealTimes;
            DishesDetails = dishesDetails;
        }
   
        public String getMealDate() {
            return MealDate;
        }

        public void setMealDate(String MealDate) {
            this.MealDate = MealDate;
        }

        public String getMealTimes() {
            return MealTimes;
        }

        public void setMealTimes(String MealTimes) {
            this.MealTimes = MealTimes;
        }

        public List<DishesDetailsBean> getDishesDetails() {
            return DishesDetails;
        }

        public void setDishesDetails(List<DishesDetailsBean> DishesDetails) {
            this.DishesDetails = DishesDetails;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return childNode;
        }


        public static class DishesDetailsBean extends BaseNode{
            /**
             * Dishes : 绿豆粥
             * IsSelected : 0
             */
            private String Dishes;
            private int IsSelected;

            public DishesDetailsBean(String dishes, int isSelected) {
                Dishes = dishes;
                IsSelected = isSelected;
            }

            public DishesDetailsBean() {
            }

            public String getDishes() {
                return Dishes;
            }

            public void setDishes(String Dishes) {
                this.Dishes = Dishes;
            }

            public int getIsSelected() {
                return IsSelected;
            }

            public void setIsSelected(int IsSelected) {
                this.IsSelected = IsSelected;
            }


            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return null;
            }
        }
    }
}
