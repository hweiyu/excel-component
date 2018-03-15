package com.hwy.bean;

import com.hwy.anno.Header;
import com.hwy.model.BaseExcelData;

/**
 * 时间|上午|语文
 * 时间|上午|英文
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

    @Header(name = "时间|上午|英文", sort = 2)
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

    public TestData(String course1, String course2, String course3, String course4, String course5,
                    String course6, String course7, String course8, String other1, String other2) {
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.course6 = course6;
        this.course7 = course7;
        this.course8 = course8;
        this.other1 = other1;
        this.other2 = other2;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    public String getCourse4() {
        return course4;
    }

    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    public String getCourse5() {
        return course5;
    }

    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    public String getCourse6() {
        return course6;
    }

    public void setCourse6(String course6) {
        this.course6 = course6;
    }

    public String getCourse7() {
        return course7;
    }

    public void setCourse7(String course7) {
        this.course7 = course7;
    }

    public String getCourse8() {
        return course8;
    }

    public void setCourse8(String course8) {
        this.course8 = course8;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }
}
