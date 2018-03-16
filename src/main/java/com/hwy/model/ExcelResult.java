package com.hwy.model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * 返回结果类
 * @author huangweiyu
 * @date 2018/3/13 10:27
 **/
public class ExcelResult {

    /**
     * 描述信息
     */
    private String message;

    /**
     * 返回结果 true:成功，false:失败
     */
    private String result;

    /**
     * 表头所占的行列
     */
    private RowCol headerRowCol;

    /**
     * 表头二维矩阵
     */
    private ExcelHeader[][] headers;

    /**
     * 数据
     */
    private List<List<String>> datas;

    /**
     * excel工作薄
     */
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
