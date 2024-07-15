package org.gucardev.nullSafe;

import java.util.function.Supplier;

public class NullSafeHelper {
    public static <T> T safeGet(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static <T> T safeGetOrElse(Supplier<T> supplier,T defaultValue) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return defaultValue;
        }
    }
}
