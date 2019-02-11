package com.example.common.view.LoadingDialog;

import android.view.View;

/**
 * @author:  shen
 * @date:    2018/1/30
 *
 */
public class LoadingDialogConfig {

    /** 内容 */
    private String mContent = "";

    /** 点击"返回键" -- false:不关闭窗口 true:关闭窗口 */
    private boolean mCancelable = true;



    /**
     * 构造函数
     */
    private LoadingDialogConfig(){

    }

    public String getContent() {
        return mContent;
    }

    public boolean isCancelable() {
        return mCancelable;
    }

    /**
     * 内部了 -- Builder
     */
    public static class Builder{
        /** 内容 */
        private String content = "";
        /** 点击"返回键" -- false:不关闭窗口 true:关闭窗口 */
        private boolean cancelable = true;


        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public void setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
        }


        // 设置配置
        private void applyConfig(LoadingDialogConfig config){
            config.mContent = this.content;
            config.mCancelable = this.cancelable;
        }

        public LoadingDialogConfig create(){
            LoadingDialogConfig dialogConfig = new LoadingDialogConfig();
            applyConfig(dialogConfig);
            return dialogConfig;
        }

    }

    public abstract static class OnClickListener implements View.OnClickListener {
    }

}
