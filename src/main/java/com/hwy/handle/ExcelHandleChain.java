package com.hwy.handle;

import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:31
 **/
public class ExcelHandleChain {

    private List<ExcelHandle> handles = new LinkedList<ExcelHandle>();

    public ExcelHandleChain() {}

    public ExcelHandleChain addHandle(ExcelHandle handle) {
        handles.add(handle);
        return this;
    }

    public ExcelResult doHandles(ExcelParam param, ExcelResult result) {
        for (ExcelHandle handle : handles) {
            preHandel(param, result);
            handle.handle();
            afterHandel(param, result);
        }
        return result;
    }

    private void  preHandel(ExcelParam param, ExcelResult result) {}

    private void  afterHandel(ExcelParam param, ExcelResult result) {}
}
