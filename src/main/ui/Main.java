package ui;


import model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final List<String> listOfCommands = Arrays.asList("Add Course", "Delete Course", "Quit");
    static ArrayList<Course> listOfCourses = new ArrayList<Course>();
    static boolean run = true;

    public static void main(String[] args) throws IOException {
        while (run) {
            clearScreen();
            printCourses();
            printCommands();
            inputCommand();

        }
    }

    private static void clearScreen() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    private static void inputCommand() {
        System.out.println("Please input your command (number or full string command): ");
        System.out.println();
        String command = scanner.nextLine();

        switch (command) {
            case "1":
                addCourse();
                break;
            case "2":
                deleteCourse();
                break;
            case "3":
                run = false;
                break;

        }
    }

    private static void printCommands() {
        System.out.println("Commands:");
        for (int i = 0; i < listOfCommands.size(); i++) {
            System.out.println(i + 1 + ". " + listOfCommands.get(i));
        }
    }

    private static void addCourse() {
        System.out.println("Please input the course name");
        String courseName = scanner.nextLine();
        Course course = new Course(courseName);
        listOfCourses.add(course);
        course.addGradingGroups(scanner);
    }

    private static void deleteCourse() {
        System.out.println("select which course to delete");
        int num = scanner.nextInt();
        listOfCourses.remove(num - 1);
    }

    private static void printCourses() {
        if (listOfCourses.isEmpty()) {
            System.out.println("You don't have any courses yet. Add a course!");
            return;
        }
        System.out.println("A list of your courses!");
        for (int i = 0; i < listOfCourses.size(); i++) {
            System.out.print("Course " + (i + 1) + ": ");
            listOfCourses.get(i).printCourse();
        }
        System.out.println();
    }
}
