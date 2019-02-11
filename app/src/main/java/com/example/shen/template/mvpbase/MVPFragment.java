package com.example.shen.template.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.common.util.LoadingDialogUtils;

/**
 * Created by Administrator on 2017/9/21.
 *
 * @param <P> 具体的presenter
 */

public abstract class MVPFragment<P extends BasePresenter> extends BaseFragment implements BaseView{
    protected P mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    protected abstract P createPresent();

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
