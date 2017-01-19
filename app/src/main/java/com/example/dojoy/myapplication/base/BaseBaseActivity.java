package com.example.dojoy.myapplication.base;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.example.dojoy.myapplication.R;

public class BaseBaseActivity extends LLNetWorkBaseAct {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
	}

	@Override
	public Integer setCustomerViewId () {
		return R.layout.activity_base;
	}

	@Override
	public MyToolBar setToolBar () {
		MyToolBar toolBar = new MyToolBar (BaseBaseActivity.this, "返回", "测试Base", "没啥");
		return toolBar;
	}


	@Override
	protected void Success (int actionId, JSONObject object) {

	}

	@Override
	protected void Exception (int actionId, String exception) {

	}

	@Override
	protected void Failed (int actionId, int exception) {

	}

	@Override
	public void reloadData () {

	}

	@Override
	public void otherInit () {

	}
}
