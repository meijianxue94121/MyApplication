package com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.utils.CommonAdapter;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.utils.ViewHolder;
import com.example.dojoy.myapplication.recycleSlide.mcxtzhang.swipedelmenu.view.CstSwipeDelMenu;

import java.util.ArrayList;
import java.util.List;


public class ListViewDelDemoActivity extends AppCompatActivity {
    private static final String TAG = "zxt";
    private ListView mLv;
    private List<SwipeBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slide);
        mLv = (ListView) findViewById(R.id.test);

        initDatas();
        mLv.setAdapter(new CommonAdapter<SwipeBean>(this, mDatas, R.layout./*item_swipe_menu*/item_cst_swipe) {
            @Override
            public void convert(final ViewHolder holder, SwipeBean swipeBean, final int position, View convertView) {
                //((CstSwipeDelMenu)holder.getConvertView()).setIos(false);//这句话关掉IOS阻塞式交互效果
                holder.setText(R.id.content, swipeBean.name);
                holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListViewDelDemoActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });

                holder.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListViewDelDemoActivity.this, "删除:" + position, Toast.LENGTH_SHORT).show();
                        //在ListView里，点击侧滑菜单上的选项时，如果想让擦花菜单同时关闭，调用这句话
                        ((CstSwipeDelMenu) holder.getConvertView()).quickClose();
                        mDatas.remove(position);
                        notifyDataSetChanged();
                    }
                });

            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add(new SwipeBean("" + i));
        }
    }
}
