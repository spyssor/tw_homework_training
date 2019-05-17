package com.tw;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class ScoreListTest {

    private Student student1;
    private Student student2;
    private Student student3;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        Map<String, String> scores = new HashMap<>();
        scores.put("英语", "85");
        scores.put("数学", "100");

        student1 = new Student("1", "张三", scores);

        Map<String, String> scores2 = new HashMap<>();
        scores2.put("英语", "80");
        scores2.put("数学", "70");
        student2 = new Student("2", "李四", scores2);

        Map<String, String> scores3 = new HashMap<>();
        scores3.put("英语", "60");
        scores3.put("数学", "60");

        student3 = new Student("1", "王五", scores3);
    }

    @Test
    public void should_score_list_record_new_student() {

        //由于一次性测试会发生冲突
        ScoreList.setScoreList(new HashMap<>());

        ScoreList.record(student1);
        assertThat(ScoreList.getScoreList().size()).isEqualTo(1);

        ScoreList.record(student2);
        assertThat(ScoreList.getScoreList().size()).isEqualTo(2);

        ScoreList.record(student3);
        assertThat(ScoreList.getScoreList().size()).isEqualTo(2);
        assertThat(systemOut().endsWith("已存在该学号，请检查输入学号！")).isTrue();
    }

    @Test
    public void should_score_list_contain_existed_id() {
        ScoreList.record(student1);
        assertThat(ScoreList.isInclude("1")).isTrue();
    }

    @Test
    public void should_score_list_print_scores_by_ids() throws Exception {
        ScoreList.setScoreList(new HashMap<>());
        CourseList.setCourses(new ArrayList<>());
        ScoreList.record(student1);
        CourseList.tryAdd(student1);
        student1.setTotalScore(Utils.calcTotalScore(student1));
        Method method = ScoreList.class.getDeclaredMethod("printScoreByIds", String.class);
        method.setAccessible(true);
        String input = "1";
        method.invoke(null, input);
        assertThat(systemOut().endsWith("张三|100|85|92.5|185.0\r\n")).isTrue();
    }

    @Test
    public void should_shell_sort_could_work_well() throws Exception {
        Method method = ScoreList.class.getDeclaredMethod("shellSort", Comparable[].class);
        method.setAccessible(true);
        Comparable[] arr = {5.0, 4.78, 2.25, 8.55, 7.3, 8.55};
        method.invoke(null, (Object) arr);
        Comparable[] newArr = {2.25, 4.78, 5.0, 7.3, 8.55, 8.55};
        assertThat(arr).isEqualTo(newArr);
    }

    @Test
    public void should_score_list_get_average_total_score() throws Exception {
        ScoreList.record(student1);
        CourseList.tryAdd(student1);
        student1.setTotalScore(Utils.calcTotalScore(student1));

        ScoreList.record(student2);
        CourseList.tryAdd(student2);
        student2.setTotalScore(Utils.calcTotalScore(student2));

        Method method = ScoreList.class.getDeclaredMethod("getAverageTotalScore");
        method.setAccessible(true);
        String res = (String) method.invoke(null);

        assertThat(res).isEqualTo("167.50");

    }

    @Test
    public void should_score_list_get_middle_total_score() throws Exception {
        ScoreList.record(student1);
        CourseList.tryAdd(student1);
        student1.setTotalScore(Utils.calcTotalScore(student1));

        ScoreList.record(student2);
        CourseList.tryAdd(student2);
        student2.setTotalScore(Utils.calcTotalScore(student2));

        Method method = ScoreList.class.getDeclaredMethod("getMidTotalScore");
        method.setAccessible(true);
        String res = (String) method.invoke(null);

        assertThat(res).isEqualTo("168");
    }

    private String systemOut() {
        return outContent.toString();
    }
}
