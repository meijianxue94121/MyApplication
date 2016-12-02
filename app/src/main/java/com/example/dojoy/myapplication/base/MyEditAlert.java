package com.example.dojoy.myapplication.base;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import java.util.HashMap;

/**
 * Created by dojoy on 2016/9/5.
 */
public class MyEditAlert extends MyDialogAlert {


    public MyEditAlert(Context context) {
        mContext = context;
    }


    /**
     * @param v
     * @param title
     * @param negativeButton
     * @param positiveButton
     * @param flag           //         * @param type           默认，3代表输入框
     * @param maxLength
     * @param hintString
     * @param inputType      代表输入框种类。1.代表Text密文，2代表Text明文,3.文字明文，4代表数字，5数字密码
     * @return
     */
    public AlertDialog SimpleDialogTwo(final View v
            , String title, String message,
                                       final TwoBtnListener listener,
                                       final String negativeButton, final String positiveButton,
                                       int flag, String hintString, int inputType, Integer maxLength, boolean oneBtn, Integer gravity, Integer width
    ) {
        if (v != null) {
            v.setEnabled(false);
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.simpledialog_ui, null);
        TextView titleText = (TextView) view
                .findViewById(R.id.simple_title);
        final TextView leftText = (TextView) view
                .findViewById(R.id.simple_leftCommit);
        final TextView rightText = (TextView) view
                .findViewById(R.id.simple_rightCommit);
        EditText editText = (EditText) view
                .findViewById(R.id.simple_simpleEdit);

        if (!((title == null) || title.equals(""))) {
            titleText.setText(title);
        } else {
            titleText.setVisibility(View.GONE);
        }
        if (hintString != null) {
            editText.setHint(hintString);
        }
        if (message != null) {
            editText.setText(message);
            //可能导致异常
            editText.setSelection(message.length());
        }
        leftText.setText(negativeButton);
        rightText.setText(positiveButton);
        if (oneBtn) {
            rightText.setVisibility(View.GONE);
        }
        leftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.left();
                }
            }
        });
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.right();
                }
            }
        });
        if (gravity != null) {
            editText.setGravity(gravity);
        }
        if (maxLength == null) {
            maxLength = 200;
        }
        InputFilter[] filters = {new InputFilter.LengthFilter(
                maxLength)};
        editText.setFilters(filters);


        switch (inputType) {
            case InputMode.StringEncryption:
                editText.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case InputMode.StringNormal:
                editText
                        .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case InputMode.ChineseNormal:
                break;
            case InputMode.NumberNormal:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case InputMode.NumberEncryption:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                break;

        }

        InputMethodManager im2 = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        im2.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        editText.setVisibility(View.VISIBLE);


        dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setContentView(view);
        switch (flag) {
            case CloseMode.Type_OutTouch:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case CloseMode.Type_Cancel:
                dialog.setCancelable(false);
                break;

            default:
                break;
        }
        if (width == null) {
            width = 300;
        }
        dialog.getWindow().setLayout(
                ZhUtils.DimenTrans.dip2px(mContext, width), -2);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        if (v != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    v.setEnabled(true);
                }
            }, 1000);

        }


        return dialog;
    }


    /**
     * @param v
     * @param messageValues
     * @param closeMode
     * @param inputType
     * @param maxLength
     * @param oneBtn
     */
    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode, int inputType, int maxLength, boolean oneBtn, Integer gravity, Integer width) {
        SimpleDialogTwo(v, messageValues.get(TITLE_VALUE) == null ? "" : messageValues.get(TITLE_VALUE), messageValues.get(MESSAGE_VALUE) == null ? "" : messageValues.get(MESSAGE_VALUE)
                , listener, messageValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : messageValues.get(LEFT_COMMIT_VALUE), messageValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : messageValues.get(RIGHT_COMMIT_VALUE),
                closeMode, messageValues.get(HINT_COMMIT_VALUE) == null ? "" : messageValues.get(HINT_COMMIT_VALUE),
                inputType, maxLength, oneBtn, gravity, width);
    }

    /**
     * @param v
     * @param messageValues
     * @param closeMode
     * @param inputType
     * @param maxLength
     */
    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode, boolean oneBtn, int inputType, int maxLength) {
        showDialog(v, messageValues, listener, closeMode, inputType, maxLength, oneBtn, null, null);
    }

    /**
     * @param v
     * @param messageValues
     * @param listener
     * @param closeMode
     * @param inputType
     * @param maxLength
     */
    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode, int inputType, int maxLength) {
        showDialog(v, messageValues, listener, closeMode, inputType, maxLength, false, null, null);
    }


}
