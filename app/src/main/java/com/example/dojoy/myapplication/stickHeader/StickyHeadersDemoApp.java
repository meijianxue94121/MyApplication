package com.example.dojoy.myapplication.stickHeader;

import android.app.Activity;
import android.app.Application;

import com.example.dojoy.myapplication.helputils.ZhUtils;
import com.example.dojoy.myapplication.stickHeader.api.RandomUserLoader;

import java.util.Stack;

import sdk.utils.OkHttpActionBase;


/**
 * Created by shamyl on 4/22/16.
 */
public class StickyHeadersDemoApp extends Application {

    RandomUserLoader randomUserLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        activities = new Stack<Activity>();
        randomUserLoader = new RandomUserLoader();
        ZhUtils.configImageLoader(this);
        OkHttpActionBase.setDebug(true);
    }

    public RandomUserLoader getRandomUserLoader() {
        return randomUserLoader;
    }

    Stack<Activity> activities;
    public static StickyHeadersDemoApp application;

    public static StickyHeadersDemoApp getInstance() {
        return application;
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
