//package sdk;
//
//
//import java.util.HashMap;
//
//import sdk.utils.OkHttpActionBase;
//import sdk.utils.OnActionListener;
//import sdk.utils.PostAction;
//import sdk.utils.PostParam;
//
//public class ActionPost extends OkHttpActionBase {
//
//    private PostAction action;
//    private StringBuffer url;
//    private PostParam outParam;
//
//    public ActionPost(PostParam outParam) {
//        super();
//        this.outParam = outParam;
//    }
//
//    public PostAction getAction() {
//        return action;
//    }
//
//    public void setAction(PostAction action) {
//        this.action = action;
//    }
//
//    public PostParam getOutParam() {
//        return outParam;
//    }
//
//    public void setOutParam(PostParam outParam) {
//        this.outParam = outParam;
//    }
//
//    public ActionPost(int actionId, String actionName) {
//        action = new PostAction(actionId, BASE_URL + actionName);
//        url = new StringBuffer();
//        url.append(BASE_URL + actionName);
//        outParam = new PostParam();
//    }
//
//    public ActionPost(int actionId, String top, String actionName) {
//        action = new PostAction(actionId, top + actionName);
//        url = new StringBuffer();
//        url.append(top + actionName);
//        outParam = new PostParam();
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
