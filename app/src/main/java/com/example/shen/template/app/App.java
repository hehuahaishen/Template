package com.example.shen.template.app;

import com.example.common.app.BaseApp;

/**
 * describe:自定义Application
 */
public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里进行第三方的初始化
        AppConfig.getInstance().init();
    }
}
