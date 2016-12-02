package com.a51dojoy.www.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.a51dojoy.www.myapplication.anno.NorIcons;
import com.a51dojoy.www.myapplication.anno.SeleIcons;
import com.a51dojoy.www.myapplication.anno.Titles;
import com.a51dojoy.www.myapplication.badgeview.BadgeRelativeLayout;
import com.a51dojoy.www.myapplication.badgeview.Badgeable;
import com.a51dojoy.www.myapplication.badgeview.DragDismissDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Titles
    private static final String[] mTitles = {"页面一", "页面二", "页面三", "页面四"};

    @SeleIcons
    private static final int[] mSeleIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @NorIcons
    private static final int[] mNormalIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @BindView(R.id.mmmm)
    BadgeRelativeLayout mmmm;
    @BindView(R.id.tabbar)
    JPTabBar tabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mmmm.showTextBadge(5 + "");
        mmmm.setDragDismissDelegage(new DragDismissDelegate() {
            @Override
            public void onDismiss(Badgeable badgeable) {
                Toast.makeText(MainActivity.this, "消失了吧", Toast.LENGTH_SHORT).show();
            }
        });
        mmmm.getBadgeViewHelper().setDragable(true);
        tabbar.ShowBadge(0, 50);
    }
}
