package com.school.timetable.domain.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;

public class SubjectPeriodTrackerPopulatedTest {

    @Test
    public void testPopulatedSubjectPeriodTracker() {
        SubjectPeriodTracker tracker = new SubjectPeriodTracker();
        PeriodRequirement requirement = new PeriodRequirement(2, 5);

        // Simulate populated data
        tracker.addPeriodRequirement("Mathematics", "10A", requirement);

        PeriodRequirement retrieved = tracker.getPeriodRequirement("Mathematics", "10A");

        // Assertions
        assertNotNull(retrieved, "Period requirement should not be null");
        assertEquals(2, retrieved.getRemainingPeriodsPerDay(), "Daily periods should match");
        assertEquals(5, retrieved.getRemainingPeriodsPerWeek(), "Weekly periods should match");
    }
}
