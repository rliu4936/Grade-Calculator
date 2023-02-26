package ui;


import model.Course;
import model.GradingGroup;
import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Grade Calculator Console Application
public class GradeCalculatorConsole {
    static final List<String> listOfCommands = Arrays.asList(
            "Add Course",
            "Delete Course",
            "Add Grading Group to Course",
            "Calculate Overall Average",
            "Display All Courses",
            "Quit");

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    static Scanner sc;
    static boolean run;
    static Student student;

    // MODIFIES: this
    // EFFECTS: Initialize a new Grade Calculator, and processes user input
    public GradeCalculatorConsole() {
        student = new Student();
        run = true;
        sc = new Scanner(System.in);

        while (run) {
            printCommands();
            inputCommand();
        }
    }

    // EFFECTS: Processes user command
    private static void inputCommand() {
        colorPrint("Please input your command", CYAN);
        String command = sc.nextLine();
        switch (command) {
            case "1":
                addCourse();
                break;
            case "2":
                deleteCourse();
                break;
            case "3":
                addGradingGroupToCourse();
                break;
            case "4":
                printAverage();
                break;
            case "5":
                printCourses();
                break;
            case "6":
                run = false;
                break;
        }
    }

    // EFFECTS: Processes inputs for adding grading groups to a course
    private static void addGradingGroupToCourse() {
        listAllCourses();
        colorPrint("Select which course you want to add the grading group to", CYAN);

        int num = sc.nextInt();
        sc.nextLine();
        addGradingGroups(student.getListOfCourses().get(num - 1));
    }

    // EFFECTS: displays menu of options to user
    private static void printCommands() {
        for (int i = 0; i < listOfCommands.size(); i++) {
            colorPrint((i + 1) + " -> " + listOfCommands.get(i), YELLOW);
        }
    }

    // MODIFIES: student
    // EFFECTS: Processes the inputs for adding a course
    private static void addCourse() {
        colorPrint("Please input the course name", CYAN);
        String courseName = sc.nextLine();
        Course course = new Course(courseName);
        student.addCourse(course);
        addGradingGroups(course);
    }

    // MODIFIES: student
    // EFFECTS: Processes user inputs for deleting a course
    private static void deleteCourse() {
        listAllCourses();
        if (student.getListOfCourses().size() == 0) {
            colorPrint("No courses to delete", RED);
            return;
        }
        colorPrint("select which course to delete", CYAN);
        int num = sc.nextInt();
        sc.nextLine();
        student.deleteCourse(num - 1);
    }

    // EFFECTS: Prints the average grade for all courses
    private static void printAverage() {
        if (student.getListOfCourses().size() == 0) {
            colorPrint("You do not have any courses yet!", RED);
            return;
        }
        colorPrint("Your average is: " + student.calculateAverage() + "%", GREEN);
    }

    // EFFECTS: Prints a list of all the added courses
    private static void printCourses() {
        if (student.getListOfCourses().isEmpty()) {
            colorPrint("You don't have any courses yet. Add a course!", RED);
            return;
        }
        for (int i = 0; i < student.getListOfCourses().size(); i++) {
            colorPrint("Course " + (i + 1) + ": ", GREEN);
            printCourse(student.getListOfCourses().get(i));
        }
    }

    // EFFECTS: Print a single course including each respectively list of grading groups
    public static void printCourse(Course c) {
        colorPrint(c.getCourseName() + ": " + c.getGrade() + "%" + " | " + c.findLetterGrade(), GREEN);
        if (c.getGradingGroups().isEmpty()) {
            colorPrint("\tNo Grading Group Yet!", RED);
            return;
        }
        for (GradingGroup gg : c.getGradingGroups()) {
            printGradingGroup(gg);
        }
        if (c.getLowestGrade() < c.getHighestGrade()) {
            colorPrint("Your lowest possible grade for this course is: " + c.getLowestGrade() + "%", GREEN);
            colorPrint("Your highest possible grade for this course is: " + c.getHighestGrade() + "%", GREEN);
        } else {
            colorPrint("Your course grade is: " + c.getGrade() + "%", GREEN);
        }
        System.out.println();
    }

    // EFFECTS: Add a grading group to the course
    public static void addGradingGroups(Course c) {
        colorPrint("Please input how many grading groups you want to add", CYAN);
        int numberOfGradingGroups = sc.nextInt();
        sc.nextLine();
        if (numberOfGradingGroups == 0) {
            return;
        }
        colorPrint("List your grading groups <name> <weight (out of 100)> <grade (out of 100)>", CYAN);
        for (int i = 0; i < numberOfGradingGroups; i++) {
            String groupName = sc.next();
            Integer weighting = sc.nextInt();
            Integer grade = sc.nextInt();
            sc.nextLine();
            c.addGradingGroup(groupName, weighting, grade);
        }
    }

    // EFFECTS: print out a single grading group
    public static void printGradingGroup(GradingGroup gc) {
        colorPrint("\t" + gc.getGroupName() + ": grade: " + gc.getGrade() + "% | " + "weight: "
                + gc.getWeight() + "%", GREEN);
    }

    // EFFECTS: print out all courses
    public static void listAllCourses() {
        List<Course> lc = student.getListOfCourses();
        for (int i = 0; i < lc.size(); i++) {
            colorPrint((i + 1) + " -> " + lc.get(i).getCourseName(), YELLOW);
        }
    }

    // EFFECTS: print out a string with colors
    public static void colorPrint(String s, String c) {
        System.out.println(c + s + RESET);
    }
}
