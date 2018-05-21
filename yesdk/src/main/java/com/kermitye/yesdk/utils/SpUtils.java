package com.kermitye.yesdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.kermitye.yesdk.base.BaseApplication;

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
        String strJson = getData(key, "");
        return JsonUtils.deserializeToList(strJson, cls);
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
