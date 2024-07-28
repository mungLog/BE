package com.example.munglog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert to JSON", e);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
