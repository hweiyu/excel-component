package com.hwy.handle;

import com.hwy.anno.Header;
import com.hwy.model.BaseExcelData;
import com.hwy.model.ExcelOriginHeader;
import com.hwy.model.ExcelParam;
import com.hwy.uitl.CollectionUitl;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 参数工厂
 * @author huangweiyu
 * @date 2018/3/13 15:01
 **/
public class ExcelParamFactory {

    /**
     * 参数实体
     */
    private ExcelParam param;

    /**
     * 创建参数实体
     * @param cls 继承com.hwy.model.BaseExcelData的类，表示可导出
     * @param datas 源数据
     * @return
     */
    public ExcelParam create(Class cls, List<? extends BaseExcelData> datas) {
        param = new ExcelParam();
        //设置源表头
        setOriginHeaders(cls);
        //设置源数据
        setOriginDatas(datas);
        return param;
    }

    /**
     * 设置源表头
     * @param cls 类
     */
    private void setOriginHeaders(Class cls) {
        //通过反射获取源表头
        List<ExcelOriginHeader> headers = getOriginHeaders(cls);
        param.setOriginHeaders(headers);
    }

    /**
     * 设置源数据
     * @param datas 源数据
     */
    private void setOriginDatas(List<? extends BaseExcelData> datas) {
        param.setOriginDatas(datas);
    }

    /**
     * 通过反射获取源表头
     * @param cls 要反射操作的类
     * @return
     */
    private List<ExcelOriginHeader> getOriginHeaders(Class cls) {
        List<ExcelOriginHeader> originHeaders = new ArrayList<ExcelOriginHeader>();
        if (null == cls) {
            return originHeaders;
        }
        Field[] fields = cls.getDeclaredFields();
        //key:注解Header对应的name，value:类BaseExcelData的属性
        Map<String, Field> nameFieldMap = new HashMap<>();
        for (Field field : fields) {
            Header header = field.getAnnotation(Header.class);
            //如果类中的属性有注解Header，说明是作为可导出使用
            if (null != header) {
                nameFieldMap.put(header.name(), field);
                originHeaders.add(new ExcelOriginHeader(header.name(), header.sort()));
            }
        }
        if (CollectionUitl.isListEmpty(originHeaders)) {
            return originHeaders;
        }
        //源表头排序
        sort(originHeaders);
        //key:注解Header对应的name，value:排序
        Map<String, Integer> nameSortMap = new HashMap<>();
        for (int i = 0; i < originHeaders.size(); i++) {
            nameSortMap.put(originHeaders.get(i).getName(), i);
        }
        //设置每个要导出的类的属性，在excel中的列坐标
        setFieldSortMap(nameFieldMap, nameSortMap);
        return originHeaders;
    }

    /**
     * 源表头排序
     * @param originHeaders 源表头数据
     */
    private void sort(List<ExcelOriginHeader> originHeaders) {
        originHeaders.sort(new Comparator<ExcelOriginHeader>() {
            @Override
            public int compare(ExcelOriginHeader o1, ExcelOriginHeader o2) {
                return o1.getSort() - o2.getSort();
            }
        });
    }

    /**
     * 设置类中的每个属性，在excel表格中应该放置的位置
     * @param nameFieldMap
     * @param nameSortMap
     */
    private void setFieldSortMap(Map<String, Field> nameFieldMap, Map<String, Integer> nameSortMap) {
        Map<Field, Integer> fieldSortMap = new HashMap<>();
        if (!nameFieldMap.isEmpty() && !nameSortMap.isEmpty()) {
            for (Map.Entry<String, Field> entry : nameFieldMap.entrySet()) {
                int index = nameSortMap.get(entry.getKey());
                fieldSortMap.put(entry.getValue(), index);
            }
        }
        param.setFieldSortMap(fieldSortMap);
    }
}
