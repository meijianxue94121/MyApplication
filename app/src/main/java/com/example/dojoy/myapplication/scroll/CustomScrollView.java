package com.example.dojoy.myapplication.scroll;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by dojoy on 2016/6/21.
 */

public class CustomScrollView extends ScrollView {
    // 图片控件视图
    private View mRootView;
    // 用来保存手指的当前位置坐标值
    private int mpreY = 0;
    // 初始化图片视图的矩形区域坐标位置
    private Rect mNormalRect;
    private Context context;

    // 底部图片
    private View mHeaderView;

    /**
     * 构造器
     *
     * @param context
     * @param attrs
     */
    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * onFinishInflate（）函数，明显是在布局解析结束后会调用的函数。
     * 获取scrollView的孩子view控件
     */
    @Override
    protected void onFinishInflate() {
        mRootView = getChildAt(0);
        super.onFinishInflate();
    }

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setmHeaderView(View view) {
        mHeaderView = view;
    }

    /**
     * 在onTouchEvent()中根据手指的移动距离，通过layout()函数将布局跟随移动即可
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // 获取手指当前的位置y轴坐标
                mpreY = (int) event.getY();
                if (mRootView != null) {
                    // 进行布局位置--获取图片初始化的矩形区域
                    mNormalRect = new Rect(mRootView.getLeft(), mRootView.getTop(),
                            mRootView.getRight(), mRootView.getBottom());

                    Log.e("TAG", "" + px2dip(context, mRootView.getTop()));//100dp
                    Log.e("TAG", "" + px2dip(context, mRootView.getBottom()));//612dp
                }
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                //获取目前的移动的y轴值
                float curY = event.getY();
                // 获取手指移动的距离--乘以0.25是凸显阻尼效果
                int delta = (int) (curY - mpreY);
                // 根据手指滑动的距离，进行重新布局那个图片View控件！！！！！！！！！！！！
                //将移动的距离转为dp进行判断
                int deltaDP = px2dip(context, delta);

                int deltaPx = dip2px(context, 100);

                if (deltaDP <= 100) {
                    mRootView.layout(mRootView.getLeft(), mNormalRect.top + 2
                            * delta, mRootView.getRight(), mNormalRect.bottom + 2
                            * delta);

                    mHeaderView.layout(mRootView.getLeft(), dip2px(context, -100)
                            + delta, mRootView.getRight(), dip2px(context, 200)
                            + delta);
                } else {

                    mRootView.layout(mRootView.getLeft(), mNormalRect.top + 2
                            * deltaPx, mRootView.getRight(), mNormalRect.bottom + 2
                            * deltaPx);
                }

            }
            break;
            case MotionEvent.ACTION_UP: {
                //回归
                mHeaderView.layout(mRootView.getLeft(), dip2px(context, -100),
                        mRootView.getRight(), dip2px(context, 200));
                // 回归到初始化的那个矩形位置即可
                mRootView.layout(mNormalRect.left, mNormalRect.top,
                        mNormalRect.right, mNormalRect.bottom);

            }
            break;
        }
        // 保存每次手指移动的最新位置
        return super.onTouchEvent(event);
    }

}
