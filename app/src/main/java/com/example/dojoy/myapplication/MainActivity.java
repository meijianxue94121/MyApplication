package com.example.dojoy.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dojoy.myapplication.baidu.BaiduActivity;
import com.example.dojoy.myapplication.cardView.CardViewItemActivity;
import com.example.dojoy.myapplication.dialog.DialogActivity;
import com.example.dojoy.myapplication.dialogs.CityPicker_three;
import com.example.dojoy.myapplication.dialogs.DialogUtils;
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
    AlertDialog alertDialog;

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
            case 15:
                //地址弹窗
                alertDialog = DialogUtils.SimpleDialog.SimpleDialog22(null, MainActivity.this, "温馨提示", "aha", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CityPicker_three viewById = (CityPicker_three) alertDialog.findViewById(R.id.citypicker);
                        Toast.makeText(MainActivity.this, viewById.getInfo(), Toast.LENGTH_SHORT).show();

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, "确定", "取消", 0, 1);
                break;
        }
    }

    //

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
        da.add("地址弹窗");
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
