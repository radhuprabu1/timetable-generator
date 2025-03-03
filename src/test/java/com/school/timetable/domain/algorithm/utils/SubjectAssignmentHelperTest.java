package com.school.timetable.domain.algorithm.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.input.Teacher;

/**
 * Tests the SubjectAssignmentHelper class.
 */
public class SubjectAssignmentHelperTest {

    private SubjectPeriodTracker periodTracker;
    private SubjectAvailabilityService helper;

    @BeforeEach
    public void setUp() {
        periodTracker = new SubjectPeriodTracker();
        helper = new SubjectAvailabilityService(periodTracker);

        // Mock data for testing
        periodTracker.addPeriodRequirement("Mathematics", "10A", new PeriodRequirement(1, 5));
        periodTracker.addPeriodRequirement("Physics", "11B", new PeriodRequirement(1, 6));
    }

    @Test
    public void testGetPossibleSubjectsForTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSubjects(List.of("Mathematics", "Physics"));
        teacher.setClasses(List.of(
            new ClassInfo("10", "A", "Mathematics"),
            new ClassInfo("11", "B", "Physics")
        ));
        //teacher.setClasses(List.of("10A", "11B"));
        List<String> possibleSubjects = helper.getPossibleSubjectsForTeacher(teacher);

        assertNotNull(possibleSubjects, "The result should not be null");
        assertEquals(2, possibleSubjects.size(), "The teacher should be able to teach 2 subjects");
        assertTrue(possibleSubjects.contains("Mathematics"), "The teacher should be able to teach Mathematics");
        assertTrue(possibleSubjects.contains("Physics"), "The teacher should be able to teach Physics");
    }
}
