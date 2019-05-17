package com.tw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {

    public static String getStudentId(String input){
        String[] strings = input.trim().replaceAll(" ", "").split(",");
        return strings[1];
    }

    public static String getStudentName(String input) {
        String[] strings = input.trim().replaceAll(" ", "").split(",");
        return strings[0];
    }

    public static Map<String, String> getScores(String input) {

        String[] strings = input.replaceAll(" ", "").trim().split(",");
        Map<String, String> res = new HashMap<>();
        for (int i = 2; i < strings.length; i ++){
            String[] scores = strings[i].split(":");
            res.put(scores[0], scores[1]);
        }
        return res;
    }

    //计算每个学生的总成绩
    public static double calcTotalScore(Student student) {
        Map<String, String> scores = student.getScores();
        double sum = 0;
        Iterator<Map.Entry<String, String>> iterator = scores.entrySet().iterator();
        while (iterator.hasNext()) {
            sum += Double.parseDouble(iterator.next().getValue());
        }
        return sum;
    }

    //计算每个学生的平均成绩
    public static double calcAverageScore(Student student){
        Map<String, String> scores = student.getScores();
        double sum = 0;
        Iterator<Map.Entry<String, String>> iterator = scores.entrySet().iterator();
        while (iterator.hasNext()) {
            sum += Double.parseDouble(iterator.next().getValue());
        }
        return sum / scores.size();
    }
}
