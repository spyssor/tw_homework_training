package com.tw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.fest.assertions.api.Assertions.assertThat;

public class CourseListTest {

    private Student student1;
    private Student student2;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init(){

        System.setOut(new PrintStream(outContent));
        Map<String, String> scores = new HashMap<>();
        scores.put("英语", "88");
        scores.put("数学", "100");

        student1 = new Student("1", "张三", scores);

        Map<String, String> scores1 = new HashMap<>();
        scores.put("英语", "75");
        scores.put("数学", "90");
        scores1.put("语文", "80");
        student2 = new Student("2", "李四", scores1);
    }

    @Test
    public void should_course_list_has_not_repeat_course(){

        CourseList.tryAdd(student1);
        Assert.assertEquals(2, CourseList.getCourses().size());

        CourseList.tryAdd(student2);
        Assert.assertEquals(3, CourseList.getCourses().size());
    }

    @Test
    public void should_course_list_printed_correctly(){
        CourseList.setCourses(new ArrayList<>());
        CourseList.tryAdd(student1);
        CourseList.printCourseList();
        String res1 = "姓名|数学|英语|平均分|总分\r\n";
        assertThat(systemOut().endsWith(res1)).isTrue();

        CourseList.tryAdd(student2);
        CourseList.printCourseList();
        String res2 = "姓名|数学|英语|语文|平均分|总分\r\n";
        assertThat(systemOut().endsWith(res2)).isTrue();
    }

    private String systemOut() {
        return outContent.toString();
    }
}
