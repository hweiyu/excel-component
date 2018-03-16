package com.hwy;

import com.hwy.bean.TestData;
import com.hwy.model.ExcelHeader;
import com.hwy.model.ExcelResult;
import com.hwy.model.RowCol;
import com.hwy.service.ExcelExportService;
import com.hwy.service.impl.ExcelExportServiceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangweiyu
 * @date 2018/3/13 10:19
 **/
public class ExcelTest {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("D:/test.xls")) {
            ExcelExportService excelService = new ExcelExportServiceImpl();
            ExcelResult result = excelService.export(TestData.class, getTestDatas());
            printHeaders(result.getHeaderRowCol(), result.getHeaders());
            result.getWorkbook().write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void printHeaders(RowCol rowCol, ExcelHeader[][] headers) {
        for (int i = 0; i < rowCol.getRow(); i++) {
            for (int j = 0; j < rowCol.getCol(); j++) {
                ExcelHeader header = headers[i][j];
                System.out.print(header.getName() + "[" + header.getRow() + "," + header.getCol() + "]|");
            }
            System.out.println();
        }
    }

    private static List<TestData> getTestDatas() {
        List<TestData> result = new ArrayList<>(2);
        TestData data = new TestData(
                "语文value1",
                "英文value1",
                "英文value11",
                "物理value1",
                "化学value1",
                "生物value1",
                "政治value1",
                "历史value1",
                "其它1value1",
                "其它2value1"
        );
        result.add(data);
        data = new TestData(
                "语文value2",
                "英文value2",
                "英文value22",
                "物理value2",
                "化学value2",
                "生物value2",
                "政治value2",
                "历史value2",
                "其它1value2",
                "其它2value2"
        );
        result.add(data);
        return result;
    }
}
