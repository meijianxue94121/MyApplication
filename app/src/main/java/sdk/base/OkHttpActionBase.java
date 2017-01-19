package sdk.base;


import java.util.HashMap;

public abstract class OkHttpActionBase {

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        OkHttpActionBase.debug = debug;
    }

    public static boolean debug = false;

    public abstract void setParam(HashMap<String, String> params);

    public static final String BASE_URL = "";

    public abstract void setOnActionListener(OnActionListener listener);

    public abstract void startAction();


}
