package model;

import java.util.ArrayList;

// Many courses make up a student. A Course has a name, list of grading groups, grade, the lowes grade,
// and the highest grade
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

    // MODIFIES: this
    // EFFECTS: Calculates the grade based on the grading groups
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

    // REQUIRES: weighting and grade to be between 0 and 100
    // MODIFIES: this
    // EFFECTS: Adds a new grading group
    public void addGradingGroup(String groupName, Integer weighting, Integer grade) {
        gradingGroups.add(new GradingGroup(groupName, weighting, grade));
        calculateGrade();
    }

    // EFFECTS: Return the letter grade based on the percent
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

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setGradingGroups(ArrayList<GradingGroup> lgg) {
        gradingGroups = lgg;
    }

    // REQUIRES: Object o must have a class of Course
    // EFFECTS: returns true if the other course has the same name and the same grading groups as this
    @Override
    public boolean equals(Object o) {
        Course c = (Course) o;
        return courseName.equals(c.getCourseName()) && gradingGroups.equals(c.getGradingGroups());
    }
}
