package com.tw;

import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class UtilsTest {

    private Student student;

    @Before
    public void init(){

        Map<String, String> scores = new HashMap<>();
        scores.put("英语", "88");
        scores.put("数学", "100");

        student = new Student("1", "张三", scores);
    }

    @Test
    public void should_method_get_right_student_id(){
        String input = "张三,2012,数学:88,语文:80";

        assertThat(Utils.getStudentId(input)).isEqualTo("2012");
    }

    @Test
    public void should_method_get_right_student_name() {
        String input = "张三,2012,数学:88,语文:80";

        assertThat(Utils.getStudentName(input)).isEqualTo("张三");
    }

    @Test
    public void should_method_get_right_student_scores() {
        String input = "张三,2012,数学:88,语文:80";

        Map<String, String> scores = Utils.getScores(input);
        assertThat(scores.get("数学")).isEqualTo("88");
        assertThat(scores.get("语文")).isEqualTo("80");
    }

    @Test
    public void should_method_calculate_total_score() {

        double totalScore = Utils.calcTotalScore(student);
        assertThat(totalScore).isEqualTo(188.0);

    }

    @Test
    public void should_method_calculate_average_score() {

        double totalScore = Utils.calcAverageScore(student);
        assertThat(totalScore).isEqualTo(94.0);
    }
}
