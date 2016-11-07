package sdk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sdk.utils.ActionHelper;
import sdk.utils.OnActionListener;

public class OkHttpAction implements Runnable {

    /**
     * 成功获得网络数据 ID
     */
    private final static int MSG_TYPE_SUCCESS = 0x10;
    /**
     * 获取网络数据失败 ID
     */
    private final static int MSG_TYPE_FILED = 0x20;
    /**
     * 获取网络数据异常 ID
     */
    private final static int MSG_TYPE_EXCEPTION = 0x30;
    /**
     * 请求成功返回参数名
     */
    private final static String MSG_KEY_PARAM = "param";
    /**
     * 请求异常返回参数名
     */
    private final static String MSG_KEY_EXCEPTION = "exception";
    /**
     * post请求参数名
     */
    //    private final static String POST_PARAM_KEY = "param";

    private int id;

    private String url;
    private int requestMethod;

    private OnActionListener listener;

    private OkHttpParam param;

    private Handler handler;

    @SuppressLint("HandlerLeak")
    public OkHttpAction(int requestMethod, int actionId, String url) {// baseUrlm没有参数的地址
        this.url = url;
        this.id = actionId;
        this.requestMethod = requestMethod;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == id) {
                    switch (msg.arg1) {
                        case MSG_TYPE_SUCCESS:
                            // 成功获得消息
                            if (listener != null) {
                                listener.onActionSuccess(id, msg
                                        .getData().getString(MSG_KEY_PARAM));
                            }
                            break;
                        case MSG_TYPE_FILED:
                            if (listener != null) {
                                listener.onActionFailed(id, msg.arg2);
                            }
                            break;
                        case MSG_TYPE_EXCEPTION:
                            if (listener != null) {
                                listener.onActionException(id, msg.getData()
                                        .getString(MSG_KEY_EXCEPTION));
                            }
                            break;
                    }
                }
                super.handleMessage(msg);
            }
        };
    }

    public void setParam(OkHttpParam param) {
        this.param = param;
    }

    public void setOnActionListener(OnActionListener l) {
        listener = l;
    }

    /**
     * 开启线程
     */
    public void startAction() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = null;
            switch (requestMethod) {
                case ActionHelper.GET:
                    //GET request
                    if (param != null) {
                        request = new Request.Builder().url(url + param.getRequestParam()).build();
                    } else {
                        request = new Request.Builder().url(url).build();
                    }
                    Log.d("Debug GET-->", url + param != null ? url + param.getRequestParam() : url);

                    break;
                case ActionHelper.POST:
                    //POST request
                    if (param != null) {
                        FormBody.Builder builder = new FormBody.Builder();//构建参数
                        Map<String, String> stringParams = param.getParams();
                        if (stringParams != null && stringParams.size() > 0) {
                            //在字符串集合中有数据
                            Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<String, String> next = iterator.next();
                                builder.add(next.getKey(), next.getValue());
                            }
                        }
                        request = new Request.Builder().url(url).post(builder.build()).build();
                        Log.d("Debug POST-->", url + param != null ? url + "&" + param.postRequestParam() : url);
                    } else {
                        //GET请求,
                        request = new Request.Builder().url(url).build();
                        Log.d("Debug POST-->GET-->", url);
                    }

                    break;
                case ActionHelper.PUT:
                    if (param != null) {
                        FormBody.Builder builder = new FormBody.Builder();//构建参数
                        Map<String, String> stringParams = param.getParams();
                        if (stringParams != null && stringParams.size() > 0) {
                            //在字符串集合中有数据
                            Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<String, String> next = iterator.next();
                                builder.add(next.getKey(), next.getValue());
                            }
                        }
                        request = new Request.Builder().url(url).put(builder.build()).build();
                        Log.d("Debug Put-->", url + param != null ? url + "&" + param.putRequestParam() : url);
                    } else {
                        //GET请求,
                        request = new Request.Builder().url(url).build();
                        Log.d("Debug PUT-->GET-->", url);
                    }

                    break;
                case ActionHelper.DEL:
                    //Del request
                    if (param != null) {
                        //                    FormBody.Builder builder = new FormBody.Builder();//构建参数
                        //                    Map<String, String> stringParams = param.getParams();
                        //                    if (stringParams != null && stringParams.size() > 0) {
                        //                        //在字符串集合中有数据
                        //                        Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet().iterator();
                        //                        while (iterator.hasNext()) {
                        //                            Map.Entry<String, String> next = iterator.next();
                        //                            builder.add(next.getKey(), next.getValue());
                        //                        }
                        //                    }
                        request = new Request.Builder().url(url + param.delRequestParam()).delete().build();
                        Log.d("Debug Del-->", url + param != null ? url + param.delRequestParam() : url);
                    } else {
                        //GET请求,
                        request = new Request.Builder().url(url).build();
                        Log.d("Debug Del-->GET-->", url);
                    }

                    break;
                case ActionHelper.PATCH:
                    //Del request
                    if (param != null) {
                        FormBody.Builder builder = new FormBody.Builder();//构建参数
                        Map<String, String> stringParams = param.getParams();
                        if (stringParams != null && stringParams.size() > 0) {
                            //在字符串集合中有数据
                            Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry<String, String> next = iterator.next();
                                builder.add(next.getKey(), next.getValue());
                            }
                        }
                        request = new Request.Builder().url(url).patch(builder.build()).build();
                        Log.d("Debug PATCH-->", url + param != null ? url + param.patchRequestParam() : url);
                    } else {
                        //GET请求,
                        request = new Request.Builder().url(url).build();
                        Log.d("Debug PATCH-->GET-->", url);
                    }

                    break;

            }


            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                //请求成功
                Message msg = Message.obtain();
                msg.what = id;
                msg.arg1 = MSG_TYPE_SUCCESS;
                Bundle data = new Bundle();
                String string = response.body().string();
                //                Log.d("Debug", "Result:" + string);
                data.putString(MSG_KEY_PARAM, string);
                msg.setData(data);
                handler.sendMessage(msg);
            } else {
                Message msg = Message.obtain();
                msg.what = id;
                msg.arg1 = MSG_TYPE_FILED;
                msg.arg2 = response.code();
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = Message.obtain();
            msg.what = id;
            msg.arg1 = MSG_TYPE_EXCEPTION;
            Bundle data = new Bundle();
            data.putString(MSG_KEY_EXCEPTION, e.getMessage());
            msg.setData(data);
            handler.sendMessage(msg);
        }


    }
}
