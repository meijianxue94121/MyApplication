package com.example.dojoy.myapplication.stickHeader;

import android.app.Application;

import com.example.dojoy.myapplication.stickHeader.api.RandomUserLoader;


/**
 * Created by shamyl on 4/22/16.
 */
public class StickyHeadersDemoApp extends Application {

	RandomUserLoader randomUserLoader;

	@Override
	public void onCreate() {
		super.onCreate();
		randomUserLoader = new RandomUserLoader();
	}

	public RandomUserLoader getRandomUserLoader() {
		return randomUserLoader;
	}
}
