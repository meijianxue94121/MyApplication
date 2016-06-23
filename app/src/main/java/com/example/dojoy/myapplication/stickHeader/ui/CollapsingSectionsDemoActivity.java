package com.example.dojoy.myapplication.stickHeader.ui;

import android.os.Bundle;

import com.example.dojoy.myapplication.stickHeader.StickyHeaderLayoutManager;
import com.example.dojoy.myapplication.stickHeader.adapters.SimpleDemoAdapter;


/**
 * Created by shamyl on 6/7/16.
 */
public class CollapsingSectionsDemoActivity extends DemoActivity {

	SimpleDemoAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new SimpleDemoAdapter(100, 5, false, true, false);

		recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
		recyclerView.setAdapter(adapter);
	}
}
