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

    public double calculateAverage() {
        double sum = 0;
        for (Course c : listOfCourses) {
            sum += c.getGrade();
        }
        sum /= listOfCourses.size();
        return sum;
    }
}
