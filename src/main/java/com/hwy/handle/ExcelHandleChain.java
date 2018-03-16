package com.hwy.handle;

import com.hwy.handle.impl.DataAssembleHandle;
import com.hwy.handle.impl.DataValidatorHandle;
import com.hwy.handle.impl.WorkbookCreateHandle;
import com.hwy.model.ExcelParam;
import com.hwy.model.ExcelResult;

import java.util.LinkedList;
import java.util.List;

/**
 * excel处理链
 * @author huangweiyu
 * @date 2018/3/13 10:31
 **/
public class ExcelHandleChain {

    /**
     * 处理类列表
     */
    private List<ExcelHandle> handles = new LinkedList<ExcelHandle>();

    /**
     * 传入参数
     */
    private ExcelParam param;

    /**
     * 返回结果
     */
    private ExcelResult result;

    public ExcelHandleChain(ExcelParam param, ExcelResult result) {
        this.param = param;
        this.result = result;
    }

    public ExcelHandleChain addHandle(ExcelHandle handle) {
        handles.add(handle);
        return this;
    }

    public ExcelResult doHandles() {
        for (ExcelHandle handle : handles) {
            preHandel(param, result);
            handle.handle();
            afterHandel(param, result);
        }
        return result;
    }

    public ExcelResult result() {
        this.addHandle(new DataValidatorHandle(param, result)).
                addHandle(new DataAssembleHandle(param, result)).
                addHandle(new WorkbookCreateHandle(param, result)).
                doHandles();
        return result;
    }

    private void  preHandel(ExcelParam param, ExcelResult result) {}

    private void  afterHandel(ExcelParam param, ExcelResult result) {}

}
