package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseList {
    private static List<String> courses = new ArrayList<>();

    public static List<String> getCourses() {
        return courses;
    }

    public static void setCourses(List<String> courses) {
        CourseList.courses = courses;
    }

    public static void tryAdd(Student student){
        Map<String, String> scores = student.getScores();
        for (String course : scores.keySet()) {
            if (!courses.contains(course)) {
                courses.add(course);
            }
        }
    }

    public static void printCourseList(){
        StringBuilder sb = new StringBuilder();
        sb.append("姓名");
        for (String course : courses) {
            sb.append("|").append(course);
        }
        sb.append("|平均分|总分");
        System.out.println(sb.toString());
    }
}
