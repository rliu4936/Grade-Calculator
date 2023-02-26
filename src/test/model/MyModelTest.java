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

    @Test
    void testDeleteCourse() {
        Course c = new Course("CPSC 110");
        s.addCourse(c);
        assertEquals(1, s.getListOfCourses().size());

        s.deleteCourse(0);
        assertEquals(0, s.getListOfCourses().size());
    }

    @Test
    void testFindLetterGrade() {
        Course c1 = new Course("MATH 101");
        c1.addGradingGroup("Final", 100, 90);
        assertEquals("A+", c1.findLetterGrade());

        Course c2 = new Course("MATH 102");
        c2.addGradingGroup("Final", 100, 85);
        assertEquals("A", c2.findLetterGrade());

        Course c3 = new Course("MATH 103");
        c3.addGradingGroup("Final", 100, 80);
        assertEquals("A-", c3.findLetterGrade());

        Course c4 = new Course("MATH 104");
        c4.addGradingGroup("Final", 100, 76);
        assertEquals("B+", c4.findLetterGrade());

        Course c5 = new Course("MATH 105");
        c5.addGradingGroup("Final", 100, 72);
        assertEquals("B", c5.findLetterGrade());

        Course c6 = new Course("MATH 106");
        c6.addGradingGroup("Final", 100, 68);
        assertEquals("B-", c6.findLetterGrade());

        Course c7 = new Course("MATH 107");
        c7.addGradingGroup("Final", 100, 64);
        assertEquals("C+", c7.findLetterGrade());

        Course c8 = new Course("MATH 108");
        c8.addGradingGroup("Final", 100, 60);
        assertEquals("C", c8.findLetterGrade());

        Course c9 = new Course("MATH 109");
        c9.addGradingGroup("Final", 100, 55);
        assertEquals("C-", c9.findLetterGrade());

        Course c10 = new Course("MATH 110");
        c10.addGradingGroup("Final", 100, 50);
        assertEquals("D", c10.findLetterGrade());

        Course c11 = new Course("MATH 111");
        c11.addGradingGroup("Final", 100, 45);
        assertEquals("F", c11.findLetterGrade());
    }
}