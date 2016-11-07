package com.example.dojoy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dojoy.myapplication.baidu.BaiduActivity;
import com.example.dojoy.myapplication.cardView.CardViewItemActivity;
import com.example.dojoy.myapplication.dialog.DialogActivity;
import com.example.dojoy.myapplication.gallery.ActivityCoverFlow;
import com.example.dojoy.myapplication.helputils.ZhUtils;
import com.example.dojoy.myapplication.okhttps.OkHttpActivity;
import com.example.dojoy.myapplication.photos.PhotoViewActivity;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.FullDemo.FullDelDemoActivity;
import com.example.dojoy.myapplication.scroll.PullScrollViewZoomView;
import com.example.dojoy.myapplication.scroll.PullZoomListViewActivity;
import com.example.dojoy.myapplication.scroll.recyclerview.PullToZoomRecyclerActivity;
import com.example.dojoy.myapplication.stickHeader.ui.MyStickHearder;
import com.example.dojoy.myapplication.superTextView.MainAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    ItemAdapter adapter;
    //    @OnClick({R.id.mCardView, R.id.mStickyHeader, R.id.mCoverFlow, R.id.mDialog, R.id.mBaidu,
    //            R.id.mHeadScroll, R.id.mSuperText, R.id.mHeadListView, R.id.mHeadRecycler, R.id.mPhotoView, R.id.mViewPager, R.id.mOkHttps, R.id.mRecyclerSlide})
    //    public void onClick(View view) {
    //        switch (view.getId()) {
    //            case R.id.mCardView:
    //                //CardView。4.4以上会比较漂亮
    //                startActivity(new Intent(this, CardViewItemActivity.class));
    //                break;
    //            case R.id.mDialog:
    //                //Dialog相关
    //                startActivity(new Intent(this, DialogActivity.class));
    //                break;
    //            case R.id.mBaidu:
    //                //百度地图
    //                startActivity(new Intent(this, BaiduActivity.class));
    //                break;
    //            case R.id.mHeadScroll:
    //                //头部下拉控件ScrollView
    //                startActivity(new Intent(this, PullScrollViewZoomView.class));
    //                break;
    //            case R.id.mHeadListView:
    //                //头部下拉控件ListView
    //                startActivity(new Intent(this, PullZoomListViewActivity.class));
    //                break;
    //            case R.id.mHeadRecycler:
    //                //头部下拉控件Recycler
    //                startActivity(new Intent(this, PullToZoomRecyclerActivity.class));
    //                break;
    //            case R.id.mPhotoView:
    //                //PhotoView图片展示控件
    //                startActivity(new Intent(this, PhotoViewActivity.class));
    //                break;
    //            case R.id.mViewPager:
    //                //PhotoView图片展示控件
    //                startActivity(new Intent(this, com.example.dojoy.myapplication.viewpager.MainActivity.class));
    //                break;
    //            case R.id.mCoverFlow:
    //                //CoverFlow图片展示控件
    //                startActivity(new Intent(this, ActivityCoverFlow.class));
    //                break;
    //            case R.id.mStickyHeader:
    //                //头部的Recycler版本
    //                startActivity(new Intent(this, MyStickHearder.class));
    //                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
    //                break;
    //            case R.id.mOkHttps:
    //                //头部的Recycler版本
    //                startActivity(new Intent(this, OkHttpActivity.class));
    //                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
    //                break;
    //            case R.id.mRecyclerSlide:
    //                //头部的Recycler版本
    //                startActivity(new Intent(this, FullDelDemoActivity.class));
    //                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
    //                break;
    //            case R.id.mSuperText:
    //                //头部的Recycler版本
    //                startActivity(new Intent(this, MainAct.class));
    //                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
    //                break;
    //        }
    //    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.addItemDecoration(new ItemDec());
        adapter = new ItemAdapter(this);
        recycler.setAdapter(adapter);
        adapter.setNewData(getDatas());
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                onClick(i);
            }
        });
    }

    /**
     * 按位置索取点击事件
     *
     * @param position
     */
    private void onClick(int position) {
        switch (position) {
            case 0:
                //                CardView。4.4 以上会比较漂亮
                startActivity(new Intent(this, CardViewItemActivity.class));
                break;
            case 1:
                //Dialog相关
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case 2:
                //百度地图
                startActivity(new Intent(this, BaiduActivity.class));
                break;
            case 3:
                //头部下拉控件ScrollView
                startActivity(new Intent(this, PullScrollViewZoomView.class));
                break;
            case 4:
                //头部下拉控件ListView
                startActivity(new Intent(this, PullZoomListViewActivity.class));
                break;
            case 5:
                //头部下拉控件Recycler
                startActivity(new Intent(this, PullToZoomRecyclerActivity.class));
                break;
            case 6:
                //PhotoView图片展示控件
                startActivity(new Intent(this, PhotoViewActivity.class));

                break;
            case 7:
                ZhUtils.ToastUtils.MyToast(this, "无效测试先不管了");
                break;
            case 8:
                //CoverFlow图片展示控件
                startActivity(new Intent(this, ActivityCoverFlow.class));
                break;
            case 9:
                //头部的Recycler版本
                startActivity(new Intent(this, MyStickHearder.class));
                //                startActivity(new Intent(this, com.example.dojoy.myapplication.stickHeader.ui.MainActivity.class));
                break;
            case 10:
                //OKhttps
                startActivity(new Intent(this, OkHttpActivity.class));
                break;
            case 11:
                //头部的Recycler版本
                startActivity(new Intent(this, FullDelDemoActivity.class));
                break;
            case 12:
                //超级TextView
                startActivity(new Intent(this, MainAct.class));
                break;
            case 13:
                //绑定一个桌面组件
                ZhUtils.ToastUtils.MyToast(this, "无效测试先不管了");
                break;
            case 14:
                //绑定桌面控件
                ZhUtils.ToastUtils.MyToast(this, "无效测试先不管了");
                break;
        }
    }

    //    /**
    //     * 绑定一个appWidgetId到一个provider
    //     *
    //     * @param appWidgetId 桌面插件实例id
    //     * @param provider    桌面插件应用<包名，provider类名>
    //     */
    //    public void bindAppWidgetId(int appWidgetId, ComponentName provider) {
    //        MainActivity.this.enforceCallingPermission(android.Manifest.permission.BIND_APPWIDGET,
    //                "bindGagetId appWidgetId=" + appWidgetId + " provider=" + provider);
    //
    //        synchronized (mAppWidgetIds) {
    //            //根据桌面插件实例id找到桌面插件AppWidgetId对象，AppWidgetId对象在分配桌面插件实例id时生成的
    //            AppWidgetId id = lookupAppWidgetIdLocked(appWidgetId);
    //
    //            if (id == null) {
    //                throw new IllegalArgumentException("bad appWidgetId");
    //            }
    //            //id.provider未设置目的是保证该桌面插件实例id是未绑定到桌面插件应用的
    //            if (id.provider != null) {
    //                throw new IllegalArgumentException("appWidgetId " + appWidgetId + " already bound to "
    //                        + id.provider.info.provider);
    //            }
    //            //根据桌面插件应用包名，provider类名，找到桌面插件应用provider实例，一个Provider对象代表一个桌面插件应用
    //            Provider p = lookupProviderLocked(provider);
    //            if (p == null) {
    //                throw new IllegalArgumentException("not a appwidget provider: " + provider);
    //            }
    //            if (p.zombie) {
    //                throw new IllegalArgumentException("can't bind to a 3rd party provider in"
    //                        + " safe mode: " + provider);
    //            }
    //            //桌面插件实例与桌面插件应用相互关联
    //            id.provider = p;
    //            p.instances.add(id);
    //            int instancesSize = p.instances.size();
    //            if (instancesSize == 1) {
    //                //第一次添加系统插件实例，发送ACTION_APPWIDGET_ENABLED消息,让桌面插件应用做好准备
    //                sendEnableIntentLocked(p);
    //            }
    //
    //            // send an update now -- We need this update now, and just for this appWidgetId.
    //            // It's less critical when the next one happens, so when we schdule the next one,
    //            // we add updatePeriodMillis to its start time.  That time will have some slop,
    //            // but that's okay.
    //            //发送更新系统插件消息ACTION_APPWIDGET_UPDATE
    //            sendUpdateIntentLocked(p, new int[]{appWidgetId});
    //            //注册定时更新消息ACTION_APPWIDGET_UPDATE广播,如果没有注册的话.
    //            registerForBroadcastsLocked(p, getAppWidgetIds(p));
    //            //保存数据到持久化文件
    //            saveStateLocked();
    //        }
    //    }

    /**
     * 获取数据讲述每个按钮相关的功能
     *
     * @return
     */
    private List<String> getDatas() {
        ArrayList<String> da = new ArrayList<>();
        da.add("CardView");
        da.add("Dialog");
        da.add("BDMap");
        da.add("PullScroll");
        da.add("PullListView");
        da.add("PullRecycler");
        da.add("PhotoView1");
        da.add("PhotoView2");
        da.add("CoverFlow");
        da.add("HeadRecycler");
        da.add("OKhttps");
        da.add("FullDelRecycler");
        da.add("SuperTextView");
        da.add("桌面快捷0");
        da.add("桌面快捷1");
        return da;
    }


    class ItemDec extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            //            super.getItemOffsets(outRect, itemPosition, parent);
            outRect.set(10, 10, 10, 10);
        }
    }

    class ItemAdapter extends BaseQuickAdapter<String> {

        public ItemAdapter(Context context) {
            super(context, R.layout.main_item, null);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(R.id.mItem, s);
        }
    }
}
