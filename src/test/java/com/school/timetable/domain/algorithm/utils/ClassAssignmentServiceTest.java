package com.school.timetable.domain.algorithm.utils;

import org.junit.jupiter.api.BeforeEach;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;

/**
 * Tests the ClassAssignmentService class.
 * Test cases yet to be implemented.
 */
public class ClassAssignmentServiceTest {

    private SubjectPeriodTracker periodTracker;
    private ClassAssignmentService assignmentService = new ClassAssignmentService();

    @BeforeEach
    public void setUp() {
        periodTracker = new SubjectPeriodTracker();

        // Mock data
        periodTracker.addPeriodRequirement("Mathematics", "10A", new PeriodRequirement(1, 5));
        periodTracker.addPeriodRequirement("Physics", "11B", new PeriodRequirement(1, 6));
    }

}
