package com.tw;


import java.util.Map;

public class Student {

    private String id;
    private String name;
    private Map<String, String> scores;
    private double totalScore;

    public Student(String id, String name, Map<String, String> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getScores() {
        return scores;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
}
