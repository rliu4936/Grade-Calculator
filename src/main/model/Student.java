package model;

import java.util.ArrayList;

public class Student {
    private ArrayList<Course> listOfCourses;

    public Student() {
        listOfCourses = new ArrayList<Course>();
    }

    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    public void addCourse(Course c) {
        listOfCourses.add(c);
    }
}
