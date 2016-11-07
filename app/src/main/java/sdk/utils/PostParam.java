//package sdk.utils;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map.Entry;
//import java.util.Set;
//
///**
// * post请求发送的参数
// *
// * @author Zhang
// */
//public class PostParam extends RequestParam {
//
//    //    private Map<String, String> params;
//    //
//    //    public Map<String, String> getParams() {
//    //        return params;
//    //    }
//    //
//    //    public void setParams(Map<String, String> params) {
//    //        this.params = params;
//    //    }
//
//    public void addParam(String key, String value) {
//        if (params == null) {
//            params = new HashMap<String, String>();
//        }
//        params.put(key, value);
//    }
//
//    @Override
//    public String toString() {
//        return params.toString();
//    }
//
//    public String getRequestParam() {
//        StringBuffer request = new StringBuffer();
//        if (params != null && params.size() > 0) {
//            Set<Entry<String, String>> entrySet = params.entrySet();
//            Iterator<Entry<String, String>> iterator = entrySet.iterator();
//            while (iterator.hasNext()) {
//
//                Entry<String, String> next = iterator.next();
//                if (next.getKey().equals("v")) {
//                    continue;
//                }
//                request.append(next.getKey());
//                request.append("=");
//                request.append(next.getValue());
//                request.append("&");
//            }
//            request.delete(request.length() - 1, request.length());
//            return request.toString();
//        }
//        return "";
//    }
//}
