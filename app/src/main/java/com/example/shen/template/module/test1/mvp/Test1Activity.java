package com.example.shen.template.module.test1.mvp;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app.R;
import com.example.shen.template.mvpbase.MVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:  shen
 * date:    2019/2/11
 */
public class Test1Activity extends MVPActivity<Test1PresenterImpl> implements Test1View {
    @BindView(R.id.tv_message_Test1Activity)
    TextView mTvMessage;

    @Override
    protected Test1PresenterImpl createPresenter() {
        return new Test1PresenterImpl(this);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_test1;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showVersion(String s) {
        mTvMessage.setText(s);
    }

    @OnClick(R.id.tv_loading_Test1Activity)
    public void onViewClicked() {
        mPresenter.versionUpdated();
    }
}
