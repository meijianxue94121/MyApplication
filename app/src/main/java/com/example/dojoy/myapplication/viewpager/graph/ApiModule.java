package com.example.dojoy.myapplication.viewpager.graph;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by leo on 16/3/20.
 * Retrofit2 配置
 */
public class ApiModule {
    private static String ENDPOINT = "http://api.douban.com/";
    private static Retrofit.Builder builder;
    private static Map<Class, Object> apiModule;
    private static OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
        apiModule = new ConcurrentHashMap<>();
        builder = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create()); // 添加Gson转换器
    }

    /**
     * 创建api 单例
     */
    public static <T> T of(Class<T> sClass) {
        if (apiModule.containsKey(sClass)) {
            return (T) apiModule.get(sClass);
        }
        Retrofit retrofit = builder.client(httpClient).build();
        T t = retrofit.create(sClass);
        apiModule.put(sClass, t);
        return t;
    }
}
