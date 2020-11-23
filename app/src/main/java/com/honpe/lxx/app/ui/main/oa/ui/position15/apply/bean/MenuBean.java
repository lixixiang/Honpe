package com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.GridViewAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: MenuBean
 * Author: asus
 * Date: 2020/8/10 17:20
 * Description: 客户订餐申请实体类
 */
public class MenuBean extends BaseExpandNode {

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"FoodStyle":"粤菜","Type":"全部","FoodName":"红烧桂鱼        ","Unit":"份","Taste":"正常","FoodCode":"94963544-F67F-4D00-8E90-BCB2594BB57F","Status":0}]
     */
    public static final int TYPE_LEVEL_0 = 1;
    private int Status;
    private String Msg;
    private List<DataBean> Data;
    private List<BaseNode> childNode;
    private int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

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

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public static class DataBean  extends BaseNode {
        /**
         * OrderNo : 菜品订单号
         * FoodStyle : 粤菜
         * Type : 全部
         * FoodName : 红烧桂鱼
         * Unit : 份
         * Taste : 正常
         * FoodCode : 94963544-F67F-4D00-8E90-BCB2594BB57F
         * Status : 0
         */
        public static final int TYPE_PERSON = 3;
        private String OrderNo;
        private String FoodStyle;
        private String Type;
        private String FoodName;
        private String Unit;
        private String Taste;
        private String FoodCode;
        private int Status;
        private boolean isMenuCheck;
        private int itemType;


        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public DataBean() {
        }

        public DataBean(String foodStyle) {
            FoodStyle = foodStyle;
        }

        public DataBean(String foodStyle, String foodName, String unit, String foodCode, int status) {
            FoodStyle = foodStyle;
            FoodName = foodName;
            Unit = unit;
            FoodCode = foodCode;
            Status = status;
        }

        public DataBean(String foodName,String foodCode, int status) {
            FoodName = foodName;
            FoodCode = foodCode;
            Status = status;
        }



        public boolean isMenuCheck() {
            return isMenuCheck;
        }

        public void setMenuCheck(boolean menuCheck) {
            isMenuCheck = menuCheck;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String orderNo) {
            OrderNo = orderNo;
        }

        public String getFoodStyle() {
            return FoodStyle;
        }

        public void setFoodStyle(String FoodStyle) {
            this.FoodStyle = FoodStyle;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getFoodName() {
            return FoodName;
        }

        public void setFoodName(String FoodName) {
            this.FoodName = FoodName;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public String getTaste() {
            return Taste;
        }

        public void setTaste(String Taste) {
            this.Taste = Taste;
        }

        public String getFoodCode() {
            return FoodCode;
        }

        public void setFoodCode(String FoodCode) {
            this.FoodCode = FoodCode;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }
}
