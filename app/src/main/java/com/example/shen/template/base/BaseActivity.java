package com.example.shen.template.base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.example.common.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    Unbinder unbinder;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);       // 常亮

        // Android setRequestedOrientation用法
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 通过程序改变屏幕显示的方向
        // 1.landscape：     横屏(风景照) ， 显示时宽度大于高度；
        // 2.portrait：      竖屏(肖像照) ， 显示时高度大于宽度；
        // 3.user：          用户当前的首选方向；
        // 4.behind：        继承Activity堆栈中当前Activity下面的那个Activity的方向；
        // 5.sensor：        由物理感应器决定显示方向，它取决于用户如何持有设备，当设备被旋转时方向会随之变化——在横屏与竖屏之间；
        // 6.nosensor：      忽略物理感应器——即显示方向与物理感应器无关，不管用户如何旋转设备显示方向都不会随着改变("unspecified"设置除外)；
        // 7.unspecified：   未指定，此为默认值，由Android系统自己选择适当的方向，选择策略视具体设备的配置情况而定，因此不同的设备会有不同的方向选择；
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getResId());
        mContext = this;
        unbinder = ButterKnife.bind(this);

        init();
        initDataEvent();
    }


    protected abstract int getResId();

    protected void init() {
    }

    protected abstract void initDataEvent();

    /** 增大音量 */
    protected void setAudioEnlarge() {}
    /** 减少音量 */
    protected void setAudioReduce() { }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    protected void showToast(String message){
        ToastUtils.showToast(message);
    }

    /**
     * 当前在前台的是不是本Activity
     * @return
     */
    protected boolean isCurrentActivity(){
        ActivityManager am = (ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        // LogUtils.i("current:" + "包名:"+cn.getPackageName());
        // LogUtils.i("currentclass:" + "类名:"+cn.getClassName());
        if(cn.getPackageName().equals(mContext.getPackageName())) {
            if (cn.getClassName().equals(getClass().getName())) {                   /* 主界面 */
                return true;
            }
        }
        return false;
    }

    /**
     * 覆写startActivity方法，加入切换动画
     */
    public void startActivity(Bundle bundle, Class<?> target) {
        Intent intent = new Intent(this, target);
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
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 不带切换动画
     */
    public void finishSimple() {
        super.finish();
    }

    public void finishResult(Intent intent) {
        setResult(RESULT_OK, intent);
        this.finish();
    }

    public void finishResult() {
        setResult(RESULT_OK);
        this.finish();
    }
}
