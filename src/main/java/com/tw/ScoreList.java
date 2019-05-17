package com.tw;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ScoreList {

    private static Map<String, Student> scoreList = new HashMap<>();

    public static void record(Student student){
        if (!scoreList.containsKey(student.getId())) {
            scoreList.put(student.getId(), student);
        } else {
            System.out.print("已存在该学号，请检查输入学号！");
        }
    }

    public static Map<String, Student> getScoreList() {
        return scoreList;
    }

    public static void printScoreList(String input){
        System.out.println("成绩单");
        CourseList.printCourseList();
        System.out.println("=============================================");
        printScoreByIds(input);
        System.out.println("=============================================");
        System.out.println("全班总分平均数: " + getAverageTotalScore());
        System.out.println("全班总分中位数: " + getMidTotalScore());
    }

    public static boolean isInclude(String studentId) {
        return scoreList.containsKey(studentId);
    }

    private static void printScoreByIds(String str) {
        str = str+",";
        String[] ids = str.trim().replaceAll(" ", "").split(",");
        for (String id : ids){
            for (String key : scoreList.keySet()) {
                if (scoreList.containsKey(id) && id.equals(key)){
                    StringBuilder sb = new StringBuilder();
                    Student student = scoreList.get(key);
                    sb.append(student.getName());
                    Map<String, String> scores = student.getScores();
                    for (String course : CourseList.getCourses()){
                        if (scores.containsKey(course)) {
                            sb.append("|").append(scores.get(course));
                        } else {
                            sb.append("|").append("没修");
                        }
                    }
                    sb.append("|").append(Utils.calcAverageScore(student));
                    sb.append("|").append(student.getTotalScore());
                    System.out.println(sb.toString());
                }
            }
        }
    }

    //获取总分的平均分
    private static String getAverageTotalScore() {
        if (scoreList.size() == 0){
            return "0";
        }else{
            Iterator<Map.Entry<String, Student>> iterator = scoreList.entrySet().iterator();
            double sum = 0;
            while (iterator.hasNext()) {
                sum += iterator.next().getValue().getTotalScore();
            }

            return new BigDecimal(sum).divide(new BigDecimal(scoreList.size()), 2, RoundingMode.HALF_UP).toString();
        }
    }

    //获取总分的中位分
    private static String getMidTotalScore() {
        if (scoreList.size() == 0) {
            return "0";
        } else {
            Double[] array = new Double[scoreList.size()];
            Iterator<Map.Entry<String, Student>> iterator = scoreList.entrySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                array[index] = iterator.next().getValue().getTotalScore();
                index ++;
            }

            shellSort(array);

            BigDecimal postMid = new BigDecimal(array[array.length / 2]);
            if ((array.length & 1) == 0) {
                BigDecimal preMid = new BigDecimal(array[array.length/2 -1]);
                return preMid.add(postMid).divide(new BigDecimal(2),2).toString();
            } else {
                return postMid.toString();
            }
        }
    }

    //希尔排序
    private static void shellSort(Comparable[] arr){

        int n = arr.length;
        int h = 1;

        // 计算递增序列: 1, 4, 13, 40, 121, 364, 1093...
        while(h < n/3){
            h = h*3+1;
        }

        while(h >= 1){
            //每个间隔情况下进行插入排序
            for (int i = h; i < n; i ++){
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j-h]) < 0; j-=h){
                    arr[j] = arr[j-h];
                }
                arr[j] = e;
            }

            h /= 3;
        }
    }
}
