package com.example.dojoy.myapplication.stickHeader.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.dojoy.myapplication.stickHeader.adapters.SimpleDemoAdapter;


/**
 * Created by shamyl on 4/26/16.
 */
public class SectioningAdapterDemoActivity extends DemoActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(new SimpleDemoAdapter(5, 5, false, false, false));
	}
}
