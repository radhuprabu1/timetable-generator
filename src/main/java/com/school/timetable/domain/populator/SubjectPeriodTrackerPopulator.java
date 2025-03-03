package com.school.timetable.domain.populator;

import java.util.List;
import java.util.Map;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;
import com.school.timetable.domain.model.input.SubjectDetails;
import com.school.timetable.domain.model.input.TimetableInput;

/**
 * Populates the {@link SubjectPeriodTracker} with the minimum and total periods required per subject.
 */
public class SubjectPeriodTrackerPopulator {

	/**
	 * Populates the {@link SubjectPeriodTracker} with subject-wise period requirements.
	 *
	 * @param input   The {@link TimetableInput} containing subject details.
	 * @param tracker The {@link SubjectPeriodTracker} to store period requirements.
	 */
	public void populate(TimetableInput input, SubjectPeriodTracker tracker) {
		for (Map.Entry<String, List<SubjectDetails>> entry : input.getSubjectDetails().entrySet()) {
			String subject = entry.getKey();
			for (SubjectDetails detail : entry.getValue()) {
				String classKey = detail.getClassGrade();
				classKey = detail.getClassGrade() + detail.getSection(); // Combine classGrade and section
				tracker.addPeriodRequirement(
						subject,
						classKey,
						new PeriodRequirement(detail.getMinPeriodsPerDay(), detail.getPeriodsPerWeek())
						);//}
			}
		}
	}
}
