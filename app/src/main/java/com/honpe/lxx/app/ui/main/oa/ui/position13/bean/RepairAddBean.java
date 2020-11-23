package com.honpe.lxx.app.ui.main.oa.ui.position13.bean;

import java.util.List;

/**
 * FileName: RepairAddBean
 * Author: asus
 * Date: 2020/9/3 14:59
 * Description:
 */
public class RepairAddBean {

    /**
     * repairBillDetailsModel : [{"序号":1,"维修人":"","完成时间":"","位置":"c 栋 2楼","问题描述":"测试 内容","要求时限":"即时"}]
     * 确认时间 :
     * F_TypeContext : 测试 报修
     * F_AdminConfirmer :
     * 报修原因 : 测试 原因
     * F_Evaluate :
     * 电工鉴定 :
     * 验收人员 :
     * F_AdminDepart :
     * 确认人 :
     * 鉴定结果 :
     * 行政审批 :
     * F_DepartmentHead :
     * 维修类型 : 0
     * 核准人 :
     * 验收结果 :
     */
    private List<RepairBillDetailsModelEntity> RepairBillInstallDetailsModel;
    private String 确认时间;
    private String F_TypeContext;
    private String F_AdminConfirmer;
    private String 报修原因;
    private String F_Evaluate;
    private String 电工鉴定;
    private String 验收人员;
    private String F_AdminDepart;
    private String 确认人;
    private String 鉴定结果;
    private String 行政审批;
    private String F_DepartmentHead;
    private int 维修类型;
    private String 核准人;
    private String 验收结果;
    private String 维修单号;
    private String 报修性质;
    private String 变更时间;
    private String 变更人;
    private String 核准时间;
    private String 验收时间;
    private String 行政审批时间;

    public String get行政审批时间() {
        return 行政审批时间;
    }

    public void set行政审批时间(String 行政审批时间) {
        this.行政审批时间 = 行政审批时间;
    }

    public String get验收时间() {
        return 验收时间;
    }

    public void set验收时间(String 验收时间) {
        this.验收时间 = 验收时间;
    }

    public String get核准时间() {
        return 核准时间;
    }

    public void set核准时间(String 核准时间) {
        this.核准时间 = 核准时间;
    }

    public void setRepairBillDetailsModel(List<RepairBillDetailsModelEntity> repairBillDetailsModel) {
        this.RepairBillInstallDetailsModel = repairBillDetailsModel;
    }

    public void set确认时间(String 确认时间) {
        this.确认时间 = 确认时间;
    }

    public void setF_TypeContext(String F_TypeContext) {
        this.F_TypeContext = F_TypeContext;
    }

    public void setF_AdminConfirmer(String F_AdminConfirmer) {
        this.F_AdminConfirmer = F_AdminConfirmer;
    }

    public String get维修单号() {
        return 维修单号;
    }

    public void set维修单号(String 维修单号) {
        this.维修单号 = 维修单号;
    }

    public String get报修性质() {
        return 报修性质;
    }

    public void set报修性质(String 报修性质) {
        this.报修性质 = 报修性质;
    }

    public String get变更时间() {
        return 变更时间;
    }

    public void set变更时间(String 变更时间) {
        this.变更时间 = 变更时间;
    }

    public String get变更人() {
        return 变更人;
    }

    public void set变更人(String 变更人) {
        this.变更人 = 变更人;
    }

    public void set报修原因(String 报修原因) {
        this.报修原因 = 报修原因;
    }

    public void setF_Evaluate(String F_Evaluate) {
        this.F_Evaluate = F_Evaluate;
    }

    public void set电工鉴定(String 电工鉴定) {
        this.电工鉴定 = 电工鉴定;
    }

    public void set验收人员(String 验收人员) {
        this.验收人员 = 验收人员;
    }

    public void setF_AdminDepart(String F_AdminDepart) {
        this.F_AdminDepart = F_AdminDepart;
    }

    public void set确认人(String 确认人) {
        this.确认人 = 确认人;
    }

    public void set鉴定结果(String 鉴定结果) {
        this.鉴定结果 = 鉴定结果;
    }

    public void set行政审批(String 行政审批) {
        this.行政审批 = 行政审批;
    }

    public void setF_DepartmentHead(String F_DepartmentHead) {
        this.F_DepartmentHead = F_DepartmentHead;
    }

    public void set维修类型(int 维修类型) {
        this.维修类型 = 维修类型;
    }

    public void set核准人(String 核准人) {
        this.核准人 = 核准人;
    }

    public void set验收结果(String 验收结果) {
        this.验收结果 = 验收结果;
    }

    public List<RepairBillDetailsModelEntity> getRepairBillDetailsModel() {
        return RepairBillInstallDetailsModel;
    }

    public String get确认时间() {
        return 确认时间;
    }

    public String getF_TypeContext() {
        return F_TypeContext;
    }

    public String getF_AdminConfirmer() {
        return F_AdminConfirmer;
    }

    public String get报修原因() {
        return 报修原因;
    }

    public String getF_Evaluate() {
        return F_Evaluate;
    }

    public String get电工鉴定() {
        return 电工鉴定;
    }

    public String get验收人员() {
        return 验收人员;
    }

    public String getF_AdminDepart() {
        return F_AdminDepart;
    }

    public String get确认人() {
        return 确认人;
    }

    public String get鉴定结果() {
        return 鉴定结果;
    }

    public String get行政审批() {
        return 行政审批;
    }

    public String getF_DepartmentHead() {
        return F_DepartmentHead;
    }

    public int get维修类型() {
        return 维修类型;
    }

    public String get核准人() {
        return 核准人;
    }

    public String get验收结果() {
        return 验收结果;
    }

    public static class RepairBillDetailsModelEntity {
        /**
         * 序号 : 1
         * 维修人 :
         * 完成时间 :
         * 位置 : c 栋 2楼
         * 问题描述 : 测试 内容
         * 要求时限 : 即时
         */
        private int 序号;
        private String 维修人;
        private String 完成时间;
        private String 位置;
        private String 问题描述;
        private String 要求时限;

        public void set序号(int 序号) {
            this.序号 = 序号;
        }

        public void set维修人(String 维修人) {
            this.维修人 = 维修人;
        }

        public void set完成时间(String 完成时间) {
            this.完成时间 = 完成时间;
        }

        public void set位置(String 位置) {
            this.位置 = 位置;
        }

        public void set问题描述(String 问题描述) {
            this.问题描述 = 问题描述;
        }

        public void set要求时限(String 要求时限) {
            this.要求时限 = 要求时限;
        }

        public int get序号() {
            return 序号;
        }

        public String get维修人() {
            return 维修人;
        }

        public String get完成时间() {
            return 完成时间;
        }

        public String get位置() {
            return 位置;
        }

        public String get问题描述() {
            return 问题描述;
        }

        public String get要求时限() {
            return 要求时限;
        }
    }
}
