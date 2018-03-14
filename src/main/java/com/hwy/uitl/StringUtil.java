package com.hwy.uitl;

/**
 * @author huangweiyu
 * @date 2018/3/13 17:25
 **/
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
