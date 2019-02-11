package com.example.shen.template.mvpbase;

/**
 *
 */
public interface BaseView {
    void onSuccess(String msg);

    void onError(int code, String msg);

    void showLoading(String message);

    void hideLoading();
}
