package com.example.shen.template.module.test1.mvp;

import com.example.shen.template.mvpbase.BaseModel;
import com.example.shen.template.mvpbase.BaseObjectObserver;

/**
 * author:  shen
 * date:
 */
public class Test1ModelImpl extends BaseModel {
    public void versionUpdated(BaseObjectObserver observer) {
        universal(mApiService.versionUpdated(), observer);
    }
}
