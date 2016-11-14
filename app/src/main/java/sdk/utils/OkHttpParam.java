package sdk.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * post请求发送的参数
 *
 * @author Zhang
 */
public class OkHttpParam {
    public HashMap<String, String> params;

    public OkHttpParam() {
        params = new HashMap<>();
    }

    public HashMap<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void addParam(String key, String value) {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.put(key, value);
    }


    public String getRequestParam() {
        if (params != null && params.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append('?');
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                sb.append(key);
                sb.append('=');
                sb.append(params.get(key));
                if (it.hasNext()) {
                    sb.append('&');
                }
            }
            return sb.toString();
        }
        return "";
    }

    public String postRequestParam() {
        StringBuffer request = new StringBuffer();
        if (params != null && params.size() > 0) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            Iterator<Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                request.append(next.getKey());
                request.append("=");
                request.append(next.getValue());
                request.append("&");
            }
            request.delete(request.length() - 1, request.length());
            return request.toString();
        }
        return "";
    }

    public String putRequestParam() {
        StringBuffer request = new StringBuffer();
        if (params != null && params.size() > 0) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            Iterator<Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {

                Entry<String, String> next = iterator.next();
                request.append(next.getKey());
                request.append("=");
                request.append(next.getValue());
                request.append("&");
            }
            request.delete(request.length() - 1, request.length());
            return request.toString();
        }
        return "";
    }

    public String delRequestParam() {
        StringBuffer request = new StringBuffer();
        if (params != null && params.size() > 0) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            Iterator<Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                request.append("/");
                request.append(next.getValue());
            }
            return request.toString();
        }
        return "";
    }


    public String patchRequestParam() {
        StringBuffer request = new StringBuffer();
        if (params != null && params.size() > 0) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            Iterator<Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                request.append(next.getKey());
                request.append("=");
                request.append(next.getValue());
                request.append("&");
            }
            request.delete(request.length() - 1, request.length());
            return request.toString();
        }
        return "";
    }
}
