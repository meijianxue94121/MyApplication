package com.example.dojoy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.dojoy.myapplication.base.MyToolBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAct extends AppCompatActivity {

    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        toolbar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setText(test.getText().toString().trim() + test.getText().toString().trim());
            }
        });
        test.setMovementMethod(new ScrollingMovementMethod());
    }
}
