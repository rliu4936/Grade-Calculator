package model;


import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<GradingGroup> gradingGroups;
    private double grade;
    private double lowestGrade;
    private double highestGrade;

    public Course(String courseName) {
        this.courseName = courseName;
        this.gradingGroups = new ArrayList<>();
        this.grade = 0;
    }

    public void calculateGrade() {
        int sumWeight = 0;
        double sum = 0;
        for (GradingGroup gg : gradingGroups) {
            sumWeight += gg.getWeight();
            sum += gg.getGrade() * gg.getWeight();
        }
        grade = sum / sumWeight;
        lowestGrade = sum / 100;
        highestGrade = 100 - (100 - grade) * sumWeight / 100;
    }

    public void addGradingGroup(String groupName, Integer weighting, Integer grade) {
        gradingGroups.add(new GradingGroup(groupName, weighting, grade));
        calculateGrade();
    }

    public String findLetterGrade() {
        if (grade >= 90) {
            return "A+";
        } else if (grade >= 85) {
            return "A";
        } else if (grade >= 80) {
            return "A-";
        } else if (grade >= 76) {
            return "B+";
        } else if (grade >= 72) {
            return "B";
        } else if (grade >= 68) {
            return "B-";
        } else if (grade >= 64) {
            return "C+";
        } else if (grade >= 60) {
            return "C";
        } else if (grade >= 55) {
            return "C-";
        } else if (grade >= 50) {
            return "D";
        }
        return "F";
    }

    public double getGrade() {
        return grade;
    }

    public double getLowestGrade() {
        return lowestGrade;
    }

    public double getHighestGrade() {
        return highestGrade;
    }

    public ArrayList<GradingGroup> getGradingGroups() {
        return gradingGroups;
    }

    public String getCourseName() {
        return courseName;
    }
}
