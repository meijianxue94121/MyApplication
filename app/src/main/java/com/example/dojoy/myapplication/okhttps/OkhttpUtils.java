package com.example.dojoy.myapplication.okhttps;


import com.example.dojoy.myapplication.okhttps.base.OkhttpBase;
import com.example.dojoy.myapplication.okhttps.core.OkhttpCore;
import com.example.dojoy.myapplication.okhttps.listener.OnActionListener;
import com.example.dojoy.myapplication.okhttps.params.OkhttpParam;

/**
 * Created by Administrator on 2016/8/16.
 * okhttp 请求工具类
 */
public class OkhttpUtils {

    public static final int GET = 0;
    public static final int POST = 1;
    public static final int FILE = 2;

    /**
     * okhttp的请求工具类。
     *
     * @param actionId      标识，区分不同的请求
     * @param requestMethod 请求方式，0：GET；1：POST；2：上传文件（上传文件时，要在OkhttpParam中传入文件的信息）
     * @param servlet       服务器请求路径
     * @param okhttpParam   请求参数
     * @param listener      回调监听
     */
    public static void sendRequest(int actionId, int requestMethod, String servlet, OkhttpParam okhttpParam, OnActionListener listener) {
        OkhttpCore okhttpCore;
        if (servlet.startsWith("http")) {
            okhttpCore = new OkhttpCore(actionId, servlet);//如果地址是以http开头，说明是完整的地址，也许是第三方的地址
        } else {
            okhttpCore = new OkhttpCore(actionId, OkhttpBase.BASE_URL + servlet);
        }
        okhttpCore.setParam(okhttpParam);
        okhttpCore.setRequestMethod(requestMethod);
        okhttpCore.setOnActionListener(listener);
        okhttpCore.startAction();


    }
}
