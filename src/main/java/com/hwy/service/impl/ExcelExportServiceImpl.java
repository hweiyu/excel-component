package com.hwy.service.impl;

import com.hwy.handle.ExcelParamFactory;
import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandleChain;
import com.hwy.service.ExcelExportService;

import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:35
 **/
public class ExcelExportServiceImpl implements ExcelExportService {

    /**
     * excel导出功能
     * @param cls 实现bean，要求实现 com.hwy.model.BaseExcelData
     * @param datas 数据
     * @return
     */
    @Override
    public ExcelResult export(Class cls, List<? extends BaseExcelData> datas) {
        //封装参数
        ExcelParam param = new ExcelParamFactory().create(cls, datas);
        //封装返回
        ExcelResult result = new ExcelResult();
        //责任链处理，返回结果
        ExcelHandleChain chain = new ExcelHandleChain(param, result);
        return chain.result();
    }
}
