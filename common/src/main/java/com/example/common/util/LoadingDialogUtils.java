package com.example.common.util;

import android.content.Context;

import com.example.common.view.LoadingDialog.LoadingDialog;
import com.example.common.view.LoadingDialog.LoadingDialogConfig;

/**
 * author:  shen
 * date:    2018/7/13
 *
 * 重复扫描
 */
public class LoadingDialogUtils {

    private static LoadingDialog mLoadingDialog;

    public static void showDialog(Context context, String massage) {

        hideDialog();

        mLoadingDialog = new LoadingDialog(context);
        /** 窗口的配置 */
        LoadingDialogConfig config = new LoadingDialogConfig.Builder()
                .setContent(massage)
                .create();

        mLoadingDialog.init(config);
        mLoadingDialog.show();
    }

    public static void hideDialog(){
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog.cancel();
        }
        mLoadingDialog = null;
    }
}
