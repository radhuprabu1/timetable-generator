package com.school.timetable.domain.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.model.output.SubjectUsage.ClassPeriodCount;

public class SubjectUsageMapTest {

    @Test
    public void testAddAndRetrieveUsage() {
        SubjectUsageMap map = new SubjectUsageMap();
        ClassPeriodCount count = new ClassPeriodCount("10A", "A", 5);

        map.addUsage("Mathematics", count);

        List<ClassPeriodCount> usage = map.getUsage("Mathematics");

        // Assertions
        assertNotNull(usage, "Usage list should not be null");
        assertEquals(1, usage.size(), "Usage list size should be 1");
        assertEquals("10A", usage.get(0).getClassGrade(), "Class grade should match");
    }
}
