package com.school.timetable.domain.populator;

import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;

import java.util.List;
import java.util.Map;


/**
 * Populates the {@link TeacherAvailabilityMap} with available teaching slots for teachers.
 */
public class TeacherAvailabilityPopulator {

	/**
	 * Populates the {@link TeacherAvailabilityMap} with available teaching slots.
	 *
	 * @param input The {@link TimetableInput} containing teacher availability details.
	 * @param availabilityMap The {@link TeacherAvailabilityMap} tracking available time slots.
	 */
	public void populate(TimetableInput input, TeacherAvailabilityMap availabilityMap) {
		for (Teacher teacher : input.getTeachers()) {
			// If availOnly is defined, use it exclusively
			if (teacher.getAvailablePeriods().size() > 0) {
				for (Map.Entry<DayOfWeek, List<Integer>> entry : teacher.getAvailablePeriods().entrySet()) {
					DayOfWeek day = entry.getKey();
					for (Integer period : entry.getValue()) {
						availabilityMap.addAvailability(day, period, teacher);
					}
				}
				continue; // Skip further processing for this teacher
			}

			// If availOnly is not defined, populate based on forbiddenPeriods
			for (DayOfWeek day : DayOfWeek.values()) {
				for (int period = 1; period <= input.getSchoolConfiguration().getTotalPeriodsPerDay(); period++) {
					boolean isForbidden = teacher.getForbiddenPeriods() != null
							&& teacher.getForbiddenPeriods().getOrDefault(day, List.of()).contains(period);

					if (!isForbidden) {
						availabilityMap.addAvailability(day, period, teacher);
					}
				}
			}
		}
	}
}
