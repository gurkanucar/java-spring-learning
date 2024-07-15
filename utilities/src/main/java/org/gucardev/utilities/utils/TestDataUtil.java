package org.gucardev.utilities.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static <T> T initialize(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            initializeFields(instance);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize object of class: " + clazz.getName(), e);
        }
    }

    private static void initializeFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                Object value = getDefaultValue(field.getType());
                if (value != null) {
                    field.set(obj, value);
                }
            }
        }
    }

    private static Object getDefaultValue(Class<?> type) {
        if (type == boolean.class || type == Boolean.class) {
            return true;
        } else if (type == byte.class || type == Byte.class) {
            return (byte) 1;
        } else if (type == char.class || type == Character.class) {
            return 'A';
        } else if (type == short.class || type == Short.class) {
            return (short) 1;
        } else if (type == int.class || type == Integer.class) {
            return 1;
        } else if (type == long.class || type == Long.class) {
            return 1L;
        } else if (type == float.class || type == Float.class) {
            return 1.0f;
        } else if (type == double.class || type == Double.class) {
            return 1.0;
        } else if (type == String.class) {
            return "default";
        } else if (type == BigDecimal.class) {
            return BigDecimal.ONE;
        } else if (List.class.isAssignableFrom(type)) {
            return new ArrayList<>();
        } else if (type.isEnum()) {
            return type.getEnumConstants()[0];
        } else {
            return initialize(type);
        }
    }
}
