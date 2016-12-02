package com.example.dojoy.myapplication.base;

import android.app.AlertDialog;
import android.content.Context;

import java.util.HashMap;

/**
 * Created by dojoy on 2016/12/2.
 */

public class MyDialogAlert {
    Context mContext;
    public AlertDialog dialog;
    public static final String HINT_COMMIT_VALUE = "hint";
    public static final String TITLE_VALUE = "title";
    public static final String MESSAGE_VALUE = "message";
    public static final String LEFT_COMMIT_VALUE = "left";
    public static final String MID_COMMIT_VALUE = "mid";
    public static final String RIGHT_COMMIT_VALUE = "right";

    public interface TwoBtnListener {
        void left();

        void right();
    }

    public interface ThreeBtnListener {
        void left();

        void middle();

        void right();
    }

    /**
     * 1.点击外部不关闭，点击back可以关闭
     * 2.back不可关闭
     * 3.dialog默认关闭方式
     */
    public static class CloseMode {
        public static final int Type_OutTouch = 1;
        public static final int Type_Cancel = 2;
        public static final int Type_Default = 0;
    }

    /**
     * 1.Text密文
     * 2.Text明文
     * 3.中文汉字
     * 4.数字加密
     * 5.数字明文
     */
    public static class InputMode {
        public static final int StringEncryption = 1;
        public static final int StringNormal = 2;
        public static final int ChineseNormal = 3;
        public static final int NumberNormal = 4;
        public static final int NumberEncryption = 5;
    }

    /**
     * @param title   标题
     * @param message 信息题
     * @param left    左侧按钮
     * @param right   右侧按钮
     * @return
     */
    public static HashMap<String, String> editMessage(String title, String message, String hint, String left, String right) {
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
    public static HashMap<String, String> editMessage(String title, String message, String hint) {
        return editMessage(title, message, hint, null, null);
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

    /**
     * @param title   標題
     * @param message 信息
     * @param left    左側文字
     * @param mid     中間文字
     * @param right   右側文字
     * @return
     */
    public static HashMap<String, String> showThreeMessage(String title, String message, String left, String mid, String right) {
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
        return showMessage(title, message, null, null);
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
}
