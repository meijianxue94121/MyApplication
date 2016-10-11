package com.example.dojoy.myapplication.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.example.dojoy.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullZoomListViewActivity extends AppCompatActivity {

    @BindView(R.id.mList)
    PullToZoomListViewEx mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_zoom_list_view);
        ButterKnife.bind(this);

        loadViewForCode();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        mList.setAdapter(new ArrayAdapter<String>(PullZoomListViewActivity.this, android.R.layout.simple_list_item_1, adapterData));

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        mList.setHeaderLayoutParams(localObject);
    }
    private void loadViewForCode() {
        View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        //        View contentView = LayoutInflater.from(this).inflate(R.layout.profile_content_view, null, false);
        //        scrollView.setHeaderView(headView);
        mList.setZoomView(zoomView);
        //        scrollView.setScrollContentView(contentView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_normal) {
            mList.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            mList.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
            mList.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
            mList.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
            mList.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
            mList.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

