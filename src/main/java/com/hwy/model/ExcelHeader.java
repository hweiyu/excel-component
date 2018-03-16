package com.hwy.model;

/**
 * excel表头实体
 * @author huangweiyu
 * @date 2018/3/13 15:04
 **/
public class ExcelHeader {

    /**
     * 名称
     */
    private String name;

    /**
     * 占列数
     */
    private int col;

    /**
     * 占行数
     */
    private int row;

    /**
     * 是否可用
     */
    private boolean available;

    public ExcelHeader() {
        this(null);
    }

    public ExcelHeader(String name) {
        this.name = name;
        this.col = 1;
        this.row = 1;
        available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
