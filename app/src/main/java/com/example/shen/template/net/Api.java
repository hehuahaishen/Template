package com.example.shen.template.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 */
public interface Api {
    /**
     * 通用有参数
     *
     * @param url
     * @param data
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> universal(@retrofit2.http.Url String url, @FieldMap Map<String, String> data);

    /**
     * 通用无参数
     *
     * @param url
     * @return
     */
    @POST
    Observable<ResponseBody> universal(@retrofit2.http.Url String url);

    /**
     * 获取图片 或是 视频
     * @param path
     * @return
     */
    @GET("{path}")
    Observable<ResponseBody> getAdvImageOrVideo(@Path("path") String path);

    /**
     * 版本更新
     * @return
     */
    @POST(Url.versionsUpdate)
    Observable<ResponseBody> versionUpdated();


//    /**
//     * 获取餐具的信息
//     *
//     * @param short_link 餐具中的二维码中的加密字符串
//     * @param re_number  回收柜编号
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(Url.goodsRecovery)
//    Observable<BaseWebBean<TablewareBean>> getTableware(@Field("short_link") String short_link,
//                                                        @Field("re_number") String re_number);


}
