package com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.FullDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.SwipeBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 介绍：完整的删除Demo
 * 作者：zhangxutong
 * 邮箱：zhangxutong@imcoming.com
 * 时间： 2016/9/12.
 */

public class FullDelDemoActivity extends Activity {
    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private FullDelDemoAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<SwipeBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_del_demo);
        mRv = (RecyclerView) findViewById(R.id.rv);

        initDatas();
        mAdapter = new FullDelDemoAdapter(this, mDatas);
        mAdapter.setOnDelListener(new FullDelDemoAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                Toast.makeText(FullDelDemoActivity.this, "删除:" + pos, Toast.LENGTH_SHORT).show();
                mDatas.remove(pos);
                mAdapter.notifyItemRemoved(pos);//推荐用这个
                //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                //mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTop(int pos) {
                SwipeBean swipeBean = mDatas.get(pos);
                mDatas.remove(swipeBean);
                mAdapter.notifyItemInserted(0);
                mDatas.add(0, swipeBean);
                mAdapter.notifyItemRemoved(pos + 1);
                if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
                    mRv.scrollToPosition(0);
                }
                //notifyItemRangeChanged(0,holder.getAdapterPosition()+1);
            }
        });
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add(new SwipeBean("" + i));
        }
    }
}
