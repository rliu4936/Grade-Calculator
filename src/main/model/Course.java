package model;


import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<GradingGroup> gradingGroups;
    private double grade;

    public Course(String courseName) {
        this.courseName = courseName;
        this.gradingGroups = new ArrayList<>();
        this.grade = 0;
    }

    public ArrayList<GradingGroup> getGradingGroups() {
        return gradingGroups;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double calculateGrade() {
        int grade = 0;
        for (GradingGroup gg : gradingGroups) {
            grade += gg.getGrade() * gg.getWeight();
        }
        return grade / 100.0;
    }

    public void addGradingGroup(String groupName, Integer weighting, Integer grade) {
        gradingGroups.add(new GradingGroup(groupName, weighting, grade));
        this.grade = calculateGrade();
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double n) {
        grade = n;
    }
}
