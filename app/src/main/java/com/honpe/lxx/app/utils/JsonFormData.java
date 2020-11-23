package com.honpe.lxx.app.utils;

import com.google.gson.JsonObject;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/24 16:29
 * @Author: 李熙祥
 * @Description: java类作用描述 WEB中formData 数据
 */
public class JsonFormData {
    /**
     * bfe190b5-ad15-f995-76f4-eea10de61dba  RqtBy(申请人用户ID)  7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e
     * ec76fbf5-5f4f-49fc-f470-aaebed3e88d8  Id(申请单号)     20200701(固定值)
     * 308ef04c-5eaf-92e3-3760-9560072a8250  Dpt(部门ID)      a0d42e75-a70b-4cef-8b0f-52456fdf640f
     * 9b43c3d2-b279-dd66-4657-0c1cae3101c4  RetTime(申请日期)          2020-06-23 14:54:13
     * ba902b11-88c6-03f1-1f49-5f2798e298b3  Catorgry（请假类型）      1事假，2病假，3婚假,4年假，5产假
     * abe1039d-1c08-e623-aa9d-5c1e70eca6a8  Level（紧急程度）         1
     * 2fc9a93a-f15e-35d2-b094-52cafe49e5fc  StartTime(起始时间)        2019-12-23 18:58
     * c86d0850-4b46-5202-2388-850ef164ad30  EndTime(结束时间)    2019-12-23 12:00
     * ddedb53c-ff7c-4e4b-18d9-098ee6b98352  CountHours(请假时长)       0
     * bafa0e31-c717-e456-1d05-687d8a4c8c53  Holiday(剩余年假天数)     0
     * c18ca06a-96e4-0db3-20f1-4d5b08b169e9  Reason(请假原因)           测试
     * c13ce94f-d9ea-4f1e-9e32-5ef0d61311ff  Lguid            123456789(固定值)
     *
     * @return
     */


    //请假申请表单数据
    public static String LeaveFormData(String RqtBy, String id, String Depart, String reTime, String catorgry,
                                       String level, String startTime, String endTime, String countHours, String reason, String guid) {
        JsonObject JsonData = new JsonObject();
        JsonData.addProperty("bfe190b5-ad15-f995-76f4-eea10de61dba", RqtBy);
        JsonData.addProperty("ec76fbf5-5f4f-49fc-f470-aaebed3e88d8", id);
        JsonData.addProperty("308ef04c-5eaf-92e3-3760-9560072a8250", Depart);
        JsonData.addProperty("9b43c3d2-b279-dd66-4657-0c1cae3101c4", reTime);
        JsonData.addProperty("ba902b11-88c6-03f1-1f49-5f2798e298b3", catorgry);
        JsonData.addProperty("abe1039d-1c08-e623-aa9d-5c1e70eca6a8", level);
        JsonData.addProperty("2fc9a93a-f15e-35d2-b094-52cafe49e5fc", startTime);
        JsonData.addProperty("c86d0850-4b46-5202-2388-850ef164ad30", endTime);
        JsonData.addProperty("ddedb53c-ff7c-4e4b-18d9-098ee6b98352", countHours);
        JsonData.addProperty("bafa0e31-c717-e456-1d05-687d8a4c8c53", "0");
        JsonData.addProperty("c18ca06a-96e4-0db3-20f1-4d5b08b169e9", reason);
        JsonData.addProperty("c13ce94f-d9ea-4f1e-9e32-5ef0d61311ff", guid);

        return String.valueOf(JsonData);
    }

    /**
     *
     * @param RqtBy 申请人
     * @param applyNo 出差申请单号
     * @param applyTime  申请时间
     * @param applyDepart  申请人部门
     * @param level   紧急程度
     * @param dormType  出差类型
     * @param address 出差地点
     * @param startTime 开始时间
     * @param endTime  结束时间
     * @param dayNo 出差天数
     * @param reason  出差原因
     * @param guid GUID
     * @return
     */

    //出差申请表单数据
    public static String DomFormData(String RqtBy, String applyNo, String applyTime, String applyDepart, String level, String dormType, String address,
                                     String startTime, String endTime, String dayNo, String reason, String guid) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("5f15a8b9-1707-bcfb-0af5-ef52419c7563", RqtBy);
        jsonObject.addProperty("335f3350-d0e0-b5d4-20a2-cff76994778b", applyNo);
        jsonObject.addProperty("52184715-fd1e-a878-d628-d91bab1e77d0", applyTime);
        jsonObject.addProperty("2ca7112b-3c25-af01-d01d-c752d3c91754", applyDepart);
        jsonObject.addProperty("dff0f506-59ad-23ad-34d6-2caab2aba632", level);
        jsonObject.addProperty("36677fb8-43a8-c6e4-151c-4b8c85ec85ed", dormType);
        jsonObject.addProperty("0a27a9de-5930-e142-2d59-6e173766ebde", address);
        jsonObject.addProperty("f53c72ba-5117-c049-9335-4ba7abe094f2", startTime);
        jsonObject.addProperty("d840e723-a7e2-2f7e-1e92-03ea40d7b715", endTime);
        jsonObject.addProperty("a5e19e62-7961-49b6-205f-c408a6f8b9c3", dayNo);
        jsonObject.addProperty("147e589f-c085-123b-7503-8ecf708766db", reason);
        jsonObject.addProperty("6ad0e4e8-4f9a-e45a-6d6f-a15a6729207b", guid);
        return String.valueOf(jsonObject);
    }

    //外出申请表单数据
    public static String GoOutFormData(String RqtBy, String applyNo, String applyTime, String Dpt, String Level,
                                       String Catorgry, String CountDay, String StartTime, String EndTime, String Reason, String guid) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("8959d260-f3d3-ebf1-51ef-ddae58156991", RqtBy);
        jsonObject.addProperty("9c5e0781-7132-4a12-f503-3a0307042293", applyNo);
        jsonObject.addProperty("d07964ee-7793-b6a0-6dfb-b05360861feb", applyTime);
        jsonObject.addProperty("f8fa68e9-c19d-9ec3-9822-9106499cf810", Dpt);
        jsonObject.addProperty("14a242d6-674e-02ea-babb-3858e8cb6b64", Level);
        jsonObject.addProperty("749ce51d-84bd-eb6b-db91-cf0e6fd68368", Catorgry);
        jsonObject.addProperty("955bb5fa-6f2d-8f2f-e992-789b6f356c61", CountDay);
        jsonObject.addProperty("fb89d6bd-3734-9947-0e45-7c3c7a74715f", StartTime);
        jsonObject.addProperty("348aaa6a-2c46-f31a-1cdb-278f44b5d9bc", EndTime);
        jsonObject.addProperty("d472e692-3cff-9878-d8f4-8ddbbe27085c", Reason);
        jsonObject.addProperty("39bf9de0-3ae8-481a-1662-05e6ecd5f244", guid);
        return String.valueOf(jsonObject);
    }


    /**
     * 显示请假表单数据
     *
     * @param keyword   例如：点餐  请假  可传空
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static String showFormData(String keyword, String startTime, String endTime) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("keyword", keyword);
        jsonObject.addProperty("StartTime", startTime);
        jsonObject.addProperty("EndTime", endTime);

        return String.valueOf(jsonObject);
    }

    //获取个人流程信息列表，用于流程查询查看
    public static String showTaskList(String startTime,String endTime) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("keyword", "");
        jsonObject.addProperty("StartTime",startTime);
        jsonObject.addProperty("EndTime",endTime);
        return String.valueOf(jsonObject);
    }
}











