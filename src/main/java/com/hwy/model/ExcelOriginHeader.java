package com.hwy.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 16:58
 **/
public class ExcelOriginHeader {

    private List<String> name;

    private int sort;

    public ExcelOriginHeader(String name, int sort) {
        this.name = Arrays.asList(name.split("[|]"));
        this.sort = sort;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
