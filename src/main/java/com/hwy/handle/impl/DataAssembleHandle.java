package com.hwy.handle.impl;

import com.hwy.anno.Header;
import com.hwy.model.*;
import com.hwy.handle.ExcelHandle;
import com.hwy.uitl.CollectionUitl;
import com.hwy.uitl.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 数据封装处理类
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
        //封装表头
        assembleHeaders();
        //封装数据
        assembleDatas();
    }

    private void assembleHeaders() {
        //计算表头所占的行列
        RowCol rowCol = calHeadersRowCol();
        //将表头转换成二维矩阵数组
        ExcelHeader[][] origins = getOriginHeaders(rowCol);
        //行列转换
        rowCol.rowColConvert();
        //矩阵行列转换，转换成excel中真正要生成的表头
        ExcelHeader[][] news = getNewHeaders(rowCol, origins);
        //合并
        merge(rowCol, news);
        //设置有效的表头单元
        setAvailableHeaders(rowCol, news);
        result.setHeaderRowCol(rowCol);
        result.setHeaders(news);
    }

    /**
     * 源表头矩阵转换成新表头矩阵
     * @param rowCol 行列
     * @param origins 源表头
     * @return
     */
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

    /**
     * 获取源表头二维矩阵
     * @param rowCol 行列
     * @return
     */
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

    /**
     * 计算表头所占的最大的行和列
     * @return
     */
    private RowCol calHeadersRowCol() {
        List<ExcelOriginHeader> originHeaders = param.getOriginHeaders();
        int row = originHeaders.size();
        int col = 1;
        for (ExcelOriginHeader originHeader : originHeaders) {
            int size = originHeader.getNameList().size();
            if (size > col) {
                col = size;
            }
        }
        return new RowCol(row, col);
    }

    /**
     * 合并表头
     * @param rowCol 行列
     * @param headers 表头矩阵
     */
    private void merge(RowCol rowCol, ExcelHeader[][] headers) {
        //合并每一行中，相同名称所占的列数
        mergeRow(rowCol, headers);
        //合并每一列中，名称所占的行数
        mergeCol(rowCol, headers);
    }

    /**
     * 合并每一行中，相同名称所占的列数
     * @param rowCol 行列
     * @param headers 表头矩阵
     */
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

    /**
     * 获取垂直方向名称，用"|"连接
     * @param row 行
     * @param col 列
     * @param headers 表头二维矩阵
     * @return
     */
    private String getVerticalName(int row, int col, ExcelHeader[][] headers) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i <= row; i++) {
            buf.append("|").append(headers[i][col].getName());
        }
        return buf.substring(1);
    }

    /**
     * 合并每一列中，名称所占的行数
     * @param rowCol 行列
     * @param headers 表头矩阵
     */
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

    /**
     * 设置表头单元的可用标记
     * @param rowCol 行列
     * @param headers 表头二维矩阵
     */
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
                        //同一行中，如果当前单元和前一个单元名称相同，则当前单元要设置成不可用
                        String preVerticalName = getVerticalName(i, preIndex, headers);
                        String curVerticalName = getVerticalName(i, j, headers);
                        headers[i][j].setAvailable(!preVerticalName.equals(curVerticalName));
                    }
                }
            }
        }
    }

    /**
     * 根据下标获取源表头单元
     * @param index 下标
     * @param originHeader 源表头
     * @return
     */
    private ExcelHeader getExcelHeader(int index, ExcelOriginHeader originHeader) {
        int len = originHeader.getNameList().size();
        String name = len > index ? originHeader.getNameList().get(index) : null;
        return new ExcelHeader(name);
    }

    /**
     * 表头单元是否为空
     * @param excelHeader 表头单元
     * @return
     */
    private boolean isHeaderEmpty(ExcelHeader excelHeader) {
        return null == excelHeader || StringUtil.isEmpty(excelHeader.getName());
    }

    /**
     * 封装数据
     */
    private void assembleDatas() {
        List<? extends BaseExcelData> originDatas = param.getOriginDatas();
        List<List<String>> datas = new ArrayList<>();
        RowCol rowCol = result.getHeaderRowCol();
        if (!CollectionUitl.isListEmpty(originDatas)) {
            Map<Field, Integer> fieldSortMap = param.getFieldSortMap();
            for (BaseExcelData originData : originDatas) {
                List<String> data = new ArrayList<>(Collections.nCopies(rowCol.getCol(), StringUtil.EMPTY_STRING));
                if (null != originData) {
                    for (Field field : fieldSortMap.keySet()) {
                        Object value = getFieldValue(field, originData);
                        if (null != value) {
                            data.set(fieldSortMap.get(field), String.valueOf(value));
                        }
                    }
                }
                datas.add(data);
            }
        }
        result.setDatas(datas);
    }

    /**
     * 反射获取属性对应的值
     * @param field 类属性
     * @param obj 类实体
     * @return
     */
    private Object getFieldValue(Field field, Object obj) {
        try {
            return getMethod(field, obj).invoke(obj);
        } catch (IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取类方法
     * @param field 类属性
     * @param obj 当前实体类
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethod(Field field, Object obj) throws NoSuchMethodException {
        Header header = field.getAnnotation(Header.class);
        return obj.getClass().getMethod(
                StringUtil.isEmpty(header.method())
                        ? getDefaultMethodName(field)
                        : header.method());
    }

    /**
     * 获取类属性对应的get方法
     * @param field 类属性
     * @return
     */
    private String getDefaultMethodName(Field field) {
        String name = field.getName();
        final String PRE = "get";
        String result = StringUtil.join(PRE, name.substring(0, 1).toUpperCase());
        if (name.length() > 1) {
            result = StringUtil.join(result, name.substring(1));
        }
        return result;
    }
}
