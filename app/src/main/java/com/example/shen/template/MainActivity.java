package com.example.shen.template;

import android.content.Intent;
import android.view.View;
import com.example.shen.template.module.test1.mvp.Test1Activity;
import com.example.shen.template.mvpbase.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }


    @OnClick({R.id.tv_test1_MainActivity})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_test1_MainActivity:                /* 测试1 */
                startActivity(new Intent(this, Test1Activity.class));
                break;
        }
    }
}
