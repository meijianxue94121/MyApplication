package com.a51dojoy.www.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a51dojoy.www.myapplication.animate.AlphaAnimater;
import com.a51dojoy.www.myapplication.animate.Animatable;
import com.a51dojoy.www.myapplication.animate.FlipAnimater;
import com.a51dojoy.www.myapplication.animate.JumpAnimater;
import com.a51dojoy.www.myapplication.animate.RotateAnimater;
import com.a51dojoy.www.myapplication.animate.ScaleAnimater;
import com.a51dojoy.www.myapplication.anno.NorIcons;
import com.a51dojoy.www.myapplication.anno.SeleIcons;
import com.a51dojoy.www.myapplication.anno.Titles;

import java.lang.reflect.Field;

/**
 * Created by jpeng on 16-11-13.
 */
public class JPTabBar extends LinearLayout implements ViewPager.OnPageChangeListener {


    //Alpha动画
    private static final int ALPHA_TYPE = 0;
    //ROTATE3D动画
    private static final int FLIP_TYPE = 1;
    //旋转动画
    private static final int ROTATE_TYPE = 2;
    //缩放动画
    private static final int SCALE_TYPE = 3;
    //跳跃动画
    private static final int JUMP_TYPE = 4;

    //默认TABBAR的高度
    private static final int DEFAULT_HEIGHT = 56;
    //默认的图标大小
    private static final int DEFAULT_ICONSIZE = 24;
    //字体默认大小
    private static final int DEFAULT_TEXTSIZE = 14;
    //默认的没有选中的文字和图标的颜色
    private static final int DEFAULT_NORMAL_COLOR = 0xffAEAEAE;
    //默认的上下背景间隔
    private static final int DEFAULT_MARGIN = 8;
    //默认的选中颜色
    private static final int DEFAULT_SELECT_COLOR = 0xff59D9B9;
    //默认是否接受颜色随着字体变化
    private static final boolean DEFAULT_ACEEPTFILTER = true;
    //默认的动画类型是3D旋转
    private static final int DEFAULT_ANIMATE_TYPE = 1;
    //默认的动画时间
    private static final int DEFAULT_DURATION = 400;
    //默认的徽章背景颜色
    private static final int DEFAULT_BADGE_COLOR = 0xffff0000;
    //默认的徽章字体大小
    private static final int DEFAULT_BADGE_TEXTSIZE = 11;
    //默认的徽章距离中心点距离
    private static final int DEFAULT_PADDING = 4;
    //默认徽章编剧
    private static final int DEFAULT_BADGEMARGIN = 9;


    private Context mContext;

    private TypedArray mAttribute;

    /**
     *
     */
    private int mHeight;
    /**
     * 选中的当前Tab的位置
     */
    private int mSelectIndex;

    /**
     * 动画的实现类
     */
    private Animatable mAnimater;

    /**
     * 所有Tabitem
     */
    private JPTabItem[] mJPTabItems;

    /**
     * 中间按钮
     */
    private ImageView mMiddleItem;

    /**
     * 监听点击Tab回调的观察者
     */
    private OnTabSelectListener mTabSelectLis;

    /**
     * 判断是否需要动画,解决Viewpager回调onpageChange冲突事件
     */
    private boolean mNeedAnimate;

    /**
     * Tab对应的ViewPager
     */
    private ViewPager mTabPager;


    public JPTabBar(Context context) {
        super(context);
        init(context, null);
    }

    public JPTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public JPTabBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化TabBar
     *
     * @param context 上下文
     * @param set     XML结点集合
     */
    private void init(Context context, AttributeSet set) {

        mContext = context;

        mAttribute = context.obtainStyledAttributes(set, R.styleable.JPTabBar);
        setClipChildren(false);
        initFromAttribute();

    }

    private void setAnimater(int type) {
        if (type == ALPHA_TYPE) {
            mAnimater = new AlphaAnimater();
        } else if (type == SCALE_TYPE) {
            mAnimater = new ScaleAnimater();
        } else if (type == ROTATE_TYPE) {
            mAnimater = new RotateAnimater();
        } else if (type == FLIP_TYPE) {
            mAnimater = new FlipAnimater();
        } else if (type == JUMP_TYPE) {
            mAnimater = new JumpAnimater();
        }
    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (mHeight != 0) {
            getLayoutParams().height = mHeight;
        }
    }


