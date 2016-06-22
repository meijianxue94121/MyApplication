package com.example.dojoy.myapplication.cardView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardViewItemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @InjectView(R.id.careViewRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.cardViewRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    CardViewAdapter adapter;
    @InjectView(R.id.Layout)
    LinearLayout Layout;
    //    @InjectView(R.id.topBar)
//    MyToolBar topbar;
//    @InjectView(R.id.scroll)
//    MyScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        init();
    }

    //初始化数据
    private void init() {
        mRefreshLayout.setColorSchemeResources(ZhUtils.getRefreshColors());
        mRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        adapter = new CardViewAdapter(this);
        adapter.openLoadAnimation();
        adapter.setDuration(500);
        adapter.openLoadMore(10, true);
        adapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(adapter);

        refreshData();
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    /**
     * 刷新方法
     */
    private void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(loadData());
                adapter.openLoadMore(10, true);
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private ArrayList<String> loadData() {
        ArrayList<String> ad = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ad.add("我是CardView第" +
                    (adapter.getItemCount() + i) + "个子项目");
        }
        return ad;
    }

    @Override
    public void onLoadMoreRequested() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (adapter.getItemCount() > 50) {
                    adapter.notifyDataChangedAfterLoadMore(false);
                } else {
                    adapter.notifyDataChangedAfterLoadMore(loadData(), true);
                }
            }
        });

    }


    class CardViewAdapter extends BaseQuickAdapter<String> {

        public CardViewAdapter(Context context) {
            super(context, R.layout.card_item, null);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(R.id.itemText, s);
        }
    }
}
