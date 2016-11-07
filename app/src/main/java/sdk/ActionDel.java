//package sdk;
//
//
//import java.util.HashMap;
//
//import sdk.base.ActionBase;
//import sdk.utils.DelAction;
//import sdk.utils.DelParam;
//import sdk.utils.OnActionListener;
//
//public class ActionOkHttp extends ActionBase {
//
//    private DelAction action;
//    private StringBuffer url;
//    private DelParam outParam;
//
//    public ActionOkHttp(DelParam outParam) {
//        super();
//        this.outParam = outParam;
//    }
//
//    public DelAction getAction() {
//        return action;
//    }
//
//    public void setAction(DelAction action) {
//        this.action = action;
//    }
//
//    public DelParam getOutParam() {
//        return outParam;
//    }
//
//    public void setOutParam(DelParam outParam) {
//        this.outParam = outParam;
//    }
//
//    public ActionOkHttp(int actionId, String actionName) {
//        action = new DelAction(actionId, BASE_URL + actionName);
//        url = new StringBuffer();
//        url.append(BASE_URL + actionName);
//        outParam = new DelParam();
//    }
//
//    public ActionOkHttp(int actionId, String top, String actionName) {
//        action = new DelAction(actionId, top + actionName);
//        url = new StringBuffer();
//        url.append(top + actionName);
//        outParam = new DelParam();
//    }
//
//    public void setOnActionListener(OnActionListener listener) {
//        action.setOnActionListener(listener);
//    }
//
//    public void startAction() {
//        action.setParam(outParam);
//        action.startAction();
//    }
//
//    public void setString(String key, String value) {
//        outParam.addParam(key, value);
//    }
//
//    @Override
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
//
//    @Override
//    public void setParam(HashMap<String, String> params) {
//        outParam.setParams(params);
//    }
//}
