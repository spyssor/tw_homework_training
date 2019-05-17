package com.tw;

import org.junit.Assert;
import org.junit.Test;

public class AppFormatTest {

    @Test
    public void should_student_message_is_correct_format() throws Exception {
        String input1 = "张三,2012,数学:88";
        String input2 = "张三,2012,数学:88,语文:100";
        String input3 = "张三,2012,数学:88,语文:100,";
        String input4 = "张三，2012，数学:88，语文:100";
        String input5 = "张三,2012,数学： 88,语文:100";
        String input6 = "zhangsan,2012,数学:88,语文:100";
        String input7 = "张三,huhu,数学:88,语文:100";
        String input8 = "张三,2012,数学:200,语文:100";

        Assert.assertTrue(AppFormat.checkNewStudent(input1));
        Assert.assertTrue(AppFormat.checkNewStudent(input2));
        Assert.assertTrue(AppFormat.checkNewStudent(input3));
        Assert.assertFalse(AppFormat.checkNewStudent(input4));
        Assert.assertFalse(AppFormat.checkNewStudent(input5));
        Assert.assertFalse(AppFormat.checkNewStudent(input6));
        Assert.assertFalse(AppFormat.checkNewStudent(input7));
        Assert.assertFalse(AppFormat.checkNewStudent(input8));
    }

    @Test
    public void should_student_id_is_correct_format() throws Exception {
        String input1 = "2016";
        String input2 = "2013080360019";
        String input3 = "2013080360019,2013080365487,";
        String input4 = "2013080360019,10135888,2013";
        String input5 = "2013080360019, 0123";
        String input6 = "2013080360019,,";
        String input7 = "2013080360019huyufan";
        String input8 = "2013080360019:2013,";

        Assert.assertTrue(AppFormat.checkStudentId(input1));
        Assert.assertTrue(AppFormat.checkStudentId(input2));
        Assert.assertTrue(AppFormat.checkStudentId(input3));
        Assert.assertTrue(AppFormat.checkStudentId(input4));
        Assert.assertFalse(AppFormat.checkStudentId(input5));
        Assert.assertFalse(AppFormat.checkStudentId(input6));
        Assert.assertFalse(AppFormat.checkStudentId(input7));
        Assert.assertFalse(AppFormat.checkStudentId(input8));
    }

}
