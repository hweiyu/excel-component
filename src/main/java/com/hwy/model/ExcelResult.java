package com.hwy.model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:27
 **/
public class ExcelResult {

    private String message;

    private String result;

    private RowCol headerRowCol;

    private ExcelHeader[][] headers;

    private List<List<String>> datas;

    private HSSFWorkbook workbook;

    public ExcelResult() {
        result = Boolean.TRUE.toString();
        workbook = new HSSFWorkbook();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ExcelHeader[][] getHeaders() {
        return headers;
    }

    public void setHeaders(ExcelHeader[][] headers) {
        this.headers = headers;
    }

    public List<List<String>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public RowCol getHeaderRowCol() {
        return headerRowCol;
    }

    public void setHeaderRowCol(RowCol headerRowCol) {
        this.headerRowCol = headerRowCol;
    }
}
