package com.example.dojoy.myapplication.helputils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dojoy.myapplication.R;
import com.example.dojoy.myapplication.base.BaseReceiver;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by zkl on 2016/3/30.
 * 工具类:
 * 1、getBytesFromFile 把文件读取出来转成字节数组
 * 2、dateFormate 把时间字符串转成毫秒数，如1992-12-01 18:22:32转成175515211125毫秒
 * 3、把指定时间字符串转成毫秒数，如1992-12-01 18:22:32转成175515211125毫秒
 * 4、根据ImageView控件的大小压缩本地图片
 * 5、得到缓存目录，并创建指定文件夹
 * 6、获取到当前应用程序的versionCode
 * 7、获取到当前应用程序的versionName
 * 8、json转成对象
 * 9、判断手机是否有SD卡。
 * 10、判断是否连接网络
 * 11、模糊图片
 * 12、获取应用缓存图片大小
 * 13、获取缓存目录
 * 14、删除缓存目录下指定文件
 * 15、尺寸转换类
 * 16、获得屏幕宽度
 * 17、获得屏幕高度
 * 18、拷贝文字到剪切板
 * 19、 字符串转unicode
 * 20、unicode 转字符串
 * 21、压缩bitmap
 * 22、获取状态栏高度，注意，要在onWindowFocusChanged中调用，在onCreate中获取高度为0
 * 23、判断手机连接的网络类型(2G,3G,4G)
 * 24.获取网络类型
 * 25、判断当前App处于前台还是后台状态
 * 26、判断当前是否是WIFI连接状态
 * 27.进度框
 * 28.传进来一个double格式化保留2位
 * 29.隐藏虚拟键盘
 * 30.显示虚拟键盘
 * 31.md5加密 大写
 * 32.自定义吐司
 * 33.隐藏手机号中间四位
 */
public class ZhUtils {


