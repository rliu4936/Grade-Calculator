package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testEquals() {
        GradingGroup gg = new GradingGroup("MT1", 30, 90);
        GradingGroup gg2 = new GradingGroup("MT1", 30, 90);
        assertEquals(gg, gg2);
    }

    @Test
    void testNotEquals() {
        GradingGroup gg = new GradingGroup("MT1", 30, 90);
        GradingGroup gg2 = new GradingGroup("MT1", 40, 80);
        assertNotEquals(gg, gg2);
    }
}
