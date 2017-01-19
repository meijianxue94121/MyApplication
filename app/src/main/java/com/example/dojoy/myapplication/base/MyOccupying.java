package com.example.dojoy.myapplication.base;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.stickHeader.StickyHeadersDemoApp;


public class MyOccupying extends LinearLayout {
	public ImageView img;
	public TextView text;
	public TextView commit;
	public View mView;
	private LayoutInflater mInflater;
	public LinearLayout layout;

	public MyOccupying (Context context, AttributeSet attrs, int defStyle) {
		super (context, attrs, defStyle);
		initData ();
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes (attrs,
					R.styleable.MyOccupy, defStyle, 0);
			Drawable drawable = a.getDrawable (R.styleable.MyOccupy_imgBtnIcon);
			setImage (drawable);
			String te = a.getString (R.styleable.MyOccupy_textText);
			setTextString (te);
			String co = a.getString (R.styleable.MyOccupy_commitText);
			setCommitString (co);
		}
	}

	public MyOccupying (Context context, AttributeSet attrs) {
		this (context, attrs, 0);
	}

	public MyOccupying (Context context) {
		this (context, null);
	}

	public MyOccupying (Context context, int imgId, String textString,
	                    String commitString) {
		super (context);
		initData ();
		if (imgId != -1) {
			setImage (imgId);
		}
		if (!(textString == null || textString.equals ("") || textString
				.length () <= 0)) {
			setTextString (textString);

		}
		if (!(commitString == null || commitString.equals ("") || commitString
				.length () <= 0)) {
			setCommitString (commitString);
		}

	}

	public MyOccupying (Context context, int imgId, int textString,
	                    String commitString) {
		super (context);
		initData ();
		if (imgId != -1) {
			setImage (imgId);
		}
		String s = context.getResources ().getString (textString);
		if (!(s == null || s.equals ("") || s
				.length () <= 0)) {
			setTextString (s);

		}
		if (!(commitString == null || commitString.equals ("") || commitString
				.length () <= 0)) {
			setCommitString (commitString);
		}

	}

	public void setCommitListener (OnClickListener listener) {
		if (commit != null) {
			commit.setOnClickListener (listener);
		}
	}

	public void setCommitString (String textString) {
		if (commit != null) {
			commit.setText (textString);
		}
	}

	public void setTextString (String textString) {
		if (text != null) {
			text.setText (textString);
		}
	}

	public void setImage (int imgId) {
		if (img != null) {
			img.setImageResource (imgId);
		}
	}

	public void setImage (Drawable imgDrawable) {
		if (img != null) {
			img.setImageDrawable (imgDrawable);

		}
	}

	private void initData () {
		if (mView == null) {
			mInflater = LayoutInflater.from (getContext ());
			mView = mInflater.inflate (R.layout.liu_occupyinglayout, this);
			img = (ImageView) findViewById (R.id.occupy_image);
			text = (TextView) findViewById (R.id.occupy_text);
			commit = (TextView) findViewById (R.id.occupy_commit);
			layout = (LinearLayout) findViewById (R.id.occupy_layout);
		}
	}

	public void setCommitVisiable (int visible) {
		if (commit != null) {
			commit.setVisibility (visible);
		}
	}

	public void reset (MyOccupyValue values, OnClickListener listener,
	                   int imgVisible, int textVisible, int commitVisible) {

		if (values.getIntImageResourceId () != -1)
			setImage (values.getIntImageResourceId ());
		if (values.getStrHint () != null)
			setTextString (values.getStrHint ());
		if (values.getStrCommit () != null)
			setCommitString (values.getStrCommit ());
		setCommitListener (listener);
		if (this.img != null) {
			this.img.setVisibility (imgVisible);
		}
		if (this.text != null) {
			this.text.setVisibility (textVisible);
		}
		if (this.commit != null) {
			this.commit.setVisibility (commitVisible);
		}

	}

	public void reset (MyOccupyValue values, OnClickListener listener) {
		reset (values, listener, VISIBLE, VISIBLE, GONE);

	}

	/**
	 * @param imageResId 图片资源
	 * @param hint       提示性文案
	 * @param commit     操作文案
	 * @return
	 */
	public static MyOccupyValue getOccupyValue (int imageResId, String hint, String commit) {
		MyOccupyValue value = new MyOccupyValue ();
		value.setIntImageResourceId (imageResId);
		value.setStrHint (hint);
		value.setStrCommit (commit);
		return value;
	}

	/**
	 * @param imageResId 图片资源
	 * @param hintId     提示性文案
	 * @param commitId   操作文案
	 * @return
	 */
	public static MyOccupyValue getOccupyValue (int imageResId, int hintId, int commitId) {
		return getOccupyValue (imageResId, StickyHeadersDemoApp.getInstance ().getString (hintId), StickyHeadersDemoApp.getInstance ().getString (commitId));
	}

	static class MyOccupyValue {
		int intImageResourceId;
		String strHint;
		String strCommit;

		public MyOccupyValue () {
		}

		public int getIntImageResourceId () {
			return intImageResourceId;
		}

		public void setIntImageResourceId (int intImageResourceId) {
			this.intImageResourceId = intImageResourceId;
		}

		public String getStrHint () {
			return strHint;
		}

		public void setStrHint (String strHint) {
			this.strHint = strHint;
		}

		public String getStrCommit () {
			return strCommit;
		}

		public void setStrCommit (String strCommit) {
			this.strCommit = strCommit;
		}
	}
}
