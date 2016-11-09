package com.example.dojoy.myapplication.base;

import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import sdk.utils.OnActionListener;

/**
 * Created by dojoy on 2016/11/9.
 */

public abstract class LNetWorkBaseAct extends LBaseAct implements OnActionListener {
    //网络请求
    @Override
    public void onActionSuccess(int actionId, String ret) {
        JSONObject object = JSONObject.parseObject(ret);
        int status = object.getInteger("status");
        String message = object.getString("message");
        requestOtherInit();
        if (status == 200) {
            Success(actionId, object);
        } else if (status == 230) {
            if (message != null) {
                ZhUtils.ToastUtils.showToast(LNetWorkBaseAct.this, message);
            }
        }
    }

    @Override
    public void onActionFailed(int actionId, int httpStatus) {
        requestOtherInit();
        Failed(actionId, httpStatus);
    }

    @Override
    public void onActionException(int actionId, String exception) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestOtherInit();
            }
        }, 10000);
        Exception(actionId, exception);
    }

    protected abstract void Success(int actionId, JSONObject object);

    protected abstract void Exception(int actionId, String exception);

    protected abstract void Failed(int actionId, int exception);


    //刷新函数
    public abstract void initData();

    //请求完成后需要执行的操作
    public abstract void requestOtherInit();
}
