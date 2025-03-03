package com.school.timetable.domain.datastructure;

import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.output.Timetable.ScheduleEntry;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassScheduleMapPopulatedTest {

    @Test
    public void testPopulatedClassSchedule() {
        ClassScheduleMap map = new ClassScheduleMap();
        ClassInfo classInfo = new ClassInfo("10", "A", "Mathematics");
        ScheduleEntry entry = new ScheduleEntry(1, classInfo);

        // Simulate populated data
        map.addSchedule("10A", DayOfWeek.Monday, entry);

        List<ScheduleEntry> schedule = 
        		map.getSchedule("10A", DayOfWeek.Monday);

        // Assertions
        assertNotNull(schedule, "Schedule should not be null");
        assertEquals(1, schedule.size(), "Expected one schedule entry");
        assertEquals("Mathematics", schedule.get(0).getClassInfo().getSubject(), "Subject should match");
    }
}
