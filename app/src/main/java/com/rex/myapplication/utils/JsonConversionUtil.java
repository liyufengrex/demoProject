package com.rex.myapplication.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description : 关于Gson处理减少内存消耗整合为统一调用类
 * Created by Mc on 2017/9/23.
 * Job number：135033
 * Phone ：18601413765
 * Email：wangyue@syswin.com
 * Person in charge : Mc
 * Leader：Mc
 */

public class JsonConversionUtil {

    private static Gson mGson = new Gson();

    private static Gson getGson() {
        return mGson == null ? (mGson = new Gson()) : mGson;
    }

    /**
     * transform src into json
     * @param src input object what want convert to json
     * @return json string
     */
    public static String toJson(Object src) {
        if (src == null){
            return "";
        }
        return getGson().toJson(src);
    }

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified class.
     * @param json the string from which the object is to be deserialized
     * @param cls the class of T
     * @param <T> the type of the desired object
     * @return an object of type T from the string.
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            if (TextUtils.isEmpty(json)){
                return null;
            }
            return getGson().fromJson(json, cls);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified class.
     * @param json the string from which the object is to be deserialized
     * @param <T> the type of the desired object
     * @return an object of type List<T> from the string.
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        try {
            if (TextUtils.isEmpty(json)){
                return null;
            }
            List<T> list =  new ArrayList<>();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            if (null != array && array.size() > 0) {
                for (final JsonElement elem : array) {
                    list.add(getGson().fromJson(elem, cls));
                }
            }
            return list;
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static <T> List<T> fromJsonListforArray(String json, Class<T[]> type) {
        try {
            if (TextUtils.isEmpty(json)){
                return null;
            }
            T[] list = getGson().fromJson(json,type);
            return list == null ? null : Arrays.asList(list);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified class.
     * @param json the string from which the object is to be deserialized
     * @param <T> the type of the desired object
     * @return an object of type List<Map<String, T>> from the string.
     */
    public static <T> List<Map<String, T>> fromJsonListMap(String json) {
        try {
            if (TextUtils.isEmpty(json)){
                return null;
            }
            return getGson().fromJson(json, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * This method deserializes the Json read from the specified reader into an object of the
     * specified class.
     * @param json the string from which the object is to be deserialized
     * @param <T> the type of the desired object
     * @return an object of type Map<String, T> from the string.
     */
    public static <T> Map<String, T> fromJsonMap(String json) {
        try {
            if (TextUtils.isEmpty(json)){
                return null;
            }
            return getGson().fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public static String disableHtmlEscaping(Object obj) {
        if (obj == null){
            return "";
        }
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        return builder.create().toJson(obj);
    }
}
