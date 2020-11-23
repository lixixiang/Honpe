package com.honpe.lxx.app.ui.main.oa.ui.car_manager.entity;

import java.util.List;

/**
 * FileName: CarIntoEntity
 * Author: asus
 * Date: 2020/10/21 17:53
 * Description:
 */
public class CarInfoEntity {

    /**
     * code : 200
     * data : [{"StartDate":"2020-09-22 00:00:00","CardNo":"云A1B06J","UserName":"许汝鹏","ValidDate":"2099-10-23 00:00:00","RegisterDate":"2020-09-23 16:01:02","CarNo":null,"CID":20321},{"StartDate":"2020-10-07 00:00:00","CardNo":"粤TXK532","UserName":"李小康","ValidDate":"2050-11-07 00:00:00","RegisterDate":"2020-10-07 10:25:25","CarNo":null,"CID":21599},{"StartDate":"2020-10-20 00:00:00","CardNo":"粤ST3S35","UserName":"胡小川","ValidDate":"2099-11-20 00:00:00","RegisterDate":"2020-10-20 16:52:25","CarNo":null,"CID":23236},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤S8MHK2","UserName":"胡俊颖","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":451},{"StartDate":"2020-09-07 00:00:00","CardNo":"粤S8K17N","UserName":"郭赛群","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:10:19","CarNo":null,"CID":18576},{"StartDate":"2020-09-07 00:00:00","CardNo":"粤S7Z09V","UserName":"韩立双","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:11:25","CarNo":null,"CID":18580},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤S7YU36","UserName":"梁深","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":460},{"StartDate":"2020-08-29 00:00:00","CardNo":"粤S3EM50","UserName":"张灵和","ValidDate":"2099-09-29 00:00:00","RegisterDate":"2020-07-29 18:38:12","CarNo":null,"CID":14334},{"StartDate":"2020-03-12 00:00:00","CardNo":"粤S1GX65","UserName":"徐金海","ValidDate":"2021-10-12 00:00:00","RegisterDate":"2020-03-12 17:35:56","CarNo":null,"CID":3327},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤S0RX40","UserName":"陈昌成","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":401},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤S0LC31","UserName":"陈奕龙","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":443},{"StartDate":"2020-08-05 00:00:00","CardNo":"粤P3S081","UserName":"张亦标","ValidDate":"2054-09-06 00:00:00","RegisterDate":"2020-08-06 11:01:40","CarNo":null,"CID":15205},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤M5M920","UserName":"洪志英","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":440},{"StartDate":"2020-08-06 00:00:00","CardNo":"粤LZ860G","UserName":"杨健","ValidDate":"2041-09-06 00:00:00","RegisterDate":"2020-08-06 10:47:43","CarNo":null,"CID":15196},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤LB280D","UserName":"朱晓玮","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":461},{"StartDate":"2020-10-07 00:00:00","CardNo":"粤L9189V","UserName":"周德创","ValidDate":"2050-11-07 00:00:00","RegisterDate":"2020-10-07 10:23:32","CarNo":null,"CID":21595},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤L55U67","UserName":"白玉强","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":439},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤KZT691","UserName":"陈东晓","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":435},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤KLM675","UserName":"吴先茂","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":432},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤KCP369","UserName":"陈祖泽","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":431},{"StartDate":"2020-04-12 00:00:00","CardNo":"粤K951R8","UserName":"陈祖恩","ValidDate":"2099-05-12 00:00:00","RegisterDate":"2020-03-12 17:37:59","CarNo":null,"CID":3329},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤K8830N","UserName":"周准艺","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":442},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤K75Q80","UserName":"梁学海","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":429},{"StartDate":"2020-09-07 00:00:00","CardNo":"粤K7032U","UserName":"杨敏","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:09:58","CarNo":null,"CID":18575},{"StartDate":"2020-09-07 00:00:00","CardNo":"粤K3832U","UserName":"陈龙","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:11:50","CarNo":null,"CID":18582},{"StartDate":"2020-08-06 00:00:00","CardNo":"粤K291Y8","UserName":"范远祥","ValidDate":"2043-09-06 00:00:00","RegisterDate":"2020-08-06 11:05:02","CarNo":null,"CID":15207},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤K103A1","UserName":"梁富玄","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":427},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤J9967Q","UserName":"范良根","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":425},{"StartDate":"2020-07-29 00:00:00","CardNo":"粤J515Z3","UserName":"向贞","ValidDate":"2099-08-29 00:00:00","RegisterDate":"2020-07-29 18:37:12","CarNo":null,"CID":14333},{"StartDate":"2020-08-20 00:00:00","CardNo":"粤G6M097","UserName":"张隆状","ValidDate":"2050-09-20 00:00:00","RegisterDate":"2020-08-20 15:13:46","CarNo":null,"CID":16868},{"StartDate":"2020-07-29 00:00:00","CardNo":"粤E360RY","UserName":"郑万荣","ValidDate":"2099-08-29 00:00:00","RegisterDate":"2020-07-29 18:28:46","CarNo":null,"CID":14327},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BUJ968","UserName":"林总","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":421},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BQ7J66","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":380},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BL21H5","UserName":"黄保勤  ","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":459},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BH259B","UserName":"杜耿锋","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":416},{"StartDate":"2020-08-05 00:00:00","CardNo":"粤BFH6552","UserName":"刘佳星","ValidDate":"2054-09-06 00:00:00","RegisterDate":"2020-08-06 10:51:43","CarNo":null,"CID":15199},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BFG7300","UserName":"李小辉","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:58","CarNo":null,"CID":420},{"StartDate":"2020-10-20 00:00:00","CardNo":"粤BFF3222","UserName":"杨帅","ValidDate":"2099-11-20 00:00:00","RegisterDate":"2020-10-20 16:51:48","CarNo":null,"CID":23235},{"StartDate":"2020-10-10 00:00:00","CardNo":"粤BFB1601","UserName":"林彬彬","ValidDate":"2099-11-10 00:00:00","RegisterDate":"2020-10-10 10:26:04","CarNo":null,"CID":21924},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤BC76M6","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":381},{"StartDate":"2020-10-07 00:00:00","CardNo":"粤B9B75L","UserName":"公司车辆","ValidDate":"2050-11-07 00:00:00","RegisterDate":"2020-10-07 10:22:27","CarNo":null,"CID":21594},{"StartDate":"2020-01-31 00:00:00","CardNo":"粤B92JR8","UserName":"潘卫东","ValidDate":"2100-02-28 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":387},{"StartDate":"2020-09-22 00:00:00","CardNo":"粤B8SC58","UserName":"杜静燕","ValidDate":"2099-10-23 00:00:00","RegisterDate":"2020-09-23 15:53:44","CarNo":null,"CID":20316},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B8JV27","UserName":"潘东海","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":412},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B8J46X","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":385},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B8D521","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:5 8:56","CarNo":null,"CID":379},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B7KJ58","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":377},{"StartDate":"2020-10-07 00:00:00","CardNo":"粤B78NK1","UserName":"孙先军","ValidDate":"2050-11-07 00:00:00","RegisterDate":"2020-10-07 10:24:30","CarNo":null,"CID":21598},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B7449Q","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":382},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B73T70","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":386},{"StartDate":"2020-08-06 00:00:00","CardNo":"粤B72FM0","UserName":"阳永平","ValidDate":"2047-09-06 00:00:00","RegisterDate":"2020-08-06 10:54:07","CarNo":null,"CID":15201},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B6B22R","UserName":"邹恒星","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":408},{"StartDate":"2020-08-06 00:00:00","CardNo":"粤B5MV65","UserName":"李萍","ValidDate":"2099-09-06 00:00:00","RegisterDate":"2020-08-06 11:33:39","CarNo":null,"CID":15219},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B5KA57","UserName":"潘卫东","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":407},{"StartDate":"2020-10-20 00:00:00","CardNo":"粤B448MF","UserName":"谭治忠","ValidDate":"2099-11-20 00:00:00","RegisterDate":"2020-10-20 16:51:18","CarNo":null,"CID":23234},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B3GU68","UserName":"林总","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":383},{"StartDate":"2020-09-08 00:00:00","CardNo":"粤B3G367","UserName":"公司车辆","ValidDate":"2060-11-08 00:00:00","RegisterDate":"2020-09-08 18:33:11","CarNo":null,"CID":18724},{"StartDate":"2020-01-31 00:00:00","CardNo":"粤B371Y6","UserName":"袁斌","ValidDate":"2100-02-28 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":467},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B2T2C3","UserName":"叶富强","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:59","CarNo":null,"CID":456},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B2KF21","UserName":"公司车辆","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:56","CarNo":null,"CID":378},{"StartDate":"2019-12-26 00:00:00","CardNo":"粤B1H78X","UserName":"梁波雅","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":403},{"StartDate":"2020-09-07 00:00:00","CardNo":"粤B1H48H","UserName":"覃春华","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:11:37","CarNo":null,"CID":18581},{"StartDate":"2020-09-22 00:00:00","CardNo":"粤B0C55X","UserName":"杜林","ValidDate":"2099-10-23 00:00:00","RegisterDate":"2020-09-23 15:54:18","CarNo":null,"CID":20317},{"StartDate":"2020-09-25 16:30:22","CardNo":"粤A5382警","UserName":"临时车牌用户","ValidDate":"2030-09-25 16:30:22","RegisterDate":"2020-09-25 16:30:22","CarNo":null,"CID":20650},{"StartDate":"2020-09-22 00:00:00","CardNo":"豫RD761H","UserName":"汪海峰","ValidDate":"2099-10-23 00:00:00","RegisterDate":"2020-09-23 16:00:11","CarNo":null,"CID":20320},{"StartDate":"2020-08-06 00:00:00","CardNo":"湘MCP759","UserName":"周晴华","ValidDate":"2052-09-06 00:00:00","RegisterDate":"2020-08-06 10:58:37","CarNo":null,"CID":15203},{"StartDate":"2020-03-12 00:00:00","CardNo":"湘HLY991","UserName":"李新明","ValidDate":"2021-10-12 00:00:00","RegisterDate":"2020-03-12 17:34:40","CarNo":null,"CID":3325},{"StartDate":"2019-12-26 00:00:00","CardNo":"湘EP0201","UserName":"刘少林","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":398},{"StartDate":"2019-12-26 00:00:00","CardNo":"湘D7 T932","UserName":"袁书杰","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":395},{"StartDate":"2020-08-06 00:00:00","CardNo":"闽DH161J","UserName":"林彬彬","ValidDate":"2099-09-06 00:00:00","RegisterDate":"2020-08-06 11:30:51","CarNo":null,"CID":15218},{"StartDate":"2019-12-26 00:00:00","CardNo":"闽C36FQ7","UserName":"陈婉琴","ValidDate":"2099-12-31 00:00:00","RegisterDate":"2019-12-26 11:58:57","CarNo":null,"CID":394},{"StartDate":"2020-09-30 00:00:00","CardNo":"桂AS8M20","UserName":"覃勇鹏","ValidDate":"2099-10-30 00:00:00","RegisterDate":"2020-09-30 18:07:04","CarNo":null,"CID":21262},{"StartDate":"2020-04-12 00:00:00","CardNo":"赣E59J53","UserName":"吴神保","ValidDate":"2099-05-12 00:00:00","RegisterDate":"2020-03-12 17:37:16","CarNo":null,"CID":3328},{"StartDate":"2020-09-07 00:00:00","CardNo":"鄂H31N86","UserName":"王运涛","ValidDate":"2099-10-07 00:00:00","RegisterDate":"2020-09-07 15:11:10","CarNo":null,"CID":18579},{"StartDate":"2020-09-22 00:00:00","CardNo":"川AA54V4","UserName":"刘朋革","ValidDate":"2099-10-23 00:00:00","RegisterDate":"2020-09-23 15:58:49","CarNo":null,"CID":20318},{"StartDate":"2020-08-17 17:25:55","CardNo":"TK82445","UserName":"临时车牌用户","ValidDate":"2030-08-17 17:25:55","RegisterDate":"2020-08-17 17:25:55","CarNo":null,"CID":16531},{"StartDate":"2020-10-04 10:42:49","CardNo":"RJ96288","UserName":"临时车牌用户","ValidDate":"2030-10-04 10:42:49","RegisterDate":"2020-10-04 10:42:49","CarNo":null,"CID":21376},{"StartDate":"2020-09-25 16:30:09","CardNo":"QB45382","UserName":"临时车牌用户","ValidDate":"2030-09-25 16:30:09","RegisterDate":"2020-09-25 16:30:09","CarNo":null,"CID":20649},{"StartDate":"2020-10-19 20:30:22","CardNo":"QB43066","UserName":"临时车牌用户","ValidDate":"2030-10-19 20:30:22","RegisterDate":"2020-10-19 20:30:22","CarNo":null,"CID":23129},{"StartDate":"2020-08-21 17:22:40","CardNo":"QB41207","UserName":"临时车牌用户","ValidDate":"2030-08-21 17:22:40","RegisterDate":"2020-08-21 17:22:40","CarNo":null,"CID":17000},{"StartDate":"2020-09-21 17:50:09","CardNo":"QB41149","UserName":"临时车牌用户","ValidDate":"2030-09-21 17:50:09","RegisterDate":"2020-09-21 17:50:09","CarNo":null,"CID":20068},{"StartDate":"2020-08-19 12:22:42","CardNo":"GB40418","UserName":"临时车牌用户","ValidDate":"2030-08-19 12:22:42","RegisterDate":"2020-08-19 12:22:42","CarNo":null,"CID":16727},{"StartDate":"2020-10-21 17:22:54","CardNo":"DG75491","UserName":"临时车牌用户","ValidDate":"2030-10-21 17:22:54","RegisterDate":"2020-10-21 17:22:54","CarNo":null,"CID":23394},{"StartDate":"2020-09-28 10:25:41","CardNo":"BM12353","UserName":"临时车牌用户","ValidDate":"2030-09-28 10:25:41","RegisterDate":"2020-09-28 10:25:41","CarNo":null,"CID":20945},{"StartDate":"2020-08-29 11:42:35","CardNo":"BG82216","UserName":"临时车牌用户","ValidDate":"2030-08-29 11:42:35","RegisterDate":"2020-08-29 11:42:35","CarNo":null,"CID":17700},{"StartDate":"2020-09-11 22:02:31","CardNo":"BA76270","UserName":"临时车牌用户","ValidDate":"2030-09-11 22:02:31","RegisterDate":"2020-09-11 22:02:31","CarNo":null,"CID":19069},{"StartDate":"2020-10-15 23:13:50","CardNo":"BA60153","UserName":"临时车牌用户","ValidDate":"2030-10-15 23:13:50","RegisterDate":"2020-10-15 23:13:50","CarNo":null,"CID":22666},{"StartDate":"2020-09-11 12:32:51","CardNo":"BA15569","UserName":"临时车牌用户","ValidDate":"2030-09-11 12:32:51","RegisterDate":"2020-09-11 12:32:51","CarNo":null,"CID":18999}]
     * info : 响应成功
     */
    private int code;
    private List<DataEntity> data;
    private String info;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * StartDate : 2020-09-22 00:00:00
         * CardNo : 云A1B06J
         * UserName : 许汝鹏
         * ValidDate : 2099-10-23 00:00:00
         * RegisterDate : 2020-09-23 16:01:02
         * CarNo : null
         * CID : 20321
         */
        private String StartDate;
        private String CardNo;
        private String UserName;
        private String ValidDate;
        private String RegisterDate;
        private String CarNo;
        private int CID;

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setValidDate(String ValidDate) {
            this.ValidDate = ValidDate;
        }

        public void setRegisterDate(String RegisterDate) {
            this.RegisterDate = RegisterDate;
        }

        public void setCarNo(String CarNo) {
            this.CarNo = CarNo;
        }

        public void setCID(int CID) {
            this.CID = CID;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getCardNo() {
            return CardNo;
        }

        public String getUserName() {
            return UserName;
        }

        public String getValidDate() {
            return ValidDate;
        }

        public String getRegisterDate() {
            return RegisterDate;
        }

        public String getCarNo() {
            return CarNo;
        }

        public int getCID() {
            return CID;
        }
    }
}
