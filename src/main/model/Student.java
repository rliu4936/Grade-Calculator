package model;

import org.json.JSONObject;

import java.util.ArrayList;

// A Student has a list of courses that they are currently taking or finished taking
public class Student {
    private ArrayList<Course> listOfCourses;

    public Student() {
        listOfCourses = new ArrayList<Course>();
    }

    //EFFECTS: returns true if the other students has the same list of courses as this
    @Override
    public boolean equals(Object o) {
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

    // EFFECTS: returns this student as a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("listOfCourses", listOfCourses);

        return json;
    }
}
