package com.example.dojoy.myapplication.base;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;


/**
 * Created by Administrator on 2015/12/25.
 */
public class MyToolBar extends LinearLayout {
    private LayoutInflater mInflater;
    LinearLayout leftLayout, rightLayout;
    TextView titleView;
    // EditText editText;
    ImageView leftImageBtn;
    ImageView rightImageBtn;
    TextView leftTextBtn;
    TextView rightTextBtn;
    View mView;
    public LinearLayout toolBarLayout;
    public static final String bg = "#f8f8f8";

    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public void setAlphas(int in) {
        this.getBackground().setAlpha(in);
    }

    /**
     * @param context   上下文
     * @param leftIcon  -1代表隐藏 左侧按钮资源图片id
     * @param titleText 标题
     * @param rightIcon -1代表隐藏 右侧按钮资源图片id
     */
    public MyToolBar(Context context, int leftIcon,
                     String titleText, int rightIcon) {
        this(context, leftIcon, "", titleText, rightIcon, "");

    }

    /**
     * @param context   上下文
     * @param leftText  左侧文字
     * @param titleText 标题
     * @param rightText 右侧文字
     */
    public MyToolBar(Context context, String leftText,
                     String titleText, String rightText) {
        this(context, -1, leftText, titleText, -1, rightText);
    }

    /**
     * @param context   上下文
     * @param leftText  左侧文字
     * @param titleText 标题
     * @param rightIcon -1代表隐藏 右侧按钮资源图片id
     */
    public MyToolBar(Context context, String leftText,
                     String titleText, int rightIcon) {
        this(context, -1, leftText, titleText, rightIcon, "");
    }

    /**
     * @param context   上下文
     * @param leftIcon  -1代表隐藏 左侧按钮资源图片id
     * @param titleText 标题
     * @param rightText 右侧文字
     */
    public MyToolBar(Context context, int leftIcon,
                     String titleText, String rightText) {
        this(context, leftIcon, "", titleText, -1, rightText);
    }

    /**
     * @param context   上下文
     * @param leftIcon  -1代表隐藏 左侧按钮资源图片id
     * @param leftText  左侧文字
     * @param titleText 标题
     * @param RightIcon -1代表隐藏 右侧按钮资源图片id
     * @param rightText 右侧文字
     */
    public MyToolBar(Context context, int leftIcon, String leftText,
                     String titleText, int RightIcon, String rightText) {
        this(context, leftIcon, leftText, titleText, RightIcon, rightText, bg);
        //        initView();
        //        if (leftIcon != -1) {
        //            setLeftBtnIcon(leftIcon);
        //        }
        //        if (!(leftText == null || leftText.length() <= 0 || leftText.equals(""))) {
        //            setLeftBtn(leftText);
        //        }
        //        if (RightIcon != -1) {
        //            setRightBtnIcon(RightIcon);
        //        }
        //        if (!(rightText == null || rightText.length() <= 0 || rightText
        //                .equals(""))) {
        //            setRightBtn(rightText);
        //        }
        //        if (!(titleText == null || titleText.length() <= 0 || titleText
        //                .equals(""))) {
        //            setTitle(titleText);
        //        }

        //        setBackgroundColor(Color.parseColor("#ffb319"));
    }

    public MyToolBar(Context context, int leftIcon, String leftText,
                     String titleText, int RightIcon, String rightText, String bgColor) {
        super(context);

        initView();
        if (leftIcon != -1) {
            setLeftBtnIcon(leftIcon);
        }
        if (!(leftText == null || leftText.length() <= 0 || leftText.equals(""))) {
            setLeftBtn(leftText);
        }
        if (RightIcon != -1) {
            setRightBtnIcon(RightIcon);
        }
        if (!(rightText == null || rightText.length() <= 0 || rightText
                .equals(""))) {
            setRightBtn(rightText);
        }
        if (!(titleText == null || titleText.length() <= 0 || titleText
                .equals(""))) {
            setTitle(titleText);
        }
        if (!(bgColor == null || bgColor.length() <= 0 || bgColor
                .equals(""))) {
            setBackgroundColor(Color.parseColor(bgColor));
        } else {
            setBackgroundColor(Color.parseColor(bg));
        }

        //        setBackgroundColor(Color.parseColor("#ffb319"));
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.MyToolBar, defStyleAttr, 0);

