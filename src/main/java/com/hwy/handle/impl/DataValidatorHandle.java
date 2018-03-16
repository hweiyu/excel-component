package com.hwy.handle.impl;

import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandle;

/**
 * 数据校验处理类
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
        //todo 1.校验参数正确性
        //todo 2.校验实体是否可导出，只有继承com.hwy.model.BaseExcelData的才允许导出
    }
}
