package com.example.shen.template.net;

import com.example.app.BuildConfig;
import com.example.common.app.BaseApp;
import com.example.common.util.AppUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 */
public class ApiEngine {
    private static ApiEngine apiEngine;
    private Retrofit retrofit;


    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public Api getApiService() {
        return retrofit.create(Api.class);
    }


    private ApiEngine() {
        /* 日志拦截器 */
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent",
                        getAgent(true)).build();

                Response response = chain.proceed(build);

                return response;
            }
        };

        /* 缓存 */
        CookieJar cookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            // Tip：這裡key必須是String
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        //缓存
        //        int size = 1024 * 1024 * 100;
        //        File cacheFile = new File(App.mContext.getCacheDir(), "OkHttpCache");
        //        Cache cache = new Cache(cacheFile, size);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                //.addInterceptor(interceptor);
                .addInterceptor(new LoggerInterceptor());
                // .cache(cache)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Url.Url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }


    public String getAgent(boolean needHost) {
        String res = "&okhttp&&android&" + android.os.Build.VERSION.RELEASE + "&V" +
                AppUtils.getVersionName(BaseApp.getContext());
        if (needHost) {
            res = "laigewan.com" + res;
        }
        return res;
    }
}