    /**
     * 1、把文件读取出来转成字节数组
     *
     * @param file 目标文件
     * @return 文件字节数组
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        // 获取文件大小
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large " + file.getName());
        }
        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int) length];
        // 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }

    /**
     * 2、把时间字符串转成毫秒数，如1992-12-01 18:22:32转成175515211125毫秒
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dates 时间
     * @return
     */
    public static long dateFormate(String dates) {
        return dateFormate(dates, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 3、把指定时间字符串转成毫秒数，如1992-12-01 18:22:32转成175515211125毫秒
     * dates和format要统一
     *
     * @param dates  时间
     * @param format 用户指定的格式 如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long dateFormate(String dates, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 此处会抛异常
        Date date = null;
        try {
            date = sdf.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 获取毫秒数
        long longDate = date.getTime();
        return longDate;
    }

    /**
     * 4、根据ImageView控件的大小压缩本地图片
     */
    public static class ImageZip {
        /**
         * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
         *
         * @param options   压缩条件，包含图片的宽高信息
         * @param reqWidth  压缩的目标宽度
         * @param reqHeight 压缩的目标高度
         * @return 压缩比例
         */
        private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                                int reqHeight) {
            int width = options.outWidth;// 实际宽度
            int height = options.outHeight;
            int inSampleSize = 1;
            // 宽高大于显示大小
            if (width > reqWidth || height > reqHeight) {
                int widthRadio = Math.round(width * 1.0f / reqWidth);
                int heightRadio = Math.round(height * 1.0f / reqHeight);
                // inSampleSize越大压的越厉害。
                // 在这里去大的更省内存。取消的会是一部分不显示
                inSampleSize = Math.min(widthRadio, heightRadio);//取小的不会导致小图片有一边出现空白
                // inSampleSize = Math.max(widthRadio, heightRadio);
            } else {

            }
            return inSampleSize;
        }

        /**
         * 根据图片需要显示的宽和高对图片进行压缩
         *
         * @param path   本地图片路径
         * @param width  控件的宽
         * @param height 控件的高（实际要现实的高）
         * @return 图片bitmap
         */
        public static Bitmap decodeSampledBitmapFromPath(String path, int width,
                                                         int height) {
            // 获取图片的宽和高，并不把图片加载到内存当中。
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);// options获得实际的宽和高
            options.inSampleSize = caculateInSampleSize(options, width, height);
            // 使用获取到的inSampleSize
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            return bitmap;
        }

        /**
         * 根据imageview的宽高，压缩path路径下的图片
         *
         * @param path      目标文件路径
         * @param imageView 图片控件
         * @return 压缩后的图片bitmap
         */
        public static Bitmap decodeSampledBitmapFromPath(String path, ImageView imageView) {
            // 获取图片的宽和高，并不把图片加载到内存当中。
            ImageSize imageViewSize = getImageViewSize(imageView);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);// options获得实际的宽和高
            options.inSampleSize = caculateInSampleSize(options, imageViewSize.width, imageViewSize.height);
            // 使用获取到的inSampleSize
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            return bitmap;
        }

        /**
         * getMaxWidth 是在api16以上，所以通过反射获取imgaeview的某个属性值
         *
         * @param object
         * @param fileName
         * @return
         */
        private static int getImageViewFieldVaule(Object object, String fileName) {

            int value = 0;

            try {
                Field field = ImageView.class.getDeclaredField(fileName);
                field.setAccessible(true);

                int fieldVaue = field.getInt(object);
                if (fieldVaue > 0 && fieldVaue < Integer.MAX_VALUE) {
                    value = fieldVaue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return value;
        }

        public static class ImageSize {
            public int width;
            public int height;
        }

        /**
         * 根据imageview获得适当的压缩的宽和高
         *
         * @param imageView
         * @return
         */
        public static ImageSize getImageViewSize(ImageView imageView) {
            ImageSize imageSize = new ImageSize();
            DisplayMetrics displayMetrics = imageView.getContext().getResources()
                    .getDisplayMetrics();

            ViewGroup.LayoutParams lp = imageView.getLayoutParams();
            // 获取宽
            // int width = (lp.width ==
            // LayoutParams.WRAP_CONTENT?0:imageView.getWidth());
            int width = imageView.getWidth();// 获取imageview的实际宽度

            if (width <= 0) {// 等于0说明宽度是wrap_ccontent或fill_parent
                width = lp.width;// 获取imagview在layout中申明的宽度
            }
            if (width <= 0) {
                // width = imageView.getMaxWidth();// 检查最大值
                width = getImageViewFieldVaule(imageView, "mMaxWidth");
            }

            if (width <= 0) {
                // 最不幸，宽度等于屏幕宽度
                width = displayMetrics.widthPixels;
            }

            int height = imageView.getHeight();// 获取imageview的实际宽度

            if (height <= 0) {
                height = lp.height;// 获取imagview在layout中申明的宽度
            }
            if (height <= 0) {
                // height = imageView.getMaxHeight();// 检查最大值
                height = getImageViewFieldVaule(imageView, "mMaxHeight");
            }

            if (height <= 0) {
                // 最不幸，宽度等于屏幕宽度
                height = displayMetrics.heightPixels;
            }

            imageSize.height = height;
            imageSize.width = width;

            return imageSize;
        }
    }


    /**
     * 5、得到缓存目录，并创建指定文件夹
     *
     * @param context    上下文
     * @param folderName 文件夹名 可以为空
     * @return 自定义缓存文件的路径
     */
    public static String getDiskCacheDir(Context context, String folderName) {
        File cachePath = getCacheDir(context);
        if (folderName.length() > 0) {
            File file = new File(cachePath, folderName);
            if (!file.exists()) {
                file.mkdir();
            }
            return file.getAbsolutePath();
        } else
            return cachePath.getAbsolutePath();
    }

    /**
     * 6、获取到当前应用程序的versionCode
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 7、获取到当前应用程序的versionName
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 8、json转成对象
     *
     * @param s: json
     * @return json对象
     */
    public static <T> T string2Object(Class<T> t, String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        T object = JSON.parseObject(jsonObject.toJSONString(), t);
        return object;
    }

    /**
     * 9、判断手机是否有SD卡。
     *
     * @return 有SD卡返回true，没有返回false。
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 10、判断是否连接网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 11、模糊图片
     *
     * @param bm
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurImage(Bitmap bm, Context context) {
        Bitmap bitmap = bm.copy(bm.getConfig(), true);
        final RenderScript rs = RenderScript.create(context);
        final Allocation input = Allocation.createFromBitmap(rs, bm, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(20 /* e.g. 3.f */);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }

