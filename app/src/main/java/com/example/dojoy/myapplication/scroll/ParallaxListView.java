package com.example.dojoy.myapplication.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

import com.example.dojoy.myapplication.R;

public class ParallaxListView extends ListView {

    /**
     * 外部传进来的imgaeview控件
     */
    private ImageView mImageView;
    /**
     * 图片的高度
     */
    private int mImageViewHeight = -1;
    /**
     * 最大拉伸高度
     */
    private int mDrawableMaxHeight = -1;

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 把拉伸的图片传进
     *
     * @param iv
     */
    public void setParallaxImageView(ImageView iv) {
        this.mImageView = iv;
        // 伸缩类型--居中填充
        mImageView.setScaleType(ScaleType.CENTER_CROP);
    }

    /**
     * 初始化图片加载进来最初的高度
     */
    public void setViewsBounds() {
        if (mImageViewHeight == -1) {
            mImageViewHeight = mImageView.getHeight();
            if (mImageViewHeight < 0) {
                // 使用默认的大小
                mImageViewHeight = getContext().getResources()
                        .getDimensionPixelSize(R.dimen.size_default_height);
            }
            mDrawableMaxHeight = (int) (mImageViewHeight * 2);
        }
    }

    /**
     * 滑动过头时回调 控制ImageVIew的高度不断的增加
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        System.out.println("deltaY:" + deltaY);
        boolean isCollapse = resizeOverScrollBy(deltaY);

        // 当return true：下拉到边界的某个地方的时候不再往下拉
        return isCollapse ? true : super.overScrollBy(deltaX, deltaY, scrollX,
                scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
                maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // 监听滑动
        super.onScrollChanged(l, t, oldl, oldt);
        //        System.out.println("这个时候调用onscrollchanged");
        View header = (View) mImageView.getParent();
        if (header.getTop() < 0 && mImageView.getHeight() > mImageViewHeight) {
            // 现在的高度如果大于以前的高度
            // 减小ImageView的高度--不能超出图片原始的高度
            mImageView.getLayoutParams().height = Math.max(// 防止减去太多使用原来的高度
                    mImageView.getHeight() + header.getTop(), mImageViewHeight);
            // imageview的容器的高度没变 getTop()-->0 容器的高度和imageview的高度要一样
            header.layout(header.getLeft(), 0, header.getRight(),
                    header.getHeight());
            System.out.println("header.getHeight():" + header.getHeight());
            mImageView.requestLayout();
        }
    }

    /**
     * @param deltaY 超出滑动的时候每毫秒滑动的距离---增量（就是往下拉不动时会产生一个增量） 负数：往下拉，正数：往上拉
     *               大小根据滑动的速度决定的，一般滑动的速度-50~50 现在处理下拉过渡
     * @return
     */
    private boolean resizeOverScrollBy(int deltaY) {
        // 下拉的过程当中，不断的控制 imageview的高度

        if (deltaY < 0) {
            if (mImageView.getLayoutParams().height < mDrawableMaxHeight) {
                // 下拉过渡，不断的增加ImageView的高度
                mImageView.getLayoutParams().height = mImageView.getHeight()
                        - deltaY;
                // view重新调整宽高
                mImageView.requestLayout();// --->onMeasure -->onLyout
            }


        } else {
            //            if (mImageView.getHeight() > mImageViewHeight) {// 大于原来的高度
            //                // 上啦过渡，不断的减小ImageView的高度
            //                mImageView.getLayoutParams().height = Math.max(
            //                        mImageView.getHeight() - deltaY, mImageViewHeight);
            //                // view重新调整宽高
            //                mImageView.requestLayout();// --->onMeasure -->onLyout
            //            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 监听触摸
        // 判断,
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            // 松开手，开启回弹
            ResetAnimation animation = new ResetAnimation(mImageView, mImageViewHeight);
            animation.setDuration(400);
            mImageView.startAnimation(animation);
        }

        return super.onTouchEvent(ev);
    }

    public class ResetAnimation extends Animation {

        private ImageView mView;
        private int targetHeight;
        private int originalHeight;
        private int extraHeight;

        public ResetAnimation(ImageView mImageView, int targetHeight) {// 目标高度
            this.mView = mImageView;
            // 东兴执行完后的高度
            this.targetHeight = targetHeight;
            // 动画执行之前的高度
            this.originalHeight = mImageView.getHeight();
            // 高度差
            extraHeight = originalHeight - targetHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            /**
             * 动画不断的执行的时候回调该方法； interpolatedTime
             * ：范围是0~1，这个值会跟着时间0-300毫秒变化，当是150ms时是0.5，是个比例
             * 0ms---------------->300ms
             * 当前的图片高度---------->动画执行之前的高度-高度差*interpolatedTime extraHeight
             * ----->0高度差变化到0
             */
            mView.getLayoutParams().height = (int) (originalHeight - extraHeight
                    * interpolatedTime);
            mView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }

}
