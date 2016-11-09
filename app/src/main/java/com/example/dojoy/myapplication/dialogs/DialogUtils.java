//package com.example.dojoy.myapplication.dialogs;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.dojoy.myapplication.R;
//
//
///**
// * Created by Administrator on 2016/2/26.
// */
//public class DialogUtils {
//
//
//    /**
//     * 通用的弹窗dialog.
//     *
//     * @author Administrator
//     */
//    public static class SimpleDialog {
//        //
//        //            public static AlertDialog dialog3;
//        //            public static AlertDialog dialog22;
//        //            public static AlertDialog dialog1;
//        //
//        //
//
//        /**
//         * 默认两个按钮的，一个标题，一段话
//         *
//         * @param v
//         * @param context
//         * @param title
//         * @param message
//         * @param commitListener
//         * @param cancleListener
//         * @param negativeButton
//         * @param positiveButton
//         * @param flag           0默认值，不关闭dialog,1.点击空白关闭，2，点击返回也不关闭
//         * @param type           默认0 。代表展示TextView,1.代表3列的城市选择。2.代表2列的城市选择,3代表一行输入框,5星座
//         */
//        public static AlertDialog SimpleDialog22(final View v,
//                                                 final Context context, String title, String message,
//                                                 View.OnClickListener commitListener,
//                                                 View.OnClickListener cancleListener,
//                                                 final String negativeButton, final String positiveButton,
//                                                 int flag, int type) {
//            return SimpleDialog22(v, context, title, message, commitListener,
//                    cancleListener, negativeButton, positiveButton, flag, type,
//                    -1, null, null, 3, false);
//        }
//        //
//
//        //
//
//        /**
//         * @param v
//         * @param context
//         * @param title
//         * @param commitListener
//         * @param cancleListener
//         * @param negativeButton
//         * @param positiveButton
//         * @param flag           //         * @param type           默认，3代表输入框
//         * @param maxLength
//         * @param hintString
//         * @param editString
//         * @param inputType      代表输入框种类。1.代表Text密文，2代表Text明文,3.文字明文，4代表数字，5数字密码
//         * @return
//         */
//        public static AlertDialog SimpleEditDialog22(final View v,
//                                                     final Context context, String title,
//                                                     View.OnClickListener commitListener,
//                                                     View.OnClickListener cancleListener,
//                                                     final String negativeButton, final String positiveButton,
//                                                     int flag, int maxLength, String hintString, String editString, int inputType, boolean isChinese) {
//
//            return SimpleDialog22(v, context, title, null, commitListener,
//                    cancleListener, negativeButton, positiveButton, flag, 3,
//                    maxLength, hintString, editString, inputType, isChinese);
//        }
//
//        public static AlertDialog SimpleEditDialog23(final View v,
//                                                     final Context context, String title,
//                                                     View.OnClickListener commitListener,
//                                                     View.OnClickListener cancleListener,
//                                                     final String negativeButton, final String positiveButton,
//                                                     int flag, int maxLength, String hintString, String editString, int inputType, boolean isChinese) {
//
//            return SimpleDialog22(v, context, title, null, commitListener,
//                    cancleListener, negativeButton, positiveButton, flag, 4,
//                    maxLength, hintString, editString, inputType, isChinese);
//        }
//
//
//        /**
//         * 默认两个按钮的，一个标题，一段话
//         *
//         * @param v
//         * @param context
//         * @param title
//         * @param message
//         * @param commitListener
//         * @param cancleListener
//         * @param negativeButton
//         * @param positiveButton
//         * @param flag           0默认值，不关闭dialog,1.点击空白关闭，2，点击返回也不关闭
//         * @param type           默认0 。代表展示TextView,1.代表3列的城市选择。2.代表2列的城市选择,3代表一行输入框,4三围输入框3个，5.选择星座
//         * @param hintString     输入框提示
//         * @param editString     输入框默认文字
//         * @param maxLength      默认输入长度
//         */
//        public static AlertDialog SimpleDialog22(final View v,
//                                                 final Context context, String title, String message,
//                                                 View.OnClickListener commitListener,
//                                                 View.OnClickListener cancleListener,
//                                                 final String negativeButton, final String positiveButton,
//                                                 int flag, int type, int maxLength, String hintString,
//                                                 String editString, int inputType, boolean isChinese) {
//            if (v != null) {
//                v.setEnabled(false);
//            }
//            View view = LayoutInflater.from(context).inflate(
//                    R.layout.simpledialog_ui, null);
//            TextView titleText = (TextView) view
//                    .findViewById(R.id.simple_title);
//            TextView messageText = (TextView) view
//                    .findViewById(R.id.simple_message);
//            final TextView leftText = (TextView) view
//                    .findViewById(R.id.simple_leftCommit);
//            final TextView rightText = (TextView) view
//                    .findViewById(R.id.simple_rightCommit);
//
//            if (!(title == null)) {
//                titleText.setText(title);
//            } else {
//                titleText.setVisibility(View.GONE);
//            }
//
//            leftText.setText(negativeButton);
//            rightText.setText(positiveButton);
//            leftText.setOnClickListener(commitListener);
//            rightText.setOnClickListener(cancleListener);
//
//            AlertDialog dialog22 = new AlertDialog.Builder(context).create();
//            dialog22.show();
//            dialog22.getWindow().setBackgroundDrawable(new ColorDrawable());
//            dialog22.getWindow().setContentView(view);
//            dialog22.getWindow().setLayout(
//                    800, -2);
//            switch (flag) {
//                case 1:
//
//                    break;
//                case 2:
//                    dialog22.setCancelable(false);
//                    break;
//
//                default:
//                    dialog22.setCanceledOnTouchOutside(false);
//                    break;
//            }
//            if (v != null) {
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        v.setEnabled(true);
//
//                    }
//                }, 1000);
//
//            }
//            switch (type) {
//
//
//                case 1:
//                    // CityPicker_three cityPicker_three1 = (CityPicker_three)
//                    // dialog22
//                    // .findViewById(R.id.citypicker);
//                    LinearLayout cityInclude = (LinearLayout) dialog22
//                            .findViewById(R.id.simple_citypickinclude);
//                    cityInclude.setVisibility(View.VISIBLE);
//
//                    break;
//                case 2:
//                    LinearLayout cityInclude1 = (LinearLayout) dialog22
//                            .findViewById(R.id.simple_citypickinclude);
//                    CityPicker_three cityPicker_three = (CityPicker_three) dialog22
//                            .findViewById(R.id.citypicker);
//                    cityPicker_three.setCountyGone();
//                    cityInclude1.setVisibility(View.VISIBLE);
//                    break;
//                default:
//                    if (!(message == null)) {
//                        messageText.setText(message);
//                    }
//                    messageText.setVisibility(View.VISIBLE);
//
//                    break;
//            }
//
//            return dialog22;
//        }
//    }
//}
