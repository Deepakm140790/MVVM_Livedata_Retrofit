package com.app.testapp.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    public static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/";
    private static int REQUEST_TIMEOUT = 10;
    private static Retrofit mRetrofit = null;
    private static OkHttpClient okHttpClient;

    /**
     * Build and return the Retrofit Object.
     * @return
     */
    public static Retrofit getClient() {
        if (okHttpClient == null) {
            initOkHttp();
        }

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * Helps to customize the httpClient.
     */
    private static void initOkHttp() {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient = httpClient.build();
    }
}