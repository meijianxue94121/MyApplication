package com.example.dojoy.myapplication.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by dell on 2015/6/9.
 */
public abstract class BaseAct extends FragmentActivity {
    protected ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        //设置沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        imageLoader = ImageLoader.getInstance();


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSoft(null);
    }

    public void hideSoft(View v) {
        InputMethodManager imm = (InputMethodManager) BaseAct.this.getSystemService(BaseAct.this.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (v != null) {
                //点击返回的时候，隐藏软键盘
                imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

            } else {
                //
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                        0);
                //                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                //                        InputMethodManager.HIDE_NOT_ALWAYS);

            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.getCurrentFocus() != null) {
            //点击空白处隐藏软键盘
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


}
