package com.hwy.handle.impl;

import com.hwy.model.ExcelHeader;
import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;
import com.hwy.handle.ExcelHandle;
import com.hwy.model.RowCol;
import com.hwy.uitl.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;


/**
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
        createWorkbook();
    }

    private void createWorkbook() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        createHeader(sheet, getHeaderStyle(workbook));
        createData(sheet, getDataStyle(workbook));
        result.setWorkbook(workbook);
    }

    private HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = getDataStyle(workbook);
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        return style;
    }

    private HSSFCellStyle getDataStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

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
                if (isHeaderAvailable(headers[i][j])) {
                    cell.setCellValue(headers[i][j].getName());
                }
            }
        }
        mergeHeader(sheet);
    }

    private void mergeHeader(HSSFSheet sheet) {
        CellRangeAddress range;
        RowCol rowCol = result.getHeaderRowCol();
        ExcelHeader[][] headers = result.getHeaders();
        int r = rowCol.getRow();
        int c = rowCol.getCol();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (isHeaderAvailable(headers[i][j]) && isMerge(headers[i][j])) {
                    //合并单元格，起始行, 终止行, 起始列, 终止列
                    range = new CellRangeAddress(i, i + headers[i][j].getRow() - 1, j, j + headers[i][j].getCol() - 1);
                    sheet.addMergedRegion(range);
                }
            }
        }
    }

    private boolean isMerge(ExcelHeader header) {
        return header.getRow() > 1 || header.getCol() > 1;
    }

    private boolean isHeaderAvailable(ExcelHeader header) {
        return null != header
                && StringUtil.isNotEmpty(header.getName())
                && header.isAvailable();
    }

    private void createData(HSSFSheet sheet, HSSFCellStyle style) {

    }

}
