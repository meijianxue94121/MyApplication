//package sdk;
//
//
//import java.util.HashMap;
//
//import sdk.base.ActionBase;
//import sdk.utils.OnActionListener;
//import sdk.utils.PutAction;
//import sdk.utils.PutParam;
//
//public class ActionPut extends ActionBase {
//
//    private PutAction action;
//    private StringBuffer url;
//    private PutParam outParam;
//
//    public ActionPut(PutParam outParam) {
//        super();
//        this.outParam = outParam;
//    }
//
//    public PutAction getAction() {
//        return action;
//    }
//
//    public void setAction(PutAction action) {
//        this.action = action;
//    }
//
//    public PutParam getOutParam() {
//        return outParam;
//    }
//
//    public void setOutParam(PutParam outParam) {
//        this.outParam = outParam;
//    }
//
//    public ActionPut(int actionId, String actionName) {
//        action = new PutAction(actionId, BASE_URL + actionName );
//        url = new StringBuffer();
//        url.append(BASE_URL + actionName);
//        outParam = new PutParam();
//    }
//
//    public ActionPut(int actionId, String top, String actionName) {
//        action = new PutAction(actionId, top + actionName );
//        url = new StringBuffer();
//        url.append(top + actionName);
//        outParam = new PutParam();
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
