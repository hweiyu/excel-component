package com.hwy.model;

/**
 * @author huangweiyu
 * @date 2018/3/14 11:31
 **/
public class RowCol {

    private int row;

    private int col;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void rowColConvert() {
        int t = getRow();
        setRow(getCol());
        setCol(t);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
