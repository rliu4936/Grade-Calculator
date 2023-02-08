package model;


import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String courseName;
    private ArrayList<GradingGroup> gradingGroups;

    public Course(String courseName) {
        this.courseName = courseName;
        this.gradingGroups = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void printCourse() {
        System.out.println(courseName + ": " + calculateGrade() + "%");
        if (gradingGroups.isEmpty()) {
            System.out.println("\tNo Grading Group Yet!");
            return;
        }
        for (GradingGroup gg : gradingGroups) {
            gg.printGradingGroup();
        }
    }

    public void addGradingGroups(Scanner sc) {
        System.out.println("input how many grading groups there are");
        int numberOfGradingGroups = sc.nextInt();
        System.out.println("List your grading groups <name> <weight (out of 100)> <grade (out of 100)>");
        for (int i = 0; i < numberOfGradingGroups; i++) {
            String groupName = sc.next();
            Integer weighting = sc.nextInt();
            Integer grade = sc.nextInt();
            addGradingGroup(groupName, weighting, grade);
        }
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
    }


}
