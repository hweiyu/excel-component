package com.hwy.uitl;

/**
 * @author huangweiyu
 * @date 2018/3/13 17:25
 **/
public class StringUtil {

    public static final String EMPTY_STRING = "";

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String join(String ... str) {
        StringBuilder buf = new StringBuilder();
        for (String s : str) {
            buf.append(s);
        }
        return buf.toString();
    }
}
