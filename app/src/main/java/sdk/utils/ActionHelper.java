package sdk.utils;


import android.text.TextUtils;

import java.util.HashMap;

import sdk.base.ActionBase;

public class ActionHelper {
    public static final int GET = 0;
    public static final int POST = 1;
    public static final int PUT = 2;
    public static final int DEL = 3;
    public static final int PATCH = 4;

    /**
     * @param requestMode 0代表Get请求，1代表post请求
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param paramz      url参数
     * @param listener
     */
    public static void request(int requestMode, int actionId, String servlet,
                               HashMap<String, String> paramz, OnActionListener listener) {

        ActionBase actionBase = null;
        actionBase = new ActionOkHttp(requestMode, actionId, servlet);
        //        switch (requestMode) {
        //            case GET:
        //                actionBase = new ActionGet(actionId, servlet);
        //                break;
        //            case POST:
        //                actionBase = new ActionPost(actionId, servlet);
        //                break;
        //            case PUT:
        //                actionBase = new ActionPut(actionId, servlet);
        //                break;
        //            case DEL:
        //                actionBase = new ActionOkHttp(actionId, servlet);
        //                break;
        //
        //        }
        //set parameters
        actionBase.setParam(paramz);
        //set listener
        actionBase.setOnActionListener(listener);
        //start action
        actionBase.startAction();
    }

    /**
     * @param requestMode 0代表Get请求，1代表post请求
     * @param actionId
     * @param servlet     请求的Servlet名称
     * @param paramz      url参数
     * @param listener
     */
    public static void request(int requestMode, int actionId, String top, String servlet,
                               HashMap<String, String> paramz, OnActionListener listener) {

        ActionBase actionBase = null;
        if (top == null || TextUtils.isEmpty(top)) {
            actionBase = new ActionOkHttp(requestMode, actionId, servlet);
            //            switch (requestMode) {
            //                case GET:
            //                    actionBase = new ActionGet(actionId, servlet);
            //                    break;
            //                case POST:
            //                    actionBase = new ActionPost(actionId, servlet);
            //                    break;
            //                case PUT:
            //                    actionBase = new ActionPut(actionId, servlet);
            //                    break;
            //                case DEL:
            //                    actionBase = new ActionOkHttp(actionId, servlet);
            //                    break;
            //            }
        } else {
            actionBase = new ActionOkHttp(requestMode, actionId, top, servlet);

            //            switch (requestMode) {
            //                case GET:
            //                    actionBase = new ActionGet(actionId, top, servlet);
            //                    break;
            //                case POST:
            //                    actionBase = new ActionPost(actionId, top, servlet);
            //                    break;
            //                case PUT:
            //                    actionBase = new ActionPut(actionId, top, servlet);
            //                    break;
            //                case DEL:
            //                    actionBase = new ActionOkHttp(actionId,top, servlet);
            //                    break;
            //            }
        }
        //set parameters
        actionBase.setParam(paramz);
        //set listener
        actionBase.setOnActionListener(listener);
        //start action
        actionBase.startAction();
    }
}
