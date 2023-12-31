package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonProcessor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonProcessorTest {
    private static final String TEST_FILE_LOCATION = "./data/test.json";
    JsonProcessor jp;

    @BeforeEach
    public void setup() {
        jp = new JsonProcessor(TEST_FILE_LOCATION);
    }

    @Test
    public void saveAndReadTest() {
        Student s1 = new Student();
        Course c1 = new Course("MATH 101");
        c1.addGradingGroup("MT1", 50, 80);
        c1.addGradingGroup("MT2", 50, 85);

        Course c2 = new Course("MATH 102");
        c2.addGradingGroup("MT1", 50, 57);
        c2.addGradingGroup("MT2", 50, 87);

        s1.addCourse(c1);
        s1.addCourse(c2);

        try {
            jp.save(s1);
        } catch (IOException e) {
            fail("Can not write to file");
        }

        Student s2 = new Student();

        try {
            s2 = jp.read();
        } catch (IOException e) {
            fail("Can not read from file");
        }

        assertEquals(s1, s2);
    }
}
