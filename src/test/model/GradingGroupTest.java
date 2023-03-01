package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradingGroupTest {
    @Test
    void testAddGradingGroup() {
        Course c = new Course("CPSC 110");
        c.addGradingGroup("Final", 30, 80);

        assertEquals(1, c.getGradingGroups().size());
    }
    @Test
    void testGetGroupName() {
        GradingGroup gg = new GradingGroup("MT1", 30, 90);
        assertEquals("MT1", gg.getGroupName());
    }
}
