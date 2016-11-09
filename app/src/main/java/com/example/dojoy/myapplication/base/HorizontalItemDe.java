package com.example.dojoy.myapplication.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.example.dojoy.myapplication.helputils.ZhUtils;


/**
 * Created by dojoy on 2016/7/12.，针对于水平布局，单行显示
 */
public class HorizontalItemDe extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int space;
    private int firstSpace;


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (itemPosition == 0) {
            outRect.set(firstSpace, 0, 0, 0);
        } else {
            outRect.set(space, 0, 0, 0);
        }
    }

    public HorizontalItemDe() {
        super();
    }

    public HorizontalItemDe(Context context) {
        super();
        mContext = context;

    }

    public HorizontalItemDe(Context context, int space) {
        super();
        mContext = context;
        this.space = ZhUtils.DimenTrans.dip2px(context, space);
        this.firstSpace = 0;

    }

    public HorizontalItemDe(Context context, int firstSpace, int space) {
        super();
        mContext = context;
        this.space = ZhUtils.DimenTrans.dip2px(context, space);
        this.firstSpace = ZhUtils.DimenTrans.dip2px(context, firstSpace);

    }
}
