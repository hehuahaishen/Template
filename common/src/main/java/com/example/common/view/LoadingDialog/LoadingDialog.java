package com.example.common.view.LoadingDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.common.R;
import com.example.common.util.StringUtils;

/**
 *
 */
public class LoadingDialog extends AlertDialog {


    /** 内容 */
    private TextView mTvContent;

    private String mContentText = "";

    /** 窗口的配置 */
    private LoadingDialogConfig mConfig = new LoadingDialogConfig.Builder()
            .setContent("内容")
            .create();


    // RescanDialog shenDialog = new RescanDialog(mContext);
    // shenDialog.init(config);
    // shenDialog.show();



    public LoadingDialog(Context context) {
        super(context, R.style.alert_dialog);
    }


    /**
     * 获得窗口参数
     * @param config
     */
    public LoadingDialog init(LoadingDialogConfig config){
        mConfig = config;
        return this;
    }


    /*-----------  要注意到一个问题，dialog.show() 之后才调用 onCreate() --------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);

        // 安卓弹出ProgressDialog进度框之后触摸屏幕就消失了的解决方法
        setCanceledOnTouchOutside(false);

        // 设置此窗口的设置
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        window.setAttributes(params);

        initView();
        setView();
    }


    private void initView(){
        mTvContent = findViewById(R.id.tv_message_LoadingDialog);
    }


    private void setView(){
        if(mConfig.isCancelable()){
            setCancelable(true);
        }else {
            setCancelable(false);
        }

        if (mTvContent != null && StringUtils.isNotEmpty(mConfig.getContent())) {
            mTvContent.setText(mConfig.getContent());
        }
    }


    /*---------------------------- 更改控件内容 -------------------------------*/
    public LoadingDialog setContentText (String text) {
        mContentText = text;
        if (mTvContent != null && StringUtils.isNotEmpty(text)) {
            mTvContent.setText(mContentText);
        }
        return this;
    }
}
