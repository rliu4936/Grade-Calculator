package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyModelTest {
    Student s;

    @BeforeEach
    void setup() {
        s = new Student();
    }

    @Test
    void testCalculateGrade() {
        Course c = new Course("MATH 100");

        c.addGradingGroup("Final", 50, 80);
        c.addGradingGroup("MT1", 25, 90);
        c.addGradingGroup("MT2", 25, 100);

        assertEquals(87.5, c.calculateGrade());
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

    @Test
    void testAddCourse() {
        Course c = new Course("MATH 100");
        s.addCourse(c);
        assertEquals(1, s.getListOfCourses().size());
    }
}