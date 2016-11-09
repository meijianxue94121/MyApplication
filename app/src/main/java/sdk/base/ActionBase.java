package sdk.base;


import java.util.HashMap;

import sdk.utils.OnActionListener;

public abstract class ActionBase {

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        ActionBase.debug = debug;
    }

    public static boolean debug = false;

    public abstract void setParam(HashMap<String, String> params);

    public static final String BASE_URL = "";
    //    public static String PIC_BASE_URL = "http://192.168.0.106/";

    public abstract void setOnActionListener(OnActionListener listener);

    public abstract void startAction();

    public abstract void setString(String key, String value);


}
