//package sdk.utils;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public final class GetAction implements Runnable {
//    /**
//     * 成功获得网络数据
//     */
//    private final static int MSG_TYPE_SUCCESS = 0x10;
//    /**
//     * 获取网络数据失败
//     */
//    private final static int MSG_TYPE_FILED = 0x20;
//    /**
//     * 获取网络数据异常
//     */
//    private final static int MSG_TYPE_EXCEPTION = 0x30;
//
//    private final static String MSG_KEY_PARAM = "param";
//    private final static String MSG_KEY_EXCEPTION = "exception";
//
//    private int id;
//
//    private String url;
//
//    private OnActionListener listener;
//
//    private GetParam param;
//
//    private Handler handler;
//
//    int httpCode;
//
//    @SuppressLint("HandlerLeak")
//    public GetAction(int actionId, String baseUrl) {// baseUrlm没有参数的地址
//        this.url = baseUrl;
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
//    public void setParam(GetParam param) {
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
//                //GET request
//                if (param != null) {
//                    request = new Request.Builder().url(url + param.getRequestParam()).get().build();
//                } else {
//                    request = new Request.Builder().url(url).get().build();
//                }
//                Log.d("Debug GET2-->", url + param != null ? url + param.getRequestParam() : url);
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
//                //                Log.d("Debug", "Result:" + string);
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
//        //        try {
//        //            URL u = new URL(url + param.toString());
//        //            if (ActionBase.isDebug())
//        //                Log.d("Debug:", "Get请求参数：" + url + param.toString());
//        //            HttpURLConnection connection = (HttpURLConnection) u
//        //                    .openConnection();
//        //            connection.setRequestMethod("GET");
//        //            connection.setRequestProperty("Accept-Charset", "utf-8");
//        //            connection.setRequestProperty("contentType", "utf-8");
//        //            connection.setReadTimeout(10000);
//        //            connection.setConnectTimeout(10000);
//        //            // 设置请求的头
//        //            connection
//        //                    .setRequestProperty("User-Agent",
//        //                            "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
//        //            System.out.println(connection.getResponseCode() + "GeT请求码");
//        //            if (connection.getResponseCode() == 200) {
//        //                // 判断http请求是否成功返回
//        //                InputStream is = connection.getInputStream();
//        //                // 创建字节输出流对象
//        //                ByteArrayOutputStream os = new ByteArrayOutputStream();
//        //                // 定义读取的长度
//        //                int len = 0;
//        //                // 定义缓冲区
//        //                byte buffer[] = new byte[1024];
//        //                // 按照缓冲区的大小，循环读取
//        //                while ((len = is.read(buffer)) != -1) {
//        //                    // 根据读取的长度写入到os对象中
//        //                    os.write(buffer, 0, len);
//        //                }
//        //                // 释放资源
//        //                is.close();
//        //                os.close();
//        //                // 返回字符串
//        //                String result = new String(os.toByteArray());
//        //                //                Log.d("Debug:", "Get请求结果："+result);
//        //                // 发送返回参数到主线程
//        //                Message msg = Message.obtain();
//        //                msg.what = id;
//        //
//        //                msg.arg1 = MSG_TYPE_SUCCESS;
//        //                Bundle data = new Bundle();
//        //                data.putString(MSG_KEY_PARAM, result);
//        //                msg.setData(data);
//        //                handler.sendMessage(msg);
//        //            } else {
//        //                // http状态码为失败的情况
//        //                Message msg = Message.obtain();
//        //                msg.what = id;
//        //                msg.arg1 = MSG_TYPE_FILED;
//        //                msg.arg2 = httpCode;
//        //                handler.sendMessage(msg);
//        //            }
//        //        } catch (Exception e) {
//        //            e.printStackTrace();
//        //            // 请求过程中发生异常的情况
//        //            Message msg = Message.obtain();
//        //            msg.what = id;
//        //            msg.arg1 = MSG_TYPE_EXCEPTION;
//        //            Bundle data = new Bundle();
//        //            data.putString(MSG_KEY_EXCEPTION, e.getMessage());
//        //            msg.setData(data);
//        //            handler.sendMessage(msg);
//        //        }
//    }
//}
