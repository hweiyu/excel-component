package com.hwy.model;

/**
 * @author huangweiyu
 * @date 2018/3/13 15:04
 **/
public class ExcelHeader {
    private String name;
    private int col;
    private int row;
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
