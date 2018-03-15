package com.hwy.service.impl;

import com.hwy.handle.ExcelParamFactory;
import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandleChain;
import com.hwy.handle.impl.DataAssembleHandle;
import com.hwy.handle.impl.DataValidatorHandle;
import com.hwy.handle.impl.WorkbookCreateHandle;
import com.hwy.service.ExcelExportService;

import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:35
 **/
public class ExcelExportServiceImpl implements ExcelExportService {

    @Override
    public ExcelResult export(Class cls, List<? extends BaseExcelData> datas) {
        ExcelParam param = new ExcelParamFactory().create(cls, datas);
        ExcelResult result = new ExcelResult();
        ExcelHandleChain chain = new ExcelHandleChain(param, result);
        return chain.result();
    }
}
