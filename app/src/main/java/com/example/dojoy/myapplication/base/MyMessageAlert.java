package com.example.dojoy.myapplication.base;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.helputils.ZhUtils;

import java.util.HashMap;

/**
 * Created by dojoy on 2016/9/5.
 */
public class MyMessageAlert {
    public static final String TITLE_VALUE = "title";
    public static final String MESSAGE_VALUE = "message";
    public static final String LEFT_COMMIT_VALUE = "left";
    public static final String MID_COMMIT_VALUE = "mid";
    public static final String RIGHT_COMMIT_VALUE = "right";
    /**
     * DIALOG_CLOSE_MODE_DEFAULT   dialog默认关闭方式
     * DIALOG_CLOSE_MODE_OUTSIZE   dialog点击空包区域不关闭，返回按钮可关闭
     * DIALOG_CLOSE_MODE_CANCEL   dialog返回按钮不可关闭，主动调用关闭方法
     */
    public static final int DIALOG_CLOSE_MODE_OUTSIZE = 1;
    public static final int DIALOG_CLOSE_MODE_CANCEL = 2;
    public static final int DIALOG_CLOSE_MODE_DEFAULT = 0;

    public interface TwoBtnListener {
        void left();

        void right();
    }

    public interface ThreeBtnListener {
        void left();

        void middle();

        void right();
    }

    public MyMessageAlert(Context context) {
        mContext = context;
    }

    Context mContext;
    /**
     * 默认2个按钮的弹窗显示
     */
    AlertDialog dialog;

    /**
     * 默认两个按钮的，一个标题，一段话
     *
     * @param v
     * @param title
     * @param message
     * @param commitListener
     * @param cancleListener
     * @param negativeButton
     * @param positiveButton
     * @param flag           0默认值，不关闭dialog,1.点击空白关闭，2，点击返回也不关闭
     */
    @Deprecated
    public AlertDialog SimpleDialog(final View v
            , String title, String message,
                                    View.OnClickListener commitListener,
                                    View.OnClickListener cancleListener, final String positiveButton,
                                    final String negativeButton,
                                    int flag, boolean oneBtn) {
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
        if (!(message == null)) {
            messageText.setText(message);
            messageText.setVisibility(View.VISIBLE);
        } else {
            messageText.setVisibility(View.GONE);
        }
        if (oneBtn) {
            rightText.setVisibility(View.GONE);
        } else {
            rightText.setVisibility(View.VISIBLE);

        }

        leftText.setText(positiveButton);
        rightText.setText(negativeButton);

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
            case DIALOG_CLOSE_MODE_OUTSIZE:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case DIALOG_CLOSE_MODE_CANCEL:
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


        return dialog;
    }

    /**
     * 默认两个按钮的，一个标题，一段话
     *
     * @param v
     * @param title
     * @param message
     * @param negativeButton
     * @param positiveButton
     * @param flag           0默认值，不关闭dialog,1.点击空白关闭，2，点击返回也不关闭
     */
    public AlertDialog showDialog(final View v
            , String title, String message,
                                  final TwoBtnListener listener, final String positiveButton,
                                  final String negativeButton,
                                  int flag, boolean oneBtn, Integer gravity) {
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
        if (!(message == null)) {
            messageText.setText(message);
            messageText.setVisibility(View.VISIBLE);
        } else {
            messageText.setVisibility(View.GONE);
        }
        if (oneBtn) {
            rightText.setVisibility(View.GONE);
        } else {
            rightText.setVisibility(View.VISIBLE);

        }
        if (gravity != null) {
            messageText.setGravity(gravity);
        }
        messageText.setMovementMethod(new ScrollingMovementMethod());
        leftText.setText(positiveButton);
        rightText.setText(negativeButton);
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
            case DIALOG_CLOSE_MODE_OUTSIZE:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case DIALOG_CLOSE_MODE_CANCEL:
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


        return dialog;
    }

