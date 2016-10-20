package com.example.dojoy.myapplication.okhttps;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dojoy on 2016/10/14.
 */
public class OkHttpsHelper {
    public static final String urlTop = "http://121.41.10.2:20000/app/";

    public interface ResponCallBack {
        void onOk(int actionId, String s);

        void onError(int actionId, String s);
    }

    @Deprecated
    public static void request(int requestMode, int actionId, String servlet,
                               HashMap<String, String> paramz, ResponCallBack listener) {
        request(requestMode, actionId, null, servlet, paramz, listener);


    }

    /**
     * @param requestMode 0代表Get请求，1代表post请求
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param params      url参数
     * @param listener
     */
    @Deprecated
    public static void request(int requestMode, final int actionId, String top, String servlet,
                               HashMap<String, String> params, final ResponCallBack listener) {
        OkHttpRequestBuilder builder = null;
        if (requestMode == 0) {
            builder = OkHttpUtils.get();
        } else if (requestMode == 1) {
            builder = OkHttpUtils.post();
        }
        if (top == null || top.isEmpty())
            builder.url(urlTop + servlet);
        else
            builder.url(top + servlet);

        if (params != null && params.size() > 0) {
            builder.params(params);
        }

        builder.build()
                .connTimeOut(5000).writeTimeOut(5000).readTimeOut(5000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        if (response != null) {
                            Log.e("tag", "URL:" + response.request().urlString());
                            if (response.code() == 200) {
                                if (listener != null) {
                                    listener.onOk(actionId, response.body().string().toString());
                                }
                            } else {
                                if (listener != null) {
                                    listener.onError(actionId, response.message().toString());
                                }
                            }
                        }

                        return null;
                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Object response) {

                    }
                });


    }

    public static void requestPost(final int actionId, String top, String servlet,
                                   HashMap<String, String> params, final ResponCallBack listener) {
        PostFormBuilder builder = OkHttpUtils.post();
        if (top == null || top.isEmpty())
            builder.url(urlTop + servlet);
        else
            builder.url(top + servlet);

        if (params != null && params.size() > 0) {
            builder.params(params);
        }
        builder.build()
                .connTimeOut(10000).writeTimeOut(10000).readTimeOut(10000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        if (response != null) {
                            Log.e("tag", "URL:" + response.request().urlString());
                            if (response.code() == 200) {
                                if (listener != null) {
                                    listener.onOk(actionId, response.body().string().toString());
                                }
                            } else {
                                if (listener != null) {
                                    listener.onError(actionId, response.message().toString());
                                }
                            }
                        }

                        return null;
                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Object response) {

                    }
                });


    }

    /**
     * @param actionId
     * @param servlet
     * @param params
     * @param listener
     */
    public static void requestPost(int actionId, String servlet,
                                   HashMap<String, String> params, final ResponCallBack listener) {
        requestPost(actionId, null, servlet, params, listener);

    }

    /**
     * @param actionId
     * @param servlet
     * @param paramz
     * @param listener
     */
    public static void requestGet(int actionId, String servlet,
                                  HashMap<String, String> paramz, ResponCallBack listener) {
        requestGet(actionId, null, servlet, paramz, listener);


    }

    /**
     * @param actionId
     * @param servlet  请求的Servlet名称
     * @param params   url参数
     * @param listener
     */
    public static void requestGet(final int actionId, String top, String servlet,
                                  HashMap<String, String> params, final ResponCallBack listener) {
        GetBuilder builder = OkHttpUtils.get();
        if (top == null || top.isEmpty())
            builder.url(urlTop + servlet);
        else
            builder.url(top + servlet);

        if (params != null && params.size() > 0) {
            builder.params(params);
        }
        builder.build()
                .connTimeOut(5000).readTimeOut(5000).writeTimeOut(5000)
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws IOException {
                        if (response != null) {
                            Log.e("tag", "URL:" + response.request().urlString());
                            if (response.code() == 200) {
                                if (listener != null) {
                                    listener.onOk(actionId, response.body().string());
                                }
                            } else {
                                if (listener != null) {
                                    listener.onError(actionId, response.message().toString());
                                }
                            }
                        }

                        return null;
                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Object response) {

                    }
                });


    }

    public static String getRequestParam(HashMap<String, String> params) {
        StringBuffer request = new StringBuffer();
        if (params != null && params.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                request.append(next.getKey());
                request.append("=");
                request.append(next.getValue());
                request.append("&");
            }
            request.delete(request.length() - 1, request.length());
            return request.toString();
        }
        return "";
    }
}
