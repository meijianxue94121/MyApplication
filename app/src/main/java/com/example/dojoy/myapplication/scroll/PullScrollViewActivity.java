package com.example.dojoy.myapplication.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.dojoy.myapplication.R;

public class PullScrollViewActivity extends AppCompatActivity {
    private ParallaxListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.pull_ui);
        mListView = (ParallaxListView) findViewById(R.id.lv_content);
        View header = View.inflate(this, R.layout.listview_head, null);
        mListView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new String[]{"腾讯", "百度",
                "潭州学院", "360", "阿里巴巴", "蓝翔"});
        //获取头部中的图片控件
        ImageView iv = (ImageView) header
                .findViewById(R.id.layout_header_iamge);
        mListView.setParallaxImageView(iv);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // 当界面显示出来的时候回调
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 当获得焦点
            mListView.setViewsBounds();

        }
    }
}
