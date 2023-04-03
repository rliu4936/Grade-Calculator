package model;

import org.json.JSONObject;

import java.util.ArrayList;

// A Student has a list of courses that they are currently taking or finished taking
public class Student {
    private ArrayList<Course> listOfCourses;

    public Student() {
        listOfCourses = new ArrayList<Course>();
    }

    public ArrayList<Course> getListOfCourses() {
        return listOfCourses;
    }

    // MODIFIES: this
    // EFFECTS: Adds a course to the listofCourses
    public void addCourse(Course c) {
        listOfCourses.add(c);
        EventLog.getInstance().logEvent(new Event(c.getCourseName() + " is added to student"));
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
        String temp = listOfCourses.get(i).getCourseName();
        listOfCourses.remove(i);
        EventLog.getInstance().logEvent(new Event(temp + " is removed from student"));
    }

    // REQUIRES: a course in listOfCourses
    // MODIFIES: this
    // EFFECTS: Removes a course from student
    public void deleteCourse(Course c) {
        for (int i = 0; i < listOfCourses.size(); i++) {
            if (c == listOfCourses.get(i)) {
                listOfCourses.remove(i);
                i--;
            }
        }
        EventLog.getInstance().logEvent(new Event(c.getCourseName() + " is removed from student"));
    }

    // EFFECTS: returns this student as a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfCourses", listOfCourses);
        return json;
    }

    // REQUIRES: Object o must have a class of Student
    // EFFECTS: returns true if the other students has the same list of courses as this
    @Override
    public boolean equals(Object o) {
        Student s = (Student) o;
        return listOfCourses.equals(s.getListOfCourses());
    }
}
