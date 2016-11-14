package sdk.utils;//package sdk;


import java.util.ArrayList;
import java.util.HashMap;

public class OkHttpAction extends OkHttpActionBase {
    private OkHttpRunnable action;
    private StringBuffer url;
    private int requestMethod;
    private OkHttpParam outParam;


    private ArrayList<String> urlParams;

    public ArrayList<String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(ArrayList<String> urlParams) {
        this.urlParams = urlParams;
    }

    public OkHttpAction(OkHttpParam outParam) {
        super();
        this.outParam = outParam;
    }

    public OkHttpRunnable getAction() {
        return action;
    }

    public void setAction(OkHttpRunnable action) {
        this.action = action;
    }

    public OkHttpParam getOutParam() {
        return outParam;
    }

    public void setOutParam(OkHttpParam outParam) {
        this.outParam = outParam;
    }

    public OkHttpAction(int requestMethod, int actionId, String actionName) {
        this(requestMethod, actionId, null, actionName);
    }

    public OkHttpAction(int requestMethod, int actionId, String top, String actionName) {
        this(requestMethod, actionId, top, actionName, null);
    }


    public OkHttpAction(int requestMethod, int actionId, String top, String actionName, ArrayList<String> urlParams) {
        this.requestMethod = requestMethod;
        if (top == null || top.equals("")) {
            action = new OkHttpRunnable(requestMethod, actionId, actionName);
        } else {
            action = new OkHttpRunnable(requestMethod, actionId, top + actionName);
        }
        url = new StringBuffer();
        url.append(top + actionName);
        this.urlParams = urlParams;
        outParam = new OkHttpParam();
    }

    public String doUrl(ArrayList<String> urls) {
        StringBuffer request = new StringBuffer();
        if (urls != null && urls.size() > 0) {
            for (String s : urls) {
                request.append("/" + s);
            }
            return request.toString();
        }
        return "";
    }

    @Override
    public void setParam(HashMap<String, String> params) {
        outParam.setParams(params);
    }

    public void setOnActionListener(OnActionListener listener) {
        action.setOnActionListener(listener);
    }

    public void startAction() {
        action.setUrl(url.toString() + doUrl(urlParams));
        outParam.getParams().putAll(getCommonParams());
        action.setParam(outParam);
        action.startAction();
    }

    public HashMap<String, String> getCommonParams() {
        HashMap<String, String> commonParams = new HashMap<>();
        commonParams.put("v", 2200 + "");
        return commonParams;

    }
}
