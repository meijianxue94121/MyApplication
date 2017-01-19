package com.example.dojoy.myapplication.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.dojoy.myapplication.helputils.ZhUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by dojoy on 2016/8/1.
 */
public abstract class LLBaseAct extends FragmentActivity {
	public LinearLayout vLayout;
	public View viewLayout;
	public MyToolBar toolBar;
	//线条
	public View view;
	protected ImageLoader imageLoader;
	BaseReceiver baseReceiver;
	private static final String receiverTag = "LBaseActReceiver";
	MyMessageAlert messageAlert;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		//        //设置沉浸式
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow ();
			window.setFlags (
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		imageLoader = ImageLoader.getInstance ();
		baseReceiver = ZhUtils.initReceiver (LLBaseAct.this, receiverTag);
		messageAlert = new MyMessageAlert (LLBaseAct.this);
		setContentView (getCustomerView ());
	}

	@Override
	protected void onPause () {
		super.onPause ();
		hideSoft (null);
	}

	@Override
	protected void onDestroy () {
		super.onDestroy ();
		recycle ();
		unregisterReceiver (baseReceiver);
	}

	public void hideSoft (View v) {
		InputMethodManager imm = (InputMethodManager) LLBaseAct.this.getSystemService (LLBaseAct.this.INPUT_METHOD_SERVICE);
		if (imm != null) {
			if (v != null) {
				//点击返回的时候，隐藏软键盘
				imm.showSoftInput (v, InputMethodManager.SHOW_FORCED);
				imm.hideSoftInputFromWindow (v.getWindowToken (), 0); //强制隐藏键盘

			} else {
				imm.hideSoftInputFromWindow (getWindow ().getDecorView ().getWindowToken (),
						0);

			}

		}
	}

	@Override
	public boolean onTouchEvent (MotionEvent event) {
		if (this.getCurrentFocus () != null) {
			//点击空白处隐藏软键盘
			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService (INPUT_METHOD_SERVICE);
			return mInputMethodManager.hideSoftInputFromWindow (this.getCurrentFocus ().getWindowToken (), 0);
		}
		return super.onTouchEvent (event);
	}


	public View getCustomerView (boolean needAuto, boolean needOccupy) {

		Integer id = setCustomerViewId ();
		if (id != null) {
			if (needAuto) {
				if (vLayout == null) {
					vLayout = new LinearLayout (getBaseContext ());
					vLayout.setLayoutParams (new LinearLayout.LayoutParams (-1, -1));
					vLayout.setOrientation (LinearLayout.VERTICAL);
					vLayout.setBackgroundColor (Color.parseColor ("#f4f4f4"));
					vLayout.setFitsSystemWindows (true);
					vLayout.setClipToPadding (true);
				}
				vLayout.removeAllViews ();
				viewLayout = getLayoutInflater ().inflate (id,
						vLayout, false);
				toolBar = setToolBar ();
				if (toolBar != null) {
					//如果沉浸式，可以将toolBar下移
					toolBar.setLeftClickListener (new View.OnClickListener () {
						@Override
						public void onClick (View v) {
							hideSoft (v);
							MyApplication.getInstance ().removeAct (LLBaseAct.this);
						}
					});
					//针对部分加条细线
					view = new View (this);
					view.setBackgroundColor (Color.parseColor ("#e9e9e9"));
					view.setLayoutParams (new LinearLayout.LayoutParams (-1, ZhUtils.DimenTrans.dip2px (this, 1)));
					vLayout.addView (view, 0);
					vLayout.addView (toolBar, 0);
				}

				vLayout.addView (viewLayout);
				return vLayout;
			} else {
				return getLayoutInflater ().inflate (id, null);

			}
		}

		return null;
	}


	public View getCustomerView () {
		return getCustomerView (true, true);
	}

	private void recycle () {
		toolBar = null;
	}


	/**
	 * @return 返回一个需要展示的视图布局文件Id，建议使用
	 */
	public abstract Integer setCustomerViewId ();

	public abstract MyToolBar setToolBar ();

	public MyToolBar getToolBar () {
		MyToolBar toolBar = new MyToolBar (LLBaseAct.this, "", this.getLocalClassName () + "", "");
		return toolBar;
	}

}
