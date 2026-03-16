package com.stripeIntegration.payments.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class for JSON conversion operations
 * Provides methods to convert between JSON strings and Java objects
 */
@Slf4j
@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Converts a JSON string to a Java object of the specified type
     *
     * @param <T>       the type parameter for the class
     * @param jsonString the JSON string to convert
     * @param classType the class type to convert the JSON into
     * @return an instance of the specified class type, or null if conversion fails
     */
    public <T> T convertJsonToObject(String jsonString, Class<T> classType) {
        try {
            if (jsonString == null || jsonString.isEmpty()) {
                log.warn("JSON string is null or empty");
                return null;
            }
            return objectMapper.readValue(jsonString, classType);
        } catch (Exception e) {
            log.error("Error converting JSON string to object of type {}: {}", classType.getName(), e.getMessage(), e);
            return null;
        }
    }

    /**
     * Converts a Java object to a JSON string
     *
     * @param object the object to convert to JSON
     * @return the JSON string representation of the object, or null if conversion fails
     */
    public String convertObjectToJson(Object object) {
        try {
            if (object == null) {
                log.warn("Object is null");
                return null;
            }
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error converting object to JSON string: {}", e.getMessage(), e);
            return null;
        }
    }
}

