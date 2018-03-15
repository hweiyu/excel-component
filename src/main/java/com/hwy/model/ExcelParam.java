package com.hwy.model;

import java.util.List;
import java.util.Map;

/**
 * @author huangweiyu
 * @date 2018/3/13 11:01
 **/
public class ExcelParam {

    private String fileName;

    private List<ExcelOriginHeader> originHeaders;

    private List<? extends BaseExcelData> originDatas;

    private Map<String, String> FieldMap;

    private ExcelFooter footer;

    public ExcelParam() {}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<? extends BaseExcelData> getOriginDatas() {
        return originDatas;
    }

    public void setOriginDatas(List<? extends BaseExcelData> originDatas) {
        this.originDatas = originDatas;
    }

    public ExcelFooter getFooter() {
        return footer;
    }

    public void setFooter(ExcelFooter footer) {
        this.footer = footer;
    }

    public List<ExcelOriginHeader> getOriginHeaders() {
        return originHeaders;
    }

    public void setOriginHeaders(List<ExcelOriginHeader> originHeaders) {
        this.originHeaders = originHeaders;
    }

    public Map<String, String> getFieldMap() {
        return FieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        FieldMap = fieldMap;
    }
}
