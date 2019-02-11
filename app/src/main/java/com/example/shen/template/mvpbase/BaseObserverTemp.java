package com.example.shen.template.mvpbase;

import com.example.common.util.L;
import com.example.common.util.ResUtil;
import com.example.shen.template.R;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2017/9/21.
 *
 */
public abstract class BaseObserverTemp<T> implements Observer {

    /* 对应HTTP的状态码 */
    private static final int NOT_FOUND = 404;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int UNSATISFIABLE_REQUEST = 504;
    private static final int SERVICE_TEMPORARILY_UNAVAILABLE = 503;

    private final BasePresenter mPresenter;
    private final BaseView mvpView;

    /** 是否显示加载框 */
    private final boolean isShowLoading;

    private String mLoadingDialogMessage = "加载中...";

    public BaseObserverTemp(BasePresenter presenter, BaseView mvpView) {
        this(presenter, mvpView, true, "");
    }

    public BaseObserverTemp(BasePresenter presenter, BaseView mvpView, boolean isShowLoading,
                            String loadingDialogMessage) {
        mPresenter = presenter;
        this.mvpView = mvpView;
        this.isShowLoading = isShowLoading;
        mLoadingDialogMessage = loadingDialogMessage;
    }


    protected abstract void onSuccess(T data);

    protected void onError(String msg) {
        mvpView.onError(0, msg);
    }



    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mPresenter.addDisposable(d);
        onStart(mLoadingDialogMessage);
    }

    @Override
    public void onNext(@NonNull Object o) {
        onSuccess((T) o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(parseError(e));
        onEnd();
    }

    @Override
    public void onComplete() {
        onEnd();
    }


    /**
     * 开始,显示加载框,不需要的话重写
     */
    protected void onStart(String message) {
        if (isShowLoading) {
            mvpView.showLoading(message);
        }
    }


    /**
     * 结束,隐藏加载框,不需要的话重写
     */
    protected void onEnd() {
        if (isShowLoading) {
            mvpView.hideLoading();
        }
    }

    /**
     * 解析异常
     *
     * @param e 异常
     * @return
     */
    public static String parseError(Throwable e) {
        String msg = "";

        if (e instanceof HttpException) {                               /* 网络错误 */
            int code = ((HttpException) e).code();
            switch (code) {
                case NOT_FOUND:                       /* 404 */
                    msg = ResUtil.getString(R.string.message_not_found);
                    break;

                case INTERNAL_SERVER_ERROR:           /* 500 */
                    msg = ResUtil.getString(R.string.message_internal_server_error);
                    break;

                case UNSATISFIABLE_REQUEST:           /* 504 */
                    msg = ResUtil.getString(R.string.message_unsatisfiable_request);
                    break;

                case SERVICE_TEMPORARILY_UNAVAILABLE: /* 503 */
                    msg = ResUtil.getString(R.string.message_server_error);

                default:
                    break;
            }

        } else if (e instanceof UnknownHostException) {                 /* 没有网络 */
            // MyApp.setHasNetWork(false);
            msg = ResUtil.getString(R.string.message_unknownhost);

        } else if (e instanceof SocketTimeoutException) {               /* 连接超时 */
            // MyApp.setHasNetWork(false);
            msg = ResUtil.getString(R.string.message_sockettimeout);

        } else if (e instanceof ConnectException) {                     /* 连接异常 */
            // MyApp.setHasNetWork(false);
            msg = ResUtil.getString(R.string.message_connectexception);

        } else if (e instanceof ParseException) {                       /* 数据解析失败 */
            msg = ResUtil.getString(R.string.message_data_parsing_filed);

        } else if (e instanceof JsonSyntaxException) {                  /* 数据解析失败 */
            msg = ResUtil.getString(R.string.message_data_parsing_filed);

        } else if (e instanceof IOException) {                          /* 数据读取失败 -- IO异常 */
            msg = ResUtil.getString(R.string.message_data_read_filed);

        } else {                                                        /* 未知错误 */
            msg = ResUtil.getString(R.string.message_unknown_mistake);
        }

        if (e != null) {
            e.printStackTrace();
            //Log.e("BaseObserver", "onError: " + e.getCause() + "  " + e.getMessage() + "  " + e.toString());
            L.i("BaseObserver<T> -- parseError:" , e);
        }

        return msg;
    }




}
