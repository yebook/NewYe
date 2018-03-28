package com.kermitye.yesdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kermitye.yesdk.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kermitye on 2018/3/27.
 */

public class SpUtils {
    private static SharedPreferences sp;
    private static String mPreferencesName = "share_preference_default";

    /**
     * 获取List
     *
     * @param key sp key值
     * @param <T> item 类型
     * @return list
     */
    public static <T> List<T> getDataList(String key, Class<T> cls) {
        List<T> datalist = new ArrayList<T>();
        String strJson = getData(key, "");

        if (null == strJson) {
            return datalist;
        }
        try {
//                    datalist = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
            JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
            for (final JsonElement elem : array) {
                datalist.add(JsonUtils.deserialize(elem, cls));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return datalist;
    }


    public static void saveData(String key, Object data) {
        if (sp == null) {
            sp = BaseApplication.getContext().getSharedPreferences(mPreferencesName, Context
                    .MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            String str = JsonUtils.serialize(data);
            editor.putString(key, str);
        }
        editor.commit();
    }


    public static <T> T getData(String key, T defaultObject) {
        if (sp == null) {
            sp = BaseApplication.getContext().getSharedPreferences(mPreferencesName, Context
                    .MODE_PRIVATE);
        }
        Object object = null;
        if (defaultObject instanceof String) {
            object = sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            object = sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            object =  sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            object = sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            object = sp.getLong(key, (Long) defaultObject);
        }
        return (T) object;
    }

}
