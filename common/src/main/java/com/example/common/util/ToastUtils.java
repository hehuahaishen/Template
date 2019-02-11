package com.example.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.R;
import com.example.common.app.BaseApp;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 单例吐司的实现
 */
public class ToastUtils {
    private static Toast toast = null;

    public static String getMessage(Object object) {
        // 这里的参数为object而不是String的目的是为了能传入更多类型的数据
        // 而不管传入什么类型，我们都把它转成String类型，这样就使用起来就比较方便了，不用再进行toString操作
        // 而我们如果传进来，不是数据类型，而是自己定义的类，就会打印出对象的toString方法
        return object.toString();
    }

    /*---------------------------------------------------------------*/
    /*---------------------- 不一定在"主线程"中显示 -------------------*/
    /*---------------------------------------------------------------*/
    public static void showToast(Object objectMsg) {
        String message = getMessage(objectMsg);
        if (toast == null) {
            synchronized (ToastUtils.class) {
                if (toast == null) {
                    toast = Toast.makeText(BaseApp.getContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        } else {
            toast.setText(message);
            toast.show();
        }
    }

    public static void showToast(Context context, Object objectMsg) {
        String message = getMessage(objectMsg);
        if (toast == null) {
            synchronized (ToastUtils.class) {
                if (toast == null) {
                    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        } else {
            toast.setText(message);
            toast.show();
        }
    }


    /**
     * 自定义Toast
     *
     * @param context
     * @param objectMsg         打印的消息
     * @param gravity           显示的文字位置
     * @param duration          显示的时间
     */
    public static void showCustomToast(Context context, Object objectMsg, int gravity, int duration){
        String message = getMessage(objectMsg);
        if (toast == null) {
            synchronized (ToastUtils.class) {
                if (toast == null) {
                    View v = LayoutInflater.from(context).inflate(R.layout.custom_toast, null, false);
                    TextView textView = v.findViewById(R.id.tv_message_CustomToast);
                    textView.setText(message);
                    toast = new Toast(context);
                    toast.setView(v);
                    toast.setGravity(gravity, 0, 0);
                    toast.setDuration(duration);
                    toast.show();
                }
            }
        } else {
            View v = toast.getView();
            TextView textView = v.findViewById(R.id.tv_message_CustomToast);
            textView.setText(message);
            toast.setGravity(gravity, 0, 0);
            toast.setDuration(duration);
            toast.show();
        }
    }



    /*---------------------------------------------------------------*/
    /*----------------------- 一定在"主线程"中显示 --------------------*/
    /*---------------------------------------------------------------*/
    public static void showToastByMainThread(Object objectMsg, final Activity activity) {
        final String message = getMessage(objectMsg);
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public static void showToastByMainThread(Object objectMsg){
        final String message = getMessage(objectMsg);
        Observable.just(message)
                .subscribeOn(Schedulers.io())                 // 上游
                .observeOn(AndroidSchedulers.mainThread())      // 下游
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        showToast(message);
                    }


                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
