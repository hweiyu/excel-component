package com.hwy.uitl;

import com.hwy.model.ExcelResult;

/**
 * @author huangweiyu
 * @date 2018/3/13 11:33
 **/
public class ResultUtil {

    public static boolean isFail(ExcelResult result) {
        return Boolean.FALSE.toString().equals(result.getResult());
    }
}
