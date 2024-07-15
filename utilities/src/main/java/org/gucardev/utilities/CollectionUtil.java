package org.gucardev.utilities;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtil {

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T extends Comparable<T>> T findMax(List<T> list) {
        return Collections.max(list);
    }

    public static <T extends Comparable<T>> T findMin(List<T> list) {
        return Collections.min(list);
    }

    public static <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }

    public static <T> List<T> shuffle(List<T> list) {
        Collections.shuffle(list);
        return list;
    }

    public static <T> T findMax(List<T> list, Comparator<? super T> comparator) {
        return Collections.max(list, comparator);
    }
}
