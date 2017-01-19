package com.example.dojoy.myapplication.base;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import sdk.base.OkHttpActionHelper;
import sdk.base.OnActionListener;

/**
 * Created by dojoy on 2016/11/9.
 */

public abstract class LLNetWorkBaseAct extends LLBaseAct implements OnActionListener {
	protected OkHttpActionHelper okHttpActionHelper;
	private MyOccupying occupying;

	//网络请求
	@Override
	public void onActionSuccess (int actionId, String ret) {
		JSONObject object = JSONObject.parseObject (ret);
		int status = object.getInteger ("status");
		String message = object.getString ("message");
		otherInit ();
		if (status == 200) {
			Success (actionId, object);
		} else if (status == 230) {
			if (message != null) {
				ZhUtils.ToastUtils.showToast (LLNetWorkBaseAct.this, message);
			}
		}
	}

	@Override
	public void onActionFailed (int actionId, int httpStatus) {
		otherInit ();
		Failed (actionId, httpStatus);
	}

	@Override
	public void onActionException (int actionId, String exception) {

		new Handler ().postDelayed (new Runnable () {
			@Override
			public void run () {
				otherInit ();
			}
		}, 10000);
		Exception (actionId, exception);
	}

	@Override
	public View getCustomerView (boolean needAuto, boolean needOccupy) {

		Integer id = setCustomerViewId ();
		if (id != null) {
			if (needAuto) {
				if (vLayout == null) {
					vLayout = new LinearLayout (getBaseContext ());
					vLayout.setLayoutParams (new LinearLayout.LayoutParams (-1, -1));
					vLayout.setOrientation (LinearLayout.VERTICAL);
					vLayout.setFitsSystemWindows (true);
					vLayout.setClipToPadding (true);
				}
				vLayout.removeAllViews ();
				viewLayout = getLayoutInflater ().inflate (id,
						vLayout, false);
				toolBar = setToolBar ();
				if (toolBar != null) {
					toolBar.setLeftClickListener (new View.OnClickListener () {
						@Override
						public void onClick (View v) {
							hideSoft (v);
							MyApplication.getInstance ().removeAct (LLNetWorkBaseAct.this);
						}
					});
					//针对部分加条细线
					view = new View (this);
					view.setBackgroundColor (Color.parseColor ("#e9e9e9"));
					view.setLayoutParams (new LinearLayout.LayoutParams (-1, ZhUtils.DimenTrans.dip2px (this, 1)));
					vLayout.addView (view, 0);
					vLayout.addView (toolBar, 0);
				}

				if (needOccupy) {
					occupying = new MyOccupying (getBaseContext ());
					if (occupying != null) {
						occupying.setPadding (0, ZhUtils.DimenTrans.dip2px (LLNetWorkBaseAct.this, 100), 0, 0);
						vLayout.addView (this.occupying);
					}
					if (ZhUtils.isNetworkConnected (getBaseContext ())) {
						showCustomerView (true);
					} else {
						netError ();
						showCustomerView (false);
					}

				}

				vLayout.addView (viewLayout);
				return vLayout;
			} else {
				return getLayoutInflater ().inflate (id, null);

			}
		}

		return null;
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
	 * 网络异常
	 */
	public void netError () {
		netError (this.occupying);

	}

	public void netError (MyOccupying occupying) {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, getString (R.string.str_net_error), "再试一下"), new View.OnClickListener () {

			@Override
			public void onClick (View v) {
				reloadData ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);


	}

	//占位图相关
	public void dataEmpty (MyOccupying.MyOccupyValue values, View.OnClickListener listener) {
		dataEmpty (this.occupying, values, listener);
	}

	public void dataEmpty (MyOccupying.MyOccupyValue values) {
		dataEmpty (this.occupying, values);
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
		serverError (this.occupying);
	}

	public void serverError (MyOccupying occupying) {
		if (occupying == null)
			return;
		occupying.reset (MyOccupying.getOccupyValue (R.mipmap.no, (R.string.str_server_error), R.string.str_hint_reload), new View.OnClickListener () {
			@Override
			public void onClick (View v) {
				reloadData ();
			}
		}, View.VISIBLE, View.VISIBLE, View.GONE);

	}

	protected abstract void Success (int actionId, JSONObject object);

	protected abstract void Exception (int actionId, String exception);

	protected abstract void Failed (int actionId, int exception);

	//重新加载界面或刷新函数
	public abstract void reloadData ();

	//请求完成后需要执行的操作.比如关闭一些弹窗，等等
	public abstract void otherInit ();
}
