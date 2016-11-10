//package sdk;
//
//
//import java.util.HashMap;
//
//import sdk.utils.OkHttpActionBase;
//import sdk.utils.GetAction;
//import sdk.utils.GetParam;
//import sdk.utils.OnActionListener;
//
//public class ActionGet extends OkHttpActionBase {
//
//    private GetAction action;
//    private GetParam outParam;
//    private StringBuffer url;
//
//    public GetParam getOutParam() {
//        return outParam;
//    }
//
//    public void setOutParam(GetParam outParam) {
//        this.outParam = outParam;
//    }
//
//    public ActionGet(int actionId, String actionName) {
//        action = new GetAction(actionId, BASE_URL + actionName);
//        url = new StringBuffer();
//        url.append(BASE_URL + actionName);
//        outParam = new GetParam();
//    }
//
//    public ActionGet(int actionId, String top, String actionName) {
//        action = new GetAction(actionId, top + actionName);
//        url = new StringBuffer();
//        url.append(top + actionName);
//        outParam = new GetParam();
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
//        return url.toString();
//    }
//
//    @Override
//    public void setParam(HashMap<String, String> params) {
//        outParam.setParams(params);
//    }
//}
