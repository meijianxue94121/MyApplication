package com.example.dojoy.myapplication.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.viewpager.adapter.PageAdapter;
import com.example.dojoy.myapplication.viewpager.graph.ApiModule;
import com.example.dojoy.myapplication.viewpager.graph.DouBanService;
import com.example.dojoy.myapplication.viewpager.info.ListDTO;
import com.example.dojoy.myapplication.viewpager.info.SubjectsInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private DouBanService douBanService;
    private ViewPager viewPager;
    private PageAdapter adapter;
    private LoadingDialog loaddingDialog;
    private ArrayList<SubjectsInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainviewpager);
        init();
        LoadData();
    }

    private void init() {
        //获取一个Api实例
        douBanService = ApiModule.of(DouBanService.class);
        loaddingDialog = new LoadingDialog(this);
        //初始化ViewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new PageAdapter(MainActivity.this, list);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    /**
     * 获取豆瓣Api数据
     */
    private void LoadData() {
        loaddingDialog.show();
        douBanService.getRepoData("20").enqueue(new Callback<ListDTO<SubjectsInfo>>() {
            @Override
            public void onResponse(Call<ListDTO<SubjectsInfo>> call, Response<ListDTO<SubjectsInfo>> response) {
                Log.e("onResponse", response.body().toString());
                list.addAll(response.body().getList());
                viewPager.setAdapter(adapter);


                loaddingDialog.cancel();
            }

            @Override
            public void onFailure(Call<ListDTO<SubjectsInfo>> call, Throwable t) {
                Log.e("Throwable", t.getMessage());
                loaddingDialog.cancel();
            }
        });
    }
}