            String leftString = a.getString(R.styleable.MyToolBar_leftText);
            if (leftTextBtn != null) {
                setLeftBtn(leftString);

            }
            String rightString = a.getString(R.styleable.MyToolBar_rightText);
            if (rightTextBtn != null) {
                setRightBtn(rightString);
            }

            Drawable leftIcon = a
                    .getDrawable(R.styleable.MyToolBar_leftBtnIcon);
            if (leftImageBtn != null) {
                setLeftBtnIcon(leftIcon);
            }
            Drawable rightIcon = a
                    .getDrawable(R.styleable.MyToolBar_rightBtnIcon);
            if (rightImageBtn != null) {
                setRightBtnIcon(rightIcon);

            }
            String titleString = a.getString(R.styleable.MyToolBar_titleText);
            if (titleView != null) {
                setTitle(titleString);

            }
            int bgColor = a.getColor(R.styleable.MyToolBar_bgColor, Color.parseColor(bg));
            setBackgroundColor(bgColor);
            a.recycle();

        }

    }

    //    private void setbgColor(int bgColor) {
    //        if (toolBarLayout != null) {
    //            toolBarLayout.setBackgroundColor(bgColor);
    //        }
    //    }

    public void setLeftClickListener(OnClickListener l) {
        if (leftLayout != null) {
            leftLayout.setOnClickListener(l);
        }

    }

    public void setRightClickListener(OnClickListener l) {
        if (rightLayout != null) {
            rightLayout.setOnClickListener(l);

        }

    }


    private void initView() {
        if (mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.liu_toolbarlayout, this);
            titleView = (TextView) mView.findViewById(R.id.toolbar_title);
            rightImageBtn = (ImageView) mView
                    .findViewById(R.id.toolbar_rightButton);
            leftImageBtn = (ImageView) mView
                    .findViewById(R.id.toolbar_leftButton);
            leftTextBtn = (TextView) mView
                    .findViewById(R.id.toolbar_leftTextButton);
            rightTextBtn = (TextView) mView
                    .findViewById(R.id.toolbar_rightTextButton);
            leftLayout = (LinearLayout) mView
                    .findViewById(R.id.toolbar_leftLayout);
            rightLayout = (LinearLayout) mView
                    .findViewById(R.id.toolbar_rightLayout);
            toolBarLayout = (LinearLayout) mView
                    .findViewById(R.id.toolBar);

        }

    }

    public void setRightBtnIcon(Drawable ic) {
        if (rightImageBtn != null) {
            rightImageBtn.setImageDrawable(ic);
            rightImageBtn.setVisibility(VISIBLE);
        }
    }

    public void setRightBtnIcon(int icId) {
        if (rightImageBtn != null) {
            rightImageBtn.setImageResource(icId);
            rightImageBtn.setVisibility(VISIBLE);
        }
    }

    public void setRightBtn(String ic) {
        if (rightTextBtn != null) {
            rightTextBtn.setText(ic);
            rightTextBtn.setVisibility(VISIBLE);
        }
    }

    public void setLeftBtnIcon(Drawable ic) {
        if (leftImageBtn != null) {
            leftImageBtn.setImageDrawable(ic);
            leftImageBtn.setVisibility(VISIBLE);
        }
    }

    public void setLeftBtnIcon(int icId) {
        if (leftImageBtn != null) {
            leftImageBtn.setImageResource(icId);
            leftImageBtn.setVisibility(VISIBLE);
        }
    }

    public void setLeftBtn(String ic) {
        if (leftTextBtn != null) {
            leftTextBtn.setText(ic);
            leftTextBtn.setVisibility(VISIBLE);
        }
    }

    public void setTitle(String title) {
        if (titleView != null) {
            titleView.setText(title);
            showTitleView();
        }
    }

    public void showTitleView() {
        if (titleView != null) {
            titleView.setVisibility(VISIBLE);
        }

    }

    public void hideTitleView() {
        if (titleView != null) {
            titleView.setVisibility(INVISIBLE);
        }
    }

    public void showRightView() {
        if (rightLayout != null) {
            rightLayout.setVisibility(VISIBLE);
        }

    }

    public void hideRightView() {
        if (rightLayout != null) {
            rightLayout.setVisibility(INVISIBLE);
        }
    }

    public void showLeftView() {
        if (leftLayout != null) {
            leftLayout.setVisibility(VISIBLE);
        }

    }

    public void hideLeftView() {
        if (leftLayout != null) {
            leftLayout.setVisibility(INVISIBLE);
        }
    }

}
