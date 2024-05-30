package com.gucardev.springlearning.relationship_cases.lookup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class GsonAttributeConverter implements AttributeConverter<Map<String, String>, String> {

    private final Gson gson = new Gson();
    private final Type type = new TypeToken<Map<String, String>>() {
    }.getType();

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, type);
    }
}
