package com.tw;

import java.util.Scanner;

public class AppMain {
    //胡宇帆,2012,数学:88,语文:80
    //艾岩,2012,数学:80,语文:100
    //景诗文,2014,数学:55,英语:99
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        boolean isExit = false;
        while (!isExit){
            printHomePage();
            switch (sc.nextInt()){
                case 1 :
                    System.out.println("请输入学生信息(格式: 姓名,学号,学科:成绩, ...)，按回车提交:");
                    String input = sc.next();
                    if (AppFormat.checkNewStudent(input)) {
                        init(input);
                        break;
                    } else {
                        System.out.println("请按正确的格式输入(格式: 姓名,学号,学科:成绩, ...)，按回车提交:");
                        break;
                    }
                case 2 :
                    System.out.println("请输入要打印的学生的学号(格式: 学号,学号, ...)，按回车提交:");
                    String str = sc.next();
                    if (AppFormat.checkStudentId(str)) {
                        ScoreList.printScoreList(str);
                        break;
                    } else {
                        System.out.println("请按正确的格式输入要打印的学生的学号(格式: 学号,学号, ...)，按回车提交:");
                        break;
                    }
                case 3 :
                    isExit = true;
                    break;
                default:
                    break;
            }
        }

    }

    private static void printHomePage(){
        System.out.println("请输入你的选择(1~3):");
        System.out.println("1. 添加学生");
        System.out.println("2. 生成成绩单");
        System.out.println("3. 退出");
    }

    private static void init(String input){
        String studentId = Utils.getStudentId(input);
        if (!ScoreList.isInclude(studentId)) {
            Student student = new Student(studentId, Utils.getStudentName(input), Utils.getScores(input));

            student.setTotalScore(Utils.calcTotalScore(student));
            CourseList.tryAdd(student);
            ScoreList.record(student);

            System.out.println("学生" + student.getName() + "的成绩被添加");
        } else {
            System.out.println("已存在该学号，请检查输入学号！");
        }
    }
}
