package com.example.shen.template.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Reims
 * init:获得数据new对象等
 * initData:初始化数据
 * initEvent:设置监听事件
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    Unbinder unbinder;
    protected Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        mContext = getActivity();

        return mRootView;
    }

    public abstract int getResId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initDataEvent();
    }

    protected void init() {
    }

    protected abstract void initDataEvent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    protected void showToast(String message){
        ToastUtils.showToast(message);
    }


    /**
     * 覆写startActivity方法，加入切换动画
     */
    public void startActivity(Bundle bundle, Class<?> target) {
        Intent intent = new Intent(getActivity(), target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 带回调的跳转
     *
     * @param bundle
     * @param requestCode
     * @param target
     */
    public void startForResult(Bundle bundle, int requestCode, Class<?> target) {
        Intent intent = new Intent(getActivity(), target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 不带切换动画
     */
    public void finishSimple() {
        getActivity().finish();
    }

    public void finishResult(Intent intent) {
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();
    }

    public void finishResult() {
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

}
