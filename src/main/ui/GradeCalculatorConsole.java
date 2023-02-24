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
    static final List<String> listOfCommands = Arrays.asList("Add Course", "Delete Course", "Calculate Overall Average",
            "Display All Courses", "Quit");

    static Scanner sc;
    static boolean run;
    static Student student;

    // MODIFIES: this
    // EFFECTS: processes user input
    public GradeCalculatorConsole() {
        student = new Student();
        run = true;
        sc = new Scanner(System.in);

        while (run) {
            printCommands();
            inputCommand();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private static void inputCommand() {
        System.out.println("Please input your command (number): ");
        String command = sc.nextLine();
        switch (command) {
            case "1":
                addCourse();
                break;
            case "2":
                deleteCourse();
                break;
            case "3":
                printAverage();
                break;
            case "4":
                printCourses();
                break;
            case "5":
                run = false;
                break;
        }
    }

    // EFFECTS: displays menu of options to user
    private static void printCommands() {
        System.out.println("Commands:");
        for (int i = 0; i < listOfCommands.size(); i++) {
            System.out.println(i + 1 + ". " + listOfCommands.get(i));
        }
    }

    // EFFECTS: adds a course
    private static void addCourse() {
        System.out.println("Please input the course name");
        String courseName = sc.nextLine();
        Course course = new Course(courseName);
        student.addCourse(course);
        addGradingGroups(course);
    }

    private static void deleteCourse() {
        System.out.println("select which course to delete");
        int num = sc.nextInt();
        sc.nextLine();
        student.getListOfCourses().remove(num - 1);
    }

    // EFFECTS: Prints the average grade for all courses
    private static void printAverage() {
        if (student.getListOfCourses().size() == 0) {
            return;
        }
        double sum = 0;
        for (Course c : student.getListOfCourses()) {
            sum += c.getGrade();
        }
        sum /= student.getListOfCourses().size();
        System.out.println("Your average is: " + sum + "%");
    }

    // EFFECTS: Prints a list of all the added courses
    private static void printCourses() {
        if (student.getListOfCourses().isEmpty()) {
            System.out.println("You don't have any courses yet. Add a course!");
            return;
        }
        System.out.println("A list of your courses!");
        for (int i = 0; i < student.getListOfCourses().size(); i++) {
            System.out.print("Course " + (i + 1) + ": ");
            printCourse(student.getListOfCourses().get(i));
        }
        System.out.println();
    }

    // EFFECTS: Print a single course
    public static void printCourse(Course c) {
        System.out.println(c.getCourseName() + ": " + c.calculateGrade() + "%");
        if (c.getGradingGroups().isEmpty()) {
            System.out.println("\tNo Grading Group Yet!");
            return;
        }
        for (GradingGroup gg : c.getGradingGroups()) {
            gg.printGradingGroup();
        }
    }

    // EFFECTS: Add a grading group to the course
    public static void addGradingGroups(Course c) {
        System.out.println("input how many grading groups there are");
        int numberOfGradingGroups = sc.nextInt();
        System.out.println("List your grading groups <name> <weight (out of 100)> <grade (out of 100)>");
        for (int i = 0; i < numberOfGradingGroups; i++) {
            String groupName = sc.next();
            Integer weighting = sc.nextInt();
            Integer grade = sc.nextInt();
            sc.nextLine();
            c.addGradingGroup(groupName, weighting, grade);
        }
        c.setGrade(c.calculateGrade());
    }
}
