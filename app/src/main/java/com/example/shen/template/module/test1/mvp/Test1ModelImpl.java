package com.example.shen.template.module.test1.mvp;

import com.example.shen.template.mvpbase.BaseModel;
import com.example.shen.template.mvpbase.BaseObserverTemp;
import com.example.shen.template.net.Api;

import java.util.Map;

/**
 * author:  shen
 * date:
 */
public class Test1ModelImpl extends BaseModel {
    public void versionUpdated(BaseObserverTemp observer) {
        universal(mApiService.versionUpdated(), observer);
    }
}
