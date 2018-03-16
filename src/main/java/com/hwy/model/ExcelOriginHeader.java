package com.hwy.model;

import com.hwy.uitl.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * excel源表头信息实体
 * @author huangweiyu
 * @date 2018/3/13 16:58
 **/
public class ExcelOriginHeader {

    /**
     * 注解com.hwy.anno.Header中对应的name
     */
    private String name;

    /**
     * 注解com.hwy.anno.Header中对应的sort
     */
    private int sort;

    public ExcelOriginHeader(String name, int sort) {
        this.name = name;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * 将name转换成List集合
     * @return
     */
    public List<String> getNameList() {
        return StringUtil.isEmpty(this.name)
                ? new ArrayList<>(0)
                : new ArrayList<>(Arrays.asList(this.name.split("[|]")));
    }
}
