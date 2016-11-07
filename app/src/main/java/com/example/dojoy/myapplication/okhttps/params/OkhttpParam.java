package com.example.dojoy.myapplication.okhttps.params;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/15.
 */
public class OkhttpParam {
    private Map<String, String> stringParams;
    /**
     * 文件相关的参数
     */
    private Map<String, String> fileParams;
    /**
     * 二进制数组
     */
    private List<ByteData> byteDatas;

    public List<ByteData> getByteDatas() {
        return byteDatas;
    }

    public void setByteDatas(List<ByteData> byteDatas) {
        this.byteDatas = byteDatas;
    }

    public Map<String, String> getFileParams() {
        return fileParams;
    }

    public void setFileParams(Map<String, String> fileParams) {
        this.fileParams = fileParams;
    }

    public Map<String, String> getStringParams() {
        return stringParams;
    }

    public void setStringParams(Map<String, String> stringParams) {
        this.stringParams = stringParams;
    }

    public void putString(String key, String value) {
        if (stringParams == null) {
            stringParams = new HashMap<String, String>();
        }
        stringParams.put(key, value);
    }

    /**
     * 把文件信息放入Map集合中
     *
     * @param fileKey  要上传的文件参数
     * @param fileName 要上传的文件名
     */
    public void putFileString(String fileKey, String fileName) {
        if (fileParams == null)
            fileParams = new HashMap<>();
        fileParams.put(fileKey, fileName);
    }

    @Override
    public String toString() {
        return stringParams.toString();
    }

    public String getRequestParam() {
        if (stringParams != null) {
            StringBuffer sb = new StringBuffer();
            sb.append('?');
            Iterator<String> it = stringParams.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                sb.append(key);
                sb.append('=');
                sb.append(stringParams.get(key));
                if (it.hasNext()) {
                    sb.append('&');
                }
            }
            return sb.toString();
        }
        return "";
    }


}
