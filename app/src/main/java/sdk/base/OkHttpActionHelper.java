package sdk.base;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class OkHttpActionHelper {
    //Get默认 &参数=value,方式
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int PUT = 3;
    public static final int DEL = 4;
    public static final int PATCH = 5;

    /**
     * @param requestMode 0代表Get请求，1代表post请求
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param mapParams   url参数
     * @param listener
     */
    public static void requestWithoutUrlParams(int requestMode, int actionId, String servlet,
                                               HashMap<String, String> mapParams, OnActionListener listener) {

        request(requestMode, actionId, null, servlet, null, mapParams, listener);
    }

    public static void requestWithoutParams(int requestMode, int actionId, String servlet,
                                            ArrayList<String> urls, OnActionListener listener) {

        request(requestMode, actionId, null, servlet, urls, null, listener);
    }

    /**
     * @param requestMode
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param urls        url参数
     * @param listener
     */
    public static void requestWithoutParams(int requestMode, int actionId, String top, String servlet,
                                            ArrayList<String> urls, OnActionListener listener) {
        request(requestMode, actionId, top, servlet, urls, null, listener);

    }

    public static void requestWithoutUrlParams(int requestMode, int actionId, String top, String servlet,
                                               HashMap<String, String> mapParams, OnActionListener listener) {
        request(requestMode, actionId, top, servlet, null, mapParams, listener);

    }

    public static void request(int requestMode, int actionId, String top, String servlet, ArrayList<String> urls,
                               HashMap<String, String> mapParams, OnActionListener listener) {

        OkHttpAction actionBase = null;
        if (top == null || TextUtils.isEmpty(top)) {
            actionBase = new OkHttpAction (requestMode, actionId, OkHttpActionBase.BASE_URL, servlet);
        } else {
            actionBase = new OkHttpAction (requestMode, actionId, top, servlet);
        }
        actionBase.setUrlParams(urls);
        actionBase.setParam(mapParams);
        actionBase.setOnActionListener(listener);
        actionBase.startAction();
    }
    public  void get(int actionId, String servlet,
                     ArrayList<String> mapParams, OnActionListener listener) {
        request(GET,actionId,getDomainAddress(),servlet,mapParams,null,listener);
    }
    public  void get(int actionId, String servlet,
                     HashMap<String, String> mapParams, OnActionListener listener) {
        request(GET,actionId,getDomainAddress(),servlet,null,mapParams,listener);
    }
    public  void get(int actionId, String servlet, ArrayList<String> urls,
                     HashMap<String, String> mapParams, OnActionListener listener) {
        request(GET,actionId,getDomainAddress(),servlet,urls,mapParams,listener);
    }
    public  void post(int actionId, String servlet,
                      HashMap<String, String> mapParams, OnActionListener listener) {
        request(POST,actionId,getDomainAddress(),servlet,null,mapParams,listener);
    }
    public void put(int actionId, String servlet,
                    HashMap<String, String> mapParams, OnActionListener listener){
        request(PUT,actionId,getDomainAddress(),servlet,null,mapParams,listener);
    }
    public void put(int actionId, String servlet, ArrayList<String> urls,
                    HashMap<String, String> mapParams, OnActionListener listener){
        request(PUT,actionId,getDomainAddress(),servlet,urls,mapParams,listener);
    }
    public void delete(int actionId, String servlet, ArrayList<String> urls,
                       HashMap<String, String> mapParams, OnActionListener listener){
        request(DEL,actionId,getDomainAddress(),servlet,urls,mapParams,listener);
    }


    public abstract String getDomainAddress();

}
