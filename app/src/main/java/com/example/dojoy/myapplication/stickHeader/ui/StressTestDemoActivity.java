package com.example.dojoy.myapplication.stickHeader.ui;

import android.os.Bundle;

import com.example.dojoy.myapplication.stickHeader.StickyHeaderLayoutManager;
import com.example.dojoy.myapplication.stickHeader.adapters.SimpleDemoAdapter;


/**
 * Created by shamyl on 6/5/16.
 */
public class StressTestDemoActivity extends DemoActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        recyclerView.setAdapter(new SimpleDemoAdapter(1000, 5, false, false, false));
    }
}
