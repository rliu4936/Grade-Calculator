package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student s;

    @BeforeEach
    void setup() {
        s = new Student();
    }

    @Test
    void testDeleteCourse() {
        Course c = new Course("CPSC 110");
        s.addCourse(c);
        assertEquals(1, s.getListOfCourses().size());

        s.deleteCourse(0);
        assertEquals(0, s.getListOfCourses().size());
    }

    @Test
    void testAddCourse() {
        Course c = new Course("MATH 100");
        s.addCourse(c);
        assertEquals(1, s.getListOfCourses().size());
    }

    @Test
    void testCalculateOverallAverage() {
        Course c1 = new Course("MATH 100");
        c1.addGradingGroup("Final", 100, 80);

        Course c2 = new Course("MATH 101");
        c2.addGradingGroup("Final", 100, 90);

        s.addCourse(c1);
        s.addCourse(c2);

        assertEquals(85, s.calculateAverage());
    }
}