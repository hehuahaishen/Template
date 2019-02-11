package com.example.shen.template.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.common.util.LoadingDialogUtils;
import com.example.shen.template.base.BaseActivity;


/**
 * @param <P>具体的presenter
 */
public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity implements BaseView{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * @return 返回具体的Persenter
     */
    protected abstract P createPresenter();


    @Override
    public void onSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void onError(int code, String msg) {
        showToast("code:" + code + " -- msg:" + msg);
    }

    @Override
    public void showLoading(String message) {
        LoadingDialogUtils.showDialog(mContext, message);
    }

    @Override
    public void hideLoading() {
        LoadingDialogUtils.hideDialog();
    }
}
