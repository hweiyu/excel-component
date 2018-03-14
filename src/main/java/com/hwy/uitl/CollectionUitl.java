package com.hwy.uitl;

import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 14:07
 **/
public class CollectionUitl {

    public static <T> boolean isArrayEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    public static <T> boolean isArrayNotEmpty(T[] array) {
        return !isArrayEmpty(array);
    }

    public static <T> boolean isListEmpty(List<T> list) {
        return null == list || list.isEmpty();
    }
}
