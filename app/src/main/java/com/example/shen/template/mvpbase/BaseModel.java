package com.example.shen.template.mvpbase;

import com.example.shen.template.net.Api;
import com.example.shen.template.net.ApiEngine;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseModel {

    protected final Api mApiService;
    public static long delayTime = 500;
    public static long delayTime200 = 200;
    public BaseModel() {
        mApiService = ApiEngine.getInstance().getApiService();
    }


    /**
     * 通用的
     *
     * @param observable
     * @param observer
     */
    public static void universal(Observable observable, BaseObserver observer) {
        universal(observable, observer, true);
    }

    public static void universal(Observable observable, BaseObserver observer, boolean isDelay) {
        Observable obser = observable;
        if (isDelay) {
            obser = observable.delay(delayTime, TimeUnit.MILLISECONDS);
        }

        obser.subscribeOn(Schedulers.io())                  // 上游
                .observeOn(AndroidSchedulers.mainThread())  // 下游
                .subscribe(observer);
    }


    /**
     * 通用的
     *
     * @param observable
     * @param observer
     */
    public static void universal(Observable observable, BaseObserverTemp observer) {
        universal(observable, observer, true);
    }

    public static void universal(Observable observable, BaseObserverTemp observer, boolean isDelay) {
        Observable obser = observable;
        if (isDelay) {
            obser = observable.delay(delayTime, TimeUnit.MILLISECONDS);
        }

        obser.subscribeOn(Schedulers.io())                  // 上游
                .observeOn(AndroidSchedulers.mainThread())  // 下游
                .subscribe(observer);
    }
}
