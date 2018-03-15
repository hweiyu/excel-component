package com.hwy.handle.impl;

import com.hwy.model.*;
import com.hwy.handle.ExcelHandle;
import com.hwy.uitl.StringUtil;

import java.util.*;

/**
 * @author huangweiyu
 * @date 2018/3/13 11:39
 **/
public class DataAssembleHandle implements ExcelHandle {

    private ExcelParam param;

    private ExcelResult result;

    public DataAssembleHandle(ExcelParam param, ExcelResult result) {
        this.param = param;
        this.result = result;
    }

    @Override
    public void handle() {
        assembleHeaders();
        assembleDatas();
    }

    private void assembleHeaders() {
        RowCol rowCol = calHeadersRowCol();
        ExcelHeader[][] origins = getOriginHeaders(rowCol);
        //行列转换
        rowCol.rowColConvert();
        //矩阵行列转换
        ExcelHeader[][] news = getNewHeaders(rowCol, origins);
        //合并
        merge(rowCol, news);
        //设置有效的表头单元
        setAvailableHeaders(rowCol, news);
        result.setHeaderRowCol(rowCol);
        result.setHeaders(news);
    }

    private ExcelHeader[][] getNewHeaders(RowCol rowCol, ExcelHeader[][] origins) {
        int row = rowCol.getRow();
        int col = rowCol.getCol();
        //源表头矩阵
        ExcelHeader[][] news = new ExcelHeader[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                news[i][j] = origins[j][i];
            }
        }
        return news;
    }

    private ExcelHeader[][] getOriginHeaders(RowCol rowCol) {
        List<ExcelOriginHeader> originHeaders = param.getOriginHeaders();
        int row = rowCol.getRow();
        int col = rowCol.getCol();
        //源表头矩阵
        ExcelHeader[][] origins = new ExcelHeader[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                origins[i][j] = getExcelHeader(j, originHeaders.get(i));
            }
        }
        return origins;
    }

    private RowCol calHeadersRowCol() {
        List<ExcelOriginHeader> originHeaders = param.getOriginHeaders();
        int row = originHeaders.size();
        int col = 1;
        for (ExcelOriginHeader originHeader : originHeaders) {
            int size = originHeader.getName().size();
            if (size > col) {
                col = size;
            }
        }
        return new RowCol(row, col);
    }

    private void merge(RowCol rowCol, ExcelHeader[][] headers) {
        mergeRow(rowCol, headers);
        mergeCol(rowCol, headers);
    }

    private void mergeRow(RowCol rowCol, ExcelHeader[][] headers) {
        Set<String> nameSet;
        Map<String, Integer> count;
        String key;
        boolean added;
        int row = rowCol.getRow();
        int col = rowCol.getCol();
        for (int i = 0; i < row; i++) {
            nameSet = new HashSet<String>();
            count = new HashMap<String, Integer>();
            //计算每个名称出现次数
            for (int j = 0; j < col; j++) {
                if (!isHeaderEmpty(headers[i][j])) {
                    key = getVerticalName(i, j, headers);
                    added = nameSet.add(key);
                    count.put(key, added ? 1 : count.get(key) + 1);
                }
            }
            //设置每个名称占的列数
            for (int j = 0; j < col; j++) {
                if (!isHeaderEmpty(headers[i][j])) {
                    key = getVerticalName(i, j, headers);
                    headers[i][j].setCol(count.get(key));
                }
            }
        }
    }

    private String getVerticalName(int row, int col, ExcelHeader[][] headers) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i <= row; i++) {
            buf.append("|").append(headers[i][col].getName());
        }
        return buf.substring(1);
    }

    private void mergeCol(RowCol rowCol, ExcelHeader[][] headers) {
        int row = rowCol.getRow();
        int col = rowCol.getCol();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!isHeaderEmpty(headers[i][j])) {
                    int nextIndex = i + 1;
                    if (nextIndex < row && isHeaderEmpty(headers[nextIndex][j])) {
                        headers[i][j].setRow(row - i);
                    }
                }
            }
        }
    }

    private void setAvailableHeaders(RowCol rowCol, ExcelHeader[][] headers) {
        int row = rowCol.getRow();
        int col = rowCol.getCol();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!isHeaderEmpty(headers[i][j])) {
                    int preIndex = j - 1;
                    if (preIndex >= 0
                            && !isHeaderEmpty(headers[i][preIndex])
                            && !isHeaderEmpty(headers[i][j])) {
                        String preVerticalName = getVerticalName(i, preIndex, headers);
                        String curVerticalName = getVerticalName(i, j, headers);
                        headers[i][j].setAvailable(!preVerticalName.equals(curVerticalName));
                    }
                }
            }
        }
    }

    private ExcelHeader getExcelHeader(int index, ExcelOriginHeader originHeader) {
        int len = originHeader.getName().size();
        String name = len > index ? originHeader.getName().get(index) : null;
        return new ExcelHeader(name);
    }

    private boolean isHeaderEmpty(ExcelHeader excelHeader) {
        return null == excelHeader || StringUtil.isEmpty(excelHeader.getName());
    }

    private void assembleDatas() {
        Map<String, Integer> headerMap = getHeaderIndexMap();
        Map<String, String> fieldMap = param.getFieldMap();

    }

    private Map<String, Integer> getHeaderIndexMap() {
        Map<String, Integer> headerMap = new HashMap<>();
        List<ExcelOriginHeader> originHeaders = param.getOriginHeaders();
        int len = originHeaders.size();
        for (int i = 0; i < len; i++) {
            headerMap.put(join(originHeaders.get(i).getName()), i);
        }
        return headerMap;
    }

    private String join(List<String> list) {
        StringBuilder buf = new StringBuilder();
        for (String s : list) {
            buf.append("|").append(s);
        }
        return buf.substring(1);
    }
}
