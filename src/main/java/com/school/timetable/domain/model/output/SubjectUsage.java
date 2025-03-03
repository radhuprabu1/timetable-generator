package com.school.timetable.domain.model.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for output json for the field - {@link SubjectsUsage}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
@Builder
public class SubjectUsage {
	/**
	 * The name of the subject.
	 */
	private String subjectName;

	/**
	 * List of classes where the subject is taught, including period count.
	 */
	private List<ClassPeriodCount> classesTaughtIn;

	/*
	 * Commented out method (Not in use)
    public SubjectUsage(String subjectName, List<ClassPeriodCount> classesTaughtIn) {
        this.subjectName = subjectName;
        this.classesTaughtIn = classesTaughtIn;
    }
	 */

	/**
	 * Represents the count of periods a subject is taught in a specific class.
	 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ClassPeriodCount {
		private String classGrade;
		private String section;

		/**
		 * The total number of periods allocated for this subject in the class.
		 */
		private int totalPeriods;

		@Override
		public String toString() {
			return "ClassPeriodCount(classGrade=" + classGrade +
					", section=" + section +
					", totalPeriods=" + totalPeriods + ")";
		}

	}

}
