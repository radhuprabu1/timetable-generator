package com.school.timetable.domain.populator;

import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.populator.TeacherAvailabilityPopulator;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherAvailabilityPopulatorTest {

    @Test
    public void testPopulateWithForbiddenPeriods() {
        TeacherAvailabilityMap map = new TeacherAvailabilityMap();
        TeacherAvailabilityPopulator teacherAvailabilityPopulator = new TeacherAvailabilityPopulator();

        // Mocking input
        Teacher teacher = new Teacher("Radhu", List.of("Mathematics"), null,
                Map.of(DayOfWeek.Monday, List.of(3, 5)), Map.of(), 2, 1, 5, null);

        TimetableInput input = new TimetableInput();
        input.setTeachers(List.of(teacher));
        input.setSchoolConfiguration(new com.school.timetable.domain.model.input.SchoolConfiguration(8));

        teacherAvailabilityPopulator.populate(input, map);

        assertNotNull(map.getAvailabilityMap(), "Availability map should not be null");
        assertFalse(map.getAvailableTeachers(DayOfWeek.Monday, 3).contains(teacher), "Teacher should not be available for forbidden period");
        assertTrue(map.getAvailableTeachers(DayOfWeek.Monday, 2).contains(teacher), "Teacher should be available for non-forbidden periods");
    }
}