    /**
     * 12、获取应用缓存图片大小
     *
     * @param context 上下文
     * @return 文件大小 byte
     */
    public static long getCacheSize(Context context) {
        long leng = 0;
        File cacheDir = getCacheDir(context);
        File[] listFiles = cacheDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].getName().endsWith(".png") ||
                    listFiles[i].getName().endsWith(".jpg")
                    || listFiles[i].getName().endsWith(".jpeg") || listFiles[i].getName().endsWith(".bmp"))
                leng += listFiles[i].length();
        }
        return leng;
    }

    /**
     * 13、获取缓存目录
     *
     * @param context 上下文
     * @return 缓存目录File对象
     */
    public static File getCacheDir(Context context) {
        File root = null;
        if (hasSDCard() || !Environment.isExternalStorageRemovable()) {
            root = context.getExternalCacheDir();
        } else {
            root = context.getCacheDir();
        }
        return root;
    }

    /**
     * 14、删除缓存目录下指定文件
     *
     * @param context
     * @param type    0:删除所有文件 1：删除图片
     */
    public static void deleteAllCacheImageFile(Context context, int type) {
        File fileDir = getCacheDir(context);
        File[] listFiles = fileDir.listFiles();
        if (type == 1) {
            for (int i = 0; i < listFiles.length; i++) {
                String name = listFiles[i].getName().toLowerCase();
                if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".bmp")) {
                    listFiles[i].delete();
                }
            }
        } else {
            for (int i = 0; i < listFiles.length; i++) {
                listFiles[i].delete();
            }
        }
    }

    /**
     * 14、删除缓存目录下所有文件
     *
     * @param context
     */
    public static void deleteAllCacheImageFile(Context context) {
        deleteAllCacheImageFile(context, 0);
    }

    /**
     * 15、尺寸转换类
     */
    public static class DimenTrans {
        /**
         * 将px值转换为dip或dp值，保证尺寸大小不变
         *
         * @param pxValue 像素值
         * @return
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }

        /**
         * 将dip或dp值转换为px值，保证尺寸大小不变
         *
         * @param dipValue dp尺寸
         * @return
         */
        public static int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }

        /**
         * 将px值转换为sp值，保证文字大小不变
         *
         * @param pxValue 像素值
         * @return
         */
        public static int px2sp(Context context, float pxValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5f);
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         *
         * @param spValue 字体大小值
         * @return
         */
        public static int sp2px(Context context, float spValue) {
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }
    }

    /**
     * 16、获得屏幕宽度
     *
     * @param context
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 17、获得屏幕高度
     *
     * @param context
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 18、拷贝文字到剪切板
     *
     * @param content 拷贝内容
     * @param context
     */
    public static void copy2Clipboard(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.toString().trim());
    }

    /**
     * 19、 字符串转unicode
     *
     * @param string 要转成unicode的字符串
     * @return
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\U" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 20、unicode 转字符串
     *
     * @param unicode 要转成字符串的unicode
     * @return
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\U");
        if (unicode.contains("\\u") || unicode.contains("\\U")) {

            for (int i = 1; i < hex.length; i++) {

                // 转换出每一个代码点
                int data = Integer.parseInt(hex[i], 16);

                // 追加成string
                string.append((char) data);
            }
        } else {
            return unicode;
        }

        return string.toString();
    }

    /**
     * 21、压缩bitmap
     *
     * @param image bitmap
     * @return 压缩过的bitmap
     */
    public static Bitmap bitmapCompress(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 300) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;
        // be
        // 1 / 2;// 设置缩放比例
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return bitmap;// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 22、获取状态栏高度，注意，要在onWindowFocusChanged中调用，在onCreate中获取高度为0
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 23、获取状态栏高度，获取是否存在NavigationBar注意，要在onWindowFocusChanged中调用，在onCreate中获取高度为0
     *
     * @param context
     * @return
     */
    //
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    /**
     * 24、获取状态栏高度，注意，要在onWindowFocusChanged中调用，在onCreate中获取高度为0
     *
     * @param mActivity
     * @return
     */
    public static int getStatusBarHeight2(Activity mActivity) {
        Resources resources = mActivity.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        //        Log.v("dbw", "Status height:" + height);
        return height;
    }

    /**
     * 25、获取底部导航状态栏高度，注意，要在onWindowFocusChanged中调用，在onCreate中获取高度为0
     *
     * @param mActivity
     * @return
     */
    public static int getNavigationBarHeight(Activity mActivity) {
        Resources resources = mActivity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 26、判断手机连接的网络类型(2G,3G,4G)
     */
    static class Constants {
        /**
         * Unknown network class
         */
        public static final int NETWORK_CLASS_UNKNOWN = 0;

        /**
         * wifi net work
         */
        public static final int NETWORK_WIFI = 1;

        /**
         * "2G" networks
         */
        public static final int NETWORK_CLASS_2_G = 2;

        /**
         * "3G" networks
         */
        public static final int NETWORK_CLASS_3_G = 3;

        /**
         * "4G" networks
         */
        public static final int NETWORK_CLASS_4_G = 4;

    }

    /**
     * 27.获取网络类型
     *
     * @param context 上下文
     * @return
     */
    public static int getNetWorkClass(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return Constants.NETWORK_CLASS_2_G;

            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return Constants.NETWORK_CLASS_3_G;

            case TelephonyManager.NETWORK_TYPE_LTE:
                return Constants.NETWORK_CLASS_4_G;

            default:
                return Constants.NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * 28、判断当前App处于前台还是后台状态
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBackground(final Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 29、判断当前是否是WIFI连接状态
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 30.进度框
     */
    public static class ProgressDialog {
        /**
         * 加载中的弹窗
         *
         * @param context    上下文
         * @param content    加载中的内容
         * @param canCelable 是否可以取消
         * @return
         */
        public static android.app.ProgressDialog showProgressDialog(Context context, String content, boolean canCelable) {
            android.app.ProgressDialog pd = new android.app.ProgressDialog(context);
            pd.setMessage(content);
            //            pd.setCancelable(canCelable);
            pd.setCanceledOnTouchOutside(canCelable);//点击其他区域不消失，点击返回键消失
            return pd;
        }

        public static android.app.ProgressDialog showProgressDialog(Context context, String content, boolean canCelable, boolean cancel) {
            android.app.ProgressDialog pd = new android.app.ProgressDialog(context);
            pd.setMessage(content);
            pd.setCancelable(cancel);
            pd.setCanceledOnTouchOutside(canCelable);//点击其他区域不消失，点击返回键消失
            return pd;
        }


        /**
         * 加载中的弹窗，默认内容：“加载中，请稍后...”
         *
         * @param context    上下文
         * @param canCelable 是否可以取消
         * @return
         */
        public static android.app.ProgressDialog showProgressDialog(Context context, boolean canCelable) {
            return showProgressDialog(context, "加载中,请稍后...", canCelable);
        }

        /**
         * 加载中的弹窗，默认内容：“加载中，请稍后...”，不能取消
         *
         * @param context 上下文
         * @return
         */
        public static android.app.ProgressDialog showProgressDialog(Context context) {
            return showProgressDialog(context, "加载中,请稍后...", false);
        }
    }

    /**
     * 31.传进来一个double格式化保留2位
     *
     * @param d
     * @return
     */
    public static String keep2Double(double d) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        if ((bd.doubleValue() - bd.intValue()) < 1e-10) {
            return bd.intValue() + "";
        } else {
            return (bd.doubleValue() + "");
        }
        //        return bd.doubleValue() + "";
        //                int di = (int) (d * 100);
        //                if ((di % 100) > 0)
        //                    return (new DecimalFormat("0.00").format(d));
        //                else {
        //                    return (int) d + "";
        //                }
        //        return (new DecimalFormat("0.00").format(d));
    }

    /**
     * 32.隐藏虚拟键盘
     */
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }

    /**
     * 33.显示虚拟键盘
     */
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    /**
     * 34.md5加密
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 35.自定义吐司
     */
    public static class ToastUtils {
        public static Toast mToast;
        public static TextView view;

        /**
         * 自定义吐司样式，方便统一修改
         *
         * @param context
         * @param msg
         */
        public static void showToast(Context context, String msg) {
            if (mToast == null) {
                mToast = new Toast(context);
            }
            if (view == null) {
                view = new TextView(context);
                view.setBackgroundResource(R.drawable.bg_toast);
                view.setPadding(20, 10, 20, 10);
                view.setTextSize(15);
                view.setTextColor(Color.parseColor("#ffffff"));
                view.setGravity(Gravity.CENTER);
            }
            view.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
            mToast.show();
        }

        /**
         * 自定义吐司样式，方便统一修改
         *
         * @param context
         * @param msgId
         */
        public static void showToast(Context context, int msgId) {

            showToast(context, context.getResources().getString(msgId));
        }


    }

    /**
     * 36.隐藏手机号中间4位
     *
     * @param code
     * @return
     * @throws Exception
     */
    public static String getCode(String code) throws Exception {
        if (code != null && !TextUtils.isEmpty(code) && code.length() == 11) {
            return code.substring(0, 3) + "****" + code.substring(code.length() - 4);
        } else
            throw new Exception("手机号码有误");
    }


    //    /**
    //     * 把日期转成 几天前，或几秒前
    //     *
    //     * @param time
    //     * @return
    //     */
    //    public static String timeChange(String time) {
    //        String finalTime = time;
    //        try {
    //            SimpleDateFormat format = new SimpleDateFormat(
    //                    "yyyy-MM-dd HH:mm:ss");
    //            // 此处会抛异常
    //            Date date = format.parse(time);
    //            // 获取毫秒数
    //            long longDate = date.getTime();
    //            long timex = System.currentTimeMillis() - longDate;
    //            finalTime = "";
    //            long second = timex / 1000;// 转成秒
    //            long timeVo = second;
    //            finalTime = timeVo + "秒前";
    //            // 大于60秒，要转成分
    //            if (second > 60) {
    //                timeVo = second / 60;
    //                finalTime = timeVo + "分钟前";
    //            }
    //            // 大于60分转成时
    //            if (timeVo > 60) {
    //                timeVo = timeVo / 60;
    //                finalTime = timeVo + "小时前";
    //                // 只有转成时，才执行大于24小时，转成天
    //                if (timeVo > 24) {
    //                    timeVo = timeVo / 24;
    //                    finalTime = timeVo + "天前";
    //                    // 大于30天，则直接使用日期
    //                    if (timeVo > 30) {
    //                        finalTime = time;
    //                    }
    //                }
    //            }
    //        } catch (ParseException e) {
    //            e.printStackTrace();
    //        }
    //        return finalTime;
    //    }

    /**
     * 37.把日期转成 几天前，或几秒前
     *
     * @param time
     * @return
     */
    public static String timeChangeShort(String time) {
        String finalTime = time;
        try {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy年MM月dd日 HH:mm:ss");
            // 此处会抛异常
            Date date = format.parse(time);
            // 获取毫秒数
            long longDate = date.getTime();
            // 当前时间
            Date currentDate = new Date(System.currentTimeMillis());
            SimpleDateFormat formatc = new SimpleDateFormat("yyyy-MM-dd");

            String CurrentFormat = formatc.format(currentDate);
            // 2015-09-28 00:00:00 格式当天
            Date parse = formatc.parse(CurrentFormat);
            // 得到当前时间和点天凌晨时间的差值
            long cha = currentDate.getTime() - parse.getTime();
            // 目标时间和当前时间的差值
            long timex = System.currentTimeMillis() - longDate;
            finalTime = "";
            // 如果目标时间和现在的差值小于得到当前时间和点天凌晨时间的差值，表示是在当天内发布的
            if (timex < cha) {
                long second = timex / 1000;// 转成秒
                long timeVo = second;
                //                finalTime = timeVo + "秒前";
                finalTime = "刚刚";
                // 大于60秒，要转成分
                if (second > 60) {
                    timeVo = second / 60;
                    finalTime = timeVo + "分钟前";
                }
                if (timeVo > 60) {
                    timeVo = timeVo / 60;
                    finalTime = timeVo + "小时前";
                }
            } else {
                // 如果目标时间和现在的差值大于得到当前时间和点天凌晨时间的差值，表示是在昨天之前发布的
                // 小时
                long timeVo = parse.getTime() - longDate;// 转成秒
                timeVo = timeVo / 1000 / 60 / 60;// 小时
                if (timeVo < 24) {
                    // 在24小时内都是昨天
                    finalTime = "昨天";
                } else {
                    // 否则显示日期
                    finalTime = time.substring(5, 17);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalTime;
    }

    /**
     * 38判断是否当前版本大于4.4，使用沉浸式
     *
     * @return
     */
    public static boolean isCanChenjin() {

        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    //    android.R.color.holo_blue_light,
    //    android.R.color.holo_orange_light,
    //    android.R.color.holo_green_light,
    //    android.R.color.holo_red_light

    /**
     * 39refreshLayout获得刷新颜色
     *
     * @return
     */
    public static int[] getRefreshColors() {
        return new int[]{android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        };
    }

    /**
     * 40传入bitmapo获得其大小
     *
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
        //            return bitmap.getAllocationByteCount();
        //        }
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
        //            return bitmap.getByteCount();
        //        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }


    /**
     * 41方法：从asset中获取文件并读取数据
     */

    public static String getFromAsset(Context context, String fileName) {
        //        InputStream resourceAsStream = context.getClassLoader().getResourceAsStream("assets/city.json");
        String result = "";
        try {
            InputStream in = context.getResources().getAssets().open(fileName);     //从Assets中的文件获取输入流
            byte[] bytes = new byte[in.available()];
            int read = in.read(bytes);
            result = new String(bytes);
            return result;

        } catch (IOException e) {


        }
        return "";
    }

    /**
     * 42方法：获取一个option
     */
    public static DisplayImageOptions getDisplayOption(int resId) {

        return getDisplayOption(resId, resId, resId);
    }

    /**
     * 43
     *
     * @param loadingId 加载中图片
     * @param errId     地址无效默认图片
     * @param failId    加载失败默认图片
     * @return
     */
    public static DisplayImageOptions getDisplayOption(int loadingId, int errId, int failId) {
        // 初始化ImageLoader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadingId) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(errId) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(failId) // 设置图片加载或解码过程中发生错误显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
                .imageScaleType(ImageScaleType.EXACTLY)//图片会缩放到目标大小完全
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                //                .displayer(new FadeInBitmapDisplayer(1000))//淡入
                .build(); // 创建配置过得DisplayImageOption对象
        return options;
    }

    /**
     * 初始化ImageLoad
     *
     * @param context
     */
    public static void configImageLoader(Context context) {
        // 初始化ImageLoader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
                .imageScaleType(ImageScaleType.EXACTLY)//图片会缩放到目标大小完全
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                //                .displayer(new FadeInBitmapDisplayer(1000))//淡入
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .discCacheFileCount(100)//缓存文件数
                .discCacheSize(50 * 1024 * 1024)//缓存大小
                .discCache(new UnlimitedDiscCache(ZhUtils.getCacheDir(context)))// 自定义缓存路径
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 44验证手机号码合法性
     *
     * @param tel
     * @return
     */
    public static boolean isMobile(String tel) {
        //          Pattern pattern = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))//d{8}$");
        if (tel.length() != 11) {
            return false;
        }

        Pattern pattern = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$");
        Matcher m = pattern.matcher(tel);
        return m.matches();
    }

    /**
     * 44验证手机号码合法性
     *
     * @param cardNo
     * @return
     */
    public static boolean isIDCard(String cardNo) {
        IdcardValidator iv = new IdcardValidator();
        return iv.isValidatedAllIdcard(cardNo);

    }

    /**
     * 45 拨打电话
     *
     * @param context
     * @param tel
     */
    public static void callPhone(Context context, String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 46为上下文注册广播,基础广播
     */
    //初始化广播
    public static BaseReceiver initReceiver(Context context, String tag) {
        BaseReceiver refreshReceiver = new BaseReceiver(context, tag);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(tag);
        context.registerReceiver(refreshReceiver, intentFilter);
        return refreshReceiver;
    }


    /**
     * 47发请求是创建所需的hashMap
     *
     * @param needLogin 是否登陆，需要自动添加userId,SessionToken,否则返回空的HashMap
     */
    //    public static HashMap<String, String> getLoginRequestMap(boolean needLogin) {
    //        HashMap<String, String> hashMap = new HashMap<>();
    //        if (needLogin) {
    //            if (MyApplication.getInstance().isLogin()) {
    //                hashMap.put("userID", MyApplication.getInstance().userInfo.userid + "");
    //                hashMap.put("sessionToken", MyApplication.getInstance().userInfo.token + "");
    //            }
    //        }
    //        hashMap.put("phoneModel", MyApplication.getInstance().getRelease().replaceAll(" ", ""));
    //        hashMap.put("v", MyApplication.getInstance().getVersionCode() + "");
    //
    //        return hashMap;
    //    }

    /**
     * 48获取手机型号
     *
     * @return
     */
    public static String getMobileType() {
        return Build.MODEL;
    }

    /**
     * 49获取SDK版本号
     *
     * @return
     */
    public static String getSdkType() {
        return Build.VERSION.SDK;
    }

    /**
     * 50获取系统型号
     *
     * @return
     */
    public static String getVersionType() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 51使用bigdecimal进行运算，保留2位小数点，返回double
     *
     * @return
     */
    public static class BigDecimalUse {

        // 默认运算精度,<span style="color:#ff0000;">保留有效数字6位</span>
        private static final int DEF_DIV_SCALE = 2;
        private static MathContext mc = new MathContext(DEF_DIV_SCALE,
                RoundingMode.HALF_UP); //RoundingMode.HALF_UP 舍入模式，四舍五入 向上

        //        /**
        //         * 测试除法
        //         */
        //        public static void main(String[] args) {
        //            double bb1 = 1;
        //            double bb2 = 3;
        //            String pattern = "0.000000";    //<span style="color:#ff0000;">保留小数点后6位</span>
        //            DecimalFormat df = new DecimalFormat(pattern);
        //
        //            double res = divide(bb1, bb2);
        //            System.out.println(df.format(res));
        //        }

        public static double add(double v1, double v2, boolean isDouble) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));    //用 new BigDecimal(String str)构造器才能构造出准确的Decimal

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return isDouble ? (b1.add(b2, mc)).doubleValue() : (b1.add(b2, mc)).intValue();
        }

        public static double subtract(double v1, double v2, boolean isDouble) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return isDouble ? (b1.subtract(b2, mc)).doubleValue() : (b1.subtract(b2, mc)).intValue();
        }

        public static double multiply(double v1, double v2, boolean isDouble) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return isDouble ? (b1.multiply(b2, mc)).doubleValue() : (b1.multiply(b2, mc)).intValue();
        }

        public static double divide(double v1, double v2) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return (b1.divide(b2, mc)).doubleValue();
        }

        public static double divide2(double v1, double v2) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return (b1.divide(b2, DEF_DIV_SCALE, RoundingMode.HALF_UP))
                    .doubleValue();
        }

        public static double divide3(double v1, double v2) {

            BigDecimal b1 = new BigDecimal(Double.toString(v1));

            BigDecimal b2 = new BigDecimal(Double.toString(v2));

            return (b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP))
                    .doubleValue();
        }

    }

    public static String getTime(Long seconds, String type) {//输入秒
        if (seconds != null) {
            Date d = new Date(seconds * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            return sdf.format(d);
        } else {
            return "";
        }
    }

    /**
     * 身份证认证方法
     */
    public static class IdcardValidator {
        protected String codeAndCity[][] = {{"11", "北京"}, {"12", "天津"},
                {"13", "河北"}, {"14", "山西"}, {"15", "内蒙古"}, {"21", "辽宁"},
                {"22", "吉林"}, {"23", "黑龙江"}, {"31", "上海"}, {"32", "江苏"},
                {"33", "浙江"}, {"34", "安徽"}, {"35", "福建"}, {"36", "江西"},
                {"37", "山东"}, {"41", "河南"}, {"42", "湖北"}, {"43", "湖南"},
                {"44", "广东"}, {"45", "广西"}, {"46", "海南"}, {"50", "重庆"},
                {"51", "四川"}, {"52", "贵州"}, {"53", "云南"}, {"54", "西藏"},
                {"61", "陕西"}, {"62", "甘肃"}, {"63", "青海"}, {"64", "宁夏"},
                {"65", "新疆"}, {"71", "台湾"}, {"81", "香港"}, {"82", "澳门"},
                {"91", "国外"}};
        private String cityCode[] = {"11", "12", "13", "14", "15", "21", "22",
                "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
                "64", "65", "71", "81", "82", "91"};
        //每位加权因子
        private int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        //第18位校验码
        private String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5",
                "4", "3", "2"};

        //验证所有的身份证的合法性
        public boolean isValidatedAllIdcard(String idcard) {
            if (idcard.length() == 15) {
                idcard = this.convertIdcarBy15bit(idcard);
            }
            return this.isValidate18Idcard(idcard);
        }

        //判断18位身份证的合法性
        public boolean isValidate18Idcard(String idcard) {
            if (idcard.length() != 18) {
                return false;
            }
            // 获取前17位
            String idcard17 = idcard.substring(0, 17);
            // 获取第18位
            String idcard18Code = idcard.substring(17, 18);
            char c[] = null;
            String checkCode = "";
            //是否都为数字
            if (isDigital(idcard17)) {
                c = idcard17.toCharArray();
            } else {
                return false;
            }
            if (null != c) {
                int bit[] = new int[idcard17.length()];
                bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);
                // 将和值与11取模得到余数进行校验码判断
                checkCode = getCheckCodeBySum(sum17);
                if (null == checkCode) {
                    return false;
                }
                // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
                if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                    return false;
                }
            }
            return true;
        }

        //验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断
        public boolean isValidate15Idcard(String idcard) {
            if (idcard.length() != 15) {
                return false;
            }
            if (isDigital(idcard)) {
                String provinceid = idcard.substring(0, 2);
                String birthday = idcard.substring(6, 12);
                int year = Integer.parseInt(idcard.substring(6, 8));
                int month = Integer.parseInt(idcard.substring(8, 10));
                int day = Integer.parseInt(idcard.substring(10, 12));
                // 判断是否为合法的省份
                boolean flag = false;
                for (String id : cityCode) {
                    if (id.equals(provinceid)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
                // 该身份证生出日期在当前日期之后时为假
                Date birthdate = null;
                try {
                    birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (birthdate == null || new Date().before(birthdate)) {
                    return false;
                }

                // 判断是否为合法的年份
                GregorianCalendar curDay = new GregorianCalendar();
                int curYear = curDay.get(Calendar.YEAR);
                int year2bit = Integer.parseInt(String.valueOf(curYear)
                        .substring(2));
                // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
                if ((year < 50 && year > year2bit)) {
                    return false;
                }

                // 判断是否为合法的月份
                if (month < 1 || month > 12) {
                    return false;
                }

                // 判断是否为合法的日期
                boolean mflag = false;
                curDay.setTime(birthdate); // 将该身份证的出生日期赋于对象curDay
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        mflag = (day >= 1 && day <= 31);
                        break;
                    case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                        if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                            mflag = (day >= 1 && day <= 29);
                        } else {
                            mflag = (day >= 1 && day <= 28);
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        mflag = (day >= 1 && day <= 30);
                        break;
                }
                if (!mflag) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        //将15位的身份证转成18位身份证
        public String convertIdcarBy15bit(String idcard) {
            String idcard17 = null;
            // 非15位身份证
            if (idcard.length() != 15) {
                return null;
            }

            if (isDigital(idcard)) {
                // 获取出生年月日
                String birthday = idcard.substring(6, 12);
                Date birthdate = null;
                try {
                    birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cday = Calendar.getInstance();
                cday.setTime(birthdate);
                String year = String.valueOf(cday.get(Calendar.YEAR));

                idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

                char c[] = idcard17.toCharArray();
                String checkCode = "";

                if (null != c) {
                    int bit[] = new int[idcard17.length()];

                    // 将字符数组转为整型数组
                    bit = converCharToInt(c);
                    int sum17 = 0;
                    sum17 = getPowerSum(bit);

                    // 获取和值与11取模得到余数进行校验码
                    checkCode = getCheckCodeBySum(sum17);
                    // 获取不到校验位
                    if (null == checkCode) {
                        return null;
                    }

                    // 将前17位与第18位校验码拼接
                    idcard17 += checkCode;
                }
            } else { // 身份证包含数字
                return null;
            }
            return idcard17;
        }

        //15位和18位身份证号码的基本数字和位数验校
        public boolean isIdcard(String idcard) {
            return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                    "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
        }

        //15位身份证号码的基本数字和位数验校
        public boolean is15Idcard(String idcard) {
            return idcard == null || "".equals(idcard) ? false : Pattern.matches(
                    "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
                    idcard);
        }

        //18位身份证号码的基本数字和位数验校
        public boolean is18Idcard(String idcard) {
            return Pattern
                    .matches(
                            "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$",
                            idcard);
        }

        //数字验证
        public boolean isDigital(String str) {
            return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
        }

        //将身份证的每位和对应位的加权因子相乘之后，再得到和值
        public int getPowerSum(int[] bit) {

            int sum = 0;

            if (power.length != bit.length) {
                return sum;
            }

            for (int i = 0; i < bit.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        sum = sum + bit[i] * power[j];
                    }
                }
            }
            return sum;
        }

        //将和值与11取模得到余数进行校验码判断
        public String getCheckCodeBySum(int sum17) {
            String checkCode = null;
            switch (sum17 % 11) {
                case 10:
                    checkCode = "2";
                    break;
                case 9:
                    checkCode = "3";
                    break;
                case 8:
                    checkCode = "4";
                    break;
                case 7:
                    checkCode = "5";
                    break;
                case 6:
                    checkCode = "6";
                    break;
                case 5:
                    checkCode = "7";
                    break;
                case 4:
                    checkCode = "8";
                    break;
                case 3:
                    checkCode = "9";
                    break;
                case 2:
                    checkCode = "x";
                    break;
                case 1:
                    checkCode = "0";
                    break;
                case 0:
                    checkCode = "1";
                    break;
            }
            return checkCode;
        }

        //将字符数组转为整型数组
        public int[] converCharToInt(char[] c) throws NumberFormatException {
            int[] a = new int[c.length];
            int k = 0;
            for (char temp : c) {
                a[k++] = Integer.parseInt(String.valueOf(temp));
            }
            return a;
        }
    }

}


