package sdk.base;


import java.util.HashMap;

import sdk.utils.OnActionListener;
import sdk.utils.RequestParam;

public abstract class ActionBase {
    RequestParam param;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        ActionBase.debug = debug;
    }

    public static boolean debug = false;


    public RequestParam getParam() {
        return param;
    }

    public abstract void setParam(HashMap<String, String> params);

    public static final String BASE_URL = "";
    //    public static String PIC_BASE_URL = "http://192.168.0.106/";

    public abstract void setOnActionListener(OnActionListener listener);

    public abstract void startAction();

    public abstract void setString(String key, String value);


}
