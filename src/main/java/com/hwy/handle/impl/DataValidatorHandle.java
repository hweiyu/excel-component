package com.hwy.handle.impl;

import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandle;

/**
 * @author huangweiyu
 * @date 2018/3/13 11:38
 **/
public class DataValidatorHandle implements ExcelHandle {

    private ExcelParam param;

    private ExcelResult result;

    public DataValidatorHandle(ExcelParam param, ExcelResult result) {
        this.param = param;
        this.result = result;
    }

    @Override
    public void handle() {

    }
}
