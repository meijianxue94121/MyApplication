package com.example.dojoy.myapplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullBooleanAsFalse;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

/**
 * Created by dojoy on 2016/11/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
public class FastJsonTest {
    String title;
    String sTitle;
    Integer iNumber;
    Double dNumber;
    Long lNumber;
    ArrayList<Fast> sArrayList;
    ArrayList<Integer> iArrayList;


    private static ArrayList<Fast> gets() {
        ArrayList<Fast> arrayList = new ArrayList<>();
        arrayList.add(Fast.builder().a("哈哈哈").build());
        arrayList.add(Fast.builder().a("哈哈哈").build());
        arrayList.add(Fast.builder().a("哈哈哈").build());
        return arrayList;
    }

    public static void main(String[] args) {
        FastJsonTest jsonTest = FastJsonTest.builder().title("title").sArrayList(gets()).build();
        String jsonString = JSONObject.toJSONString(jsonTest,
                new SerializerFeature[]{WriteNullNumberAsZero,
                        WriteNullStringAsEmpty,
                        WriteNullBooleanAsFalse,
                        WriteMapNullValue,
                        QuoteFieldNames,
                        WriteNullListAsEmpty});
        System.out.println("Model转Json:" + jsonString);
        //        jsonString =jsonString.substring(1,jsonString.length()-1);
        //        JSONObject object = new JSONObject();
        //        object.put("a", jsonString);
        //        JSONObject object = JSON.parseObject(jsonString);
        //        FastJsonTest fastJsonTest = object.toJavaObject(FastJsonTest.class);
        FastJsonTest fastJsonTest = JSON.toJavaObject(JSON.parseObject(jsonString), FastJsonTest.class);
        //        FastJsonTest fastJsonTest = object.getObject("a", FastJsonTest.class);
        System.out.println("将String转会json:" + "\nModel：" +
                fastJsonTest.toString() + "\n某个数据" + fastJsonTest.getSArrayList().toString());

    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Builder
class Fast {
    String a;
}

