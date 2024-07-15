package org.gucardev.utilities.utils;

public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String join(String[] array, String delimiter) {
        return String.join(delimiter, array);
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }
}