    /**
     * 三个按钮的弹窗
     *
     * @param v
     * @param title
     * @param message
     * @param listener
     * @param positiveButton
     * @param midButton
     * @param negativeButton
     * @param flag
     * @return
     */
    public AlertDialog SimpleDialogThree(final View v
            , String title, String message,
                                         final ThreeBtnListener listener, final String positiveButton, final String midButton,
                                         final String negativeButton,
                                         int flag, Integer gravity) {
        if (v != null) {
            v.setEnabled(false);
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.simpledialog_threebtn_ui, null);
        TextView titleText = (TextView) view
                .findViewById(R.id.simple_title);
        TextView messageText = (TextView) view
                .findViewById(R.id.simple_message);
        final TextView leftText = (TextView) view
                .findViewById(R.id.simple_leftCommit);
        final TextView midText = (TextView) view
                .findViewById(R.id.simple_midCommit);
        final TextView rightText = (TextView) view
                .findViewById(R.id.simple_rightCommit);


        if (!((title == null) || title.equals(""))) {
            titleText.setText(title);
        } else {
            titleText.setVisibility(View.GONE);
        }
        if (!(message == null)) {
            messageText.setText(message);
            messageText.setVisibility(View.VISIBLE);
        } else {
            messageText.setVisibility(View.GONE);
        }

        leftText.setText(positiveButton);
        midText.setText(midButton);
        rightText.setText(negativeButton);
        leftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.left();
                }
            }
        });
        midText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.middle();
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
            case DIALOG_CLOSE_MODE_OUTSIZE:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case DIALOG_CLOSE_MODE_CANCEL:
                dialog.setCancelable(false);
                break;

            default:

                break;
        }

        if (gravity != null) {
            messageText.setGravity(gravity);
        }
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
     * 默认2个按钮的弹窗显示
     *
     * @param v
     * @param title
     * @param message
     * @param leftLis
     * @param rightLis
     * @param leftTxt
     * @param rigTxt
     * @param flag
     */
    @Deprecated
    public void show(View v, String title, String message, View.OnClickListener leftLis, View.OnClickListener rightLis, String leftTxt, String rigTxt, int flag, boolean ontBtn) {
        SimpleDialog(v, title, message, leftLis, rightLis, leftTxt, rigTxt, flag, ontBtn);
    }

    /**
     * @param v
     * @param strsValues
     * @param leftLis
     * @param rightLis
     * @param flag
     */
    @Deprecated
    public void show(View v, HashMap<String, String> strsValues, View.OnClickListener leftLis, View.OnClickListener rightLis, int flag, boolean ontBtn) {
        if (strsValues == null) {
            return;
        }
        SimpleDialog(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE), leftLis,
                rightLis, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE), flag, ontBtn);
    }

    /**
     * 默认2个按钮的弹窗显示
     *
     * @param v
     * @param strsValues
     * @param leftLis
     * @param rightLis
     * @param flag
     */
    @Deprecated
    public void show(View v, HashMap<String, String> strsValues, View.OnClickListener leftLis, View.OnClickListener rightLis, int flag) {
        if (strsValues == null) {
            return;
        }
        show(v, strsValues, leftLis, rightLis, flag, false);
    }

    /**
     * @param v
     * @param strsValues
     * @param flag
     */

    public void showDialog(View v, HashMap<String, String> strsValues, TwoBtnListener listener, int flag, boolean ontBtn, Integer gravity) {
        if (strsValues == null) {
            return;
        }
        showDialog(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE), listener, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE), flag, ontBtn, gravity);
    }

    public void showDialog(View v, HashMap<String, String> strsValues, TwoBtnListener listener, int flag, boolean ontBtn) {
        if (strsValues == null) {
            return;
        }
        showDialog(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE), listener, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE), flag, ontBtn, null);
    }

    /**
     * 默认2个按钮的弹窗显示
     *
     * @param v
     * @param strsValues
     * @param flag
     */
    public void showDialog(View v, HashMap<String, String> strsValues, TwoBtnListener listener, int flag) {
        if (strsValues == null) {
            return;
        }
        showDialog(v, strsValues, listener, flag, false, null);
    }

    /**
     * 三个按钮的弹窗
     *
     * @param v
     * @param strsValues
     * @param listener
     * @param flag
     */
    public void showThree(View v, HashMap<String, String> strsValues, ThreeBtnListener listener, int flag) {
        if (strsValues == null) {
            return;
        }
        SimpleDialogThree(v, strsValues.get(TITLE_VALUE) == null ? "" : strsValues.get(TITLE_VALUE), strsValues.get(MESSAGE_VALUE) == null ? "" : strsValues.get(MESSAGE_VALUE),
                listener, strsValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : strsValues.get(LEFT_COMMIT_VALUE), strsValues.get(MID_COMMIT_VALUE) == null ? "取消" : strsValues.get(MID_COMMIT_VALUE), strsValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : strsValues.get(RIGHT_COMMIT_VALUE)
                , flag, null);
    }

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
    public static HashMap<String, String> showMessage(String title, String message, String left, String right) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE_VALUE, title);
        hashMap.put(MESSAGE_VALUE, message);
        hashMap.put(LEFT_COMMIT_VALUE, left);
        hashMap.put(RIGHT_COMMIT_VALUE, right);
        return hashMap;
    }

    public static HashMap<String, String> showThreeBtnMessage(String title, String message, String left, String mid, String right) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE_VALUE, title);
        hashMap.put(MESSAGE_VALUE, message);
        hashMap.put(LEFT_COMMIT_VALUE, left);
        hashMap.put(MID_COMMIT_VALUE, mid);
        hashMap.put(RIGHT_COMMIT_VALUE, right);
        return hashMap;
    }

    /**
     * @param title   标题
     * @param message 信息题
     *                默认左侧确定，右侧取消
     * @return
     */
    public static HashMap<String, String> showMessage(String title, String message) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE_VALUE, title);
        hashMap.put(MESSAGE_VALUE, message);
        return hashMap;
    }
}
