package com.bawei.demo0102;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: Demo0102
 * PackageName: com.bawei.demo0102
 * ClassName:   RetrofitUtils
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/3_8:39
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private RetrofitUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public <T> T getData(Class<T> tClass) {
        return retrofit.create(tClass);
    }


}
