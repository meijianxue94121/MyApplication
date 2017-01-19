package com.example.dojoy.myapplication.okhttps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dojoy.myapplication.R;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sdk.base.OkHttpActionHelper;

public class OkHttpActivity extends AppCompatActivity implements sdk.base.OnActionListener {
	private static final String TAG = "OkHttpActivity";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_ok_http);
		ButterKnife.bind (this);
	}

	@OnClick ({R.id.get, R.id.post, R.id.put, R.id.del, R.id.patch})
	public void onClick (View view) {
		switch (view.getId ()) {
			case R.id.get:
				doGet ();
				break;
			case R.id.post:
				doPost ();
				break;
			case R.id.put:
				doPut ();
				break;
			case R.id.del:
				doDel ();
				break;
			case R.id.patch:
				doPatch ();
				break;
		}
	}

	private void doPatch () {
		HashMap<String, String> pa = new HashMap<> ();
		pa.put ("demoID", "1");
		pa.put ("demoIDInt", "1");
		pa.put ("demoIDLong", "1");
		pa.put ("demoName", "1");
		//        OkHttpActionHelper.request(OkHttpActionHelper.PATCH, 500, "http://192.168.116.253:8080/", "demo", pa, this);

	}

	private void doDel () {
		//        http://192.168.116.253:8080/demo/4
		HashMap<String, String> pa = new HashMap<> ();
		pa.put ("demoID", "1");
		//        OkHttpActionHelper.request(OkHttpActionHelper.DEL, 400, "http://192.168.116.253:8080/", "demo", pa, this);
	}

	private void doPut () {
		//        http://192.168.116.253:8080/demo?demoID=1&demoIDInt=1&demoIDLong=1&demoName=1


		HashMap<String, String> pa = new HashMap<> ();
		pa.put ("demoID", "1");
		pa.put ("demoIDInt", "1");
		pa.put ("demoIDLong", "1");
		pa.put ("demoName", "1");
		//        OkHttpActionHelper.request(OkHttpActionHelper.PUT, 300, "http://192.168.116.253:8080/", "demo", pa, this);

	}

	private void doPost () {
		HashMap<String, String> pa = new HashMap<> ();
		pa.put ("demoID", "1");
		pa.put ("demoIDInt", "1");
		pa.put ("demoIDLong", "1");
		pa.put ("demoName", "1");
		//        OkHttpActionHelper.request(OkHttpActionHelper.POST, 200, "http://192.168.116.253:8080/", "demo", pa, this);


		//        //        http://121.41.10.2:20000/app/app/init?v=2200&cityID=179&phoneModel=MI5&deviceType=0
		//        //        http://121.41.10.2:23000/club/league/list?v=2200&pageNum=1&phoneModel=MI5&pageSize=10
		//        //        HashMap<String, String> hashMap = new HashMap<>();
		//        //        hashMap.put("v", "2200");
		//        //        hashMap.put("cityID", "179");
		//        //        hashMap.put("phoneModel", "MI5");
		//        //        hashMap.put("deviceType", "0");
		//        //        OkHttpsHelper.requestPost(2, "app/init", hashMap, this);
		//        HashMap<String, String> hashMap = new HashMap<>();
		//        hashMap.put("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin");
		//        //        hashMap.put("cityID", "179");
		//        //        hashMap.put("phoneModel", "MI5");
		//        //        hashMap.put("deviceType", "0");
		//        OkHttpsHelper.requestPost(2, "http://bracelet.cositea.com:8089/bracelet/", "download_downLoad", hashMap, this);
	}

	private void doGet () {
		//        http://192.168.116.253:8080/demo
		//        http://192.168.116.24:8080/match/1
		OkHttpActionHelper.request (OkHttpActionHelper.GET, 100, "https://kyfw.12306.cn/otn/", "", null, null, this);

		//        HashMap<String, String> pa = new HashMap<>();
		//        pa.put("userName", "jj%");
		//        pa.put("v", "2200");
		//        pa.put("idCardNo", "341203199212262812");
		//        pa.put("phoneModel", "MI4LTE");
		//        pa.put("tel", "18815276435");
		//        pa.put("userID", "39642");
		//        pa.put("userContactID", "10110");
		//        pa.put("sessionToken", "2094cbce-b259-43b7-ad19-91548e0c3e5f");
		//        OkhttpParam okhttpParam = new OkhttpParam();
		//        okhttpParam.setStringParams(pa);
		//        OkhttpUtils.sendRequest(1, 1, "http://121.41.10.2:23000/club/userContact/update", okhttpParam, this);

		// http://bracelet.cositea.com:8089/bracelet/download_downLoad?filename=k18s_20160909_01_V0.04_dongxiang1_T.bin
		//        HashMap<String, String> hashMap = new HashMap<>();
		//        hashMap.put("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin");
		//        OkHttpsHelper.requestGet(10, "http://bracelet.cositea.com:8089/bracelet/", "download_downLoad", hashMap, this);
		//        String fileSrc = Environment.getExternalStorageDirectory().getAbsolutePath();
		//        String fileName = "a.bin";
		//        OkHttpUtils.get().url("http://bracelet.cositea.com:8089/bracelet/download_downLoad")
		//                .addParams("filename", "k18s_20160909_01_V0.04_dongxiang1_T.bin").build().execute(new FileCallBack(fileSrc, fileName) {
		//            @Override
		//            public void inProgress(float progress) {
		//
		//            }
		//
		//            @Override
		//            public void onError(Request request, Exception e) {
		//
		//            }
		//
		//            @Override
		//            public void onResponse(File response) {
		//                Log.e("TAG", response.length() + "");
		//                FileInputStream fis = null;
		//                try {
		//                    fis = new FileInputStream(response);
		//
		//                    int length = fis.available();
		//
		//                    byte[] buffer = new byte[length];
		//                    fis.read(buffer);
		//                    Log.e("TAG", buffer.length + "数据：");
		//                    StringBuilder builder = new StringBuilder();
		//                    for (int i = 0; i < buffer.length; i++) {
		//                        builder.append(Integer.toHexString(buffer[i] & 0xFF) + "    ");
		//                        if (i % 5 == 4)
		//                            builder.append("\n");
		//                    }
		//                    Log.e("TAG", builder.toString());
		//
		//
		//                    fis.close();
		//                } catch (FileNotFoundException e) {
		//
		//
		//                } catch (IOException e) {
		//                    e.printStackTrace();
		//                }
		//
		//            }
		//        });
	}


	@Override
	public void onActionSuccess (int actionId, String ret) {
		System.out.println ("返回数据：" + ret);
	}

	@Override
	public void onActionFailed (int actionId, int httpStatus) {
		System.out.println ("Fail返回数据：" + httpStatus);
	}


	@Override
	public void onActionException (int actionId, String exception) {

	}
}
