package ui;


import model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final List<String> listOfCommands = Arrays.asList("Add Course", "Delete Course", "Quit");

    static Scanner sc;
    static ArrayList<Course> listOfCourses;
    static boolean run;

    public static void main(String[] args) throws IOException {
        listOfCourses = new ArrayList<Course>();
        run = true;
        sc = new Scanner(System.in);

        while (run) {
            printCourses();
            printAverage();
            printCommands();
            inputCommand();
        }
    }

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
        String courseName = sc.nextLine();
        Course course = new Course(courseName);
        listOfCourses.add(course);
        course.addGradingGroups(sc);
    }

    private static void deleteCourse() {
        System.out.println("select which course to delete");
        int num = sc.nextInt();
        sc.nextLine();
        listOfCourses.remove(num - 1);
    }

    private static void printAverage() {
        if (listOfCourses.size() == 0) {
            return;
        }
        double sum = 0;
        for (Course c : listOfCourses) {
            sum += c.getGrade();
        }
        sum /= listOfCourses.size();
        System.out.println("Your average is: " + sum + "%");
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
