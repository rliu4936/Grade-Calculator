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

    public ArrayList<GradingGroup> getGradingGroups() {
        return gradingGroups;
    }

    public String getCourseName() {
        return courseName;
    }

    public void calculateGrade() {
        int sumGrade = 0;
        int sumWeight = 0;
        double sum = 0;
        for (GradingGroup gg : gradingGroups) {
            sumGrade += gg.getGrade();
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

    public double getGrade() {
        return grade;
    }

    public double getLowestGrade() {
        return lowestGrade;
    }

    public double getHighestGrade() {
        return highestGrade;
    }
}
