package com.example.dojoy.myapplication.photos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.dojoy.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hadcn.davinci.DaVinci;

/**參考文獻
 * https://github.com/bm-x/PhotoView
 */
public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.mPhotoView)
    PhotoView mPhotoView;
    @BindView(R.id.mPhotoView2)
    PhotoView mPhotoView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        mPhotoView.enable();
        mPhotoView.setAnimaDuring(500);
        // 获取/设置 最大缩放倍数
        mPhotoView.setMaxScale(2);
        // 设置动画的插入器
        mPhotoView.setInterpolator(new LinearInterpolator());
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        mPhotoView2.enable();
        mPhotoView2.setAnimaDuring(500);
        // 获取/设置 最大缩放倍数
        mPhotoView2.setMaxScale(2);
        // 设置动画的插入器
        mPhotoView2.setInterpolator(new LinearInterpolator());
        mPhotoView2.setScaleType(ImageView.ScaleType.CENTER_CROP);


        Glide
                .with(this)
                .load("http://7xlkhg.com2.z0.glb.qiniucdn.com/qbi_cry.gif")
                .into(mPhotoView);
        DaVinci
                .with(this)
                .getImageLoader()
                .load("http://7xlkhg.com2.z0.glb.qiniucdn.com/qbi_cry.gif")
                .into(mPhotoView2,R.mipmap.ic_launcher,R.mipmap.test);
    }

    @OnClick(R.id.mPhotoView)
    public void onClick() {
        finish();
    }
}
