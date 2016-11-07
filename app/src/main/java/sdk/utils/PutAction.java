//package sdk.utils;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//
//import java.util.Iterator;
//import java.util.Map;
//
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class PutAction implements Runnable {
//
//    /**
//     * 成功获得网络数据 ID
//     */
//    private final static int MSG_TYPE_SUCCESS = 0x10;
//    /**
//     * 获取网络数据失败 ID
//     */
//    private final static int MSG_TYPE_FILED = 0x20;
//    /**
//     * 获取网络数据异常 ID
//     */
//    private final static int MSG_TYPE_EXCEPTION = 0x30;
//    /**
//     * 请求成功返回参数名
//     */
//    private final static String MSG_KEY_PARAM = "param";
//    /**
//     * 请求异常返回参数名
//     */
//    private final static String MSG_KEY_EXCEPTION = "exception";
//    /**
//     * post请求参数名
//     */
//    //    private final static String POST_PARAM_KEY = "param";
//
//    private int id;
//
//    private String url;
//
//    private OnActionListener listener;
//
//    private PutParam param;
//
//    private Handler handler;
//
//    @SuppressLint("HandlerLeak")
//    public PutAction(int actionId, String url) {// baseUrlm没有参数的地址
//        this.url = url;
//        this.id = actionId;
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//                if (msg.what == id) {
//                    switch (msg.arg1) {
//                        case MSG_TYPE_SUCCESS:
//                            // 成功获得消息
//                            if (listener != null) {
//                                listener.onActionSuccess(id, msg
//                                        .getData().getString(MSG_KEY_PARAM));
//                            }
//                            break;
//                        case MSG_TYPE_FILED:
//                            if (listener != null) {
//                                listener.onActionFailed(id, msg.arg2);
//                            }
//                            break;
//                        case MSG_TYPE_EXCEPTION:
//                            if (listener != null) {
//                                listener.onActionException(id, msg.getData()
//                                        .getString(MSG_KEY_EXCEPTION));
//                            }
//                            break;
//                    }
//                }
//                super.handleMessage(msg);
//            }
//        };
//    }
//
//    public void setParam(PutParam param) {
//        this.param = param;
//    }
//
//    public void setOnActionListener(OnActionListener l) {
//        listener = l;
//    }
//
//    /**
//     * 开启线程
//     */
//    public void startAction() {
//        new Thread(this).start();
//    }
//
//    @Override
//    public void run() {
//        try {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            Request request = null;
//            {
//                if (param != null) {
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
//                    request = new Request.Builder().url(url).put(builder.build()).build();
//                    Log.d("Debug Put-->", url + param != null ? url + "&" + param.getRequestParam() : url);
//                } else {
//                    //GET请求,
//                    request = new Request.Builder().url(url).build();
//                    Log.d("Debug GET1-->", url);
//                }
//            }
//
//            Response response = okHttpClient.newCall(request).execute();
//            if (response.isSuccessful()) {
//                //请求成功
//                Message msg = Message.obtain();
//                msg.what = id;
//                msg.arg1 = MSG_TYPE_SUCCESS;
//                Bundle data = new Bundle();
//                String string = response.body().string();
//                Log.d("Debug", "Result:" + string);
//                data.putString(MSG_KEY_PARAM, string);
//                msg.setData(data);
//                handler.sendMessage(msg);
//            } else {
//                Message msg = Message.obtain();
//                msg.what = id;
//                msg.arg1 = MSG_TYPE_FILED;
//                msg.arg2 = response.code();
//                handler.sendMessage(msg);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Message msg = Message.obtain();
//            msg.what = id;
//            msg.arg1 = MSG_TYPE_EXCEPTION;
//            Bundle data = new Bundle();
//            data.putString(MSG_KEY_EXCEPTION, e.getMessage());
//            msg.setData(data);
//            handler.sendMessage(msg);
//        }
//
//
//        //        URL postUrl = null;
//        //        HttpURLConnection connection = null;
//        //        try {
//        //            postUrl = new URL(url);
//        //            connection = (HttpURLConnection) postUrl
//        //                    .openConnection();
//        //            connection.setDoOutput(true);
//        //            connection.setDoInput(true);
//        //            connection.setRequestMethod("POST");
//        //            connection.setUseCaches(false);
//        //            connection.setInstanceFollowRedirects(true);
//        //            connection.setRequestProperty("Content-Type",
//        //                    "application/x-www-form-urlencoded");
//        //            connection.setRequestProperty("Accept-Charset", "utf-8");
//        //            connection.setRequestProperty("contentType", "utf-8");
//        //            connection.setChunkedStreamingMode(5);
//        //            connection.connect();
//        //            DataOutputStream out = new DataOutputStream(connection
//        //                    .getOutputStream());
//        //            String content = param.getRequestParam();
//        //            if (ActionBase.isDebug())
//        //                Log.d("Debug:", "Post请求参数：" + url + "&" + content);
//        //            out.writeBytes(content);
//        //            out.flush();
//        //            out.close();
//        //            // 到此时服务器已经收到了完整的http request了，而在readContentFromPost()函数里，要等到下一句服务器才能收到http请求。
//        //            BufferedReader reader = new BufferedReader(new InputStreamReader(
//        //                    connection.getInputStream()));
//        //            out.flush();
//        //            out.close(); // flush and close
//        //            String line;
//        //            String result = "";
//        //            while ((line = reader.readLine()) != null) {
//        //                result += line;
//        //            }
//        //            reader.close();
//        //            connection.disconnect();
//        //            //            System.out.println("PostAction请求结果：" + result + "请求码" +
//        //            //                    "" + connection.getResponseCode());
//        //            //            Log.d("Debug:", "Post请求结果：" + result);
//        //
//        //            Message msg = Message.obtain();
//        //            msg.what = id;
//        //            msg.arg1 = MSG_TYPE_SUCCESS;
//        //            Bundle data = new Bundle();
//        //            data.putString(MSG_KEY_PARAM, result);
//        //            msg.setData(data);
//        //            handler.sendMessage(msg);
//        //            //			} else {
//        //            //				// http状态码为失败的情况
//        //            //				Message msg = Message.obtain();
//        //            //				msg.what = id;
//        //            //				msg.arg1 = MSG_TYPE_FILED;
//        //            //				msg.arg2 = httpCode;
//        //            //				handler.sendMessage(msg);
//        //            //			}
//        //        } catch (Exception e) {
//        //            if (connection != null) {
//        //                try {
//        //                    System.out.println(connection.getResponseCode() + "Post请求参数：");
//        //                } catch (IOException e1) {
//        //                    e1.printStackTrace();
//        //                }
//        //            }
//        //            e.printStackTrace();
//        //            // 请求过程中发生异常的情况
//        //            Message msg = Message.obtain();
//        //            msg.what = id;
//        //            msg.arg1 = MSG_TYPE_EXCEPTION;
//        //            Bundle data = new Bundle();
//        //            data.putString(MSG_KEY_EXCEPTION, e.getMessage());
//        //            msg.setData(data);
//        //            handler.sendMessage(msg);
//        //        } finally {
//        //            if (connection != null) {
//        //                connection.disconnect();
//        //            }
//        //        }
//    }
//}
