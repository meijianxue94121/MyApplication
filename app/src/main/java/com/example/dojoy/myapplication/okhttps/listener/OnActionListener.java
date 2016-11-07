package com.example.dojoy.myapplication.okhttps.listener;

/**
 * 请求回调接口
 */
public interface OnActionListener {

	void onActionSuccess(int actionId, String ret);

	void onActionServerFailed(int actionId, int httpStatus);

	void onActionException(int actionId, String exception);
}
