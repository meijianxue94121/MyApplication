package com.example.dojoy.myapplication.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dojoy on 2016/8/5.
 */
public class BaseReceiver extends BroadcastReceiver {
    public static final int TAG_LOGIN = 0;
    public static final int TAG_Finish = 1;
    public static final int TAG_Refresh = 2;
    public static final int TAG_Refresh_Code = 3;
    private String RECEIVER_TAG = "";
    private Context mContext;

    public BaseReceiver() {
    }

    public BaseReceiver(Context mContext, String RECEIVER_TAG) {
        this.mContext = mContext;
        this.RECEIVER_TAG = RECEIVER_TAG;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == RECEIVER_TAG) {
            int type = intent.getIntExtra(ExtraUtils._TYPE, TAG_LOGIN);
            switch (type) {
                case TAG_LOGIN:
                    break;
                case TAG_Finish:
                    if (context instanceof LBaseAct) {
                        MyApplication.getInstance().removeAct((LBaseAct) context);
                    }

                    break;
                case TAG_Refresh:
                    if (context instanceof LNetWorkBaseAct) {
                        ((LNetWorkBaseAct) context).initData();
                    }
                    break;
                case TAG_Refresh_Code:
                    break;
            }
        }
    }
}
