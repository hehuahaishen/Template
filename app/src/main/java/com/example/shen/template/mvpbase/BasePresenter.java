package com.example.shen.template.mvpbase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @param <V>具体的View
 * @param <M>具体的model
 */
public abstract class BasePresenter<V, M extends BaseModel> {
    protected V mvpView;
    protected CompositeDisposable mDisposable;
    protected M mModel;

    public BasePresenter(V mvpView) {
        this.mvpView = mvpView;
        mModel = createModel();
    }

    protected abstract M createModel();

    public void detachView() {
        this.mvpView = null;
        clearDisposable();
    }

    /**
     * Rxjava取消注册(取消订阅)，以避免内存泄露
     */
    public void clearDisposable() {
        if (mDisposable != null && mDisposable.size() != 0) {
            mDisposable.clear();        // 执行 disposable.dispose();
        }
    }


    /**
     * 添加 -- disposable -- 取消订阅的时候要用到 (执行 disposable.dispose();)
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }

}
