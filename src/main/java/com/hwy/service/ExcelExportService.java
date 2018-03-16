package com.hwy.service;

import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelResult;

import java.util.List;

/**
 * excel导出服务
 * @author huangweiyu
 * @date 2018/3/13 10:35
 **/
public interface ExcelExportService {

    /**
     * excel导出功能
     * @param cls 实现bean，要求实现 com.hwy.model.BaseExcelData
     * @param datas 数据
     * @return
     */
    ExcelResult export(Class cls, List<? extends BaseExcelData> datas);
}
