package com.honpe.lxx.app.ui.main.oa.ui.position8.entity;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: SearchEntity
 * Author: asus
 * Date: 2020/10/12 11:06
 * Description:
 */
public class SearchEntity implements Serializable {

    /**
     * code : 200
     * data : {"total":1,"records":24,"page":1,"rows":[{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"93e06c50-2ea7-45cf-b5cb-10a9212964af","F_StartTime":"","F_Reason":"","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"3d7fb077-0bee-8fb3-0020-f6a876ced527","F_SchemeName":"客户点餐申请","F_TaskName":null,"F_ProcessName":"徐菊的客户点餐申请流程","F_CurState":"","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-07-18 17:46:24","F_SchemeCode":"CustomerCanteen","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李峰","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 8:00:00","F_Reason":"休假回家","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"64954f47-1b5c-4b22-9154-05b25ef2139b","F_Id":"16c64327-abfe-afc7-cffa-5db872cca693","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李峰的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-09-30 10:29:40","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/9 17:20:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李熙祥","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/7 9:00:00","F_Reason":"回深圳","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"7d57dc74-81a9-4d05-a78f-e51cfab5e83c","F_Id":"4cb1c5a1-2947-4b41-84c7-e36711b56fa8","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李熙祥的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 08:41:17","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"回深圳","F_ProcessParentId":null,"F_EndTime":"2020/10/9 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 13:53:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"640fbf35-3b29-4583-b703-ba9f7272a38e","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"徐菊的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:18:41","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/7 12:18:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"1565f2d1-9cd9-4889-8cb0-f09143f61e38","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:22:06","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/6 12:22:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-  b072-cee56622fd52","F_Id":"1a52615d-ad7d-468d-b58b-98b251d2985f","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:22:42","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:22:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"e3702923-8083-4baa-96b3-6af9f4bfcc60","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:24:29","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/9 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/7 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"7b43a4f9-c51e-498d-ab0b-66fa2603ec88","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:24:56","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 14:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"12a51176-dab9-4822-9f94-f9efe30a3701","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:25:22","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:25:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"9fc5c021-4159-4861-9d88-b73d63c64597","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:25:57","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:25:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_Reason  ":"测试","F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/8 15:00:00","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"4975fb7f-9feb-4ef8-b9da-76a8da5523a5","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 15:59:16","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 20:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 9:00:00","F_Reason":"调休","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"72fc7ac0-769d-4d66-a1ec-910b162f28ed","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-08 09:54:27","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"调休","F_ProcessParentId":null,"F_EndTime":"2020/10/6 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/7 9:00:00","F_Reason":"有事请假一天。","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"77478989-a9a6-45a0-8b39-49a7fb003188","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-08 10:07:19","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"有事请假一天。","F_ProcessParentId":null,"F_EndTime":"2020/10/7 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李熙祥","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/8 16:00:00","F_Reason":"test","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"7d57dc74-81a9-4d05-a78f-e51cfab5e83c","F_Id":"2beadbe7-a70f-4210-9222-3aa699429715","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李熙祥的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-09 00:43:21","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"test","F_ProcessParentId":null,"F_EndTime":"2020/10/9 16:00:00","F_TaskId":null},{"F_ProcessLevel":2,"F_EnabledMark":1,"F_CreateUserName":"李伟","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/9 12:30:00","F_Reason":"外出办事","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"30ec3acf-b405-4582-ab11-7eccb69c640f","F_Id":"06448df3-0cf4-0857-a7e0-3371a82fcbba","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李伟的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-09 10:20:32","F_SchemeCode":"Leave","F_DepartmentId":"65cc0d28-32ea-4eb8-9aba-2b1a0d5a28b3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/9 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/10 13:00:00","F_Reason":"有事需要请假。","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"0ad1f95e-caef-404b-8b39-21e8e789b9c4","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 12:05:42","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"有事需要请假。","F_ProcessParentId":null,"F_EndTime":"2020/10/10 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 16:04:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"4185baeb-053c-9b0f-de32-12b05edfdf03","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:02:58","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/12 16:04:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/12 16:06:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"79e8e6a1-d6f8-bb58-27d7-7da2c58141bf","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"张建辉的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:04:33","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/13 16:06:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"毛敏","F_IsFinished":0,"F_SchemeId":"0d33d397-62ae-4865-9adf-87a3fe8d0100","F_StartTime":"2020/10/11 9:00:00","F_Reason":"值班，处理打印订单","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ac4a2b12-ac0c-4384-bcb8-0b732a738820","F_Id":"9c4ca3eb-bcbd-f787-5956-851d00519b4a","F_SchemeName":"加班申请","F_TaskName":null,"F_ProcessName":"毛敏的加班申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:06:18","F_SchemeCode":"ExtraWork","F_DepartmentId":"5ba51dbc-f1d7-482e-a249-ebfe4a006aa0","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/11 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 16:04:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"90c5529b-d078-f265-973d-2cbb8c6f7044","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"张建辉的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:19:50","F_SchemeCode":"GoOut","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/12 16:04:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/10 17:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"2315adb4-67a2-469b-a596-0052c782345a","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"徐菊的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:09:44","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/11 17:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_Descript  ion":"出差","F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/10 17:00:00","F_Reason":"出差","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"8e2b162e-e4da-4a2e-afbd-828faa08da63","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:10:26","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_ProcessParentId":null,"F_EndTime":"2020/10/10 23:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 17:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"fb303dd7-75f7-441a-b392-f2667b6475d3","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:10:54","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/10 23:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/11 11:30:00","F_Reason":"返修冰箱","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"bf2c4090-2477-9e47-b30f-008e2ebdef8c","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"张建辉的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:16:53","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/11 21:30:00","F_TaskId":null}]}
     * info : 响应成功
     */
    private int code;
    private DataEntity data;
    private String info;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public class DataEntity implements Serializable{
        /**
         * total : 1
         * records : 24
         * page : 1
         * rows : [{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"93e06c50-2ea7-45cf-b5cb-10a9212964af","F_StartTime":"","F_Reason":"","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"3d7fb077-0bee-8fb3-0020-f6a876ced527","F_SchemeName":"客户点餐申请","F_TaskName":null,"F_ProcessName":"徐菊的客户点餐申请流程","F_CurState":"","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-07-18 17:46:24","F_SchemeCode":"CustomerCanteen","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李峰","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 8:00:00","F_Reason":"休假回家","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"64954f47-1b5c-4b22-9154-05b25ef2139b","F_Id":"16c64327-abfe-afc7-cffa-5db872cca693","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李峰的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-09-30 10:29:40","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/9 17:20:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李熙祥","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/7 9:00:00","F_Reason":"回深圳","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"7d57dc74-81a9-4d05-a78f-e51cfab5e83c","F_Id":"4cb1c5a1-2947-4b41-84c7-e36711b56fa8","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李熙祥的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 08:41:17","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"回深圳","F_ProcessParentId":null,"F_EndTime":"2020/10/9 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 13:53:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"640fbf35-3b29-4583-b703-ba9f7272a38e","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"徐菊的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:18:41","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/7 12:18:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"1565f2d1-9cd9-4889-8cb0-f09143f61e38","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:22:06","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/6 12:22:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-  b072-cee56622fd52","F_Id":"1a52615d-ad7d-468d-b58b-98b251d2985f","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:22:42","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:22:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"e3702923-8083-4baa-96b3-6af9f4bfcc60","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:24:29","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/9 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/7 12:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"7b43a4f9-c51e-498d-ab0b-66fa2603ec88","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:24:56","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 14:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:00:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"12a51176-dab9-4822-9f94-f9efe30a3701","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:25:22","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"0ea8c669-6002-49c4-97ec-c82d612fe3e3","F_StartTime":"2020/10/6 12:25:00","F_Reason":"测试","F_IsAgain":1,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"9fc5c021-4159-4861-9d88-b73d63c64597","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"不通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 12:25:57","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 12:25:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_Reason  ":"测试","F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"83d4b42e-397f-4264-98f0-b942b92a0d0f","F_StartTime":"2020/10/8 15:00:00","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"4975fb7f-9feb-4ef8-b9da-76a8da5523a5","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-06 15:59:16","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/8 20:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/6 9:00:00","F_Reason":"调休","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"72fc7ac0-769d-4d66-a1ec-910b162f28ed","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-08 09:54:27","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"调休","F_ProcessParentId":null,"F_EndTime":"2020/10/6 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/7 9:00:00","F_Reason":"有事请假一天。","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"77478989-a9a6-45a0-8b39-49a7fb003188","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-08 10:07:19","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"有事请假一天。","F_ProcessParentId":null,"F_EndTime":"2020/10/7 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"李熙祥","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/8 16:00:00","F_Reason":"test","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"7d57dc74-81a9-4d05-a78f-e51cfab5e83c","F_Id":"2beadbe7-a70f-4210-9222-3aa699429715","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李熙祥的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-09 00:43:21","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"test","F_ProcessParentId":null,"F_EndTime":"2020/10/9 16:00:00","F_TaskId":null},{"F_ProcessLevel":2,"F_EnabledMark":1,"F_CreateUserName":"李伟","F_IsFinished":1,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/9 12:30:00","F_Reason":"外出办事","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"30ec3acf-b405-4582-ab11-7eccb69c640f","F_Id":"06448df3-0cf4-0857-a7e0-3371a82fcbba","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"李伟的请假申请","F_CurState":"通过","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-09 10:20:32","F_SchemeCode":"Leave","F_DepartmentId":"65cc0d28-32ea-4eb8-9aba-2b1a0d5a28b3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/9 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"覃勇鹏","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/10 13:00:00","F_Reason":"有事需要请假。","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_Id":"0ad1f95e-caef-404b-8b39-21e8e789b9c4","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"覃勇鹏的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 12:05:42","F_SchemeCode":"Leave","F_DepartmentId":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","F_Description":"有事需要请假。","F_ProcessParentId":null,"F_EndTime":"2020/10/10 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 16:04:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"4185baeb-053c-9b0f-de32-12b05edfdf03","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:02:58","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/12 16:04:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/12 16:06:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"79e8e6a1-d6f8-bb58-27d7-7da2c58141bf","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"张建辉的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:04:33","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/13 16:06:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"毛敏","F_IsFinished":0,"F_SchemeId":"0d33d397-62ae-4865-9adf-87a3fe8d0100","F_StartTime":"2020/10/11 9:00:00","F_Reason":"值班，处理打印订单","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ac4a2b12-ac0c-4384-bcb8-0b732a738820","F_Id":"9c4ca3eb-bcbd-f787-5956-851d00519b4a","F_SchemeName":"加班申请","F_TaskName":null,"F_ProcessName":"毛敏的加班申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:06:18","F_SchemeCode":"ExtraWork","F_DepartmentId":"5ba51dbc-f1d7-482e-a249-ebfe4a006aa0","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/11 18:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 16:04:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"90c5529b-d078-f265-973d-2cbb8c6f7044","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"张建辉的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 16:19:50","F_SchemeCode":"GoOut","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/12 16:04:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"9a968dad-df04-4738-9313-6049b4899835","F_StartTime":"2020/10/10 17:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"2315adb4-67a2-469b-a596-0052c782345a","F_SchemeName":"请假申请","F_TaskName":null,"F_ProcessName":"徐菊的请假申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:09:44","F_SchemeCode":"Leave","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/11 17:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_Descript  ion":"出差","F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/10 17:00:00","F_Reason":"出差","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"8e2b162e-e4da-4a2e-afbd-828faa08da63","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"徐菊的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:10:26","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_ProcessParentId":null,"F_EndTime":"2020/10/10 23:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"徐菊","F_IsFinished":0,"F_SchemeId":"f1872320-35fa-41ed-9e78-e0607d3f2f31","F_StartTime":"2020/10/10 17:00:00","F_Reason":"测试","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"ace02f97-9f6c-4be1-b072-cee56622fd52","F_Id":"fb303dd7-75f7-441a-b392-f2667b6475d3","F_SchemeName":"外出申请","F_TaskName":null,"F_ProcessName":"徐菊的外出申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:10:54","F_SchemeCode":"GoOut","F_DepartmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","F_Description":"测试","F_ProcessParentId":null,"F_EndTime":"2020/10/10 23:00:00","F_TaskId":null},{"F_ProcessLevel":0,"F_EnabledMark":1,"F_CreateUserName":"张建辉","F_IsFinished":0,"F_SchemeId":"3e335f27-0460-450d-9a07-86635e964262","F_StartTime":"2020/10/11 11:30:00","F_Reason":"返修冰箱","F_IsAgain":null,"F_IsChildFlow":null,"F_CreateUserId":"9cf46e5c-c59d-4f15-aaa6-5c43d59c8b71","F_Id":"bf2c4090-2477-9e47-b30f-008e2ebdef8c","F_SchemeName":"出差申请","F_TaskName":null,"F_ProcessName":"张建辉的出差申请","F_CurState":"等待审批","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_TaskType":null,"F_CreateDate":"2020-10-10 17:16:53","F_SchemeCode":"BusinessTravel","F_DepartmentId":"1118f656-e4ee-4811-8187-71426be5e569","F_Description":"","F_ProcessParentId":null,"F_EndTime":"2020/10/11 21:30:00","F_TaskId":null}]
         */
        private int total;
        private int records;
        private int page;
        private List<RowsEntity> rows;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public int getTotal() {
            return total;
        }

        public int getRecords() {
            return records;
        }

        public int getPage() {
            return page;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public class RowsEntity extends BaseNode implements Serializable{
            /**
             * F_ProcessLevel : 0
             * F_EnabledMark : 1
             * F_CreateUserName : 徐菊
             * F_IsFinished : 0
             * F_SchemeId : 93e06c50-2ea7-45cf-b5cb-10a9212964af
             * F_StartTime :
             * F_Reason :
             * F_IsAgain : null
             * F_IsChildFlow : null
             * F_CreateUserId : ace02f97-9f6c-4be1-b072-cee56622fd52
             * F_Id : 3d7fb077-0bee-8fb3-0020-f6a876ced527
             * F_SchemeName : 客户点餐申请
             * F_TaskName : null
             * F_ProcessName : 徐菊的客户点餐申请流程
             * F_CurState :
             * F_CompanyId : 8d416f75-76f9-48b6-aa97-4a626cd81fee
             * F_TaskType : null
             * F_CreateDate : 2020-07-18 17:46:24
             * F_SchemeCode : CustomerCanteen
             * F_DepartmentId : 1cc82017-73a2-474f-a908-c59cb121b2d3
             * F_Description :
             * F_ProcessParentId : null
             * F_EndTime :
             * F_TaskId : null
             */
            private int F_ProcessLevel;
            private int F_EnabledMark;
            private String F_CreateUserName;
            private int F_IsFinished;
            private String F_SchemeId;
            private String F_StartTime;
            private String F_Reason;
            private String F_IsAgain;
            private String F_IsChildFlow;
            private String F_CreateUserId;
            private String F_Id;
            private String F_SchemeName;
            private String F_TaskName;
            private String F_ProcessName;
            private String F_CurState;
            private String F_CompanyId;
            private String F_TaskType;
            private String F_CreateDate;
            private String F_SchemeCode;
            private String F_DepartmentId;
            private String F_Description;
            private String F_ProcessParentId;
            private String F_EndTime;
            private String F_TaskId;
            private int FM_id;
            private String CountHours;
            private String Address;
            private String EmployeeId;


            public String getEmployeeId() {
                return EmployeeId;
            }

            public void setEmployeeId(String employeeId) {
                EmployeeId = employeeId;
            }

            public String getCountHours() {
                return CountHours;
            }

            public void setCountHours(String countHours) {
                CountHours = countHours;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }

            public int getFM_id() {
                return FM_id;
            }

            public void setFM_id(int FM_id) {
                this.FM_id = FM_id;
            }

            public void setF_ProcessLevel(int F_ProcessLevel) {
                this.F_ProcessLevel = F_ProcessLevel;
            }

            public void setF_EnabledMark(int F_EnabledMark) {
                this.F_EnabledMark = F_EnabledMark;
            }

            public void setF_CreateUserName(String F_CreateUserName) {
                this.F_CreateUserName = F_CreateUserName;
            }

            public void setF_IsFinished(int F_IsFinished) {
                this.F_IsFinished = F_IsFinished;
            }

            public void setF_SchemeId(String F_SchemeId) {
                this.F_SchemeId = F_SchemeId;
            }

            public void setF_StartTime(String F_StartTime) {
                this.F_StartTime = F_StartTime;
            }

            public void setF_Reason(String F_Reason) {
                this.F_Reason = F_Reason;
            }

            public void setF_IsAgain(String F_IsAgain) {
                this.F_IsAgain = F_IsAgain;
            }

            public void setF_IsChildFlow(String F_IsChildFlow) {
                this.F_IsChildFlow = F_IsChildFlow;
            }

            public void setF_CreateUserId(String F_CreateUserId) {
                this.F_CreateUserId = F_CreateUserId;
            }

            public void setF_Id(String F_Id) {
                this.F_Id = F_Id;
            }

            public void setF_SchemeName(String F_SchemeName) {
                this.F_SchemeName = F_SchemeName;
            }

            public void setF_TaskName(String F_TaskName) {
                this.F_TaskName = F_TaskName;
            }

            public void setF_ProcessName(String F_ProcessName) {
                this.F_ProcessName = F_ProcessName;
            }

            public void setF_CurState(String F_CurState) {
                this.F_CurState = F_CurState;
            }

            public void setF_CompanyId(String F_CompanyId) {
                this.F_CompanyId = F_CompanyId;
            }

            public void setF_TaskType(String F_TaskType) {
                this.F_TaskType = F_TaskType;
            }

            public void setF_CreateDate(String F_CreateDate) {
                this.F_CreateDate = F_CreateDate;
            }

            public void setF_SchemeCode(String F_SchemeCode) {
                this.F_SchemeCode = F_SchemeCode;
            }

            public void setF_DepartmentId(String F_DepartmentId) {
                this.F_DepartmentId = F_DepartmentId;
            }

            public void setF_Description(String F_Description) {
                this.F_Description = F_Description;
            }

            public void setF_ProcessParentId(String F_ProcessParentId) {
                this.F_ProcessParentId = F_ProcessParentId;
            }

            public void setF_EndTime(String F_EndTime) {
                this.F_EndTime = F_EndTime;
            }

            public void setF_TaskId(String F_TaskId) {
                this.F_TaskId = F_TaskId;
            }

            public int getF_ProcessLevel() {
                return F_ProcessLevel;
            }

            public int getF_EnabledMark() {
                return F_EnabledMark;
            }

            public String getF_CreateUserName() {
                return F_CreateUserName;
            }

            public int getF_IsFinished() {
                return F_IsFinished;
            }

            public String getF_SchemeId() {
                return F_SchemeId;
            }

            public String getF_StartTime() {
                return F_StartTime;
            }

            public String getF_Reason() {
                return F_Reason;
            }

            public String getF_IsAgain() {
                return F_IsAgain;
            }

            public String getF_IsChildFlow() {
                return F_IsChildFlow;
            }

            public String getF_CreateUserId() {
                return F_CreateUserId;
            }

            public String getF_Id() {
                return F_Id;
            }

            public String getF_SchemeName() {
                return F_SchemeName;
            }

            public String getF_TaskName() {
                return F_TaskName;
            }

            public String getF_ProcessName() {
                return F_ProcessName;
            }

            public String getF_CurState() {
                return F_CurState;
            }

            public String getF_CompanyId() {
                return F_CompanyId;
            }

            public String getF_TaskType() {
                return F_TaskType;
            }

            public String getF_CreateDate() {
                return F_CreateDate;
            }

            public String getF_SchemeCode() {
                return F_SchemeCode;
            }

            public String getF_DepartmentId() {
                return F_DepartmentId;
            }

            public String getF_Description() {
                return F_Description;
            }

            public String getF_ProcessParentId() {
                return F_ProcessParentId;
            }

            public String getF_EndTime() {
                return F_EndTime;
            }

            public String getF_TaskId() {
                return F_TaskId;
            }

            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return null;
            }
        }
    }
}
