package com.example.shen.template.net;

/**
 *
 */
public interface Url {

    // http://zx.com/api/Recyclers/verify_reclaim?user_id="f529b57097fa31511fc12cc1a077ece4"&scrambling="asdf23523"
    // http://zx.com/api/Recovery_equipment/adv
    // http://zx.com/api/Recovery_equipment/goodsRecovery?scrambling=4130c39a5d625c7725998ad1197103c7
    // http://zx.com/api/index/versionsUpdate

    // https://api.laigewan.com/upload/images/ats_recycling/ats/20180731164848248193835_ats.png
    // https://api.laigewan.com/upload/images/recovery_equipment/qr_code/0000000045.png
    // https://api.laigewan.com/api/Recovery_equipment/adv?place=1


    /** 线上 */
    String Url = "https://api.laigewan.com/api/";
    /** 线下 */
    // String Url = "http://zx.com/api/";

    /** 图片线上 */
    String ImagUrl = "https://api.laigewan.com/upload/images/";
    /** 图片线下 */
    // String ImagUrl = "http://zx.com/upload/images/";



    String HTTP = "http://";
    String HTTPS = "https://";

    /** 获取新版本 */
    String versionsUpdate = "recovery_equipment/versionsUpdate";
}