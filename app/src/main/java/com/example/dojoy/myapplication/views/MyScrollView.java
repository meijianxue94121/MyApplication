package com.example.dojoy.myapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dojoy on 2016/6/16.
 */
public class MyScrollView extends ScrollView {
    public interface ChangeTop {
        void changeTop(int dy);
    }

    ChangeTop lis;

    public ChangeTop getLis() {
        return lis;
    }

    public void setLis(ChangeTop lis) {
        this.lis = lis;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int i = computeVerticalScrollOffset();
        if (lis != null) {
            lis.changeTop(i);
        }

    }

}
