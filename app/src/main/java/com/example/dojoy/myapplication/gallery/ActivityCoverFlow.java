package com.example.dojoy.myapplication.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dojoy.myapplication.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ActivityCoverFlow extends AppCompatActivity {

    @InjectView(R.id.coverFlow)
    FancyCoverFlow homeCoverFlow;
    HomeCoverAdapter adapter;
    private ArrayList<HomeItem> mFancyCoverFlows;
    private int[] topScroll = new int[]{1, 2, 3, 4, 5,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_cover_flow);
        ButterKnife.inject(this);
        init();


    }

    private void init() {
        mFancyCoverFlows = new ArrayList<>();

        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_play_smart_nor_wanzhineng, false, "", "", topScroll[2]));
        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_challenge_nor_yaotiaozhan, false, "", "", topScroll[5]));
        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_looking_for_venue_nor_zhaochangguan, false, "", "", topScroll[4]));
        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_appointment_coach_nor_yuejiaolian, false, "", "", topScroll[3]));
        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_game_nor_qubisai, false, "", "", topScroll[0]));
        //        mFancyCoverFlows.add(new HomeItem(R.mipmap.home_btn_club_nor_julebu, false, "", "", topScroll[1]));
        adapter = new HomeCoverAdapter(this, mFancyCoverFlows);
        homeCoverFlow.setAdapter(adapter);
        //        adapter.notifyDataSetChanged();
        homeCoverFlow.setUnselectedAlpha(1f);//通明度
        homeCoverFlow.setUnselectedSaturation(1f);//设置选中的饱和度
        homeCoverFlow.setUnselectedScale(0.9f);//设置选中的规模
        homeCoverFlow.setSpacing(0);//设置间距
        homeCoverFlow.setMaxRotation(0);//设置最大旋转
        homeCoverFlow.setScaleDownGravity(0.5f);
        homeCoverFlow.setAnimationDuration(1000);
        homeCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        //        homeCoverFlow.setOnTouchListener();

        final int num = HomeCoverAdapter.MAX / 2 % mFancyCoverFlows.size();
        int selectPosition = HomeCoverAdapter.MAX / 2 - num / 2;

        homeCoverFlow.setSelection(selectPosition);
    }
}
