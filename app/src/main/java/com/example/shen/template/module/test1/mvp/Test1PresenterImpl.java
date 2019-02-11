package com.example.shen.template.module.test1.mvp;

import com.example.shen.template.mvpbase.BaseObserverTemp;
import com.example.shen.template.mvpbase.BasePresenter;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * author:  shen
 * date:
 */
public class Test1PresenterImpl extends BasePresenter<Test1View, Test1ModelImpl> {
    public Test1PresenterImpl(Test1View mvpView) {
        super(mvpView);
    }

    @Override
    protected Test1ModelImpl createModel() {
        return new Test1ModelImpl();
    }


    public void versionUpdated(){
        mModel.versionUpdated(new BaseObserverTemp<ResponseBody>(this, mvpView, true, "加载中...") {
            @Override
            protected void onSuccess(ResponseBody data) {
                try {
                    mvpView.showVersion(data.string().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
