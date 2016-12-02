package com.example.dojoy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;
import com.jpeng.jptabbar.badgeview.BadgeRelativeLayout;
import com.jpeng.jptabbar.badgeview.Badgeable;
import com.jpeng.jptabbar.badgeview.DragDismissDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JPTabBarAct extends AppCompatActivity {
    @Titles
    private static final String[] mTitles = {"页面一", "页面二", "页面三", "页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @BindView(R.id.tabbar)
    JPTabBar tabbar;
    @BindView(R.id.mmmm)
    BadgeRelativeLayout mmmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jptab_bar);
        ButterKnife.bind(this);
        tabbar.ShowBadge(0, "哈哈");
        mmmm.showTextBadge(5 + "");
        mmmm.setDragDismissDelegage(new DragDismissDelegate() {
            @Override
            public void onDismiss(Badgeable badgeable) {
                Toast.makeText(JPTabBarAct.this, "消失了吧", Toast.LENGTH_SHORT).show();
            }
        });
        mmmm.getBadgeViewHelper().setDragable(true);
    }
}
