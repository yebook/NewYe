package com.kermitye.yesdk.helper;

import com.kermitye.yesdk.helper.okhttp.CacheInterceptor;
import com.kermitye.yesdk.helper.okhttp.HttpCache;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author:kermitye
 * Time:2018/4/12 下午5:06
 * Description:
 */
public class RetrofitCreateHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //打印日志
            .addInterceptor(interceptor)
            //设置Cache拦截器
//            .addNetworkInterceptor(cacheInterceptor)
//            .addInterceptor(cacheInterceptor)
//            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();

    public static <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }




}
