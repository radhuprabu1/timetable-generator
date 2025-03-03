package com.school.timetable.domain.populator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.SchoolConfiguration;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.populator.ClassSchedulePopulator;

public class ClassSchedulePopulatorTest {

    @Test
    public void testPopulateWithClassTeacherOf() {
        ClassScheduleMap map = new ClassScheduleMap();
        ClassSchedulePopulator populator = new ClassSchedulePopulator();

        // Mock input
        Teacher teacher = new Teacher("Radhu",null, null, null, null, 2, 1, 5,
        		new ClassInfo("10", "A", "Mathematics"));
        SchoolConfiguration schoolConfig = new SchoolConfiguration(8);
        List<Teacher> teachers = List.of(teacher);    
        TimetableInput input = new TimetableInput(schoolConfig, teachers, null);
        populator.populate(input, map);
        assertNotNull(map.getScheduleMap(), "Class schedule map should not be null");
        assertEquals(1, map.getSchedule("10A", DayOfWeek.Monday).size(), "ClassTeacherOf should be assigned the first period");
    }
}
