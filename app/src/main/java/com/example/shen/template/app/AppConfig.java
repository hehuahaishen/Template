package com.example.shen.template.app;

import com.example.common.util.L;

/**
 * describe:在这里进行初始化App需要初始化的部分内容
 */
public class AppConfig {
    private static AppConfig instance;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * 初始化
     */
    public void init() {
        L.d("我被初始化了");
    }
}