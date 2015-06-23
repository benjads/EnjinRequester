package com.horusdev.enjinrequester.util;

import java.lang.reflect.Array;
import java.util.List;

@SuppressWarnings("unchecked")
public class Util {

    public static <T> T[] toArray(List<T> list, Class<T> clazz) {
        if (list.isEmpty())
            return createArray(clazz, 0);

        return list.toArray(createArray(clazz, list.size() - 1));
    }

    private static <T> T[] createArray(Class<T> type, int size){
        return (T[]) Array.newInstance(type, size);
    }
}
