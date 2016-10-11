package com.example.dojoy.myapplication.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.dialog.lib.Effectstype;
import com.example.dojoy.myapplication.dialog.lib.NiftyDialogBuilder;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {
    NiftyDialogBuilder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        dialogBuilder = NiftyDialogBuilder.getInstance(this);
    }

    @OnClick({R.id.fadeIn, R.id.Fall, R.id.FlipH, R.id.FlipV, R.id.NewsPaper,
            R.id.RotateBottom, R.id.RotateLeft, R.id.Shake, R.id.SideFall,
            R.id.SlideBottom, R.id.SlideLeft, R.id.SlideRight, R.id.SlideTop, R.id.SlitDialog})
    public void onClick(View view) {
        Effectstype effect = Effectstype.SlideTop;
        dialogBuilder
                .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("This is a modal Dialog.")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFF")                                //def
                .withDuration(700)                                          //def
                //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .isCancelableOnTouchOutside(true);


        switch (view.getId()) {
            case R.id.fadeIn:
                effect = Effectstype.Fadein;
                break;
            case R.id.Fall:
                effect = Effectstype.Fall;
                break;
            case R.id.FlipH:
                effect = Effectstype.FlipH;
                break;
            case R.id.FlipV:
                effect = Effectstype.FlipV;
                break;
            case R.id.NewsPaper:
                effect = Effectstype.NewsPager;
                break;
            case R.id.RotateBottom:
                effect = Effectstype.RotateBottom;
                break;
            case R.id.RotateLeft:
                effect = Effectstype.RotateLeft;
                break;
            case R.id.Shake:
                effect = Effectstype.Shake;
                break;
            case R.id.SideFall:
                effect = Effectstype.Sidefall;
                break;
            case R.id.SlideBottom:
                effect = Effectstype.SlideBottom;
                break;
            case R.id.SlideLeft:
                effect = Effectstype.SlideLeft;
                break;
            case R.id.SlideRight:
                effect = Effectstype.SlideRight;
                break;
            case R.id.SlideTop:
                effect = Effectstype.SlideTop;
                break;
            case R.id.SlitDialog:
                effect = Effectstype.Slit;
                break;

        }
        dialogBuilder.withEffect(effect);
        dialogBuilder.show();
//        View view1 = LayoutInflater.from(this).inflate(R.layout.dialog, null);
//        dialogBuilder.getWindow().setContentView(view1);
//        dialogBuilder.getWindow().setLayout(-1, -2);
    }

}
