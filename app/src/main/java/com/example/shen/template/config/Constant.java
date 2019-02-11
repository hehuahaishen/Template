package com.example.shen.template.config;

import android.os.Environment;

import java.io.File;

/**
 * 全局变量
 */
public class Constant {

    /* 本地文件路径 */
    /** 如果使用"/"==>File.separator<p> */
    /** 资源放置路径 -- /storage/emulated/0/ */
    public final static String rootDir = Environment.getExternalStorageDirectory().getPath() + File.separator;


    /* 网络请求信息后台返回的状态 */
    /** 200:成功; 404:没有数据; 999:用户不存在; 500:为异常 */
    public static final int REQUEST_OK = 200;

}
