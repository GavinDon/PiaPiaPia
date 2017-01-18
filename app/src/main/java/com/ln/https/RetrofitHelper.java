package com.ln.https;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linan   on 2016/10/8.
 */
public class RetrofitHelper {
    public static final String BASEURL= "http://www.jingweibus.com/";

    private  Retrofit mRetrofit;

    private RetrofitHelper() {
        init();


    }

    private void init() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.readTimeout(20, TimeUnit.SECONDS);
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logger);
        mRetrofit = new Retrofit.Builder().baseUrl(BASEURL).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    private static RetrofitHelper INSTANCE;

    public static RetrofitHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitHelper.class) {
                INSTANCE = new RetrofitHelper();
            }
        }
        return INSTANCE;
    }

    /**
     * 创建接口对象
     *
     * @param <T>
     */
    public <T> T creat(Class<T> service) {
        return mRetrofit.create(service);
    }
}
