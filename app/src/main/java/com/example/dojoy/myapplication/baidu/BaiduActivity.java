package com.example.dojoy.myapplication.baidu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.dojoy.myapplication.R;

import java.net.URISyntaxException;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**参考网址
 * http://my.oschina.net/u/1270405/blog/309774
 * http://developer.baidu.com/map/uri-intro.htm#idmykey12
 *
 */
public class BaiduActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.daohang)
    public void onClick() {
        startNeagver();
    }

    public void startNeagver() {
        //调起百度地图客户端
        //        Intent intent = null;
        //        try {
        //            intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
        //            if (isInstallByread("com.baidu.BaiduMap")) {
        //                startActivity(intent); //启动调用
        //                Log.e("GasStation", "百度地图客户端已经安装");
        //            } else {
        //                Log.e("GasStation", "没有安装百度地图客户端");
        //            }
        //        } catch (URISyntaxException e) {
        //            e.printStackTrace();
        //        }
        Intent intent = null;
//        导航Intent:
//        intent:
        //map/direction?origin=latlng:30.314466,120.070603|name:起点&destination=latlng:30.297223,120.17704|name:
        // 终点&mode=driving&referer=Autohome#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end
        try {// 如果有安装百度地图 就启动百度地图
            StringBuffer sbs = new StringBuffer();
            sbs.append("intent://map/direction?origin=latlng:")
                    // 我的位置
                    .append(30.314466 + "")
                    .append(",")
                    .append(120.070603 + "")
                    .append("|name:")
                    .append("起点")
                    // 去的位置
                    .append("&destination=latlng:")
                    .append(30.297223 + "") // 经度
                    .append(",")
                    .append(120.17704 + "")// 纬度
                    .append("|name:")
                    .append("终点")
                    // 城市
                    .append("&mode=driving")
                    //                    .append(App.area_name)
                    .append("&referer=Autohome#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            try {
                intent = Intent.getIntent(sbs.toString());
                Log.e("TAG", "导航Intent:" + sbs.toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        } catch (Exception e) {// 没有百度地图则弹出网页端
            Toast.makeText(this, "没安装百度地图", Toast.LENGTH_SHORT).show();

            //            StringBuffer sb = new StringBuffer();
            //            sb.append("http://api.map.baidu.com/direction?origin=latlng:")
            //                    // 我的位置
            //                    .append(MyApplication.getInstance().locInfo.lat)
            //                    .append(",")
            //                    .append(MyApplication.getInstance().locInfo.lon)
            //                    .append("|name:")
            //                    .append("起点")
            //                    // 去的位置
            //                    .append("&destination=latlng:")
            //                    .append(gymnasiumInfo.latitude) // 经度
            //                    .append(",")
            //                    .append(gymnasiumInfo.longitude)// 纬度
            //                    .append("|name:")
            //                    .append("终点")
            //                    .append("&mode=driving")
            //                    //                    // 城市
            //                    //                    .append("&mode=driving®ion=").append(App.area_name)
            //                    .append("&output=html");
            //            Uri uri = Uri.parse(sb.toString());
            //            intent = new Intent(Intent.ACTION_VIEW, uri);
            //            startActivity(intent);
        }
    }
}
