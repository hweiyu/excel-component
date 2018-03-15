package com.hwy.handle;

import com.hwy.anno.Header;
import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelOriginHeader;
import com.hwy.model.ExcelParam;
import com.hwy.uitl.CollectionUitl;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author huangweiyu
 * @date 2018/3/13 15:01
 **/
public class ExcelParamFactory {

    private ExcelParam param;

    public ExcelParam create(Class cls, List<? extends BaseExcelData> datas) {
        param = new ExcelParam();
        setOriginHeaders(cls);
        setFieldMap(cls);
        setOriginDatas(datas);
        return param;
    }

    private void setOriginHeaders(Class cls) {
        List<ExcelOriginHeader> headers = getOriginHeaders(cls);
        if (CollectionUitl.isListEmpty(headers)) {
            param.setOriginHeaders(headers);
            return;
        }
        Collections.sort(headers, new Comparator<ExcelOriginHeader>() {
            @Override
            public int compare(ExcelOriginHeader o1, ExcelOriginHeader o2) {
                return o1.getSort() - o2.getSort();
            }
        });
        param.setOriginHeaders(headers);
    }

    private void setOriginDatas(List<? extends BaseExcelData> datas) {
        param.setOriginDatas(datas);
    }

    private List<ExcelOriginHeader> getOriginHeaders(Class cls) {
        List<ExcelOriginHeader> originHeaders = new ArrayList<ExcelOriginHeader>();
        if (null == cls) {
            return originHeaders;
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Header header = field.getAnnotation(Header.class);
            if (null != header) {
                originHeaders.add(new ExcelOriginHeader(header.name(), header.sort()));
            }
        }
        return originHeaders;
    }

    private void setFieldMap(Class cls) {
        Field[] fields = cls.getDeclaredFields();
        Map<String, String> fieldMap = new HashMap<>();
        for (Field field : fields) {
            Header header = field.getAnnotation(Header.class);
            if (null != header) {
                fieldMap.put(header.name(), field.getName());
            }
        }
        param.setFieldMap(fieldMap);
    }
}
