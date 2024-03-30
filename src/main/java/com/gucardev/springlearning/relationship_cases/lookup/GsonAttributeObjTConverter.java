package com.gucardev.springlearning.relationship_cases.lookup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.lang.reflect.Type;

@Converter
public class GsonAttributeObjTConverter<T> implements AttributeConverter<T, String> {

    private static final Gson gson = new Gson();
    private final Type type = new TypeToken<T>() {
    }.getType();

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, type);
    }
}
