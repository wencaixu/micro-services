package com.ares.seckill.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public class ObjectUtils {

    private static ObjectMapper om = new ObjectMapper();

    public static <T> T toObject(String json,Class<T> clazz){
        if(StringUtils.isBlank(json)){
            return null;
        }
        try {
            T t = om.readValue(json,clazz);
            return t;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("json to object exception %s",e.getMessage()));
        }
    }

    public static String toJson(Object object){
        if(null == object){
            return null;
        }
        try {
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("object to json exception %s",e.getMessage()));
        }
    }
}
