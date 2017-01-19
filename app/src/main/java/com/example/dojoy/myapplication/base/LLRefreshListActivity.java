package com.example.dojoy.myapplication.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dojoy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 1.包含基础toolBar
 * 2.没有筛选体检，下面单纯一个列表Recycler
 * 3.默认是竖直LIst单项
 * 4.刷新actionId=1,
 * 5.加载actionId=2
 */
public abstract class LLRefreshListActivity<T> extends LLNetWorkBaseAct implements SwipeRefreshLayout.OnRefreshListener {
	@BindView (R.id.recyclerView)
	RecyclerView recyclerView;
	@BindView (R.id.srlRefresh)
	SwipeRefreshLayout refresh;
	protected BaseQuickAdapter adapter;
	protected int pageSize = 10;
	protected int pageIndex = 1;
	protected boolean hasData = true;
	protected boolean isLoading = false;
	protected Class<T> classT;
	public String servlet = "";
	protected int emptyImageId = R.mipmap.no;
	protected String emptyString = "暂无数据";
	public boolean needNext = true;

	/**
	 * 初始化操作
	 */
	public void init () {
		refresh.setOnRefreshListener (this);
		initRecycler ();
	}

	public void initAdapter (int firstSpace, int space) {
		if (adapter != null) {
			adapter.openLoadMore (ExtraUtils.LoadNum, needNext);
			recyclerView.addItemDecoration (new VerticalItemDe (this, firstSpace, space));
			if (needNext) {
				adapter.setOnLoadMoreListener (new BaseQuickAdapter.RequestLoadMoreListener () {
					@Override
					public void onLoadMoreRequested () {
						recyclerView.post (new Runnable () {
							@Override
							public void run () {
								if (hasData && !isLoading) {
									loadData ();
								}
							}
						});
					}
				});
			}
			recyclerView.setAdapter (adapter);
		}
	}

	public void initData () {
		isLoading = true;
		pageIndex = 1;
		hasData = true;
		adapter.openLoadMore (ExtraUtils.LoadNum, needNext);
		HashMap<String, String> pa = getRequestMap ();
		ArrayList<String> us = getUrls ();
		if (okHttpActionHelper != null) {
			okHttpActionHelper.get (1, servlet, us, pa, this);
		}
	}

	public void loadData () {
		isLoading = true;
		pageIndex++;
		final HashMap<String, String> pa = getRequestMap ();
		ArrayList<String> us = getUrls ();
		if (okHttpActionHelper != null) {
			okHttpActionHelper.get (2, servlet, us, pa, this);
		}
	}

	protected abstract HashMap<String, String> getRequestMap ();

	protected abstract ArrayList<String> getUrls ();

	/**
	 * 初始化RecyclerView
	 */
	private void initRecycler () {
		recyclerView.setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
	}

	@Override
	public void onRefresh () {
		initData ();
	}

	@Override
	protected void Success (int actionId, JSONObject object) {
		int status = object.getInteger ("status");
		String message = object.getString ("message");
		switch (actionId) {
			case 1: {
				JSONArray infobean = object.getJSONArray ("infobean");
				if (infobean != null) {
					List<T> cards = JSON.parseArray (infobean.toJSONString (), classT);
					if (cards == null || cards.size () <= 0) {
						dataEmpty (MyOccupying.getOccupyValue (emptyImageId, emptyString, ""));
						showCustomerView (false);
						hasData = false;
					} else if (cards.size () < pageSize) {
						showCustomerView (true);
						hasData = false;
						adapter.setNewData (cards);
					} else {
						showCustomerView (true);
						hasData = true;
						adapter.setNewData (cards);
					}
					adapter.openLoadMore (ExtraUtils.LoadNum, hasData);
				}
			}
			break;
			case 2: {
				JSONArray infobean = object.getJSONArray ("infobean");
				if (infobean != null) {
					List<T> ts = JSON.parseArray (infobean.toJSONString (), classT);
					if (ts.size () < pageSize) {
						hasData = false;
						adapter.notifyDataChangedAfterLoadMore (ts, false);
					} else {
						hasData = true;
						adapter.notifyDataChangedAfterLoadMore (ts, true);
					}
				}
			}
			break;
		}
	}

	@Override
	protected void Exception (int actionId, String exception) {
	}

	@Override
	protected void Failed (int actionId, int exception) {
	}


	@Override
	protected void onCreate (Bundle savedInstanceState) {

		beforeCreate ();
		super.onCreate (savedInstanceState);
		ButterKnife.bind (this);
		afterCreate ();

		init ();
	}

	@Override
	public Integer setCustomerViewId () {
		return R.layout.liu_activity_refresh_list;
	}

	@Override
	public MyToolBar setToolBar () {
		return getToolBar ();
	}

	@Override
	public void otherInit () {
		requestOther ();
	}

	public void requestOther () {
		isLoading = false;
		refresh.setRefreshing (false);
	}

	@Override
	public void reloadData () {
		initData ();
	}

	/**
	 * 绑定控件后数据处理与操作
	 * adapter = new CardListAdapter (this);
	 * initAdapter (10, 1);
	 * adapter.setOnRecyclerViewItemClickListener (new BaseQuickAdapter.OnRecyclerViewItemClickListener () {
	 *
	 * @Override public void onItemClick (View view, int i) {
	 * }
	 * });
	 * toolBar.setTitle ("会员卡");
	 * initData ();
	 */
	public abstract void afterCreate ();

	/**
	 * 初始化部分基础数据
	 * servlet = "card";
	 * emptyImageId = R.mipmap.ic_launcher;
	 * emptyString = "暂无数据";
	 * classT = Card.class;
	 * needNext = false;
	 * venueId = 3604;
	 * okHttpActionHelper = new OkHttpActionHelper () {
	 */
	public abstract void beforeCreate ();
}