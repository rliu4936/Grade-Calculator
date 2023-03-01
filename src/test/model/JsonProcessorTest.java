package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonProcessorTest {
    private static final String TEST_FILE_LOCATION = "./data/test.json";
    JsonProcessor jp;
    @BeforeEach
    public void setup() {
        jp = new JsonProcessor(TEST_FILE_LOCATION);
    }

    @Test
    public void saveAndReadTest() throws IOException {
        Student s1 = new Student();
        Course c1 = new Course("MATH 101");
        c1.addGradingGroup("MT1", 50, 80);
        c1.addGradingGroup("MT2", 50, 85);

        Course c2 = new Course("MATH 102");
        c2.addGradingGroup("MT1", 50, 57);
        c2.addGradingGroup("MT2", 50, 87);

        s1.addCourse(c1);
        s1.addCourse(c2);

        jp.save(s1);
        Student s2 = new Student();
        jp.read(s2);

        assertEquals(2, s2.getListOfCourses().size());
        assertEquals(2, s2.getListOfCourses().get(0).getGradingGroups().size());
        assertEquals("MATH 101", s2.getListOfCourses().get(0).getCourseName());
        assertEquals("MT1", s2.getListOfCourses().get(0).getGradingGroups().get(0).getGroupName());
        assertEquals("MATH 102", s2.getListOfCourses().get(1).getCourseName());
    }
}
