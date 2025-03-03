package com.school.timetable.domain.model.output;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents {@link Timetables} list object in output json.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
public class Timetable {
	/**
	 * The name of the teacher.
	 */
	private String teacherName;

	/**
	 * A map of days to the list of scheduled periods for the teacher.
	 */
	private Map<DayOfWeek, List<ScheduleEntry>> schedule;
	private int totalPeriodsPerWeek;

	/**
	 * Represents a scheduled period entry in the timetable.
	 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ScheduleEntry {

		/** The period number within the day. */
		private int period;

		/** The class information associated with this schedule entry. */
		private ClassInfo classInfo;

		@Override
		public String toString() {
			return "ScheduleEntry(period=" + period +
					", classInfo=" + classInfo + ")";
		}
	}
}
