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

import static com.example.dojoy.myapplication.base.MyDialogAlert.CloseMode.Type_Cancel;
import static com.example.dojoy.myapplication.base.MyDialogAlert.CloseMode.Type_OutTouch;

/**
 * Created by dojoy on 2016/9/5.
 */
public class MyMessageAlert extends MyDialogAlert {





    public MyMessageAlert(Context context) {
        mContext = context;
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
    public AlertDialog SimpleDialogTwo(final View v
            , String title, String message,
                                  final TwoBtnListener listener, final String positiveButton,
                                  final String negativeButton,
                                  int flag, boolean oneBtn, Integer gravity, Integer width) {
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
        if (width == null) {
            width = 300;
        }
        dialog.getWindow().setLayout(
                ZhUtils.DimenTrans.dip2px(mContext, width), -2);

        switch (flag) {
            case Type_OutTouch:
                dialog.setCanceledOnTouchOutside(false);
                break;
            case Type_Cancel:
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
                                         int flag, Integer gravity, Integer width) {
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
        messageText.setMovementMethod(new ScrollingMovementMethod());
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
        if (width == null) {
            width = 300;
        }
        dialog.getWindow().setLayout(
                ZhUtils.DimenTrans.dip2px(mContext, width), -2);
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
     * @param v
     * @param messageValues
     * @param closeMode
     */

    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode, boolean ontBtn, Integer gravity, Integer width) {
        if (messageValues == null) {
            return;
        }
        SimpleDialogTwo(v, messageValues.get(TITLE_VALUE) == null ? "" : messageValues.get(TITLE_VALUE),
                messageValues.get(MESSAGE_VALUE) == null ? "" : messageValues.get(MESSAGE_VALUE),
                listener, messageValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : messageValues.get(LEFT_COMMIT_VALUE),
                messageValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : messageValues.get(RIGHT_COMMIT_VALUE),
                closeMode, ontBtn, gravity, width);
    }

    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode, boolean ontBtn) {
        showDialog(v, messageValues, listener, closeMode, ontBtn, null, null);
    }


    /**
     * 默认2个按钮的弹窗显示
     * 默认300宽，文案居中
     *
     * @param v
     * @param messageValues
     * @param closeMode
     */
    public void showDialog(View v, HashMap<String, String> messageValues, TwoBtnListener listener, int closeMode) {
        showDialog(v, messageValues, listener, closeMode, false);
    }

    /**
     * 三个按钮的弹窗
     *
     * @param v
     * @param messageValues
     * @param listener
     * @param closeMode
     */
    public void showThree(View v, HashMap<String, String> messageValues, ThreeBtnListener listener, int closeMode, Integer gravity, Integer width) {
        if (messageValues == null) {
            return;
        }

        SimpleDialogThree(v,
                messageValues.get(TITLE_VALUE) == null ? "" : messageValues.get(TITLE_VALUE),
                messageValues.get(MESSAGE_VALUE) == null ? "" : messageValues.get(MESSAGE_VALUE),
                listener,
                messageValues.get(LEFT_COMMIT_VALUE) == null ? "确定" : messageValues.get(LEFT_COMMIT_VALUE),
                messageValues.get(MID_COMMIT_VALUE) == null ? "取消" : messageValues.get(MID_COMMIT_VALUE),
                messageValues.get(RIGHT_COMMIT_VALUE) == null ? "取消" : messageValues.get(RIGHT_COMMIT_VALUE)
                , closeMode, gravity, width);
    }

    /**
     * @param v
     * @param messageValues
     * @param listener
     * @param closeMode
     */
    public void showThree(View v, HashMap<String, String> messageValues, ThreeBtnListener listener, int closeMode) {

        showThree(v, messageValues, listener, closeMode, null, null);
    }


}
