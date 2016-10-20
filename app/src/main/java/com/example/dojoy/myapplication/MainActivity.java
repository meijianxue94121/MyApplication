package com.example.dojoy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dojoy.myapplication.baidu.BaiduActivity;
import com.example.dojoy.myapplication.cardView.CardViewItemActivity;
import com.example.dojoy.myapplication.dialog.DialogActivity;
import com.example.dojoy.myapplication.gallery.ActivityCoverFlow;
import com.example.dojoy.myapplication.okhttps.OkHttpActivity;
import com.example.dojoy.myapplication.photos.PhotoViewActivity;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.FullDemo.FullDelDemoActivity;
import com.example.dojoy.myapplication.scroll.PullScrollViewZoomView;
import com.example.dojoy.myapplication.scroll.PullZoomListViewActivity;
import com.example.dojoy.myapplication.scroll.recyclerview.PullToZoomRecyclerActivity;
import com.example.dojoy.myapplication.stickHeader.ui.MyStickHearder;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ad;

    @OnClick({R.id.mCardView, R.id.mStickyHeader, R.id.mCoverFlow, R.id.mDialog, R.id.mBaidu,
            R.id.mHeadScroll, R.id.mHeadListView, R.id.mHeadRecycler, R.id.mPhotoView, R.id.mViewPager, R.id.mOkHttps, R.id.mRecyclerSlide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mCardView:
                //CardView。4.4以上会比较漂亮
                startActivity(new Intent(this, CardViewItemActivity.class));
                break;
            case R.id.mDialog:
                //Dialog相关
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.mBaidu:
                //百度地图
                startActivity(new Intent(this, BaiduActivity.class));
                break;
            case R.id.mHeadScroll:
                //头部下拉控件ScrollView
                startActivity(new Intent(this, PullScrollViewZoomView.class));
                break;
            case R.id.mHeadListView:
                //头部下拉控件ListView
                startActivity(new Intent(this, PullZoomListViewActivity.class));
                break;
            case R.id.mHeadRecycler:
                //头部下拉控件Recycler
                startActivity(new Intent(this, PullToZoomRecyclerActivity.class));
                break;
            case R.id.mPhotoView:
                //PhotoView图片展示控件
                startActivity(new Intent(this, PhotoViewActivity.class));
                break;
            case R.id.mViewPager:
                //PhotoView图片展示控件
                startActivity(new Intent(this, com.example.dojoy.myapplication.viewpager.MainActivity.class));
                break;
            case R.id.mCoverFlow:
                //CoverFlow图片展示控件
                startActivity(new Intent(this, ActivityCoverFlow.class));
                break;
            case R.id.mStickyHeader:
                //头部的Recycler版本
                startActivity(new Intent(this, MyStickHearder.class));
                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
                break;
            case R.id.mOkHttps:
                //头部的Recycler版本
                startActivity(new Intent(this, OkHttpActivity.class));
                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
                break;
            case R.id.mRecyclerSlide:
                //头部的Recycler版本
                startActivity(new Intent(this, FullDelDemoActivity.class));
                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
