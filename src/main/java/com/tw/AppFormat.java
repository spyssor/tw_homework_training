package com.tw;

import java.util.regex.Pattern;

public class AppFormat {

    //检查添加学生信息的格式
    public static boolean checkNewStudent(String str){
        String pattern = "^([\\u4e00-\\u9fa5]{2,},)([1-9][0-9]*,)([\\u4e00-\\u9fa5]{2,}:([1-9][0-9]{0,1}|100),?)+$";
        try {
            boolean isMatched = Pattern.matches(pattern, str.replaceAll(" ", "").trim());
            if (isMatched){
                return true;
            }else{
                System.out.println("请按正确的格式输入(格式: 姓名,学号,学科:成绩, ...);");
                return false;
            }
        }catch (Exception e){
            System.out.println("请按正确的格式输入(格式: 姓名,学号,学科:成绩, ...);");
            return false;
        }
    }

    //检查输入学生学号的格式
    public static boolean checkStudentId(String str){
        String pattern = "^(([1-9][0-9]{0,}),?)+$";

        boolean isMatched = Pattern.matches(pattern, str.replaceAll(" ", "").trim());
        if (isMatched){
            return true;
        }else{
            System.out.println("请按正确的格式输入要打印的学生的学号(格式: 学号,学号, ...),按回车提交;");
            return false;
        }

    }
}
