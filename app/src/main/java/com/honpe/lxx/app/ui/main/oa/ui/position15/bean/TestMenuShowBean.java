package com.honpe.lxx.app.ui.main.oa.ui.position15.bean;

import java.util.List;

/**
 * FileName: TestMenuShowBean
 * Author: asus
 * Date: 2020/8/10 18:05
 * Description:
 */
public class TestMenuShowBean {
    private String MenuType;
    private List<Details> details;

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setMenuType(String menuType) {
        MenuType = menuType;
    }

    public static class Details  {
        private String foodName;

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }


    }
}
