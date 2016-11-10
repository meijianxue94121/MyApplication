package sdk.utils;


import android.text.TextUtils;

import java.util.HashMap;

public class OkHttpActionHelper {
    public static final int GET = 1;
    public static final int GET_STYLE1 = 11;
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
    public static void request(int requestMode, int actionId, String servlet,
                               HashMap<String, String> mapParams, OnActionListener listener) {

        request(requestMode, actionId, null, servlet, mapParams, listener);
        //        OkHttpActionBase actionBase = null;
        //        actionBase = new OkHttpAction(requestMode, actionId, servlet);
        //        actionBase.setParam(mapParams);
        //        //set listener
        //        actionBase.setOnActionListener(listener);
        //        //start action
        //        actionBase.startAction();
    }

    /**
     * @param requestMode 0代表Get请求，1代表post请求
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param mapParams   url参数
     * @param listener
     */
    public static void request(int requestMode, int actionId, String top, String servlet,
                               HashMap<String, String> mapParams, OnActionListener listener) {

        OkHttpActionBase actionBase = null;
        if (top == null || TextUtils.isEmpty(top)) {
            actionBase = new OkHttpAction(requestMode, actionId, servlet);
        } else {
            actionBase = new OkHttpAction(requestMode, actionId, top, servlet);
        }
        actionBase.setParam(mapParams);
        //set listener
        actionBase.setOnActionListener(listener);
        //start action
        actionBase.startAction();
    }
}
