package com.school.timetable.domain.populator;

import java.util.List;
import java.util.Map;

import com.school.timetable.domain.datastructure.SubjectUsageMap;
import com.school.timetable.domain.model.input.SubjectDetails;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.output.SubjectUsage.ClassPeriodCount;

/**
 * Populates the {@link SubjectUsageMap} to track subject usage across different classes.
 */
public class SubjectUsagePopulator {

	/**
	 * Populates the subject usage data structure based on the provided timetable input.
	 *
	 * @param input The {@link TimetableInput} containing subject and class details.
	 * @param usageMap The {@link SubjectUsageMap} tracking subject periods.
	 */
	public void populate(TimetableInput input, SubjectUsageMap usageMap) {
		for (Map.Entry<String, List<SubjectDetails>> entry : input.getSubjectDetails().entrySet()) {
			String subject = entry.getKey();
			for (SubjectDetails detail : entry.getValue()) {
				usageMap.addUsage(subject, new ClassPeriodCount(detail.getClassGrade(), detail.getSection(), 0));
			}
		}
	}
}
