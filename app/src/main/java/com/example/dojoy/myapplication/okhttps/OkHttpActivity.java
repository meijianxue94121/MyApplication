package com.example.dojoy.myapplication.okhttps;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dojoy.myapplication.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkHttpActivity extends AppCompatActivity implements OkHttpsHelper.ResponCallBack {
    private static final String TAG = "OkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get, R.id.post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get:
                doGet();
                break;
            case R.id.post:
                doPost();
                break;
        }
    }

    private void doPost() {
        //        http://121.41.10.2:20000/app/app/init?v=2200&cityID=179&phoneModel=MI5&deviceType=0
        //        http://121.41.10.2:23000/club/league/list?v=2200&pageNum=1&phoneModel=MI5&pageSize=10
        //        HashMap<String, String> hashMap = new HashMap<>();
        //        hashMap.put("v", "2200");
        //        hashMap.put("cityID", "179");
        //        hashMap.put("phoneModel", "MI5");
        //        hashMap.put("deviceType", "0");
        //        OkHttpsHelper.requestPost(2, "app/init", hashMap, this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin");
        //        hashMap.put("cityID", "179");
        //        hashMap.put("phoneModel", "MI5");
        //        hashMap.put("deviceType", "0");
        OkHttpsHelper.requestPost(2, "http://bracelet.cositea.com:8089/bracelet/", "download_downLoad", hashMap, this);
    }

    private void doGet() {
        // http://bracelet.cositea.com:8089/bracelet/download_downLoad?filename=k18s_20160909_01_V0.04_dongxiang1_T.bin
        //        HashMap<String, String> hashMap = new HashMap<>();
        //        hashMap.put("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin");
        //        OkHttpsHelper.requestGet(10, "http://bracelet.cositea.com:8089/bracelet/", "download_downLoad", hashMap, this);
        String fileSrc = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "a.bin";
        OkHttpUtils.get().url("http://bracelet.cositea.com:8089/bracelet/download_downLoad")
                .addParams("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin").build().execute(new FileCallBack(fileSrc, fileName) {
            @Override
            public void inProgress(float progress) {

            }

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(File response) {
                Log.e("TAG", response.length() + "");
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(response);

                    int length = fis.available();

                    byte[] buffer = new byte[length];
                    fis.read(buffer);
                    Log.e("TAG", buffer.length + "数据：");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < buffer.length; i++) {
                        builder.append(Integer.toHexString(buffer[i] & 0xFF) + "    ");
                        if (i % 5 == 4)
                            builder.append("\n");
                    }
                    Log.e("TAG", builder.toString());


                    fis.close();
                } catch (FileNotFoundException e) {


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onOk(int actionId, String s) {
        byte[] utf8Decode = null;
        try {
            utf8Decode = s.getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("utf8:长度：" + utf8Decode.length + "数据：");
        //        for (int i = 0; i < utf8Decode.length; i++) {
        //            System.out.print(Integer.toHexString(utf8Decode[i] & 0xFF));
        //            System.out.print("    ");
        //            if (i % 5 == 0)
        //                System.out.println();
        //        }
        //        System.out.println();

    }

    @Override
    public void onError(int actionId, String s) {
        Log.e(TAG, "请求ID:" + actionId + "内容：" + s);
    }
}
