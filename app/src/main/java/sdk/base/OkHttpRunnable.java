package sdk.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpRunnable implements Runnable {

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

	public String getUrl () {
		return url;
	}

	public void setUrl (String url) {
		this.url = url;
	}

	private String url;
	private int requestMethod;

	private OnActionListener listener;

	private OkHttpParam param;
	private Handler handler;

	@SuppressLint ("HandlerLeak")
	public OkHttpRunnable (int requestMethod, int actionId, String url) {// baseUrlm没有参数的地址
		this.url = url;
		this.id = actionId;
		this.requestMethod = requestMethod;
		handler = new Handler () {
			@Override
			public void handleMessage (Message msg) {

				if (msg.what == id) {
					switch (msg.arg1) {
						case MSG_TYPE_SUCCESS:
							// 成功获得消息
							if (listener != null) {
								listener.onActionSuccess (id, msg
										.getData ().getString (MSG_KEY_PARAM));
							}
							break;
						case MSG_TYPE_FILED:
							if (listener != null) {
								listener.onActionFailed (id, msg.arg2);
							}
							break;
						case MSG_TYPE_EXCEPTION:
							if (listener != null) {
								listener.onActionException (id, msg.getData ()
										.getString (MSG_KEY_EXCEPTION));
							}
							break;
					}
				}
				super.handleMessage (msg);
			}
		};
	}

	public void setParam (OkHttpParam param) {
		this.param = param;
	}

	public void setOnActionListener (OnActionListener l) {
		listener = l;
	}

	/**
	 * 开启线程
	 */
	public void startAction () {
		new Thread (this).start ();
	}

	@Override
	public void run () {
		try {
			OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder ();
			Request.Builder request = new Request.Builder ();
			switch (requestMethod) {
				case OkHttpActionHelper.GET:
					//GET request
					if (param != null) {
						request.url (url + param.getRequestParam ()).build ();
					} else {
						request.url (url).build ();
					}
					if (OkHttpActionBase.isDebug ()) {
						Log.d ("Debug GET-->", url + param != null ? url + param.getRequestParam () : url);
					}

					break;

				//                case OkHttpActionHelper.GET_STYLE1:
				//                    if (param != null) {
				//                        request.url(url + param.getStyle1RequestParam()).build();
				//                    } else {
				//                        request.url(url).build();
				//                    }
				//                    if (OkHttpActionBase.isDebug()) {
				//                        Log.d("Debug GET-->", url + param != null ? url + param.getStyle1RequestParam() : url);
				//                    }
				//
				//                    break;
				case OkHttpActionHelper.POST:
					//POST request
					if (param != null) {
						FormBody.Builder builder = new FormBody.Builder ();//构建参数
						Map<String, String> stringParams = param.getParams ();
						if (stringParams != null && stringParams.size () > 0) {
							//在字符串集合中有数据
							Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet ().iterator ();
							while (iterator.hasNext ()) {
								Map.Entry<String, String> next = iterator.next ();
								builder.add (next.getKey (), next.getValue ());
							}
						}
						request.url (url).post (builder.build ()).build ();
						if (OkHttpActionBase.isDebug ()) {
							Log.d ("Debug POST-->", url + param != null ? url + param.getRequestParam () : url);
						}
					}
					//                    else {
					//                        //GET请求,
					//                        request.url(url).build();
					//                        if (OkHttpActionBase.isDebug()) {
					//                            Log.d("Debug POST-->GET-->", url);
					//                        }
					//                    }

					break;
				case OkHttpActionHelper.PUT:
					if (param != null) {
						FormBody.Builder builder = new FormBody.Builder ();//构建参数
						Map<String, String> stringParams = param.getParams ();
						if (stringParams != null && stringParams.size () > 0) {
							//在字符串集合中有数据
							Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet ().iterator ();
							while (iterator.hasNext ()) {
								Map.Entry<String, String> next = iterator.next ();
								builder.add (next.getKey (), next.getValue ());
							}
						}
						request.url (url).put (builder.build ()).build ();
						if (OkHttpActionBase.isDebug ()) {
							Log.d ("Debug Put-->", url + param != null ? url + param.getRequestParam () : url);
						}
					}
					//                    else {
					//                        //GET请求,
					//                        request.url(url).build();
					//                        if (OkHttpActionBase.isDebug()) {
					//                            Log.d("Debug PUT-->GET-->", url);
					//                        }
					//                    }

					break;
				case OkHttpActionHelper.DEL:
					//Del request
					if (param != null) {
						//                        FormBody.Builder builder = new FormBody.Builder();//构建参数
						//                        Map<String, String> stringParams = param.getParams();
						//                        if (stringParams != null && stringParams.size() > 0) {
						//                            //在字符串集合中有数据
						//                            Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet().iterator();
						//                            while (iterator.hasNext()) {
						//                                Map.Entry<String, String> next = iterator.next();
						//                                builder.add(next.getKey(), next.getValue());
						//                            }
						//                        }
						request.url (url + param.getRequestParam ()).delete ().build ();
						if (OkHttpActionBase.isDebug ()) {
							Log.d ("Debug Del-->", url + param != null ? url + param.getRequestParam () : url);
						}
					} else {
						request.url (url).delete ().build ();
						if (OkHttpActionBase.isDebug ()) {
							Log.d ("Debug Del-->GET-->", url);
						}
					}

					break;
				case OkHttpActionHelper.PATCH:
					//Del request
					if (param != null) {
						FormBody.Builder builder = new FormBody.Builder ();//构建参数
						Map<String, String> stringParams = param.getParams ();
						if (stringParams != null && stringParams.size () > 0) {
							//在字符串集合中有数据
							Iterator<Map.Entry<String, String>> iterator = stringParams.entrySet ().iterator ();
							while (iterator.hasNext ()) {
								Map.Entry<String, String> next = iterator.next ();
								builder.add (next.getKey (), next.getValue ());
							}
						}
						request.url (url).patch (builder.build ()).build ();

						if (OkHttpActionBase.isDebug ()) {
							Log.d ("Debug PATCH-->", url + param != null ? url +
									param.getRequestParam () : url);
						}
					}
					//					else {
					//						//GET请求,
					//						request.url (url).build ();
					//						if (OkHttpActionBase.isDebug ()) {
					//							Log.d ("Debug PATCH-->GET-->", url);
					//						}
					//					}

					break;

			}
			okHttpClient.connectTimeout (10, TimeUnit.SECONDS)
					.writeTimeout (10, TimeUnit.SECONDS)
					.readTimeout (10, TimeUnit.SECONDS);
			Response response = okHttpClient.build ().newCall (request.build ()).execute ();

			System.out.println ("httpStatus:" + response.code ());
			if (response.isSuccessful ()) {
				//请求成功
				Message msg = Message.obtain ();
				msg.what = id;
				msg.arg1 = MSG_TYPE_SUCCESS;
				Bundle data = new Bundle ();
				String string = response.body ().string ();
				if (OkHttpActionBase.isDebug ()) {
					Log.d ("Debug", "Result:" + string);
				}
				data.putString (MSG_KEY_PARAM, string);
				msg.setData (data);
				handler.sendMessage (msg);
			} else {
				Message msg = Message.obtain ();
				msg.what = id;
				msg.arg1 = MSG_TYPE_FILED;
				msg.arg2 = response.code ();
				handler.sendMessage (msg);
			}
		} catch (Exception e) {
			e.printStackTrace ();
			Message msg = Message.obtain ();
			msg.what = id;
			msg.arg1 = MSG_TYPE_EXCEPTION;
			Bundle data = new Bundle ();
			data.putString (MSG_KEY_EXCEPTION, e.getMessage ());
			msg.setData (data);
			handler.sendMessage (msg);
		}


	}
}
