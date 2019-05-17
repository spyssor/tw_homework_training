package com.tw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class StudentTest {

    private Map<String, String> scores;


    @Before
    public void init() {
        scores = new HashMap<>();
        scores.put("英语", "88");
        scores.put("数学", "100");
    }

    @Test
    public void should_Student_have_id_name_scores() {
        Student student = new Student("1", "张三", scores);

        Assert.assertEquals("1", student.getId());
        Assert.assertEquals("张三", student.getName());
        Assert.assertEquals("88", student.getScores().get("英语"));
        Assert.assertEquals("100", student.getScores().get("数学"));
    }

    @Test
    public void should_student_get_correct_totalScore() {
        Student student = new Student("1", "张三", scores);
        student.setTotalScore(165.55);

        assertThat(student.getTotalScore()).isEqualTo(165.55);
    }
}
