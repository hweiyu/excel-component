package com.hwy;

import com.hwy.bean.TestData;
import com.hwy.model.ExcelResult;
import com.hwy.service.ExcelExportService;
import com.hwy.service.impl.ExcelExportServiceImpl;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:19
 **/
public class ExcelTest {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream out = new FileOutputStream("D:/test.xls")) {
            ExcelExportService excelService = new ExcelExportServiceImpl();
            ExcelResult result = excelService.export(TestData.class, null);
            result.getWorkbook().write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
