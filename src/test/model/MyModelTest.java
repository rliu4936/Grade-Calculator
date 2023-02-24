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
    void testAddCourse() {
        Course c = new Course("MATH 100");
        s.addCourse(c);
        assertEquals(1, s.getListOfCourses().size());
    }
}