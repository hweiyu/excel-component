package com.hwy.service;

import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelResult;

import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:35
 **/
public interface ExcelExportService {

    ExcelResult export(Class cls, List<? extends BaseExcelData> datas);
}
