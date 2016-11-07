package com.example.dojoy.myapplication.appWidget;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.superTextView.MainAct;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyAppWidget extends AppWidgetProvider {
    public static String tag = "com.pg.appwidget_provider";
    public int mm = 1;

    public MyAppWidget() {
        mm = 0;
    }

    public MyAppWidget(int s) {
        mm = s;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        String flag = intent.getAction();
        if (tag.equals(flag)) {
            if (mm == 0) {
                final RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_item);
                rViews.setTextViewText(R.id.txt, "success");
                //                http://sc.jb51.net/uploads/allimg/150603/14-150603145201143.jpg

                AsyncTask<Void, Void, Bitmap> execute = new AsyncTask<Void, Void, Bitmap>() {
                    @Override
                    protected Bitmap doInBackground(Void... params) {
                        ImageLoader imageLoader = ImageLoader.getInstance();
                        Bitmap bitmap = imageLoader.loadImageSync("http://sc.jb51.net/uploads/allimg/150603/14-150603145201143.jpg");
                        return bitmap;
                    }

                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        rViews.setImageViewBitmap(R.id.img1, bitmap);

                        AppWidgetManager manager = AppWidgetManager.getInstance(context);
                        ComponentName cName = new ComponentName(context, MyAppWidget.class);
                        manager.updateAppWidget(cName, rViews);


                    }
                }.execute();


                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                ComponentName cName = new ComponentName(context, MyAppWidget.class);
                manager.updateAppWidget(cName, rViews);


            } else if (mm == 1) {
                RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_item);
                rViews.setTextViewText(R.id.txt, "hahahah");
                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                ComponentName cName = new ComponentName(context, MyAppWidget.class);
                manager.updateAppWidget(cName, rViews);
            }

        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager,
                         final int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            System.out.println(context.getPackageName() + appWidgetIds[i]);

            if (mm == 0) {
                Intent intent = new Intent(context, TestActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
                final RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_item);
                rViews.setOnClickPendingIntent(R.id.btn, pIntent);


                final int finalI = i;
                AlertDialog d = new AlertDialog.Builder(context).setTitle("温馨提示").setMessage("啊哈啊哈啊哈啊哈啊哈啊哈啊哈啊哈啊哈啊哈啊哈啊哈")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent2 = new Intent();
                                intent2.setAction(tag);
                                PendingIntent pIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
                                rViews.setOnClickPendingIntent(R.id.btn2, pIntent2);
                                appWidgetManager.updateAppWidget(appWidgetIds[finalI], rViews);
                            }
                        }).create();
                d.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                d.show();
                appWidgetManager.updateAppWidget(appWidgetIds[i], rViews);
            } else if (mm == 1) {
                Intent intent = new Intent(context, MainAct.class);
                PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
                final RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_item);
                rViews.setOnClickPendingIntent(R.id.btn, pIntent);

                Intent intent2 = new Intent();
                intent2.setAction(tag);
                PendingIntent pIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
                rViews.setOnClickPendingIntent(R.id.btn2, pIntent2);
                appWidgetManager.updateAppWidget(appWidgetIds[i], rViews);
            }


        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // TODO Auto-generated method stub
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // TODO Auto-generated method stub
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        // TODO Auto-generated method stub
        super.onDisabled(context);
    }

}
