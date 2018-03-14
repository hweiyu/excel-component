package com.hwy.bean;

import com.hwy.anno.Header;
import com.hwy.model.BaseExcelData;

/**
 *
 * 时间|上午|语文
 * 时间|上午|数学
 * 时间|下午|英文
 * 时间|下午|物理
 * 时间|晚上|自习|化学
 * 时间|晚上|自习|生物
 * 时间|晚上|上课|政治
 * 时间|晚上|上课|历史
 * 其它|其它1
 * 其它|其它2
 *
 * @author huangweiyu
 * @date 2018/3/13 13:46
 **/
public class TestData extends BaseExcelData {

    @Header(name = "时间|上午|语文", sort = 1)
    private String course1;

    @Header(name = "时间|上午|数学", sort = 2)
    private String course2;

    @Header(name = "时间|下午|英文", sort = 3)
    private String course3;

    @Header(name = "时间|下午|物理", sort = 4)
    private String course4;

    @Header(name = "时间|晚上|自习|化学", sort = 5)
    private String course5;

    @Header(name = "时间|晚上|自习|生物", sort = 6)
    private String course6;

    @Header(name = "时间|晚上|上课|政治", sort = 7)
    private String course7;

    @Header(name = "时间|晚上|上课|历史", sort = 8)
    private String course8;

    @Header(name = "其它|其它1", sort = 9)
    private String other1;

    @Header(name = "其它|其它2", sort = 10)
    private String other2;
}
