package com.example.dojoy.myapplication.base;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by dojoy on 2016/11/9.
 */

public class MyApplication extends Application {
    Stack<Activity> activities;
    public static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        activities = new Stack<Activity>();
    }

    public void addAct(Activity activity) {
        if (activities != null) {
            activities.add(activity);
        }

    }

    public final void removeAct(Activity act) {
        activities.remove(act);
        if (act != null) {
            act.finish();
        }
    }

    public final void clearAct() {
        for (Activity act : activities) {
            if (act != null) {
                act.finish();
            }
        }
        activities.clear();
        // 杀死进程
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