    private void initFromAttribute() {
        /**
         * 获取所有节点属性
         */
        mHeight = mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_TabHeight, DensityUtils.dp2px(mContext, DEFAULT_HEIGHT));
        int normalColor = mAttribute.getColor(R.styleable.JPTabBar_TabNormalColor, DEFAULT_NORMAL_COLOR);
        int selectColor = mAttribute.getColor(R.styleable.JPTabBar_TabSelectColor, DEFAULT_SELECT_COLOR);
        int textSize = DensityUtils.px2sp(mContext, mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_TabTextSize, DensityUtils.sp2px(mContext, DEFAULT_TEXTSIZE)));
        //这里
        int iconSize = mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_TabIconSize, DensityUtils.dp2px(mContext, DEFAULT_ICONSIZE));
        int margin = mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_TabMargin, DensityUtils.dp2px(mContext, DEFAULT_MARGIN));
        int AnimateType = mAttribute.getInt(R.styleable.JPTabBar_TabAnimate, DEFAULT_ANIMATE_TYPE);
        int BadgeColor = mAttribute.getColor(R.styleable.JPTabBar_BadgeColor, DEFAULT_BADGE_COLOR);
        int BadgetextSize = DensityUtils.px2sp(mContext, mAttribute.getDimensionPixelSize(R.styleable.JPTabBar_BadgeTextSize, DensityUtils.sp2px(mContext, DEFAULT_BADGE_TEXTSIZE)));
        boolean draggable = mAttribute.getBoolean(R.styleable.JPTabBar_BadgeDraggable, false);
        int badgePadding = DensityUtils.px2dp(mContext, mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_BadgePadding, DensityUtils.dp2px(mContext, DEFAULT_PADDING)));
        int badgeMargin = DensityUtils.px2dp(mContext, mAttribute.getDimensionPixelOffset(R.styleable.JPTabBar_BadgeMargin, DensityUtils.dp2px(mContext, DEFAULT_BADGEMARGIN)));
        boolean acceptFilter = mAttribute.getBoolean(R.styleable.JPTabBar_TabIconFilter, DEFAULT_ACEEPTFILTER);
        Drawable tabselectbg = mAttribute.getDrawable(R.styleable.JPTabBar_TabSelectBg);

        setAnimater(AnimateType);
        //开发者定义注解的数组
        String[] titles = null;
        //开发者定义注解的普通图标
        int[] normalIcon = null;
        //开发者定义注解的选中的图标
        int[] selectedIcon = null;

        //反射注解
        Field[] fields = mContext.getClass().getDeclaredFields();

        //遍历所有字段,寻找标记
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Titles.class)) {
                //标题
                try {
                    if (field.get(mContext).getClass().equals(String[].class)) {
                        titles = (String[]) field.get(mContext);
                    } else if (field.get(mContext).getClass().equals(int[].class)) {
                        int[] title_Res = (int[]) field.get(mContext);
                        titles = new String[title_Res.length];
                        for (int i = 0; i < title_Res.length; i++) {
                            titles[i] = mContext.getString(title_Res[i]);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(NorIcons.class)) {
                try {
                    normalIcon = (int[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(SeleIcons.class)) {
                try {
                    selectedIcon = (int[]) field.get(mContext);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        if (!isInEditMode()) {
            CheckIfAssertError(titles, normalIcon, selectedIcon);


            //计算Tab的宽度
            int MiddleIconId = mAttribute.getResourceId(R.styleable.JPTabBar_TabMiddleIcon, 0);
            mJPTabItems = new JPTabItem[titles.length];
            //实例化TabItem添加进去
            for (int i = 0; i < titles.length; i++) {
                final int temp = i;

                mJPTabItems[i] = new JPTabItem.Builder(mContext).setTitle(titles[i]).setIndex(temp).setTextSize(textSize)
                        .setNormalColor(normalColor).setSelectBg(tabselectbg)
                        .setBadgeTextSize(BadgetextSize).setNormalIcon(normalIcon[i])
                        .setSelectedColor(selectColor).setBadgeDrable(draggable).setBadgeColor(BadgeColor)
                        .setBadgePadding(badgePadding).setIconSize(iconSize).setIconFilte(acceptFilter)
                        .setBadgeMargin(badgeMargin).setMargin(margin)
                        .setSelectIcon(selectedIcon == null ? 0 : selectedIcon[i]).build();
                mJPTabItems[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTabPager != null && mTabPager.getAdapter() != null && mTabPager.getAdapter().getCount() >= mJPTabItems.length) {
                            mNeedAnimate = true;
                            mTabPager.setCurrentItem(temp, false);
                        } else if (mTabPager != null && mTabPager.getAdapter() != null && mTabPager.getAdapter().getCount() <= mJPTabItems.length) {
                            mNeedAnimate=true;
                            mTabPager.setCurrentItem(temp, false);
                            setSelectTab(temp);
                        } else {
                            setSelectTab(temp, true);
                        }

                    }
                });
                addView(mJPTabItems[i]);

                //判断是不是准备到中间的tab,假如设置了中间图标就添加进去
                if (i == (titles.length / 2 - 1)) {

                    mMiddleItem = BuildMiddleBtn(mHeight, MiddleIconId);

                }

            }


            mJPTabItems[0].setSelect(mAnimater,true,true);
            for(int i =1;i<mJPTabItems.length;i++){
                mJPTabItems[i].setSelect(null,false,false);
            }
            mAttribute.recycle();
        }
    }


    /**
     * 判断有没有声明变量的错误
     *
     * @param titles       标题
     * @param normalIcon   没有选中的图标
     * @param selectedIcon 选中的图标
     */
    private void CheckIfAssertError(String[] titles, int[] normalIcon, int[] selectedIcon) {
        if (titles == null || normalIcon == null) {
            throw new TabException("you must set the Titles Annotation and NormalIcon Annotation for the JPTabbar!!!");
        }
        int originlen = titles.length;
        //判断注解里面的数组长度是否一样
        if ((originlen != normalIcon.length)
                || (selectedIcon != null && originlen != selectedIcon.length)) {
            throw new TabException("the Annotation Array length is not equal,Please Check Your Annotation in your Activity,Ensure Every Array length is equals!");
        }
    }


    private ImageView BuildMiddleBtn(int height, int icon_res) {
        if (icon_res == 0) return null;
        ImageView middleBtn = new ImageView(mContext);
        LayoutParams params = new LayoutParams(height,height);
        params.setMargins(0,0,0, (int) (height*0.33));
        params.gravity= Gravity.BOTTOM;
        middleBtn.setLayoutParams(params);
        middleBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        middleBtn.setImageDrawable(mContext.getResources().getDrawable(icon_res));
        middleBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTabSelectLis != null)
                    mTabSelectLis.onClickMiddle(mMiddleItem);
            }
        });

        addView(middleBtn);
        return middleBtn;
    }


    /****-------提供给开发者调用的方法---------****/


    /**
     * 切换Tab页面
     */
    public void setSelectTab(int index) {
        setSelectTab(index, true);
    }

    /**
     * 切换Tab页面,是否带动画
     */
    public void setSelectTab(int index, boolean animated) {
        if (index>mJPTabItems.length-1)return;
        mSelectIndex = index;
        //把全部tab selected设置为false
        for (int i = 0; i < mJPTabItems.length; i++) {
            if (i == index) {
                continue;
            }
            if(!mJPTabItems[i].isSelect()){
                mJPTabItems[i].setSelect(mAnimater, false, animated);
            }
            else{
                mJPTabItems[i].setSelect(mAnimater, false, animated);
            }

        }

        mJPTabItems[index].setSelect(mAnimater, true, animated);

        if (mTabSelectLis != null) {
            mTabSelectLis.onTabSelect(index);
        }
    }

    /**
     * 设置容器和TabBar联系在一起
     */
    public void setContainer(ViewPager pager) {
        if (pager != null) {
            mTabPager = pager;
            mTabPager.setOnPageChangeListener(this);
        }
    }

    /**
     * 显示图标的Badge
     */
    public void ShowBadge(int pos, String text) {
        if (mJPTabItems != null)
            mJPTabItems[pos].showTextBadge(text);
    }

    /**
     * 重载方法
     * 设置徽章,传入int
     */
    public void ShowBadge(int pos, int count) {
        if (mJPTabItems[pos] == null) return;
        if (count == 0) {
            mJPTabItems[pos].hiddenBadge();

        } else if (count >= 100) {
            mJPTabItems[pos].showTextBadge("99+");
        } else {
            mJPTabItems[pos].showTextBadge(count + "");
        }
    }

    public void HideBadge(int position) {
        if (mJPTabItems != null)
            mJPTabItems[position].hiddenBadge();
    }


    /**
     * 获得选中的位置
     */
    public int getSelectPosition() {

        return mSelectIndex;
    }

    /**
     * 获取徽章是否在显示
     */
    public boolean isBadgeShow(int index) {
        if (mJPTabItems != null)
            return mJPTabItems[index].isBadgeShow();

        return false;
    }

    /**
     * 获得中间的TABItem
     *
     * @return
     */
    public ImageView getMiddleBtn() {
        return mMiddleItem;
    }


    /**
     * 设置自定义动画
     */
    public void setCustomAnimate(@NonNull Animatable customAnimate) {
        this.mAnimater = customAnimate;
    }

    /**
     * 设置点击TabBar事件的观察者
     */
    public void setTabListener(OnTabSelectListener listener) {
        mTabSelectLis = listener;
    }

    /**
     * 设置badgeView消失的回调事件
     */
    public void setDismissListener(BadgeDismissListener listener) {
        for (JPTabItem item : mJPTabItems) {
            item.setDismissDelegate(listener);
        }
    }

    /****---------------****/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position>mJPTabItems.length-1||1+position>mJPTabItems.length-1)return;
        if (positionOffset > 0f) {
            mJPTabItems[position].changeAlpha(1 - positionOffset);
            mJPTabItems[position + 1].changeAlpha(positionOffset);

            if (mAnimater != null) {
                if (mAnimater.isNeedPageAnimate()) {
                    mNeedAnimate = false;
                    mAnimater.onPageAnimate(mJPTabItems[position].getIconView(), 1 - positionOffset);
                    mAnimater.onPageAnimate(mJPTabItems[position + 1].getIconView(), positionOffset);
                } else {
                    mNeedAnimate = true;
                }
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        setSelectTab(position, mNeedAnimate);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1) {
            mNeedAnimate = false;
        }
    }


}

