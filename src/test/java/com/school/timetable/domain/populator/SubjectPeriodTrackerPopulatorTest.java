package com.school.timetable.domain.populator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;
import com.school.timetable.domain.model.input.SubjectDetails;
import com.school.timetable.domain.populator.SubjectPeriodTrackerPopulator;

public class SubjectPeriodTrackerPopulatorTest {

	@Test
	public void testPopulatePeriodTracker() {
		SubjectPeriodTracker tracker = new SubjectPeriodTracker();
		SubjectPeriodTrackerPopulator populator = new SubjectPeriodTrackerPopulator();

		// Mock input
		SubjectDetails details = new SubjectDetails("10", "A", "Mathematics", 1, 5);
		com.school.timetable.domain.model.input.TimetableInput input = new com.school.timetable.domain.model.input.TimetableInput();
		input.setSubjectDetails(Map.of("Mathematics", List.of(details)));

		// Act
		populator.populate(input, tracker);

		// Assert
		PeriodRequirement requirement = tracker.getPeriodRequirement("Mathematics", "10A");
		assertNotNull(requirement, "PeriodRequirement should not be null");
		assertEquals(1, requirement.getRemainingPeriodsPerDay(), "Remaining periods per day should match");
		assertEquals(5, requirement.getRemainingPeriodsPerWeek(), "Remaining periods per week should match");
	}
}
