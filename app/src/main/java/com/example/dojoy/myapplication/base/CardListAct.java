package com.example.dojoy.myapplication.base;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dojoy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

import sdk.base.OkHttpActionHelper;

public class CardListAct extends LLRefreshListActivity<Card> {
	public int venueId = -1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate (savedInstanceState);


	}

	@Override
	public void afterCreate () {
		adapter = new CardListAdapter (this);
		initAdapter (10, 1);
		adapter.setOnRecyclerViewItemClickListener (new BaseQuickAdapter.OnRecyclerViewItemClickListener () {
			@Override
			public void onItemClick (View view, int i) {
			}
		});
		toolBar.setTitle ("会员卡");
		initData ();
	}

	@Override
	public void beforeCreate () {
		servlet = "card";
		emptyImageId = R.mipmap.ic_launcher;
		emptyString = "暂无数据";
		classT = Card.class;
		needNext = false;
		venueId = 3604;
		okHttpActionHelper = new OkHttpActionHelper () {
			@Override
			public String getDomainAddress () {
				return "https://napit.51dojoy.com/";
			}
		};
	}


	@Override
	protected HashMap<String, String> getRequestMap () {
		//		https://napit.51dojoy.com/card/3604?v=2260&userID=40579&phoneModel=MI5&venueID=3604&sessionToken=e1427435-4ccb-4e79-8996-d09bb6e46ef4
		HashMap<String, String> map = new HashMap<> ();
		map.put ("userID", 40579 + "");
		map.put ("sessionToken", "e1427435-4ccb-4e79-8996-d09bb6e46ef4");
		map.put ("v", 2260 + "");
		return map;
	}

	@Override
	protected ArrayList<String> getUrls () {
		ArrayList<String> urls = new ArrayList<> ();
		urls.add (venueId + "");
		return urls;
	}


	@Override
	protected void onDestroy () {
		super.onDestroy ();
	}


}
