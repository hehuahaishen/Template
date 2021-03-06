package com.example.shen.template.net;

import com.example.common.util.L;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static okhttp3.internal.Util.UTF_8;


public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "shen";

    /**
     * 打印请求信息
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        printRequestMessage(request);
        Response response = chain.proceed(request);
        printResponseMessage(response);
        return response;
    }

    /**
     * 打印请求消息
     * @param request 请求的对象
     */
    private void printRequestMessage(Request request) {
        if (request == null) {
            return;
        }
        L.i(TAG, "=");
        L.i(TAG, "==================================请求信息 start ===================================");
        L.i(TAG, "Url : " + request.url().url().toString());
        L.i(TAG, "Method: " + request.method());
        L.i(TAG, "Heads : " + request.headers());
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            L.i(TAG, "=================请求信息  end (没有请求体 requestBody == null)  ====================");
            return;
        }
        try {
            Buffer bufferedSink = new Buffer();
            requestBody.writeTo(bufferedSink);
            MediaType mediaType = requestBody.contentType();

        if(mediaType != null) {
            Charset charset = mediaType.charset();
            charset = charset == null ? Charset.forName("utf-8") : charset;
            L.i(TAG, "Params: " + bufferedSink.readString(charset));
        }
    } catch (IOException e) {
        e.printStackTrace();
        L.e("intercept -- printRequestMessage -- IOException e:" , e);
    } catch (NullPointerException e){
        L.e("intercept -- printRequestMessage -- NullPointerException e:" , e);
    }finally {
        L.i(TAG, "");
        L.i(TAG, "==================================请求信息  end  ===================================");
    }

    }
    /**
     *
     * 打印返回消息
     * @param response 返回的对象
     */
    private void printResponseMessage(Response response) {
        if (response == null || !response.isSuccessful()) {
            return;
        }
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
            L.e("intercept -- printResponseMessage -- IOException e:" , e);
        }
        Buffer buffer = source.buffer();
        Charset charset = UTF_8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset();
        }
        if (charset != null && contentLength != 0) {
            String result = buffer.clone().readString(charset);
            L.i(TAG, "=");
            L.i(TAG, "==================================返回消息 start ===================================");
            L.printJsonAll(result);
            L.i(TAG, "==================================返回消息  end  ===================================");
        }
    }
}
