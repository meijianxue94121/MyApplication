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
public class MyEditAlert {
    public static final String TITLE_VALUE = "title";
    public static final String MESSAGE_VALUE = "message";
    public static final String LEFT_COMMIT_VALUE = "left";
    public static final String RIGHT_COMMIT_VALUE = "right";
    public static final String HINT_COMMIT_VALUE = "hint";
    public static final int DIALOG_OUTTOUCH = 1;
    public static final int DIALOG_CANCEL = 2;
    public static final int DIALOG_DEFAULT = 0;

    public interface TwoBtnListener {
        void left();

        void right();
    }


    public MyEditAlert(Context context) {
        mContext = context;
    }

    Context mContext;
    public AlertDialog dialog;

    /**
     * @param v
     * @param title
     * @param commitListener
     * @param cancleListener
     * @param negativeButton
     * @param positiveButton
     * @param flag           //         * @param type           默认，3代表输入框
     * @param maxLength
     * @param hintString
     * @param inputType      代表输入框种类。1.代表Text密文，2代表Text明文,3.文字明文，4代表数字，5数字密码
     * @return
     */
    @Deprecated
    public AlertDialog SimpleDialog(final View v
            , String title, String message,
                                    View.OnClickListener commitListener,
                                    View.OnClickListener cancleListener,
                                    final String negativeButton, final String positiveButton,
                                    int flag, String hintString, int inputType, int maxLength, boolean oneBtn
    ) {
        if (v != null) {
            v.setEnabled(false);
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.simpledialog_ui, null);
        TextView titleText = (TextView) view
                .findViewById(R.id.simple_title);
        TextView messageText = (TextView) view
                .findViewById(R.id.simple_message);
        final TextView leftText = (TextView) view
                .findViewById(R.id.simple_leftCommit);
        final TextView rightText = (TextView) view
                .findViewById(R.id.simple_rightCommit);

        if (!((title == null) || title.equals(""))) {
            titleText.setText(title);
        } else {
            titleText.setVisibility(View.GONE);
        }
        leftText.setText(negativeButton);
        rightText.setText(positiveButton);
        leftText.setOnClickListener(commitListener);
        if (cancleListener != null) {

            rightText.setOnClickListener(cancleListener);
        } else {
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
        }

        dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setLayout(
                ZhUtils.DimenTrans.dip2px(mContext, 300), -2);
        switch (flag) {
            case DIALOG_OUTTOUCH:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case DIALOG_CANCEL:
                dialog.setCancelable(false);
                break;

            default:
                break;
        }
        if (v != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    v.setEnabled(true);
                }
            }, 1000);

        }


        EditText editText = (EditText) dialog
                .findViewById(R.id.simple_simpleEdit);
        if (maxLength >= 0) {
            InputFilter[] filters = {new InputFilter.LengthFilter(
                    maxLength)};
            editText.setFilters(filters);
        }

        if (hintString != null) {
            editText.setHint(hintString);
        }
        if (message != null) {
            editText.setText(message);
            editText.setSelection(message.length());
        }
        switch (inputType) {
            case 1:
                editText.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case 2:
                editText
                        .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case 3:
                break;
            case 4:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 5:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                break;
            default:
                break;

        }

        InputMethodManager im2 = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        im2.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        editText.setVisibility(View.VISIBLE);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editText.setVisibility(View.VISIBLE);

        return dialog;
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
    public AlertDialog showDialog(final View v
            , String title, String message,
                                  final TwoBtnListener listener,
                                  final String negativeButton, final String positiveButton,
                                  int flag, String hintString, int inputType, int maxLength, boolean oneBtn
    ) {
        if (v != null) {
            v.setEnabled(false);
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.simpledialog_ui, null);
        TextView titleText = (TextView) view
                .findViewById(R.id.simple_title);
        TextView messageText = (TextView) view
                .findViewById(R.id.simple_message);
        final TextView leftText = (TextView) view
                .findViewById(R.id.simple_leftCommit);
        final TextView rightText = (TextView) view
                .findViewById(R.id.simple_rightCommit);

        if (!((title == null) || title.equals(""))) {
            titleText.setText(title);
        } else {
            titleText.setVisibility(View.GONE);
        }
        if (oneBtn) {
            rightText.setVisibility(View.GONE);
        } else {
            rightText.setVisibility(View.VISIBLE);

        }
        leftText.setText(negativeButton);
        rightText.setText(positiveButton);
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


        dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setLayout(
                ZhUtils.DimenTrans.dip2px(mContext, 300), -2);
        switch (flag) {
            case DIALOG_OUTTOUCH:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case DIALOG_CANCEL:
                dialog.setCancelable(false);
                break;

            default:
                break;
        }
        if (v != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    v.setEnabled(true);
                }
            }, 1000);

        }


        EditText editText = (EditText) dialog
                .findViewById(R.id.simple_simpleEdit);
        if (maxLength >= 0) {
            InputFilter[] filters = {new InputFilter.LengthFilter(
                    maxLength)};
            editText.setFilters(filters);
        }

        if (hintString != null) {
            editText.setHint(hintString);
        }
        if (message != null) {
            editText.setText(message);
            editText.setSelection(message.length());
        }
        switch (inputType) {
            case 1:
                editText.setInputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case 2:
                editText
                        .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case 3:
                break;
            case 4:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 5:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                break;
            default:
                break;

        }

        InputMethodManager im2 = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        im2.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        editText.setVisibility(View.VISIBLE);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editText.setVisibility(View.VISIBLE);

        return dialog;
    }

    /**
     * 默认2按钮
     *
     * @param v          点解的空间
     * @param title      标题
     * @param message    输入框中信息
     * @param leftLis    左侧按钮
     * @param rightLis   右侧a按钮
     * @param leftTxt    左侧按钮文字
     * @param rigTxt     右侧按钮文字
     * @param flag       dialog关闭模式
     * @param maxLength
     * @param hintString
     * @param inputType  代表输入框种类。1.代表Text密文，2代表Text明文,3.文字明文，4代表数字，5数字密码
     * @return
     */
    @Deprecated
    public void show(View v, String title, String message, View.OnClickListener leftLis, View.OnClickListener rightLis, String leftTxt, String rigTxt, int flag, String hintString, int inputType, int maxLength) {
        SimpleDialog(v, title, message, leftLis, rightLis, leftTxt, rigTxt, flag, hintString,
                inputType, maxLength, false);
    }

    /**
     * @param v
     * @param strsValues
     * @param leftLis
     * @param rightLis
     * @param flag
     * @param inputType
     * @param maxLength
     * @param oneBtn
     */
    @Deprecated
    public void show(View v, HashMap<String, String> strsValues, View.OnClickListener leftLis, View.OnClickListener rightLis, int flag, int inputType, int maxLength, boolean oneBtn) {
        SimpleDialog(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE)
                , leftLis, rightLis, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE),
                flag, strsValues.get(HINT_COMMIT_VALUE) == null ? "" : strsValues.get(HINT_COMMIT_VALUE),
                inputType, maxLength, oneBtn);
    }

    /**
     * @param v
     * @param strsValues
     * @param leftLis
     * @param rightLis
     * @param flag
     * @param inputType
     * @param maxLength
     */
    @Deprecated
    public void show(View v, HashMap<String, String> strsValues, View.OnClickListener leftLis, View.OnClickListener rightLis, int flag, int inputType, int maxLength) {
        show(v, strsValues, leftLis, rightLis, flag, inputType, maxLength, false);
    }

    /**
     * @param v
     * @param strsValues
     * @param flag
     * @param inputType
     * @param maxLength
     * @param oneBtn
     */
    public void showDialog(View v, HashMap<String, String> strsValues, TwoBtnListener listener, int flag, int inputType, int maxLength, boolean oneBtn) {
        showDialog(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE)
                , listener, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE),
                flag, strsValues.get(HINT_COMMIT_VALUE) == null ? "" : strsValues.get(HINT_COMMIT_VALUE),
                inputType, maxLength, oneBtn);
    }

    /**
     * @param v
     * @param strsValues
     * @param flag
     * @param inputType
     * @param maxLength
     */

    public void showDialog(View v, HashMap<String, String> strsValues, TwoBtnListener listener, int flag, int inputType, int maxLength) {
        showDialog(v, strsValues, listener, flag, inputType, maxLength, false);
    }

    @Deprecated
    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void destory() {
        dismiss();
        dialog = null;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * @param title   标题
     * @param message 信息题
     * @param left    左侧按钮
     * @param right   右侧按钮
     * @return
     */
    public static HashMap<String, String> showMessage(String title, String message, String left, String right, String hint) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE_VALUE, title);
        hashMap.put(MESSAGE_VALUE, message);
        hashMap.put(LEFT_COMMIT_VALUE, left);
        hashMap.put(RIGHT_COMMIT_VALUE, right);
        hashMap.put(HINT_COMMIT_VALUE, hint);
        return hashMap;
    }

    /**
     * @param title   标题
     * @param message 信息题
     *                默认左侧确定，右侧取消
     * @return
     */
    public static HashMap<String, String> showMessage(String title, String message, String hint) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE_VALUE, title);
        hashMap.put(MESSAGE_VALUE, message);
        hashMap.put(HINT_COMMIT_VALUE, hint);
        return hashMap;
    }
}
