package com.kermitye.yesdk.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.kermitye.yesdk.bean.ParameterizedTypeImpl;
import com.kermitye.yesdk.bean.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Horrarndoo on 2017/9/20.
 * <p>
 * Json转换工具类
 */
public class JsonUtils {

    private static Gson mGson = new Gson();


    /**
     * 针对Restful结果转换对象
     * @param json
     * @param claz
     * @param <T>
     * @return
     */
    public static <T> Result<T> fromJsonObject(String json, Class<T> claz) {
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{claz});
        return JsonUtils.deserialize(json, type);
    }

    /**
     * 针对Restful结果数组转换
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Result<List<T>> fromJsonArray(String json, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return JsonUtils.deserialize(json, type);
    }


    /**
     * 将对象准换为json字符串
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object) {
        return mGson.toJson(object);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> clz) throws JsonSyntaxException {
        return mGson.fromJson(json, clz);
    }


    /**
     * 将json转为List对象
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeToList(String json, Class<T> cls) {
        List<T> datalist = new ArrayList<T>();

        if (TextUtils.isEmpty(json)) {
            return datalist;
        }
        try {
//                    datalist = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                datalist.add(deserialize(elem, cls));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return datalist;
    }

    /**
     * 将json对象转换为实体对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject json, Class<T> clz) throws JsonSyntaxException {
        return mGson.fromJson(json, clz);
    }

    /**
     * 将jsonElement转换为实体对象
     * @param jsonElement
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T deserialize(JsonElement jsonElement, Class<T> cls) {
        return mGson.fromJson(jsonElement, cls);
    }


    /**
     * 将json字符串转换为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
        return mGson.fromJson(json, type);
    }
}
