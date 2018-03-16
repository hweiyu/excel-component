package com.hwy.handle.impl;

import com.hwy.model.ExcelHeader;
import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandle;
import com.hwy.model.RowCol;
import com.hwy.uitl.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;


/**
 * excel工作薄处理类
 *
 * @author huangweiyu
 * @date 2018/3/13 11:40
 **/
public class WorkbookCreateHandle implements ExcelHandle {

    private ExcelResult result;

    public WorkbookCreateHandle(ExcelParam param, ExcelResult result) {
        this.result = result;
    }

    @Override
    public void handle() {
        //生成excel工作薄
        createWorkbook();
    }

    //生成工作薄
    private void createWorkbook() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //在excel中生成表头
        createHeader(sheet, getHeaderStyle(workbook));
        //在excel中生成数据
        createData(sheet, getDataStyle(workbook));
        result.setWorkbook(workbook);
    }

    /**
     * 表头样式
     * @param workbook 工作薄
     * @return
     */
    private HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = getDataStyle(workbook);
        //设置字体加粗
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        //设置边框
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        return style;
    }

    /**
     * 数据样式
     * @param workbook 工作薄
     * @return
     */
    private HSSFCellStyle getDataStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 创建表头
     * @param sheet sheet页
     * @param style 样式
     */
    private void createHeader(HSSFSheet sheet, HSSFCellStyle style) {
        HSSFRow row;
        HSSFCell cell;
        RowCol rowCol = result.getHeaderRowCol();
        ExcelHeader[][] headers = result.getHeaders();
        int r = rowCol.getRow();
        int c = rowCol.getCol();
        for (int i = 0; i < r; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < c; j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                //判断表头数据是否可用
                if (isHeaderAvailable(headers[i][j])) {
                    cell.setCellValue(headers[i][j].getName());
                }
            }
        }
        //合并表头
        mergeHeader(sheet);
    }

    /**
     * 合并表头
     * @param sheet sheet页
     */
    private void mergeHeader(HSSFSheet sheet) {
        CellRangeAddress range;
        RowCol rowCol = result.getHeaderRowCol();
        ExcelHeader[][] headers = result.getHeaders();
        int r = rowCol.getRow();
        int c = rowCol.getCol();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //判断当前表头单元是否可用，是否要合并
                if (isHeaderAvailable(headers[i][j]) && isMerge(headers[i][j])) {
                    //合并单元格，起始行, 终止行, 起始列, 终止列
                    range = new CellRangeAddress(i, i + headers[i][j].getRow() - 1, j, j + headers[i][j].getCol() - 1);
                    sheet.addMergedRegion(range);
                }
            }
        }
    }

    /**
     * 判断表头单元是否要合并
     * @param header 表头单元
     * @return
     */
    private boolean isMerge(ExcelHeader header) {
        return header.getRow() > 1 || header.getCol() > 1;
    }

    /**
     * 判断表头单元是否可用
     * @param header
     * @return
     */
    private boolean isHeaderAvailable(ExcelHeader header) {
        return null != header
                && StringUtil.isNotEmpty(header.getName())
                && header.isAvailable();
    }

    /**
     * 创建数据
     * @param sheet sheet页
     * @param style 样式
     */
    private void createData(HSSFSheet sheet, HSSFCellStyle style) {
        RowCol rowCol = result.getHeaderRowCol();
        int startRow = rowCol.getRow();
        List<List<String>> datas = result.getDatas();
        for (List<String> data : datas) {
            HSSFRow row = sheet.createRow(startRow);
            for (int i = 0; i < data.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(data.get(i));
            }
            startRow++;
        }
    }

}
