//package sdk.utils;
//
//import java.util.HashMap;
//import java.util.Iterator;
//
///**
// * 传入key和value，且以地址的方式输出字符串:username=aaa&password=admin
// *
// * @author Administrator
// */
//public class GetParam extends RequestParam {
//
//    public void addParam(String key, String value) {
//        if (null == params) {
//            params = new HashMap<String, String>();
//        }
//
//        params.put(key, value);
//    }
//
//    @Override
//    public String toString() {
//        if (params != null) {
//            StringBuffer sb = new StringBuffer();
//            sb.append('?');
//            Iterator<String> it = params.keySet().iterator();
//            while (it.hasNext()) {
//                String key = it.next();
//                sb.append(key);
//                sb.append('=');
//                sb.append(params.get(key));
//                if (it.hasNext()) {
//                    sb.append('&');
//                }
//            }
//            return sb.toString();
//        }
//        return "";
//    }
//
//    public String getRequestParam() {
//        if (params != null && params.size() > 0) {
//            StringBuffer sb = new StringBuffer();
//            sb.append('?');
//            Iterator<String> it = params.keySet().iterator();
//            while (it.hasNext()) {
//                String key = it.next();
//                sb.append(key);
//                sb.append('=');
//                sb.append(params.get(key));
//                if (it.hasNext()) {
//                    sb.append('&');
//                }
//            }
//            return sb.toString();
//        }
//        return "";
//    }
//
//
//}
