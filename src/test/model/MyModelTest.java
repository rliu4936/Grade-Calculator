package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyModelTest {
    Student s;

    @BeforeEach
    void setup() {
        s = new Student();
    }

    @Test
    void testAddGradingGroup() {
        Course c = new Course("CPSC 110");
        c.addGradingGroup("Final", 30, 80);

        assertEquals(1, c.getGradingGroups().size());
    }

    @Test
    void testCalculateGrade() {
        Course c = new Course("MATH 100");

        c.addGradingGroup("Final", 30, 80);
        c.addGradingGroup("MT1", 25, 90);
        c.addGradingGroup("MT2", 25, 100);

        c.calculateGrade();

        assertEquals(89.375, c.getGrade());
        assertEquals(71.5, c.getLowestGrade());
        assertEquals(91.5, c.getHighestGrade());
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

    @Test
    void testGetCourseName() {
        Course c = new Course("MATH 101");
        assertEquals("MATH 101", c.getCourseName());
    }

    @Test
    void testGetGroupName() {
        GradingGroup gg = new GradingGroup("MT1", 30, 90);
        assertEquals("MT1", gg.getGroupName());
    }
}