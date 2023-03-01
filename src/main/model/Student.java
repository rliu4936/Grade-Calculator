package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// A Student has a list of courses that they are currently taking or finished taking
public class Student {
    private ArrayList<Course> listOfCourses;

    public Student() {
        listOfCourses = new ArrayList<Course>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student s = (Student) o;
        return listOfCourses.equals(s.getListOfCourses());
    }

    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    // MODIFIES: this
    // EFFECTS: Adds a course to the listofCourses
    public void addCourse(Course c) {
        listOfCourses.add(c);
    }

    // EFFECTS: Calculates the average mark of a course
    public double calculateAverage() {
        double sum = 0;
        for (Course c : listOfCourses) {
            sum += c.getGrade();
        }
        sum /= listOfCourses.size();
        return sum;
    }

    // REQUIRES: a valid index i
    // MODIFIES: this
    // EFFECTS: Removes a course from student
    public void deleteCourse(int i) {
        listOfCourses.remove(i);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("listOfCourses", listOfCourses);

        return json;
    }
}
