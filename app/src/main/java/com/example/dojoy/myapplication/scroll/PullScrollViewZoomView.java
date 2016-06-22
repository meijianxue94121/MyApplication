package com.example.dojoy.myapplication.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.dojoy.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PullScrollViewZoomView extends AppCompatActivity {

    @InjectView(R.id.mPullToZoom)
    PullToZoomScrollViewEx mPullToZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_scroll_zoom_view);
        //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.inject(this);
        loadViewForCode();
        //        mPullToZoom.getPullRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.e("zhuwenwu1", "onClick -->");
        //            }
        //        });
        //
        //        mPullToZoom.getPullRootView().findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.e("zhuwenwu2", "onClick -->");
        //            }
        //        });
        //
        //        mPullToZoom.getPullRootView().findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.e("zhuwenwu3", "onClick -->");
        //            }
        //        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (8.0F * (mScreenWidth / 16.0F)));
        mPullToZoom.setHeaderLayoutParams(localObject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scroll_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_normal) {
            mPullToZoom.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            mPullToZoom.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
            //            scrollView.showHeaderView();
            mPullToZoom.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
            //            scrollView.hideHeaderVie˛w();
            mPullToZoom.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
            //            scrollView.setEnableZoom(false);
            mPullToZoom.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
            //            scrollView.setEnableZoom(true);
            mPullToZoom.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.mPullToZoom);
        View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
                View contentView = LayoutInflater.from(this).inflate(R.layout.pull_content_view, null, false);
        //        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
