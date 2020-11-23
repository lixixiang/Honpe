package com.honpe.lxx.app.ui.main.oa.ui.position13.bean;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: RepairBean
 * Author: asus
 * Date: 2020/8/31 10:30
 * Description: 维修实体
 */
public class RepairBean implements Serializable{

    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"行政审批时间":"","申请部门":"工程部","F_TypeContext":"操机","报修性质":"","F_AdminConfirmer":"","报修原因":"C007号机器主轴抓力不够，掉刀","F_dbSQL":null,"电工鉴定":"","验收人员":"","维修单号":"RP200141","确认人":"","核准时间":"","行政审批":"","变更人":"","维修类型":0,"申请日期":"2020-07-01 10:12","申请人":"余妹秀","验收结果":"","验收时间":"","repairBillDetailsModel":[{"维修单号":"RP200141","维修效果":"","序号":1,"维修人":"","完成时间":"","维修项目":"","位置":"数码国内","问题描述":"C007号机器主轴抓力不够，掉刀","要求时限":"一日内"}],"确认时间":"","申请组别":"数码组","F_Evaluate":"","F_AdminDepart":"","鉴定结果":"","变更时间":"","F_DepartmentHead":"","核准人":""}]
     */
    private int Status;
    private String Msg;
    private List<DataEntity> Data;

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public int getStatus() {
        return Status;
    }

    public String getMsg() {
        return Msg;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public static class DataEntity implements Serializable{
        /**
         * 行政审批时间 :
         * 申请部门 : 工程部
         * F_TypeContext : 操机
         * 报修性质 :
         * F_AdminConfirmer :
         * 报修原因 : C007号机器主轴抓力不够，掉刀
         * F_dbSQL : null
         * 电工鉴定 :
         * 验收人员 :
         * 维修单号 : RP200141
         * 确认人 :
         * 核准时间 :
         * 行政审批 :
         * 变更人 :
         * 维修类型 : 0
         * 申请日期 : 2020-07-01 10:12
         * 申请人 : 余妹秀
         * 验收结果 :
         * 验收时间 :
         * repairBillDetailsModel : [{"维修单号":"RP200141","维修效果":"","序号":1,"维修人":"","完成时间":"","维修项目":"","位置":"数码国内","问题描述":"C007号机器主轴抓力不够，掉刀","要求时限":"一日内"}]
         * 确认时间 :
         * 申请组别 : 数码组
         * F_Evaluate :
         * F_AdminDepart :
         * 鉴定结果 :
         * 变更时间 :
         * F_DepartmentHead :
         * 核准人 :
         */
        private String 行政审批时间;
        private String 申请部门;
        private String F_TypeContext;
        private String 报修性质;
        private String F_AdminConfirmer;
        private String 报修原因;
        private String F_dbSQL;
        private String 电工鉴定;
        private String 验收人员;
        private String 维修单号;
        private String 确认人;
        private String 核准时间;
        private String 行政审批;
        private String 变更人;
        private int 维修类型;
        private String 申请日期;
        private String 申请人;
        private String 验收结果;
        private String 验收时间;
        private List<RepairBillDetailsModelEntity> repairBillDetailsModel;
        private String 确认时间;
        private String 申请组别;
        private String F_Evaluate;
        private String F_AdminDepart;
        private String 鉴定结果;
        private String 变更时间;
        private String F_DepartmentHead;
        private String 核准人;


        public void set行政审批时间(String 行政审批时间) {
            this.行政审批时间 = 行政审批时间;
        }

        public void set申请部门(String 申请部门) {
            this.申请部门 = 申请部门;
        }

        public void setF_TypeContext(String F_TypeContext) {
            this.F_TypeContext = F_TypeContext;
        }

        public void set报修性质(String 报修性质) {
            this.报修性质 = 报修性质;
        }

        public void setF_AdminConfirmer(String F_AdminConfirmer) {
            this.F_AdminConfirmer = F_AdminConfirmer;
        }

        public void set报修原因(String 报修原因) {
            this.报修原因 = 报修原因;
        }

        public void setF_dbSQL(String F_dbSQL) {
            this.F_dbSQL = F_dbSQL;
        }

        public void set电工鉴定(String 电工鉴定) {
            this.电工鉴定 = 电工鉴定;
        }

        public void set验收人员(String 验收人员) {
            this.验收人员 = 验收人员;
        }

        public void set维修单号(String 维修单号) {
            this.维修单号 = 维修单号;
        }

        public void set确认人(String 确认人) {
            this.确认人 = 确认人;
        }

        public void set核准时间(String 核准时间) {
            this.核准时间 = 核准时间;
        }

        public void set行政审批(String 行政审批) {
            this.行政审批 = 行政审批;
        }

        public void set变更人(String 变更人) {
            this.变更人 = 变更人;
        }

        public void set维修类型(int 维修类型) {
            this.维修类型 = 维修类型;
        }

        public void set申请日期(String 申请日期) {
            this.申请日期 = 申请日期;
        }

        public void set申请人(String 申请人) {
            this.申请人 = 申请人;
        }

        public void set验收结果(String 验收结果) {
            this.验收结果 = 验收结果;
        }

        public void set验收时间(String 验收时间) {
            this.验收时间 = 验收时间;
        }

        public void setRepairBillDetailsModel(List<RepairBillDetailsModelEntity> repairBillDetailsModel) {
            this.repairBillDetailsModel = repairBillDetailsModel;
        }

        public void set确认时间(String 确认时间) {
            this.确认时间 = 确认时间;
        }

        public void set申请组别(String 申请组别) {
            this.申请组别 = 申请组别;
        }

        public void setF_Evaluate(String F_Evaluate) {
            this.F_Evaluate = F_Evaluate;
        }

        public void setF_AdminDepart(String F_AdminDepart) {
            this.F_AdminDepart = F_AdminDepart;
        }

        public void set鉴定结果(String 鉴定结果) {
            this.鉴定结果 = 鉴定结果;
        }

        public void set变更时间(String 变更时间) {
            this.变更时间 = 变更时间;
        }

        public void setF_DepartmentHead(String F_DepartmentHead) {
            this.F_DepartmentHead = F_DepartmentHead;
        }

        public void set核准人(String 核准人) {
            this.核准人 = 核准人;
        }

        public String get行政审批时间() {
            return 行政审批时间;
        }

        public String get申请部门() {
            return 申请部门;
        }

        public String getF_TypeContext() {
            return F_TypeContext;
        }

        public String get报修性质() {
            return 报修性质;
        }

        public String getF_AdminConfirmer() {
            return F_AdminConfirmer;
        }

        public String get报修原因() {
            return 报修原因;
        }

        public String getF_dbSQL() {
            return F_dbSQL;
        }

        public String get电工鉴定() {
            return 电工鉴定;
        }

        public String get验收人员() {
            return 验收人员;
        }

        public String get维修单号() {
            return 维修单号;
        }

        public String get确认人() {
            return 确认人;
        }

        public String get核准时间() {
            return 核准时间;
        }

        public String get行政审批() {
            return 行政审批;
        }

        public String get变更人() {
            return 变更人;
        }

        public int get维修类型() {
            return 维修类型;
        }

        public String get申请日期() {
            return 申请日期;
        }

        public String get申请人() {
            return 申请人;
        }

        public String get验收结果() {
            return 验收结果;
        }

        public String get验收时间() {
            return 验收时间;
        }

        public List<RepairBillDetailsModelEntity> getRepairBillDetailsModel() {
            return repairBillDetailsModel;
        }

        public String get确认时间() {
            return 确认时间;
        }

        public String get申请组别() {
            return 申请组别;
        }

        public String getF_Evaluate() {
            return F_Evaluate;
        }

        public String getF_AdminDepart() {
            return F_AdminDepart;
        }

        public String get鉴定结果() {
            return 鉴定结果;
        }

        public String get变更时间() {
            return 变更时间;
        }

        public String getF_DepartmentHead() {
            return F_DepartmentHead;
        }

        public String get核准人() {
            return 核准人;
        }

        public static class RepairBillDetailsModelEntity {
            /**
             * 维修单号 : RP200141
             * 维修效果 :
             * 序号 : 1
             * 维修人 :
             * 完成时间 :
             * 维修项目 :
             * 位置 : 数码国内
             * 问题描述 : C007号机器主轴抓力不够，掉刀
             * 要求时限 : 一日内
             */
            private String 维修单号;
            private String 维修效果;
            private int 序号;
            private String 维修人;
            private String 完成时间;
            private String 维修项目;
            private String 位置;
            private String 问题描述;
            private String 要求时限;

            public void set维修单号(String 维修单号) {
                this.维修单号 = 维修单号;
            }

            public void set维修效果(String 维修效果) {
                this.维修效果 = 维修效果;
            }

            public void set序号(int 序号) {
                this.序号 = 序号;
            }

            public void set维修人(String 维修人) {
                this.维修人 = 维修人;
            }

            public void set完成时间(String 完成时间) {
                this.完成时间 = 完成时间;
            }

            public void set维修项目(String 维修项目) {
                this.维修项目 = 维修项目;
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

            public String get维修单号() {
                return 维修单号;
            }

            public String get维修效果() {
                return 维修效果;
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

            public String get维修项目() {
                return 维修项目;
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
}
