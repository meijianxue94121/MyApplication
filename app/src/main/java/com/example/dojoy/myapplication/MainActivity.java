package com.example.dojoy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dojoy.myapplication.baidu.BaiduActivity;
import com.example.dojoy.myapplication.cardView.CardViewItemActivity;
import com.example.dojoy.myapplication.dialog.DialogActivity;
import com.example.dojoy.myapplication.scroll.PullScrollViewZoomView;
import com.example.dojoy.myapplication.scroll.PullZoomListViewActivity;
import com.example.dojoy.myapplication.scroll.recyclerview.PullToZoomRecyclerActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ad;

    @OnClick({R.id.mCardView, R.id.mDialog, R.id.mBaidu, R.id.mHeadScroll,R.id.mHeadListView,R.id.mHeadRecycler})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mCardView:
                startActivity(new Intent(this, CardViewItemActivity.class));
                break;
            case R.id.mDialog:

                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.mBaidu:

                startActivity(new Intent(this, BaiduActivity.class));
                break;
            case R.id.mHeadScroll:

                startActivity(new Intent(this, PullScrollViewZoomView.class));
                break;
            case R.id.mHeadListView:

                startActivity(new Intent(this, PullZoomListViewActivity.class));
                break;
            case R.id.mHeadRecycler:

                startActivity(new Intent(this, PullToZoomRecyclerActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
