package com.example.dojoy.myapplication.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import sdk.base.OkHttpActionHelper;

/**
 * Created by dojoy on 2016/8/1.
 */
public abstract class LBaseAct extends FragmentActivity {
	public LinearLayout vLayout;
	public View viewLayout;
	public MyToolBar toolBar;
	public MyOccupying occupying;
	//线条
	public View view;
	protected ImageLoader imageLoader;
	BaseReceiver baseReceiver;
	private static final String receiverTag = "LBaseActReceiver";
	OkHttpActionHelper okHttpActionHelper;

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
		baseReceiver = ZhUtils.initReceiver (LBaseAct.this, receiverTag);
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
		InputMethodManager imm = (InputMethodManager) LBaseAct.this.getSystemService (LBaseAct.this.INPUT_METHOD_SERVICE);
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


	/**
	 * 转变占位图。
	 *
	 * @param showCustomer
	 */
	public void showCustomerView (boolean showCustomer) {
		showCustomerView (this.occupying, this.viewLayout, showCustomer);

	}

	/**
	 * 使用type1时自定义occupy
	 * TRUE，代表使用xml界面，FALSE,使用占位图
	 *
	 * @param occupy
	 * @param myView
	 * @param showCustomer
	 */
	public void showCustomerView (View occupy, View myView, boolean showCustomer) {
		if (showCustomer) {
			if (myView != null) {
				myView.setVisibility (View.VISIBLE);
			}
			if (occupy != null) {
				occupy.setVisibility (View.GONE);
			}
		} else {
			if (myView != null) {
				myView.setVisibility (View.GONE);
			}
			if (occupy != null) {
				occupy.setVisibility (View.VISIBLE);
			}
		}

	}




	/**
	 * @param type
	 * @return
	 */
	public LinearLayout requestView (int type) {
		return requestView (type);
	}

	/**
	 * @param layoutId
	 * @param type
	 * @return
	 */
	public LinearLayout requestView (int layoutId, int type) {
		if (vLayout == null) {
			vLayout = new LinearLayout (getBaseContext ());
			vLayout.setLayoutParams (new LinearLayout.LayoutParams (-1, -1));
			vLayout.setOrientation (LinearLayout.VERTICAL);
			vLayout.setFitsSystemWindows (true);
			vLayout.setClipToPadding (true);
		}
		vLayout.removeAllViews ();
		viewLayout = LayoutInflater.from (getBaseContext ()).inflate (layoutId,
				vLayout, false);

		if (toolBar != null) {
			//如果沉浸式，可以将toolBar下移
			if (ZhUtils.isCanChenjin ())
				toolBar.setPadding (0, ZhUtils.getStatusBarHeight2 (LBaseAct.this), 0, 0);
			toolBar.setLeftClickListener (new View.OnClickListener () {
				@Override
				public void onClick (View v) {
					hideSoft (v);
					MyApplication.getInstance ().removeAct (LBaseAct.this);
				}
			});
			//针对部分加条细线
			view = new View (this);
			view.setBackgroundColor (Color.parseColor ("#e9e9e9"));
			view.setLayoutParams (new LinearLayout.LayoutParams (-1, ZhUtils.DimenTrans.dip2px (this, 1)));
			vLayout.addView (view, 0);
			vLayout.addView (toolBar, 0);
		}


		if (type == 0) {
			occupying = new MyOccupying (getBaseContext ());
			if (occupying != null) {
				occupying.setPadding (0, ZhUtils.DimenTrans.dip2px (LBaseAct.this, 100), 0, 0);
				vLayout.addView (this.occupying);
			}
			if (ZhUtils.isNetworkConnected (getBaseContext ())) {
				showCustomerView (true);
			} else {
				netError ();
				showCustomerView (false);
			}

		} else if (type == 1) {

		}
		vLayout.addView (viewLayout);

		return vLayout;

	}

	//占位图相关
	public void dataEmpty (MyOccupying.MyOccupyValue values, View.OnClickListener listener) {
		if (occupying == null)
			return;
		occupying.reset (values, listener, View.VISIBLE, View.VISIBLE, View.VISIBLE);
	}

	public void dataEmpty (MyOccupying.MyOccupyValue values) {
		if (occupying == null)
			return;
		occupying.reset (values, null, View.VISIBLE, View.VISIBLE, View.GONE);
	}

	/**
	 * 初始化自定义的空数据occupy,
	 *
	 * @param occupying
	 */
	public void dataEmpty (MyOccupying occupying, MyOccupying.MyOccupyValue values) {
		if (occupying == null)
			return;
		occupying.reset (values, null, View.VISIBLE, View.VISIBLE, View.GONE);
	}

	public void dataEmpty (MyOccupying occupying, MyOccupying.MyOccupyValue values, View.OnClickListener listener) {
		if (occupying == null)
			return;
		occupying.reset (values, listener, View.VISIBLE, View.VISIBLE, View.GONE);
	}

	/**
	 * 服务器异常
	 */
	public void serverError () {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, "服务器异常", "重新连接"), new View.OnClickListener () {

			@Override
			public void onClick (View v) {
				requestInit ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);
	}

	public void serverError (MyOccupying occupying) {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, "服务器异常啦", "重新连接"), new View.OnClickListener () {
			@Override
			public void onClick (View v) {
				requestInit ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);

	}


	/**
	 * 网络异常
	 */
	public void netError () {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, "网络不给力哦", "再试一下"), new View.OnClickListener () {

			@Override
			public void onClick (View v) {
				requestInit ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);


	}

	public void netError (MyOccupying occupying) {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, "网络不给力哦", "再试一下"), new View.OnClickListener () {

			@Override
			public void onClick (View v) {
				requestInit ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);


	}


	/**
	 * 回收资源
	 *
	 * @param dialog1
	 */
	public void recycleBaseDialog (AlertDialog dialog1) {
		if (dialog1 != null) {
			if (dialog1.isShowing ()) {
				dialog1.dismiss ();
			}
			dialog1 = null;
		}

	}

	public void recyclerProgressDialog (ProgressDialog p) {
		if (p != null) {
			if (p.isShowing ()) {
				p.dismiss ();
			}
			p = null;
		}
	}

	protected void closeRefresh (final SwipeRefreshLayout layout1, long time) {
		new Handler ().postDelayed (new Runnable () {
			@Override
			public void run () {
				layout1.setRefreshing (false);
			}
		}, time);
	}

	private void recycle () {
		toolBar = null;
		occupying = null;
	}


	//占位图下方按钮功能键
	public abstract void requestInit ();

	/**
	 * @return 返回一个需要展示的视图布局文件Id，建议使用
	 */
	public abstract int customerViewId ();


}
