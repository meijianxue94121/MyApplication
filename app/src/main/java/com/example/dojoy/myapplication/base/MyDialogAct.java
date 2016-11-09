package com.example.dojoy.myapplication.base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDialogAct extends LBaseAct {

    @BindView(R.id.twoMessAlert)
    TextView twoMessAlert;
    @BindView(R.id.threeMessAlert)
    TextView threeMessAlert;
    @BindView(R.id.twoEditAlert)
    TextView twoEditAlert;
    MyMessageAlert messageAlert;
    MyEditAlert editAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        messageAlert = new MyMessageAlert(MyDialogAct.this);
        editAlert = new MyEditAlert(MyDialogAct.this);
    }

    @Override
    public void requestInit() {

    }

    @OnClick({R.id.twoMessAlert, R.id.threeMessAlert, R.id.twoEditAlert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.twoMessAlert:
                messageAlert.showDialog(view, MyMessageAlert.showMessage("温馨提示", "这是两个按钮的MessageDialog"), new MyMessageAlert.TwoBtnListener() {
                    @Override
                    public void left() {
                        ZhUtils.ToastUtils.showToast(MyDialogAct.this, "操作1");
                    }

                    @Override
                    public void right() {
                        ZhUtils.ToastUtils.showToast(MyDialogAct.this, "操作2");
                    }
                }, MyMessageAlert.DIALOG_CLOSE_MODE_CANCEL);
                break;
            case R.id.threeMessAlert:
                messageAlert.showThree(view, MyMessageAlert.showThreeBtnMessage("温馨提示", "这是三个按钮的MessageDialog", "按钮1", "按钮2", "按钮3"), new MyMessageAlert.ThreeBtnListener() {
                    @Override
                    public void left() {
                        ZhUtils.ToastUtils.showToast(MyDialogAct.this, "操作1");
                    }

                    @Override
                    public void middle() {
                        ZhUtils.ToastUtils.showToast(MyDialogAct.this, "操作2");
                    }

                    @Override
                    public void right() {
                        ZhUtils.ToastUtils.showToast(MyDialogAct.this, "操作3");
                    }
                }, MyMessageAlert.DIALOG_CLOSE_MODE_CANCEL);
                break;
            case R.id.twoEditAlert:
                break;
        }
    }
}
