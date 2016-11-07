package sdk.utils;//package sdk;


import java.util.HashMap;

import sdk.OkHttpAction;
import sdk.OkHttpParam;
import sdk.base.ActionBase;

public class ActionOkHttp extends ActionBase {

    private OkHttpAction action;
    private StringBuffer url;
    private int requestMethod;
    private OkHttpParam outParam;

    public ActionOkHttp(OkHttpParam outParam) {
        super();
        this.outParam = outParam;
    }

    public OkHttpAction getAction() {
        return action;
    }

    public void setAction(OkHttpAction action) {
        this.action = action;
    }

    public OkHttpParam getOutParam() {
        return outParam;
    }

    public void setOutParam(OkHttpParam outParam) {
        this.outParam = outParam;
    }

    public ActionOkHttp(int requestMethod, int actionId, String actionName) {
        this.requestMethod = requestMethod;
        action = new OkHttpAction(requestMethod, actionId, BASE_URL + actionName);
        url = new StringBuffer();
        url.append(BASE_URL + actionName);
        outParam = new OkHttpParam();
    }

    public ActionOkHttp(int requestMethod, int actionId, String top, String actionName) {
        this.requestMethod = requestMethod;
        action = new OkHttpAction(requestMethod, actionId, top + actionName);
        url = new StringBuffer();
        url.append(top + actionName);
        outParam = new OkHttpParam();
    }

    public void setOnActionListener(OnActionListener listener) {
        action.setOnActionListener(listener);
    }

    public void startAction() {
        action.setParam(outParam);
        action.startAction();
    }

    public void setString(String key, String value) {
        outParam.addParam(key, value);
    }

    //@Override
    //    public String toString() {
    //        url.append(outParam.toString());
    //        String s1 = new String(url);
    //        String s2 = s1.replace("{\"", "?");
    //        String s3 = s2.replaceAll("\":\"", "=");
    //        String s4 = s3.replaceAll("\",\"", "&");
    //        String replace = s4.replace("\"}", "");
    //        url.delete(0, url.length());
    //        url.append(replace);
    //        return url.toString();
    //    }

    @Override
    public void setParam(HashMap<String, String> params) {
        outParam.setParams(params);
    }
}
