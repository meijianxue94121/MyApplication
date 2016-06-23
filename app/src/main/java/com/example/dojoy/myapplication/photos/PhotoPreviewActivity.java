package com.example.dojoy.myapplication.photos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dojoy.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.kareluo.intensify.image.IntensifyImageView;

public class PhotoPreviewActivity extends AppCompatActivity {

    @InjectView(R.id.preview)
    IntensifyImageView preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        ButterKnife.inject(this);

        preview.setImage("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");

        // 通过流设置
//        preview.setImage(InputStream inputStream);

        // 通过文件设置
//        preview.setImage(File file);

        // 通过文件路径设置
//        preview.setImage(String path);
    }

    @OnClick(R.id.preview)
    public void onClick() {
    }
}
