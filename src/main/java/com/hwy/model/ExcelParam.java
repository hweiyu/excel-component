package com.hwy.model;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 参数实体
 * @author huangweiyu
 * @date 2018/3/13 11:01
 **/
public class ExcelParam {

    /**
     * 文件名，暂未使用
     */
    private String fileName;

    /**
     * 反射com.hwy.model.BaseExcelData查询出的源表头信息
     */
    private List<ExcelOriginHeader> originHeaders;

    /**
     * 源excel表格数据
     */
    private List<? extends BaseExcelData> originDatas;

    /**
     * com.hwy.model.BaseExcelData中各属性在excel中的排序
     */
    private Map<Field, Integer> fieldSortMap;

    /**
     * excel页脚实体，暂未使用
     */
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

    public Map<Field, Integer> getFieldSortMap() {
        return fieldSortMap;
    }

    public void setFieldSortMap(Map<Field, Integer> fieldSortMap) {
        this.fieldSortMap = fieldSortMap;
    }
}
